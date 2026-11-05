package com.universidad.empleados_api.service;

import com.universidad.empleados_api.dto.EmpleadoRequest;
import com.universidad.empleados_api.exception.EmpleadoNotFoundException;
import com.universidad.empleados_api.model.Empleado;
import com.universidad.empleados_api.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {

    private EmpleadoService service;

    @BeforeEach
    void setUp() {
        service = new EmpleadoService(new EmpleadoRepository());
    }

    @Test
    void crearEmpleado_debeAsignarIdYGuardarDatos() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Sofía", "Morales", "sofia@correo.com", "Contabilidad", "ACTIVO");

        Empleado creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Sofía", creado.getNombre());
        assertEquals("Contabilidad", creado.getEspecialidad());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerEmpleadoExistente_debeRetornarElEmpleado() {
        Empleado creado = service.crear(new EmpleadoRequest(
                "Pedro", "Castillo", "pedro@correo.com", "Ventas", "ACTIVO"));

        Empleado encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Pedro", encontrado.getNombre());
    }

    @Test
    void obtenerEmpleadoInexistente_debeLanzarExcepcion() {
        assertThrows(EmpleadoNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}