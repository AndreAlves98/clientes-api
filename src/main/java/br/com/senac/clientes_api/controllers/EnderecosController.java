package br.com.senac.clientes_api.controllers;

import br.com.senac.clientes_api.dtos.EnderecosRequestDto;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.services.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosService enderecosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Enderecos>> listarTodos() {
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


}
