package com.universidad.profesores_api.service;

import com.universidad.profesores_api.dto.ProfesorRequest;
import com.universidad.profesores_api.exception.ProfesorNotFoundException;
import com.universidad.profesores_api.model.Profesor;
import com.universidad.profesores_api.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(ProfesorRequest request) {
        String estado = (request.estado() == null || request.estado().isBlank())
                ? "ACTIVO"
                : request.estado();

        Profesor profesor = new Profesor(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.especialidad(),
                estado
        );
        return repository.guardar(profesor);
    }

    public List<Profesor> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.buscarTodos();
        }
        return repository.buscarPorEspecialidad(especialidad);
    }

    public Profesor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }
}