package br.com.senac.clientes_api.controllers;

import br.com.senac.clientes_api.dtos.EnderecosRequestDto;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.services.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosService enderecosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Enderecos>> listar() {
        return ResponseEntity.ok(enderecosService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody EnderecosRequestDto enderecos) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(enderecosService.criar(enderecos));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Enderecos> atualizar(
            @PathVariable Long id,
            @RequestBody EnderecosRequestDto enderecos
    ) {
        try {
            return ResponseEntity.ok(enderecosService.atualizar(id, enderecos));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            enderecosService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

}