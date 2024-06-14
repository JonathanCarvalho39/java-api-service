package br.com.erudio.apijavaservice.repositores;

import br.com.erudio.apijavaservice.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}

