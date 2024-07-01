package br.com.erudio.apijavaservice.dtos;

import br.com.erudio.apijavaservice.domain.Chamado;
import br.com.erudio.apijavaservice.domain.Cliente;
import br.com.erudio.apijavaservice.domain.Tecnico;
import br.com.erudio.apijavaservice.domain.enums.Prioridade;
import br.com.erudio.apijavaservice.domain.enums.Status;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.TupleElement;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dataAbertura;
    private Date dataFechamento;
    @NotNull(message = "Prioridade requerida")

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PRIORIDADE")
    private Set<Integer> prioridade;
    @NotNull(message = "Status Requerido")

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "STATUS")
    private Set<Integer> status;
    @NotNull(message = "Titulo Requerido")


    private String titulo;
    private String observacoes;
    @NotNull(message = "Técnico Requerido")
    private Tecnico tecnico;
    @NotNull(message = "Cliente Requerido")
    private Cliente cliente;
    private String nomeCliente;
    private String nomeTecnico;


    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.status = obj.getStatus().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico();
        this.cliente = obj.getCliente();
        this.nomeCliente = obj.getCliente().getNome();
        this.nomeTecnico = obj.getTecnico().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Set<Prioridade> getPrioridade() {
        return prioridade.stream().map(x -> Prioridade.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPrioridade(Integer prioridade) {
        this.prioridade.add(prioridade);
    }

    public Set<Status> getStatus() {
        return status.stream().map(x -> Status.toEnum(x)).collect(Collectors.toSet());
    }

    public void addStatus(Integer status) {
        this.status.add(status);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }
}
