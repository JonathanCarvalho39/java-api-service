package br.com.erudio.apijavaservice.services;

import br.com.erudio.apijavaservice.domain.Chamado;
import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.domain.enums.Perfil;
import br.com.erudio.apijavaservice.domain.enums.Prioridade;
import br.com.erudio.apijavaservice.domain.enums.Status;
import br.com.erudio.apijavaservice.repositores.ChamadoRepository;
import br.com.erudio.apijavaservice.repositores.ClienteRepository;
import br.com.erudio.apijavaservice.repositores.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instaciaDB() {
        Tecnico tecnico = new Tecnico(null, "Jonathan", "271.075.670-63", "jonathan@gmail.com", "123");
        tecnico.addPerfil(Perfil.ADMIN);

        Tecnico tecnico1 = new Tecnico(null, "Andre", "347.560.120-60", "andre@gmail.com", "123");
        tecnico1.addPerfil(Perfil.ADMIN);

        Tecnico tecnico2 = new Tecnico(null, "José", "449.850.450-04", "jose@gmail.com", "123");
        tecnico2.addPerfil(Perfil.ADMIN);

        Tecnico tecnico3 = new Tecnico(null, "Fabio", "736.229.120-57", "fabio@gmail.com", "123");
        tecnico3.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Pedro", "634.394.270-05", "pedro@gmail.com", "123");

        Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado-01", "Primeiro chamado", tecnico, cliente);
        Chamado ch2 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado-02", "Segundo chamado", tecnico1, cliente);
        Chamado ch3 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado-03", "Terceiro chamado", tecnico2, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico, tecnico1, tecnico2, tecnico3));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(ch1, ch2, ch3));
    }
}
