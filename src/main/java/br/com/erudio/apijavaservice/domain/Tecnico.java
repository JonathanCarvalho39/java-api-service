package br.com.erudio.apijavaservice.domain;

import br.com.erudio.apijavaservice.domain.enums.Perfil;
import br.com.erudio.apijavaservice.dtos.TecnicoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList();
    private String especialidade;
    private Integer anosExp;

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha, String especialidade, Integer anosExp) {
        super(id, nome, cpf, email, senha);
        this.anosExp = anosExp;
        this.especialidade = especialidade;
        addPerfil(Perfil.CLIENTE);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        this.especialidade = obj.getEspecialidade();
        this.anosExp = obj.getAnosExp();
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Integer getAnosExp() {
        return anosExp;
    }

    public void setAnosExp(Integer anosExp) {
        this.anosExp = anosExp;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
