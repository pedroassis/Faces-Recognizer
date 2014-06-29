/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deve prover metodos para persistencia de dados via SQL, tais como: insert,
 * select, update, delete
 *
 * @author Pedro
 */
public class Banco {

    private PreparedStatement statement;
    private Connection connection;
    private Serializable lastID;

    public Banco() {
        /*  Carregando o arquivo Banco.properties */
        ResourceBundle dadosDoBanco = ResourceBundle.getBundle("Database");
        try {
            Class.forName(dadosDoBanco.getString("driver"));
            
            String host     = dadosDoBanco.getString("host");
            String banco    = dadosDoBanco.getString("banco");
            String porta    = dadosDoBanco.getString("porta");
            String usuario  = dadosDoBanco.getString("usuario");
            String senha    = dadosDoBanco.getString("senha");
            String tipo     = dadosDoBanco.getString("tipo");
            
            String connectionText = String.format(
                    "jdbc:%s://%s:%s/%s"
                    ,     tipo
                    ,          host
                    ,             porta
                    ,                banco);
            
            connection = DriverManager.getConnection(
                    connectionText , usuario, senha
                    );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
/*
 * executeStatement("insert into funcionario values (default, ?)", "pedro");
 * 
 */
    public boolean executeStatement(String query, Object... valores) {

        boolean sucesso = true;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < valores.length; i++) {
                setType(valores, i);
            }
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs != null && rs.next()){
                lastID = (Serializable) rs.getObject(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            sucesso = false;
        }
        return sucesso;
    }

    public List<List> executeQuery(String query, Object... valores) {
        ResultSet rs;
        List<List> tabela = new ArrayList();
        
        try {
            statement = connection.prepareStatement(query);
            for (int i = 0; i < valores.length; i++) {
                setType(valores, i);
            }
            rs = statement.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colunas = rsmd.getColumnCount();
            while (rs.next()) {
                List linha = new ArrayList();
                for (int i = 0; i < colunas; i++) {
                    linha.add(getType(rs.getObject(i+1)));
                }
                tabela.add(linha);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tabela;
    }

    private void setType(Object[] valores, int i) throws SQLException {
        if (valores[i] instanceof Date) {
            Date utilData = (Date) valores[i];
            java.sql.Timestamp sqlData = new java.sql.Timestamp(utilData.getTime());
            statement.setTimestamp(i + 1, sqlData);
        } else {
            statement.setObject(i + 1, valores[i]);
        }
    }

    private Object getType(Object objeto) throws SQLException {
        if (objeto instanceof java.sql.Timestamp) {
            java.sql.Timestamp sqlData = (java.sql.Timestamp) objeto;
            Date utilData = new Date(sqlData.getTime());
            return utilData;
        } else {
            return objeto;
        }
    }
    
    public Serializable getLastID(){
        return lastID;
    }
}