package cl.duoc.auth.dto;

public record LoginResponse(
    String token, 
    String correo, 
    String rol) 
{}

