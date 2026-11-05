package cl.duoc.auth.service;

import cl.duoc.auth.dto.*;
import cl.duoc.auth.model.Usuario;
import cl.duoc.auth.repository.UsuarioRepository;
import cl.duoc.auth.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UsuarioRepository usuarios;
  private final PasswordEncoder encoder;
  private final JwtService jwt;

  public AuthService(UsuarioRepository usuarios,
      PasswordEncoder encoder, JwtService jwt) {
    this.usuarios = usuarios;
    this.encoder = encoder;
    this.jwt = jwt;
  }

  public LoginResponse login(LoginRequest request) {
    Usuario usuario = usuarios.findByCorreoIgnoreCase(request.correo())
      .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
    if (!encoder.matches(request.password(), usuario.getPassword())) {
      throw new BadCredentialsException("Credenciales inválidas");
    }
    return new LoginResponse(jwt.generar(usuario),
      usuario.getCorreo(), usuario.getRol());
  }
}
