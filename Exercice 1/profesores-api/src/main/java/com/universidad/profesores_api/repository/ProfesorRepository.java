package com.universidad.profesores_api.repository;

import com.universidad.profesores_api.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Profesor guardar(Profesor profesor) {
        profesor.setId(contadorId.getAndIncrement());
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> buscarTodos() {
        return new ArrayList<>(profesores);
    }

    public List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(p -> p.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}