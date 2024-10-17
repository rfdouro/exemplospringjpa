package br.org.rfdouro.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.org.rfdouro.demojpa.models.Pessoa;
import br.org.rfdouro.demojpa.models.PessoaRepository;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
	}

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	private void testeAddPessoas() {
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		definition.setTimeout(-1);

		TransactionStatus status = transactionManager.getTransaction(definition);

		// teste de controle de transação
		try {

			Pessoa p = new Pessoa();
			p.setNome("teste");
			p.setCpf("1234");
			pessoaRepository.save(p);

			p = new Pessoa();
			p.setNome("teste");
			p.setCpf("1234");
			pessoaRepository.save(p);

			transactionManager.commit(status);

		} catch (Exception ex) {
			transactionManager.rollback(status);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			testeAddPessoas();
		} catch (Exception e) {
		}
	}

}
