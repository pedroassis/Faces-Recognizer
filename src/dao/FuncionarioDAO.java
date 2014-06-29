package dao;

import entity.Foto;
import entity.Funcionario;
import entity.Registro;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Banco;

/**
 * Classe DAO responsavel por implementar os metodos da Interface GenericDAO
 * provendo meios para a persistencia da entidade Funcionario
 *
 * @author Pedro
 */
public class FuncionarioDAO implements GenericDAO<Funcionario> {

    /**
     * Registrando a instancia singleton desta classe no GenericDAO
     */
    static {
        GenericDAO.instances.put(Funcionario.class, new FuncionarioDAO());
    }
    
    private Banco bd = new Banco();
    
    private static final String SQLSELECT       = "select * from funcionario";
    private static final String SQLCOUNT        = "select count(*) from funcionario";
    private static final String SQLDELETE       = "delete from funcionario where id = ?";
    private static final String SQLSAVE         = "insert into funcionario values(default, ?,?,?,?,?)";
    private static final String SQLUPDATE       = "update funcionario set nome = ?, cpf=?, pis=?, telefone=?, identidade=? where id = ?";
    private static final String SQLSELECTWHERE  = SQLSELECT + " where %s = ?";

    private FuncionarioDAO() {
    }

    @Override
    public List<Funcionario> getAll() {
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        List<List> lista = bd.executeQuery(SQLSELECT);
        convertToFuncionario(lista, funcionarios);
        return funcionarios;
    }

    @Override
    public Funcionario getByID(Serializable id) {
        List<Funcionario> funcionario = getByParameter("id", id);
        return funcionario.isEmpty() ? null : funcionario.get(0);
    }

    @Override
    public boolean delete(Funcionario entidade) {
        List<Registro> lista = GenericDAO.instances.get(Registro.class).getByParameter("funcionario", entidade.getId());
        for(int i = 0; i < lista.size(); i++){
            GenericDAO.instances.get(Registro.class).delete(lista.get(i));
        }
        String PASTA_FOTOS = new java.io.File("").getAbsolutePath() + "\\fotos\\";
        File pasta = new File(PASTA_FOTOS + entidade.getId());
        for(File f : pasta.listFiles()){
            f.delete();
        }
        pasta.delete();
        List<Foto> fotos = GenericDAO.instances.get(Foto.class).getByParameter("funcionario", entidade.getId());
        for(int i = 0; i < fotos.size(); i++){
            GenericDAO.instances.get(Foto.class).delete(fotos.get(i));
        }
        return bd.executeStatement(SQLDELETE, entidade.getId());
    }

    @Override
    public List<Funcionario> getByParameter(String propriedade, Object valor) {
        String query = String.format(SQLSELECTWHERE, propriedade);
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        List<List> tabela = bd.executeQuery(query, valor );
        convertToFuncionario(tabela, funcionarios);
        return funcionarios;
    }

    @Override
    public boolean save(Funcionario entidade) {
        boolean result = bd.executeStatement(SQLSAVE
                , entidade.getNome()
                , entidade.getCpf()
                , entidade.getPis()
                , entidade.getTelefone()
                , entidade.getIdentidade()
                );
        entidade.setId(Integer.parseInt(bd.getLastID().toString()));
        return result;
    }

    @Override
    public boolean update(Funcionario entidade) {
        return bd.executeStatement(SQLUPDATE,
                entidade.getNome()
                , entidade.getCpf()
                , entidade.getPis()
                , entidade.getTelefone()
                , entidade.getIdentidade()
                , entidade.getId()
                );
    }

    @Override
    public boolean saveOrUpdate(Funcionario entidade) {
        if(entidade.getId() != null){
            return update(entidade);
        } else {
            return save(entidade);
        }
    }

    private void convertToFuncionario(
            List<List> lista,
            List<Funcionario> funcionarios
            ) {
        
        for (List list : lista) {
            Funcionario funcionario = new Funcionario();
            int i = 0;
            funcionario.setId((Integer)list.get(i++));
            funcionario.setNome((String) list.get(i++));
            funcionario.setCpf((String) list.get(i++));
            funcionario.setPis((String)list.get(i++));
            funcionario.setTelefone((String)list.get(i++));
            funcionario.setIdentidade((String) list.get(i++));

            funcionarios.add(funcionario);
        }
    }

    @Override
    public Integer getCount() {
        return Integer.parseInt(bd.executeQuery(SQLCOUNT).get(0).get(1).toString());
    }
}
