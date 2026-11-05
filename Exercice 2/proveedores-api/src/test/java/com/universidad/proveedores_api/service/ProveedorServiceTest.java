package com.universidad.proveedores_api.service;

import com.universidad.proveedores_api.dto.ProveedorRequest;
import com.universidad.proveedores_api.exception.ProveedorNotFoundException;
import com.universidad.proveedores_api.model.Proveedor;
import com.universidad.proveedores_api.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorServiceTest {

    private ProveedorService service;

    @BeforeEach
    void setUp() {
        service = new ProveedorService(new ProveedorRepository());
    }

    @Test
    void crearProveedor_debeAsignarIdYGuardarDatos() {
        ProveedorRequest request = new ProveedorRequest(
                "Carlos", "Gómez", "carlos@correo.com", "Tecnología", "ACTIVO");

        Proveedor creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
        assertEquals("Tecnología", creado.getCategoria());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerProveedorExistente_debeRetornarElProveedor() {
        Proveedor creado = service.crear(new ProveedorRequest(
                "María", "Ruiz", "maria@correo.com", "Papelería", "ACTIVO"));

        Proveedor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("María", encontrado.getNombre());
    }

    @Test
    void obtenerProveedorInexistente_debeLanzarExcepcion() {
        assertThrows(ProveedorNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}