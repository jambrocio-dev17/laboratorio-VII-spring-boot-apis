package com.universidad.empleados_api.service;

import com.universidad.empleados_api.dto.EmpleadoRequest;
import com.universidad.empleados_api.exception.EmpleadoNotFoundException;
import com.universidad.empleados_api.model.Empleado;
import com.universidad.empleados_api.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado crear(EmpleadoRequest request) {
        String estado = (request.estado() == null || request.estado().isBlank())
                ? "ACTIVO"
                : request.estado();

        Empleado empleado = new Empleado(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.especialidad(),
                estado
        );
        return repository.guardar(empleado);
    }

    public List<Empleado> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.buscarTodos();
        }
        return repository.buscarPorEspecialidad(especialidad);
    }

    public Empleado obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }
}