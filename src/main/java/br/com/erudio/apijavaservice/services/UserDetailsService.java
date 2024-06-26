package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Pessoa;
import br.com.erudio.apijavaservice.repositores.PessoaRepository;
import br.com.erudio.apijavaservice.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);
        if (user.isPresent()) {
            return new UserSS(
                    user.get().getId(),
                    user.get().getEmail(),
                    user.get().getSenha(),
                    user.get().getPerfils());
        }
        throw new UsernameNotFoundException(email);
    }
}
