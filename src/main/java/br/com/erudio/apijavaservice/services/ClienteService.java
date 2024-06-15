package br.com.erudio.apijavaservice.services;


import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.repositores.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
