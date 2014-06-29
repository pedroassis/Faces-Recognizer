/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Foto;
import entity.Funcionario;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Banco;

/**
 *
 * @author Usuario01
 */
public class FotoDAO implements GenericDAO<Foto> {

    /**
     * Registrando a instancia singleton desta classe no GenericDAO
     */
    static {
        GenericDAO.instances.put(Foto.class, new FotoDAO());
    }
    
    private static final String PASTA_FOTOS = new java.io.File("").getAbsolutePath() + "\\fotos\\";
    
    private Banco bd = new Banco();
    private static FuncionarioDAO funcionarioDAO = 
            (FuncionarioDAO) GenericDAO.instances.get(Funcionario.class);
    
    private static final String SQLSELECT       = "select * from foto";
    private static final String SQLCOUNT        = "select count(*) from foto";
    private static final String SQLDELETE       = "delete from foto where id = ?";
    private static final String SQLSAVE         = "insert into foto values(default, ?, ?, ?)";
    private static final String SQLUPDATE       = "update foto set caminho=?, funcionario=? where id=?";
    private static final String SQLSELECTWHERE  = SQLSELECT + " where %s = ?";

    private FotoDAO() {
    }

    @Override
    public List<Foto> getAll() {
        List<Foto> fotos = new ArrayList<Foto>();
        List<List> lista = bd.executeQuery(SQLSELECT);
        convertToFoto(lista, fotos);
        return fotos;
    }

    @Override
    public Foto getByID(Serializable id) {
        List<Foto> foto = getByParameter("id", id);
        return foto.isEmpty() ? null : foto.get(0);
    }

    @Override
    public boolean delete(Foto entidade) {
        return bd.executeStatement(SQLDELETE, entidade.getId());
    }

    @Override
    public List<Foto> getByParameter(String propriedade, Object valor) {
        String query = String.format(SQLSELECTWHERE, propriedade);
        List<Foto> fotos = new ArrayList<Foto>();
        List<List> lista = bd.executeQuery(query, valor );
        convertToFoto(lista, fotos);
        return fotos;
    }

    @Override
    public boolean save(Foto entidade) {
        entidade.setData(new Date());
        boolean result = bd.executeStatement(SQLSAVE
                , entidade.getPath()
                , entidade.getFuncionario().getId()
                , entidade.getData()
                );
        entidade.setId(Integer.parseInt(bd.getLastID().toString()));
        return result;
    }

    @Override
    public boolean update(Foto entidade) {
        return bd.executeStatement(SQLUPDATE, entidade.getPath(), entidade.getFuncionario().getId());
    }

    @Override
    public boolean saveOrUpdate(Foto entidade) {
        if(entidade.getId() != null){
            return update(entidade);
        } else {
            return save(entidade);
        }
    }

    private void convertToFoto(List<List> tabela,
            List<Foto> fotos) {
        for (List linha : tabela) {
            Foto foto = new Foto();
            int i = 0;
            foto.setId((Integer) linha.get(i++));
            foto.setPath((String) linha.get(i++));
            Integer idFuncionario = (Integer)linha.get(i++);
            Funcionario f = funcionarioDAO.getByID(idFuncionario);
            foto.setFuncionario(f);
            foto.setData((Date) linha.get(i++));
            fotos.add(foto);
        }
    }

    @Override
    public Integer getCount() {
        return Integer.parseInt(bd.executeQuery(SQLCOUNT).get(0).get(0).toString());
    }
}