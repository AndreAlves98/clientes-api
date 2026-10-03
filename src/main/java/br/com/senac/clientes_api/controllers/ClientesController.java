package br.com.senac.clientes_api.controllers;


import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar() {
        return ResponseEntity.ok(clientesService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(
            @RequestBody ClientesRequestDto cliente) {

        return ResponseEntity.status(201).body(clientesService.criar(cliente));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(@PathVariable Long id, @RequestBody ClientesRequestDto clientes) {
        return ResponseEntity.ok(clientesService.atualizar(id, clientes));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clientesService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}