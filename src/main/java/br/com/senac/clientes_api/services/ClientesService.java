package br.com.senac.clientes_api.services;


import br.com.senac.clientes_api.dtos.ClientesRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;


    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }

    public Clientes buscarPorId(Long id) {
        return clientesRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Clientes criar(ClientesRequestDto cliente) {
        Clientes clienteSaida = new Clientes();
        clienteSaida.setNome(cliente.getNome());
        clienteSaida.setDocumento(cliente.getDocumento());
        clienteSaida.setEmail(cliente.getEmail());
        clienteSaida.setDataNascimento(cliente.getDataNascimento());

        return clientesRepositorio.save(clienteSaida);
    }


    //ATUALIZAR (Update crUd)
    public Clientes atualizar(Long id, ClientesRequestDto cliente) {
        Clientes existente = this.buscarPorId(id);
        this.copiarDadosParaEntidade(cliente, existente);
        return clientesRepositorio.save(existente);
    }

    //DELETAR (cruD)
    public void deletar(Long id) {
        Clientes existente = this.buscarPorId(id);
        clientesRepositorio.delete(existente);
    }


    private void copiarDadosParaEntidade(ClientesRequestDto entrada, Clientes saida) {
        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setEmail(entrada.getEmail());
        saida.setDataNascimento(entrada.getDataNascimento());
    }

}