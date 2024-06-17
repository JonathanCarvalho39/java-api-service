package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Chamado;
import br.com.erudio.apijavaservice.repositores.ChamadoRepository;
import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExeption("Chamado não encontrado: " + id));
    }

    public List<Chamado> findByAll() {
        return repository.findAll();
    }
}
