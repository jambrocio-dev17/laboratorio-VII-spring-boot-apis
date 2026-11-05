package com.universidad.empleados_api.exception;

public class EmpleadoNotFoundException extends RuntimeException {

    public EmpleadoNotFoundException(Long id) {
        super("Empleado con id " + id + " no encontrado");
    }
}