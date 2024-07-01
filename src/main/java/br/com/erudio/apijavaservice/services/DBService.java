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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static br.com.erudio.apijavaservice.domain.enums.Status.*;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    public void instaciaDB() {
        Tecnico tecnico = new Tecnico(null, "Jonathan", "271.075.670-63", "jonathan@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico.addPerfil(Perfil.ADMIN);

        Tecnico tecnico1 = new Tecnico(null, "Andre", "347.560.120-60", "andre@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico1.addPerfil(Perfil.ADMIN);

        Tecnico tecnico2 = new Tecnico(null, "José", "449.850.450-04", "jose@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico2.addPerfil(Perfil.ADMIN);

        Tecnico tecnico3 = new Tecnico(null, "Fabio", "736.229.120-57", "fabio@gmail.com", encoder.encode("123"), "TI", 2);
//        tecnico3.addPerfil(Perfil.ADMIN);

        Tecnico tecnico4 = new Tecnico(null, "Luis", "197.301.410-61", "adm@vtal.com.br", encoder.encode("eAm12346@1"), "TI", 2);
        tecnico.addPerfil(Perfil.ADMIN);

        Tecnico tecnico5 = new Tecnico(null, "Jonathan", "766.455.900-67", "jonathan1@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico.addPerfil(Perfil.ADMIN);

        Tecnico tecnico6 = new Tecnico(null, "Andre", "638.908.340-86", "andre1@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico1.addPerfil(Perfil.ADMIN);

        Tecnico tecnico7 = new Tecnico(null, "José", "876.350.430-80", "jose1@gmail.com", encoder.encode("123"), "TI", 2);
        tecnico2.addPerfil(Perfil.ADMIN);

        Tecnico tecnico8 = new Tecnico(null, "Fabio", "626.573.470-96", "fabio1@gmail.com", encoder.encode("123"), "TI", 2);
//        tecnico3.addPerfil(Perfil.ADMIN);

        Tecnico tecnico9 = new Tecnico(null, "Luis", "481.671.860-59", "ad1@vtal.com.br", encoder.encode("eAm12346@1"), "TI", 2);
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Pedro", "634.394.270-05", "pedro@gmail.com", encoder.encode("123"));
        Cliente client2 = new Cliente(null, "Julio", "477.693.910-08", "julio@gmail.com", encoder.encode("123"));

        Chamado ch1 = new Chamado(null, "Chamado-01", "Primeiro chamado", tecnico, cliente);
        ch1.addPrioridade(Prioridade.BAIXA);
        ch1.addStatus(ABERTO);
        ch1.addStatus(ANDAMENTO);
        Chamado ch2 = new Chamado(null, "Chamado-02", "Segundo chamado", tecnico1, cliente);
        ch2.addPrioridade(Prioridade.BAIXA);
        ch2.addStatus(ABERTO);
        ch2.addStatus(ANDAMENTO);
        ch2.addStatus(ENCERRADO);
        Chamado ch3 = new Chamado(null, "Chamado-03", "Terceiro chamado", tecnico3, cliente);
        ch3.addPrioridade(Prioridade.MEDIA);
        ch3.addStatus(ABERTO);

        tecnicoRepository.saveAll(Arrays.asList(tecnico, tecnico1, tecnico2, tecnico3, tecnico4, tecnico5, tecnico6, tecnico7, tecnico8, tecnico9));
        clienteRepository.saveAll(Arrays.asList(cliente, client2));
        chamadoRepository.saveAll(Arrays.asList(ch1, ch2, ch3));
    }
}
