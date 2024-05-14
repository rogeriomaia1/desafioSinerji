package com.sinergi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Endereco", schema = "sinergi")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cep", nullable = false)
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    public Endereco() {
    }

    public Endereco(String estado, String cidade, String logradouro, String numero, String cep, Pessoa pessoa) {
        this.estado = estado;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, cidade, estado, id, logradouro, numero, pessoa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return Objects.equals(cep, other.cep) && Objects.equals(cidade, other.cidade)
                && Objects.equals(estado, other.estado) && Objects.equals(id, other.id)
                && Objects.equals(logradouro, other.logradouro) && Objects.equals(numero, other.numero)
                && Objects.equals(pessoa, other.pessoa);
    }
}
	