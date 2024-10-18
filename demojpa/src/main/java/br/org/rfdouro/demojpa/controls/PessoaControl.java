package br.org.rfdouro.demojpa.controls;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.org.rfdouro.demojpa.models.Pessoa;
import br.org.rfdouro.demojpa.models.PessoaRepository;
import org.springframework.web.bind.annotation.PostMapping;

//controle para as requisições de pessoa
@Controller
public class PessoaControl {

 // injeta um objeto para manipular os dados no banco
 @Autowired
 private PessoaRepository pessoaRepository;

 // trata GET para listar as pessoas e mostrar na página
 @GetMapping("/pessoas")
 public String mostraTodasPessoas(Model model) {
  // busca a lista de pessoas
  List<Pessoa> lp = pessoaRepository.selecionaOrdenadoPorCPF();
  // adiciona no model para enviar à página
  model.addAttribute("listaPessoas", lp);
  // retorna na página /pessoas/lista.html
  return "pessoas/lista";
 }

 // GET para abrir a página de cadastro de pessoas
 @GetMapping("/pessoas/cadastro")
 public String abreCadastro() {
  return "pessoas/nova";
 }

 // POST para receber dados de uma nova pessoa e cadastrar
 @PostMapping(value = "/pessoas/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
 public String postMethodName(Pessoa pessoa) {
  // insere a pessoa no banco de dados
  pessoaRepository.save(pessoa);
  // redireciona para a lista de pessoas
  return "redirect:/pessoas";
 }

 // GET que recebe um id e abre a visualização da pessoa que tem aquele id
 @GetMapping("/pessoas/abre/{id}")
 public String visualiza(Model model, @PathVariable UUID id) {
  // seleciona a pessoa no banco de dados
  Pessoa pessoa = pessoaRepository.findById(id).get();
  if (pessoa != null) {
   // inclui a pessoa no modelo
   model.addAttribute("pessoa", pessoa);
   // envia para a página de exibição
   return "pessoas/abre";
  }
  // redireciona para a lista de pessoas
  return "redirect:/pessoas";
 }

 // GET que recebe um id e exclui a pessoa que tem aquele id
 @GetMapping("/pessoas/exclui/{id}")
 public String exclui(@PathVariable UUID id) {
  // remove a pessoa no banco de dados
  pessoaRepository.deleteById(id);
  // redireciona para a lista de pessoas
  return "redirect:/pessoas";
 }

 // POST para receber dados de uma nova pessoa e atualizar
 @PostMapping(value = "/pessoas/atualiza/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
 public String atualiza(@PathVariable UUID id, Pessoa pessoa) {
  // seleciona a pessoa no banco de dados
  Pessoa verificapessoa = pessoaRepository.findById(id).get();
  if (verificapessoa != null) {
   // atualiza a pessoa no banco de dados
   pessoaRepository.save(pessoa);
  }
  // redireciona para a lista de pessoas
  return "redirect:/pessoas";
 }

}
