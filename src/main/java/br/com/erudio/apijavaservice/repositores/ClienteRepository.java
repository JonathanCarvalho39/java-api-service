package br.com.erudio.apijavaservice.repositores;

import br.com.erudio.apijavaservice.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

