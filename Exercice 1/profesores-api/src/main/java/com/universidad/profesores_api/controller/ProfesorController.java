package com.universidad.profesores_api.controller;

import com.universidad.profesores_api.dto.ProfesorRequest;
import com.universidad.profesores_api.model.Profesor;
import com.universidad.profesores_api.service.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Profesor> crear(@Valid @RequestBody ProfesorRequest request) {
        Profesor creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> listar(
            @RequestParam(required = false) String especialidad) {
        return ResponseEntity.ok(service.listar(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}