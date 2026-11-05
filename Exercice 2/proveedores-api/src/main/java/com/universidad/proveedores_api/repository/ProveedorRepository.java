package com.universidad.proveedores_api.repository;

import com.universidad.proveedores_api.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProveedorRepository {

    private final List<Proveedor> proveedores = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(contadorId.getAndIncrement());
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> buscarTodos() {
        return new ArrayList<>(proveedores);
    }

    public List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}