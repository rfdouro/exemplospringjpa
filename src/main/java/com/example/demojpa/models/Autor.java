package com.example.demojpa.models;

import java.util.LinkedList;
import java.util.List;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_autor")
public class Autor {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(name = "nome_autor", length = 150)
 private String nome;

 @Temporal(TemporalType.DATE)
 private Calendar dtnasc; 



 //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
 @ManyToMany(mappedBy = "autores")
 private List<Livro> livros = new LinkedList<>();




 public Long getId() {
  return id;
 }
 public void setId(Long id) {
  this.id = id;
 }
 public String getNome() {
  return nome;
 }
 public void setNome(String nome) {
  this.nome = nome;
 }
 public List<Livro> getLivros() {
  return livros;
 }
 public void setLivros(List<Livro> livros) {
  this.livros = livros;
 }
 
}
