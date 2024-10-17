package br.org.rfdouro.demojpa.controls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controle de manipulação do link inicial
@Controller
public class IndexControl {

 // trata requisição GET da raiz do site
 @GetMapping({ "", "/" })
 public String index() {
  return "index";
 }

}
