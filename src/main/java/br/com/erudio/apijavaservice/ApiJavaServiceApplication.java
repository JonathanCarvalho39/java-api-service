package br.com.erudio.apijavaservice;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class ApiJavaServiceApplication implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    public static void main(String[] args) {
        SpringApplication.run(ApiJavaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tecnico tecnico = new Tecnico(null, "jonathan", "42567079807", "jonathan@gmail.com", "123");
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Pedro", "12345676543", "pedro@gmail.com", "123");

        Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado-01", "Primeiro chamado", tecnico, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(ch1));
    }
}
