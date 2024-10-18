package com.example.demojpa.controls;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demojpa.models.Autor;
import com.example.demojpa.models.AutorRepository;
import com.example.demojpa.models.Livro;
import com.example.demojpa.models.LivroRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "", "/" })
public class IndexController {

 @Autowired
 AutorRepository autorRepository;
 @Autowired
 LivroRepository livroRepository;

 // usado para queries customizadas aquis
 @PersistenceContext
 private EntityManager entityManager;

 @GetMapping("")
 public String index() {
  return "index";
 }

 @GetMapping("/custom")
 public @ResponseBody List custom() {
  //cria uma consulta customizada diretamente no controller
  Query q = entityManager.createQuery("select A.nome, L.titulo from Autor A join A.livros L");
  return q.getResultList();
 }

 @GetMapping("/autor")
 public String abreListaAutor(Model model) {
  //retorna a primeira página com 10 elementos no máximo
  model.addAttribute("autores", autorRepository.listaOrdenadoPorNomeComPaginacao(PageRequest.of(0, 10)));
  //retorna a lista buscando todos que começam o nome por 'a'
  // model.addAttribute("autores", autorRepository.listaOrdenadoPorNomeInicio("a%"));
  return "autores";
 }

 @PostMapping("/autor/add")
 public String cadastraAutor(String nome) {
  Autor a = new Autor();
  a.setNome(nome);
  autorRepository.save(a);
  return "redirect:/autor";
 }

 @GetMapping("/autor/upd/{id}")
 public String atualizaAutor(@PathVariable("id") Long id, String nome) {
  Autor a = autorRepository.getReferenceById(id);
  a.setNome(nome);
  autorRepository.save(a);
  return "redirect:/autor";
 }

 @GetMapping("/autor/remove/{id}")
 public String removeAutor(@PathVariable(name = "id", required = true) Long id) {
  Autor a = autorRepository.findById(id).get();
  if (a != null)
   autorRepository.deleteById(id);
  return "redirect:/autor";
 }

 @GetMapping("/livro")
 public String abreListaLivro(Model model) {
  model.addAttribute("livros", livroRepository.findAll());
  model.addAttribute("autores", autorRepository.findAllByOrderByNome());
  return "livros";
 }

 @PostMapping("/livro/add")
 // public String cadastraLivro(String titulo, Long id_autor) {
 public String cadastraLivro(String titulo, Long[] id_autor) {
  Livro l = new Livro();
  l.setTitulo(titulo);
  // Autor a = autorRepository.findById(id_autor).get();
  // l.setAutor(a);
  for (Long i : id_autor) {
   Autor a = autorRepository.findById(i).get();
   l.getAutores().add(a);
  }
  livroRepository.save(l);
  return "redirect:/livro";
 }

 @GetMapping("/livro/remove/{id}")
 @Transactional(readOnly = true) // impede que seja excluído deixando a transação apenas para consulta
 public String removeLivro(@PathVariable(name = "id", required = true) Long id) {
  Livro l = livroRepository.findById(id).get();
  if (l != null) {
   livroRepository.deleteById(id);
   for (Autor a : l.getAutores()) {
    autorRepository.delete(a);
   }
  }
  return "redirect:/livro";
 }

}
