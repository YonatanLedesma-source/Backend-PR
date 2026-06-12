package com.proyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.Lectura;
import com.proyecto.model.Medidor;
import com.proyecto.model.Operador;
import com.proyecto.model.Administrador;
import com.proyecto.model.Factura;
import com.proyecto.model.Financiacion;
import com.proyecto.model.Cliente;
import com.proyecto.repository.LecturaRepository;
import com.proyecto.repository.MedidorRepository;
import com.proyecto.repository.OperadorRepository;
import com.proyecto.repository.AdministradorRepository;
import com.proyecto.repository.FacturaRepository;
import com.proyecto.repository.FinanciacionRepository;
import com.proyecto.repository.ClienteRepository;
import org.springframework.data.domain.PageRequest;

@Service
public class LecturaService {
    @Autowired
    private LecturaRepository lecturaRepository;

    @Autowired
    private MedidorRepository medidorRepository;

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FinanciacionRepository financiacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    /* Para obtener todos las lecturas */
    public List<Lectura> listarLecturas(){
        return lecturaRepository.findAll();
    }

    /* Para obtener un lectura por zona */
    public Optional<Lectura> obtenerPorZona(String zona){
        return lecturaRepository.findByZona(zona);
    }

    /* Para obtener una lectura por Id */
    public Optional<Lectura> obtenerPorId(Long id){
        return lecturaRepository.findById(id);
    }

    /* Para obtener una lectura por valor actual */
    public Optional<Lectura> obtenerPorValorActual(BigDecimal valorActual){
        return lecturaRepository.findByValorActual(valorActual);
    }

    /* Para obtener una lectura por consumo m3 */
    public Optional<Lectura> obtenerPorConsumoM3(BigDecimal consumoM3){
        return lecturaRepository.findByConsumoM3(consumoM3);
    }

    /* Para obtener una lectura por fecha de lectura */
    public Optional<Lectura> obtenerPorFechaLectura(LocalDate fechaLectura){
        return lecturaRepository.findByFechaLectura(fechaLectura);
    }

    /* Para crear una nueva Lectura */
    public Lectura crearLectura(Lectura lectura){
        // 1. Resolver el Medidor
        Long idMed = lectura.getIdMedidor();
        if (idMed == null && lectura.getMedidor() != null) {
            idMed = lectura.getMedidor().getId_med();
        }
        if (idMed == null) {
            throw new IllegalArgumentException("El ID del medidor (idMedidor) es obligatorio.");
        }
        final Long finalIdMed = idMed;
        Medidor medidor = medidorRepository.findById(finalIdMed)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el medidor con ID: " + finalIdMed));
        lectura.setMedidor(medidor);

        // 2. Resolver Cliente y Presidente directamente desde el Medidor
        lectura.setCliente(medidor.getCliente());
        lectura.setPresidente(medidor.getPresidente());

        // 3. Resolver Operador
        Long idOper = lectura.getIdOperador();
        if (idOper != null && idOper > 0) {
            operadorRepository.findById(idOper).ifPresent(lectura::setOperador);
        }
        if (lectura.getOperador() == null) {
            lectura.setOperador(medidor.getOperador());
        }

        // 4. Resolver Administrador (usar el primero registrado en la base de datos)
        if (lectura.getAdministrador() == null) {
            List<Administrador> admins = administradorRepository.findAll();
            if (!admins.isEmpty()) {
                lectura.setAdministrador(admins.get(0));
            } else {
                throw new IllegalStateException("No hay ningún administrador registrado en la base de datos para asociar a la lectura.");
            }
        }

        // 5. Asignar valorActual desde el campo transitorio valorLectura si corresponde
        if (lectura.getValorActual() == null && lectura.getValorLectura() != null) {
            lectura.setValorActual(lectura.getValorLectura());
        }
        if (lectura.getValorActual() == null) {
            throw new IllegalArgumentException("El valor de la lectura (valorLectura o valorActual) es obligatorio.");
        }

        // 6. Consultar lectura más reciente para obtener el valorAnterior
        List<Lectura> latestLecturas = lecturaRepository.findLatestByMedidorId(idMed, PageRequest.of(0, 1));
        BigDecimal valorAnterior = BigDecimal.ZERO;
        if (!latestLecturas.isEmpty()) {
            valorAnterior = latestLecturas.get(0).getValorActual();
        }
        lectura.setValorAnterior(valorAnterior);

        // 7. Calcular consumo en M3
        lectura.setConsumoM3(lectura.getValorActual().subtract(valorAnterior));

        // 8. Establecer fecha de lectura si no está definida
        if (lectura.getFechaLectura() == null) {
            if (lectura.getFechaToma() != null) {
                lectura.setFechaLectura(lectura.getFechaToma());
            } else {
                lectura.setFechaLectura(LocalDate.now());
            }
        }

        // 9. Establecer zona si no está definida
        if (lectura.getZona() == null) {
            if (lectura.getObservaciones() != null && !lectura.getObservaciones().trim().isEmpty()) {
                lectura.setZona(lectura.getObservaciones());
            } else {
                lectura.setZona("Rural");
            }
        }

        Lectura savedLectura = lecturaRepository.save(lectura);

        // Generar Factura automáticamente
        Factura factura = new Factura();
        LocalDate fechaLec = savedLectura.getFechaLectura();
        String period = String.format("%04d-%02d", fechaLec.getYear(), fechaLec.getMonthValue());
        String periodClean = period.replace("-", "");
        int randomNum = (int) (1000 + Math.random() * 9000);
        String numeroFactura = "FAC-" + periodClean + "-" + randomNum;

        factura.setNumero(numeroFactura);
        factura.setPeriodo(period);
        factura.setFechaEmision(LocalDate.now());
        factura.setFechaVencimiento(LocalDate.now().plusDays(15));
        factura.setEstado(0); // 0 = Pendiente
        factura.setZona(savedLectura.getZona());
        factura.setLecturaAnterior(savedLectura.getValorAnterior());
        factura.setLecturaNueva(savedLectura.getValorActual());
        factura.setConsumo(savedLectura.getConsumoM3());

        factura.setCliente(savedLectura.getCliente());
        factura.setOperador(savedLectura.getOperador());
        factura.setAdministrador(savedLectura.getAdministrador());
        factura.setLectura(savedLectura);

        // Resolver financiación activa
        BigDecimal valorCuota = BigDecimal.ZERO;
        if (savedLectura.getCliente() != null) {
            List<Financiacion> activeFinans = financiacionRepository.findActiveByClienteId(savedLectura.getCliente().getId_cli());
            if (activeFinans != null && !activeFinans.isEmpty()) {
                Financiacion activeFinan = activeFinans.get(0);
                float cuota = activeFinan.getCuotaMensual() != null ? activeFinan.getCuotaMensual() : 0.0f;
                if (activeFinan.getSaldoPendiente() != null) {
                    if (activeFinan.getSaldoPendiente() < cuota) {
                        cuota = activeFinan.getSaldoPendiente();
                    }
                    activeFinan.setSaldoPendiente(activeFinan.getSaldoPendiente() - cuota);
                }
                if (activeFinan.getNumeroCuotas() != null && activeFinan.getNumeroCuotas() > 0) {
                    activeFinan.setNumeroCuotas(activeFinan.getNumeroCuotas() - 1);
                }
                financiacionRepository.save(activeFinan);

                valorCuota = BigDecimal.valueOf(cuota);
                factura.setFinanciacion(activeFinan);
            }
        }

        factura.setValorCuota(valorCuota);
        // Tarifa base: 1500 por m3
        BigDecimal costoConsumo = savedLectura.getConsumoM3() != null 
            ? savedLectura.getConsumoM3().multiply(new BigDecimal("1500"))
            : BigDecimal.ZERO;
        factura.setTotalPagar(costoConsumo.add(valorCuota));

        facturaRepository.save(factura);

        // Actualizar lectura acumulada del Cliente
        Cliente cliente = savedLectura.getCliente();
        if (cliente != null && savedLectura.getValorActual() != null) {
            cliente.setLectura(savedLectura.getValorActual().floatValue());
            clienteRepository.save(cliente);
        }

        return savedLectura;
    }


    /* Para guardar una Lectura */
    public Lectura guardarLectura(Lectura lectura){
        return lecturaRepository.save(lectura);
    }

    /* Para eliminar una lectura */
    public void eliminarLectura(Long id){
        lecturaRepository.deleteById(id);
    }

    /* Para actualizar una lectura existente */
    public Lectura actualizarLectura(Lectura lectura){
        return lecturaRepository.save(lectura);
    }

}