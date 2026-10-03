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

    // ATUALIZAR
    public Enderecos atualizar(Long id, EnderecosRequestDto dto) {
        Enderecos existente = this.buscarPorId(id);
        this.copiarDadosParaEntidade(dto, existente);
        return enderecosRepositorio.save(existente);
    }

    // METODO PARA BUSCAR POR ID
    public Enderecos buscarPorId(Long id) {
        return enderecosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    //DELETAR
    public void deletar(Long id) {
        if (!enderecosRepositorio.existsById(id)) {
            throw new RuntimeException("Endereço não encontrado");
        }
        enderecosRepositorio.deleteById(id);
    }

    private void copiarDadosParaEntidade(EnderecosRequestDto entrada, Enderecos saida) {
        Clientes cliente = clientesRepositorio.findById(entrada.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        saida.setCep(entrada.getCep());
        saida.setLogradouro(entrada.getLogradouro());
        saida.setBairro(entrada.getBairro());
        saida.setCidade(entrada.getCidade());
        saida.setUf(entrada.getUf());
        saida.setComplemento(entrada.getComplemento());
        saida.setCliente(cliente);
    }
}