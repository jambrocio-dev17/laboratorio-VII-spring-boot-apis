package com.universidad.empleados_api.repository;

import com.universidad.empleados_api.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpleadoRepository {

    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Empleado guardar(Empleado empleado) {
        empleado.setId(contadorId.getAndIncrement());
        empleados.add(empleado);
        return empleado;
    }

    public List<Empleado> buscarTodos() {
        return new ArrayList<>(empleados);
    }

    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(e -> e.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}