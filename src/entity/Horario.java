/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Pedro
 */
public class Horario implements Serializable{
    
    private Integer id;
    private Date    entrada;
    private Date    saida;
    private Date    saidaAlmoco;
    private Date    entradaAlmoco;

    // <editor-fold defaultstate="collapsed" desc="Getter e Setters - Horario">
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getSaidaAlmoco() {
        return saidaAlmoco;
    }

    public void setSaidaAlmoco(Date saidaAlmoco) {
        this.saidaAlmoco = saidaAlmoco;
    }

    public Date getEntradaAlmoco() {
        return entradaAlmoco;
    }

    public void setEntradaAlmoco(Date entradaAlmoco) {
        this.entradaAlmoco = entradaAlmoco;
    }
    
    //</editor-fold>
    
}