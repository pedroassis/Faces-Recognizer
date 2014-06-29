/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Pedro Viado
 */
public class Funcionario implements Serializable {
    
    private Integer id;
    private String nome;   
    private String cpf;
    private String pis;
    private String telefone;
    private String identidade;

    public Funcionario() {
    }
    
    public Funcionario(String nome, String cpf, String rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.identidade = rg;
    }

    public Funcionario(Integer id, String nome, String cpf, String pis, String telefone, String identidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.pis = pis;
        this.telefone = telefone;
        this.identidade = identidade;
    }

    public Integer getId() {
        return id;
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

    public String getIdentidade() {
        return identidade;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdentidade(String rg) {
        this.identidade = rg;
    }

    /**
     * @return the pis
     */
    public String getPis() {
        return pis;
    }

    /**
     * @param pis the pis to set
     */
    public void setPis(String pis) {
        this.pis = pis;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}