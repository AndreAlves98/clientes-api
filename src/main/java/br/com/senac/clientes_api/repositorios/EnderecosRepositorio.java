package br.com.senac.clientes_api.repositorios;


import br.com.senac.clientes_api.entidades.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepositorio extends JpaRepository<Enderecos, Long> {
}
