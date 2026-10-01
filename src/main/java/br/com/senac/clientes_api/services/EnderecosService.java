package br.com.senac.clientes_api.services;

import br.com.senac.clientes_api.dtos.EnderecosRequestDto;
import br.com.senac.clientes_api.entidades.Clientes;
import br.com.senac.clientes_api.entidades.Enderecos;
import br.com.senac.clientes_api.repositorios.ClientesRepositorio;
import br.com.senac.clientes_api.repositorios.EnderecosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecosService {

    @Autowired
    private EnderecosRepositorio enderecosRepositorio;

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Enderecos> listar() {
        return enderecosRepositorio.findAll();
    }

    public Enderecos criar(EnderecosRequestDto dto) {
        Clientes cliente = clientesRepositorio.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Enderecos enderecosSaida = new Enderecos();
        enderecosSaida.setCliente(cliente);
        enderecosSaida.setCep(dto.getCep());
        enderecosSaida.setLogradouro(dto.getLogradouro());
        enderecosSaida.setBairro(dto.getBairro());
        enderecosSaida.setCidade(dto.getCidade());
        enderecosSaida.setUf(dto.getUf());
        enderecosSaida.setComplemento(dto.getComplemento());
        
        return enderecosRepositorio.save(enderecosSaida);
    }
}
