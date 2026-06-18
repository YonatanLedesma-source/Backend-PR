package com.proyecto.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.proyecto.model.Cliente;
import com.proyecto.model.Medidor;
import com.proyecto.model.Operador;
import com.proyecto.model.Administrador;
import com.proyecto.repository.ClienteRepository;
import com.proyecto.repository.MedidorRepository;
import com.proyecto.repository.OperadorRepository;
import com.proyecto.repository.AdministradorRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.proyecto")
@EnableJpaRepositories(basePackages = "com.proyecto.repository")
@EntityScan(basePackages = "com.proyecto.model")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(
			ClienteRepository clienteRepository,
			MedidorRepository medidorRepository,
			OperadorRepository operadorRepository,
			AdministradorRepository administradorRepository) {
		return args -> {
			List<Cliente> clientes = clienteRepository.findAll();
			List<Operador> operadores = operadorRepository.findAll();
			List<Administrador> admins = administradorRepository.findAll();

			if (operadores.isEmpty() || admins.isEmpty()) {
				return;
			}

			Operador defaultOperador = operadores.get(0);
			Administrador defaultAdmin = admins.get(0);

			for (Cliente cliente : clientes) {
				if (cliente.getNumeroMedidor() != null) {
					boolean exists = medidorRepository.findAll().stream()
							.anyMatch(m -> m.getCliente() != null && m.getCliente().getId_cli().equals(cliente.getId_cli()));
					
					if (!exists) {
						Medidor medidor = new Medidor();
						medidor.setNumeroMedidor(cliente.getNumeroMedidor());
						medidor.setCliente(cliente);
						medidor.setOperador(defaultOperador);
						medidor.setAdministrador(defaultAdmin);
						medidor.setFechaInstalacion(LocalDate.now());
						medidor.setEstado("1");
						medidorRepository.save(medidor);
						System.out.println("Auto-created Medidor for Client: " + cliente.getNombre() + " (Serial: " + cliente.getNumeroMedidor() + ")");
					}
				}
			}
		};
	}
}
