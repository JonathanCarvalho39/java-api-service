package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.repositores.TecnicoRepository;
import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExeption("Técnico não encontrado: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}
