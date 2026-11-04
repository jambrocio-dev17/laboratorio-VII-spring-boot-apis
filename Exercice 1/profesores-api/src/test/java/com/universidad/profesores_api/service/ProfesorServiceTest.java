package com.universidad.profesores_api.service;

import com.universidad.profesores_api.dto.ProfesorRequest;
import com.universidad.profesores_api.exception.ProfesorNotFoundException;
import com.universidad.profesores_api.model.Profesor;
import com.universidad.profesores_api.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorServiceTest {

    private ProfesorService service;

    @BeforeEach
    void setUp() {
        service = new ProfesorService(new ProfesorRepository());
    }

    @Test
    void crearProfesor_debeAsignarIdYGuardarDatos() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "López", "ana@correo.com", "Matemática", "ACTIVO");

        Profesor creado = service.crear(request);

        assertNotNull(creado.getId());
        assertEquals("Ana", creado.getNombre());
        assertEquals("Matemática", creado.getEspecialidad());
        assertEquals("ACTIVO", creado.getEstado());
    }

    @Test
    void obtenerProfesorExistente_debeRetornarElProfesor() {
        Profesor creado = service.crear(new ProfesorRequest(
                "Luis", "Pérez", "luis@correo.com", "Física", "ACTIVO"));

        Profesor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Luis", encontrado.getNombre());
    }

    @Test
    void obtenerProfesorInexistente_debeLanzarExcepcion() {
        assertThrows(ProfesorNotFoundException.class, () -> service.obtenerPorId(999L));
    }
}