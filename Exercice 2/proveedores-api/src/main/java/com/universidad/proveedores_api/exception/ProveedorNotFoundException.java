package com.universidad.proveedores_api.exception;

public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(Long id) {
        super("Proveedor con id " + id + " no encontrado");
    }
}