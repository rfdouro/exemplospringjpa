# Projetos de exemplo para `Spring JPA`

No projeto [demojpa](demojpa) há um cadastro de pessoas e no projeto [demojpa0](demojpa0) há um cadastro de autores e livros (com relacionamento)

```java
@Entity
@Table(name = "tb_autor")
public class Autor {
  //...
 @ManyToMany(mappedBy = "autores")
 private List<Livro> livros = new LinkedList<>();
```
```java
@Entity
@Table(name = "tb_livro")
public class Livro {

 //...
 @ManyToMany
 @JoinTable(name = "tb_autores_livros")
 private List<Autor> autores = new LinkedList<>();
```

No caso do projeto demojpa execute a url [http://localhost:8080/demojpa/ ](http://localhost:8080/demojpa/) e para o projeto demojpa0 a url de acesso é [http://localhost:8080/](http://localhost:8080/) pois não tem contexto definido.

## Fontes de pesquisa
Para pesquisas mais aprofundadas:

* [Usando @Transactional](https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html)
* [Usando queries](https://www.baeldung.com/spring-data-jpa-query)
