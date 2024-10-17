package com.example.demojpa.models;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
 
 //lista os autores ordenando por nome
 List<Autor> findAllByOrderByNome();

 //usa uma query customizada
 @Query("select A from Autor A order by A.nome")
 List<Autor> listaOrdenadoPorNome();

 //usa uma query customizada e paginação
 @Query("select A from Autor A order by A.nome")
 Page<Autor> listaOrdenadoPorNomeComPaginacao(Pageable pageable);

 //usa uma query customizada que filtra por nomes no seu início
 @Query("select A from Autor A where A.nome like ?1 order by A.nome")
 List<Autor> listaOrdenadoPorNomeInicio(String inicio);

}
