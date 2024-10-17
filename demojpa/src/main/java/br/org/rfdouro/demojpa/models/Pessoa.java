package br.org.rfdouro.demojpa.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//define uma classe que será usada como entidade = tabela
@Entity
// define informações da tabela (nome será tbpessoa)
@Table(name = "tbpessoa")
public class Pessoa {
 // define uma pk para a tabela
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private UUID id;
 private String nome;
 // indica que a coluna cpf tem valor único dentro da tabela
 @Column(unique = true, length = 12)
 private String cpf;

 public UUID getId() {
  return id;
 }

 public void setId(UUID id) {
  this.id = id;
 }

 public String getNome() {
  return nome;
 }

 public void setNome(String nome) {
  this.nome = nome;
 }

 public String getCpf() {
  return cpf;
 }

 public void setCpf(String cpf) {
  this.cpf = cpf;
 }

}
