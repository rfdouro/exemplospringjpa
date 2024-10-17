package com.example.demojpa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.*;

@Entity
@Table(name = "tb_livro")
public class Livro {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(name = "titulo_livro")
 private String titulo;

 /*
  * @ManyToOne()
  * 
  * @JoinColumn(name = "id_autor")
  * private Autor autor;
  * public Autor getAutor() {
  * return autor;
  * }
  * public void setAutor(Autor autor) {
  * this.autor = autor;
  * }
  */

 @ManyToMany
 @JoinTable(name = "tb_autores_livros")
 private List<Autor> autores = new LinkedList<>();

 public List<Autor> getAutores() {
  return autores;
 }

 public void setAutores(List<Autor> autores) {
  this.autores = autores;
 }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getTitulo() {
  return titulo;
 }

 public void setTitulo(String titulo) {
  this.titulo = titulo;
 }

}
