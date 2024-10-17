package br.org.rfdouro.demojpa.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//cria um repositório para acesso aos dados de tbpessoa no banco de dados
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

 //método que executa uma consulta na tabel ordenando por cpf
 @Query(value = "select p from Pessoa p order by p.cpf")
 public List<Pessoa> selecionaOrdenadoPorCPF();
}
