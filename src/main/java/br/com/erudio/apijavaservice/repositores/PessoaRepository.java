package br.com.erudio.apijavaservice.repositores;

import br.com.erudio.apijavaservice.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}

