package br.com.erudio.apijavaservice.repositores;

import br.com.erudio.apijavaservice.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}

