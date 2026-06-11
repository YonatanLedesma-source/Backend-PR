package com.proyecto.controller;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.model.Cliente;
import com.proyecto.model.Administrador;
import com.proyecto.model.Operador;
import com.proyecto.model.Presidente;
import com.proyecto.model.TipoRol;
import com.proyecto.service.ClienteService;
import com.proyecto.service.AdministradorService;
import com.proyecto.service.OperadorService;
import com.proyecto.service.PresidenteService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private OperadorService operadorService;

    @Autowired
    private PresidenteService presidenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Clase DTO para Login
    public static class LoginRequest {
        private String email;
        private String documento;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getDocumento() { return documento; }
        public void setDocumento(String documento) { this.documento = documento; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // Clase DTO para Registro
    public static class RegisterRequest {
        private String nombreCompleto;
        private String email;
        private String documento;
        private String genero;
        private String password;
        private String rol;

        public String getNombreCompleto() { return nombreCompleto; }
        public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getDocumento() { return documento; }
        public void setDocumento(String documento) { this.documento = documento; }
        public String getGenero() { return genero; }
        public void setGenero(String genero) { this.genero = genero; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getRol() { return rol; }
        public void setRol(String rol) { this.rol = rol; }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest credentials) {
        String email = credentials.getEmail() != null ? credentials.getEmail().trim() : null;
        String docStr = credentials.getDocumento() != null ? credentials.getDocumento().trim() : null;
        String rawPassword = credentials.getPassword() != null ? credentials.getPassword().trim() : null;

        if (rawPassword == null || rawPassword.isEmpty()) {
            Map<String, String> err = new HashMap<>();
            err.put("message", "La contraseña es requerida");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        // Buscar en Clientes
        Optional<Cliente> optCliente = Optional.empty();
        if (email != null && !email.isEmpty()) {
            optCliente = clienteService.obtenerPorCorreo(email);
        } else if (docStr != null && !docStr.isEmpty()) {
            try {
                optCliente = clienteService.obtenerPorCedula(Integer.parseInt(docStr));
            } catch (NumberFormatException e) {}
        }

        if (optCliente.isPresent()) {
            Cliente c = optCliente.get();
            // BCrypt compara la contraseña en texto plano con el hash almacenado
            if (passwordEncoder.matches(rawPassword, c.getPassword())) {
                String token = generateFakeJwt(c.getEmail() != null ? c.getEmail() : String.valueOf(c.getCedula()), c.getRol().name());
                Map<String, Object> resp = new HashMap<>();
                resp.put("token", token);
                resp.put("rol", c.getRol().name());
                return ResponseEntity.ok(resp);
            }
        }

        // Buscar en Administradores
        Optional<Administrador> optAdmin = Optional.empty();
        if (email != null && !email.isEmpty()) {
            optAdmin = administradorService.obtenerPorCorreo(email);
        } else if (docStr != null && !docStr.isEmpty()) {
            try {
                optAdmin = administradorService.obtenerPorCedula(Integer.parseInt(docStr));
            } catch (NumberFormatException e) {}
        }

        if (optAdmin.isPresent()) {
            Administrador a = optAdmin.get();
            // BCrypt compara la contraseña en texto plano con el hash almacenado
            if (passwordEncoder.matches(rawPassword, a.getPassword())) {
                String token = generateFakeJwt(a.getEmail() != null ? a.getEmail() : String.valueOf(a.getCedula()), a.getRol().name());
                Map<String, Object> resp = new HashMap<>();
                resp.put("token", token);
                resp.put("rol", a.getRol().name());
                return ResponseEntity.ok(resp);
            }
        }

        // Buscar en Operadores
        Optional<Operador> optOper = Optional.empty();
        if (email != null && !email.isEmpty()) {
            optOper = operadorService.obtenerPorCorreo(email);
        } else if (docStr != null && !docStr.isEmpty()) {
            try {
                optOper = operadorService.obtenerPorCedula(Integer.parseInt(docStr));
            } catch (NumberFormatException e) {}
        }

        if (optOper.isPresent()) {
            Operador o = optOper.get();
            // BCrypt compara la contraseña en texto plano con el hash almacenado
            if (passwordEncoder.matches(rawPassword, o.getPassword())) {
                String token = generateFakeJwt(o.getEmail() != null ? o.getEmail() : String.valueOf(o.getCedula()), o.getRol().name());
                Map<String, Object> resp = new HashMap<>();
                resp.put("token", token);
                resp.put("rol", o.getRol().name());
                return ResponseEntity.ok(resp);
            }
        }

        // Buscar en Presidentes
        Optional<Presidente> optPres = Optional.empty();
        if (email != null && !email.isEmpty()) {
            optPres = presidenteService.obtenerPorCorreo(email);
        } else if (docStr != null && !docStr.isEmpty()) {
            try {
                optPres = presidenteService.obtenerPorCedula(Integer.parseInt(docStr));
            } catch (NumberFormatException e) {}
        }

        if (optPres.isPresent()) {
            Presidente p = optPres.get();
            // BCrypt compara la contraseña en texto plano con el hash almacenado
            if (passwordEncoder.matches(rawPassword, p.getPassword())) {
                String token = generateFakeJwt(p.getEmail() != null ? p.getEmail() : String.valueOf(p.getCedula()), p.getRol().name());
                Map<String, Object> resp = new HashMap<>();
                resp.put("token", token);
                resp.put("rol", p.getRol().name());
                return ResponseEntity.ok(resp);
            }
        }

        Map<String, String> err = new HashMap<>();
        err.put("message", "Credenciales incorrectas o usuario no encontrado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        String name = request.getNombreCompleto() != null ? request.getNombreCompleto().trim() : null;
        String email = request.getEmail() != null ? request.getEmail().trim() : null;
        String docStr = request.getDocumento() != null ? request.getDocumento().trim() : null;
        String password = request.getPassword() != null ? request.getPassword().trim() : null;

        Map<String, String> err = new HashMap<>();

        if (name == null || name.isEmpty()) {
            err.put("message", "El nombre completo es requerido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        if (password == null || password.isEmpty()) {
            err.put("message", "La contraseña es requerida");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        Integer cedula = null;
        if (docStr != null && !docStr.isEmpty()) {
            try {
                cedula = Integer.parseInt(docStr);
            } catch (NumberFormatException e) {
                err.put("message", "El documento debe ser un número entero válido");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
            }
        } else {
            err.put("message", "El documento de identificación es requerido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        // Validar duplicados por cédula
        if (clienteService.obtenerPorCedula(cedula).isPresent()) {
            err.put("message", "El número de documento ya se encuentra registrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }

        // Validar duplicados por correo (si se provee)
        if (email != null && !email.isEmpty()) {
            if (clienteService.obtenerPorCorreo(email).isPresent()) {
                err.put("message", "El correo electrónico ya se encuentra registrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
            }
        }

        Cliente c = new Cliente();
        c.setNombre(name);
        c.setEmail(email);
        // Cifrar la contraseña con BCrypt antes de guardar
        c.setPassword(passwordEncoder.encode(password));
        c.setCedula(cedula);
        c.setRol(TipoRol.CLIENTE);
        c.setEstado(1); // Activo

        Cliente saved = clienteService.registrarClienteNuevo(c);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> body) {
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Se ha enviado un enlace de recuperación si el correo existe");
        return ResponseEntity.ok(resp);
    }

    /**
     * Permite al administrador resetear la contraseña de cualquier usuario.
     * Body JSON: { "documento": "123456", "nuevaPassword": "abc123" }
     *   o bien: { "email": "usuario@mail.com", "nuevaPassword": "abc123" }
     */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
        String nuevaPassword = body.get("nuevaPassword");
        String documento     = body.get("documento");
        String email         = body.get("email");
        Map<String, String> err = new HashMap<>();

        if (nuevaPassword == null || nuevaPassword.trim().isEmpty()) {
            err.put("message", "El campo 'nuevaPassword' es requerido");
            return ResponseEntity.badRequest().body(err);
        }
        if ((documento == null || documento.trim().isEmpty()) &&
            (email     == null || email.trim().isEmpty())) {
            err.put("message", "Debes indicar 'documento' o 'email' del usuario");
            return ResponseEntity.badRequest().body(err);
        }

        String hashNuevo = passwordEncoder.encode(nuevaPassword.trim());

        // Buscar y actualizar en Clientes
        Optional<Cliente> optC = Optional.empty();
        if (email != null && !email.trim().isEmpty())
            optC = clienteService.obtenerPorCorreo(email.trim());
        else {
            try { optC = clienteService.obtenerPorCedula(Integer.parseInt(documento.trim())); }
            catch (NumberFormatException ignored) {}
        }
        if (optC.isPresent()) {
            Cliente c = optC.get();
            c.setPassword(hashNuevo);
            clienteService.guardarCliente(c);
            Map<String, String> ok = new HashMap<>();
            ok.put("message", "Contraseña actualizada para el cliente: " + c.getNombre());
            return ResponseEntity.ok(ok);
        }

        // Buscar y actualizar en Administradores
        Optional<Administrador> optA = Optional.empty();
        if (email != null && !email.trim().isEmpty())
            optA = administradorService.obtenerPorCorreo(email.trim());
        else {
            try { optA = administradorService.obtenerPorCedula(Integer.parseInt(documento.trim())); }
            catch (NumberFormatException ignored) {}
        }
        if (optA.isPresent()) {
            Administrador a = optA.get();
            a.setPassword(hashNuevo);
            administradorService.guardarAdministrador(a);
            Map<String, String> ok = new HashMap<>();
            ok.put("message", "Contraseña actualizada para el administrador: " + a.getNombre());
            return ResponseEntity.ok(ok);
        }

        // Buscar y actualizar en Operadores
        Optional<Operador> optO = Optional.empty();
        if (email != null && !email.trim().isEmpty())
            optO = operadorService.obtenerPorCorreo(email.trim());
        else {
            try { optO = operadorService.obtenerPorCedula(Integer.parseInt(documento.trim())); }
            catch (NumberFormatException ignored) {}
        }
        if (optO.isPresent()) {
            Operador o = optO.get();
            o.setPassword(hashNuevo);
            operadorService.guardarOperador(o);
            Map<String, String> ok = new HashMap<>();
            ok.put("message", "Contraseña actualizada para el operador: " + o.getNombre());
            return ResponseEntity.ok(ok);
        }

        err.put("message", "No se encontró ningún usuario con ese documento o email");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    private String generateFakeJwt(String sub, String role) {
        String header = "{\"alg\":\"none\",\"typ\":\"JWT\"}";
        String payload = String.format("{\"sub\":\"%s\",\"rol\":\"%s\"}", sub, role);
        
        String headerBase64 = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        String payloadBase64 = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        
        return headerBase64 + "." + payloadBase64 + ".";
    }
}