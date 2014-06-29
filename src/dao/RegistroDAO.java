/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.gson.Gson;
import entity.Foto;
import entity.Funcionario;
import entity.Registro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Banco;
import model.WebSocketCliente;

/**
 *
 * @author Pedro
 */
public class RegistroDAO implements GenericDAO<Registro> {

    /**
     * Registrando a instancia singleton desta classe no GenericDAO
     */
    static {
        GenericDAO.instances.put(Registro.class, new RegistroDAO());
    }
    
    private Gson gson = new Gson();
    private Banco bd = new Banco();
    private static FuncionarioDAO funcionarioDAO = 
            (FuncionarioDAO) GenericDAO.instances.get(Funcionario.class);
    
    private static final String SQLSELECT       = "select * from registro";
    private static final String SQLSELECTLAST   = "select * FROM registro WHERE funcionario = ? ORDER BY date DESC";
    private static final String SQLCOUNT        = "select count(*) from registro";
    private static final String SQLDELETE       = "delete from registro where id = ?";
    private static final String SQLSAVE         = "insert into registro values(default, ?, ?, ?)";
    private static final String SQLUPDATE       = "update registro set funcionario=?, date=?, tiporegistro=? where id=?";
    private static final String SQLSELECTWHERE  = SQLSELECT + " where %s = ?";

    @Override
    public Registro getByID(Serializable id) {
        List<Registro> registro = getByParameter("id", id);
        return registro.isEmpty() ? null : registro.get(0);
    }
    
    public Registro getLast(Funcionario funcionario) {
        List<Registro> registro = new ArrayList<Registro>();
        List<List> lista = bd.executeQuery(SQLSELECTLAST, funcionario.getId());
        convertToRegistro(lista, registro);
        return registro.isEmpty() ? null : registro.get(0);
    }

    @Override
    public boolean delete(Registro entidade) {
       return  bd.executeStatement(SQLDELETE, entidade.getId());
    }

    @Override
    public List<Registro> getAll() {
        List<Registro> registros = new ArrayList<Registro>();
        List<List> lista = bd.executeQuery(SQLSELECT);
        convertToRegistro(lista, registros);
        return registros;
    }

    @Override
    public List<Registro> getByParameter(String propriedade, Object valor) {
        String query = String.format(SQLSELECTWHERE, propriedade);
        List<Registro> registros = new ArrayList<Registro>();
        List<List> tabela = bd.executeQuery(query, valor);
        convertToRegistro(tabela, registros);
        return registros;
    }

    @Override
    public boolean save(Registro entidade) {
        entidade.setData(new Date());
        boolean result = bd.executeStatement(SQLSAVE
                , entidade.getFuncionario().getId()
                , entidade.getData()
                , entidade.getTipo().valor
                );
        entidade.setId(Integer.parseInt(bd.getLastID().toString()));
        WebSocketCliente.getInstance().send(gson.toJson(entidade));
        return result;
    }

    @Override
    public boolean update(Registro entidade) {
        return bd.executeStatement(SQLUPDATE, entidade.getFuncionario(), 
                entidade.getData(), entidade.getTipo(), entidade.getId());
    }

    @Override
    public boolean saveOrUpdate(Registro entidade) {
       if(entidade.getId() != null){
            return update(entidade);
        } else {
            return save(entidade);
        }
    }

    @Override
    public Integer getCount() {
        return Integer.parseInt(bd.executeQuery(SQLCOUNT).get(0).get(1).toString());
    }

    private void convertToRegistro(List<List> tabela,
            List<Registro> registros) {
        for (List linha : tabela) {
            Registro registro = new Registro();
            int i = 0;
            registro.setId((Integer) linha.get(i++));
            Integer idFuncionario = (Integer)linha.get(i++);
            Funcionario f = funcionarioDAO.getByID(idFuncionario);
            registro.setFuncionario(f);
            registro.setData((Date) linha.get(i++));
            registro.setTipo(Registro.TipoRegistro.get((Integer) linha.get(i++)));
            registros.add(registro);
        }
    }
    
}