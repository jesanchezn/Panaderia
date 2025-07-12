package com.parquesoftti.panaderia.exception;

public class VentaNotFoundException extends RuntimeException {
    public VentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
