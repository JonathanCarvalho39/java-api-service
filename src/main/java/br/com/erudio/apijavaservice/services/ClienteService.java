package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.domain.Pessoa;
import br.com.erudio.apijavaservice.dtos.ClienteDTO;
import br.com.erudio.apijavaservice.repositores.ClienteRepository;
import br.com.erudio.apijavaservice.repositores.PessoaRepository;
import br.com.erudio.apijavaservice.services.exeptions.ObjectNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExeption("Cliente não encontrado: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO obj) {
        obj.setId(null);
        obj.setSenha(encoder.encode(obj.getSenha()));
        validaCpfEEmail(obj);
        Cliente newObj = new Cliente(obj);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDto) {
        objDto.setId(id);
        Cliente oldObj;
        validaCpfEEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui chamados atrelados a ele!");
        }
        repository.deleteById(id);
    }


    private void validaCpfEEmail(ClienteDTO objDTO) {
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
