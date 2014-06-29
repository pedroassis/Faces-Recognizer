
package dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Interface responsavel por definir metodos basicos de CRUD.
 * @author Tiago
 * @param <EntidadePersistida> Tipo da entidade a ser gerenciada.
 */
public interface GenericDAO<EntidadePersistida extends Serializable> {
    
    /**
     * Constante para registro e acesso de instancias singleton de GenericDAO
     * Class tipo da entidade
     */
    Map<Class, GenericDAO> instances 
            = new HashMap<Class, GenericDAO>();
    
    /**
     * Persiste a entidade na base de dados
     * @param entidade Entidade a ser persistida
     * @return Retorna true se salvo com sucesso ou false se não.
     */
    boolean save(EntidadePersistida entidade);
    
    /**
     * Atualiza a entidade na base de dados
     * @param entidade Entidade a ser persistida
     * @return Retorna true se atualizado com sucesso ou false se não.
     */
    boolean update(EntidadePersistida entidade);
    
    /**
     * Persiste ou atualiza (se ja existir) a entidade na base de dados.
     * @param entidade Entidade a ser persistida
     * @return Retorna true se salvo com sucesso ou false se não.
     */
    boolean saveOrUpdate(EntidadePersistida entidade);
    
    /**
     * Retorna uma entidade, se encontrado com o id igual ao recebido.
     * @param id Identificador unico do entidade persistida
      @return Um Funcionario com o id igual ao recebido 
      *       ou null se não encontrado.
     */
    EntidadePersistida getByID(Serializable id);
    
    /**
     * Remove o Entidade da persistencia de dados.
     * @param EntidadePersistida A entidade a ser deletada.
     * @return Retorna true se deletado ou false se não.
     */
    boolean delete(EntidadePersistida entidade);
    
    /**
     * Retorna todas as entidades persistidas.
     * @return Uma lista de entidades.
     */
    List<EntidadePersistida> getAll();
    
    /**
     * Retorna todas as entidades filtradas pela propriedade informada.
     * @param propriedade Nome da propriedade 
     * @param valor Valor a ser comparado
     * Ex: <br/>
     *      GenericDAO funcionarioDAO = new FuncionarioDAO();<br/>
     *      funcionarioDAO.getByParameter("nome", "joao");<br/>
     *      //deve retornar todos os Funcionarios com nome igual a joao<br/>
     * @return Uma Lista de entidades.
     */
    List<EntidadePersistida> getByParameter(String propriedade, Object valor);
    
    /**
     * Retorna o numero de objetos persistidos.
     * @param classe Classe dos objetos persistentes a serem contados.
     * @return Um Integer com o numero de objetos persistidos.
     */
    public Integer getCount();
    
}