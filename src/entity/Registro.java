/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Encapsula um registro de entrada ou saida de um funcionario
 * @author Pedro
 */
public class Registro implements Serializable{
    
    public enum TipoRegistro{
        
        ENTRADA(1),
        SAIDA_ALMOCO(2),
        ENTRADA_ALMOCO(3),
        SAIDA(4);
        
        public int valor;
        private ResourceBundle bundle = ResourceBundle.getBundle("TipoRegistro");
        
        private TipoRegistro(int valor){
            this.valor = valor;
        }
        
        public TipoRegistro getNext(){
            for (TipoRegistro registro : values()){
                if(registro.valor == this.valor+1){
                    return registro;
                }
            }
            return ENTRADA;
        }
        
        public static TipoRegistro get(int index){
            for (TipoRegistro registro : values()){
                if(registro.valor == index){
                    return registro;
                }
            }
            return ENTRADA;
        }
        
        @Override
        public String toString(){
            return bundle.getString(this.name());
        }
    }
    
    public Registro() {
    }

    public Registro(Funcionario funcionario, Date data, TipoRegistro tipo) {
        this.funcionario = funcionario;
        this.data = data;
        this.tipo = tipo;
    }
    
    private Integer id;
    private Funcionario funcionario;
    private Date data;
    private TipoRegistro tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoRegistro getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegistro tipo) {
        this.tipo = tipo;
    }
    
}