package com.universidad.proveedores_api.service;

import com.universidad.proveedores_api.dto.ProveedorRequest;
import com.universidad.proveedores_api.exception.ProveedorNotFoundException;
import com.universidad.proveedores_api.model.Proveedor;
import com.universidad.proveedores_api.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crear(ProveedorRequest request) {
        String estado = (request.estado() == null || request.estado().isBlank())
                ? "ACTIVO"
                : request.estado();

        Proveedor proveedor = new Proveedor(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.categoria(),
                estado
        );
        return repository.guardar(proveedor);
    }

    public List<Proveedor> listar(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return repository.buscarTodos();
        }
        return repository.buscarPorCategoria(categoria);
    }

    public Proveedor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
    }
}