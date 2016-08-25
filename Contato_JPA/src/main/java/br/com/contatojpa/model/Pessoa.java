package br.com.contatojpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "pes_pessoa")
public class Pessoa implements Serializable, Model{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Long id;
    @Column(name = "pes_nome", nullable = false, length = 180)
    private String nome;
    @Column(name="pes_cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "pes_nascimento") 
    @Temporal(TemporalType.DATE)  
    private Date nascimento;
    @Column(name = "pes_observacao")
    @Lob
    private String observacao;
    @Column(name = "pes_sexo", columnDefinition ="enum('MASCULINO', 'FEMININO', 'OUTROS')")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Transient
    private String teste;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pes_pessoa")
    private List<Endereco> enderecos;
    
    public Pessoa(){
        this.enderecos = new  ArrayList();
    }

    public Pessoa(String nome, String cpf, Date nascimento, String observacao, Sexo sexo, String teste) {
        this();
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.observacao = observacao;
        this.sexo = sexo;
        this.teste = teste;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    public void addEndereco(Endereco e){
        this.enderecos.add(e);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nascimento=" + nascimento + ", observacao=" + observacao + ", sexo=" + sexo + ", teste=" + teste + ", enderecos=" + enderecos + '}';
    }

    
}
