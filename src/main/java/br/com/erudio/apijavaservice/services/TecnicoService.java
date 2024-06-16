package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Pessoa;
import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.dtos.TecnicoDTO;
import br.com.erudio.apijavaservice.repositores.PessoaRepository;
import br.com.erudio.apijavaservice.repositores.TecnicoRepository;
import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExeption("Técnico não encontrado: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO obj) {
        obj.setId(null);
        validaCpfEEmail(obj);
        Tecnico newObj = new Tecnico(obj);
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaCpfEEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    private void validaCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já Cadastrado no sistema");
        }
    }


}
