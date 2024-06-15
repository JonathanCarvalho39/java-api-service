package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.repositores.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
