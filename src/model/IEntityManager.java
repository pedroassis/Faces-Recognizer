/*
 * Todos os direitos reservados ao autor pedro_ha@yahoo.com.br
 * Este programa deve ser distribuido junto à sua licença
 * 
 * Aviso: 
 * Este programa de computador é protegido por leis de direitos autorais.
 * Qualquer reprodução, distribuição ou modificação não autorizada deste
 * programa de computador, em sua totalidade ou parcial, resultará em
 * severas punições civis e criminais, e os infratores serão punidos sob a
 * máxima extensão possível dentro da lei. 
 * Este programa de computador é distribuído sem nenhuma garantia
 * implícita ou explicita, em nenhum caso o licenciante será responsável
 * por quaisquer danos diretos, indiretos, incidental, incluindo, mas não 
 * limitado, a perda de uso, dados, lucros ou interrupções de negócios.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Interface para uma classe de CRUD generica.
 * @author pedro_ha@yahoo.com.br
 * @see List
 * @see List#add(java.lang.Object)
 */
public interface IEntityManager {
    
    /**
     * Retorna uma lista de todos objetos persistidos da classe designada.
     * @param classe Classe dos objetos a serem buscados.
     * @return <tt>List {@link List}</tt>.
     */
    public <T> List <T> getAll(Class<T> classe);
    
    /**
     * Retorna uma lista de objetos persistidos da classe designada entre o inicio (start)
     * e o fim (offset).
     * Este método deve ser usado para paginação e para reduzir a utilização do banco de dados.
     * @param classe Classe dos objetos a serem buscados.
     * @param start Valor 0 para começar do 1º objeto.
     * @param offset Valor valor que limita a quantidade de objetos retornados.
     * @return <tt>List {@link List}</tt>.
     */
    public <T> List <T> getAll(Class<T> classe, int start, int offset);
    
    /**
     * Retorna um objeto persistido com a id desiginada se encontrado.
     * @param classe Classe do objeto a ser buscado.
     * @param id Id <tt>{@link Object}</tt> do objeto procurado.
     * @return Se encontrado, um objeto da classe designada
     * <tt>casted on {@link classe}</tt>.
     * <tt>List {@link Object}</tt>.
     */    
    public <T> T getById(Class<T> classe, Serializable id);
    
    /**
     * Remove o objeto da persistencia se o mesmo existir.
     * @param entity Objeto a ser removido.
     */
    public boolean delete(Serializable entity);
    
    /**
     * Persiste ou atualiza o objeto para uso futuro.
     * @param entity Objeto a ser persistido.
     * @return <tt>true</tt> Se o objeto foi persistido com sucesso.
     */
    public boolean merge(Serializable entity);
    
    /**
     * Persiste o objeto para uso futuro.
     * @param entity Objeto a ser persistido.
     * @return <tt>true</tt> Se o objeto foi persistido com sucesso.
     */
    public boolean save(Serializable entity);
    
    /**
     * Aualiza um objeto já persistido para uso futuro.
     * @param entity Objeto a ser atualizado.
     * @return <tt>true</tt> Se o objeto foi atualizado com sucesso.
     */
    public boolean update(Serializable entity);
    
    /**
     * Retorna uma lista de objetos persistidos da classe especificada de acordo
     * com os parametro de busca.
     * 
     * @param classe Classe das entidades a serem recuperadas.
     * Exemplo: Cliente.class
     * @param propriedade Parametro para busca dos objetos.
     * Exemplo: "nome".
     * @param valor Valor a ser procurado como parametro.
     * Exemplo: "joao".
     * Retornando assim todos as entidades da Classe Cliente que tenham o atributo
     * nome = "joao".
     * @return Uma lista <tt>{@link List}</tt> de objetos tipo classe.
     * 
     */
    public <T> List <T> getByParameter(Class<T> classe, String propriedade, Object valor);
    
    public <T> List <T> getByParameter(Class<T> type, String propriedade, Object valor, int start, int offset);
    
    /**
     * Retorna uma lista de objetos persistidos com a expressão Between.
     * 
     * @param classe Classe da entidade a ser recuperada.
     * @param propriedade Propriedade da entidade a ser usada como parametro de busca
     * @param inicio Data de inicio para pesquisar as entidades.
     * @param fim Data fim para pesquisar as entidades.
     * @return Retorna uma lista de objetos persistidos da classe especificada.
     */
    public <T> List <T> getAllBetween(Class<T> classe, String propriedade, Date inicio, Date fim);
    
    /**
     * Retorna uma lista de objetos persistidos da classe especificada de acordo
     * com os parametro de busca.
     * 
     * @param classe Classe das entidades a serem recuperadas.
     * @param propriedade Parametro para busca dos objetos. Exemplo: "nome".
     * @param valor Valor a ser procurado como parametro.
     * Exemplo: "joao".
     * Este valor pode conter uma expressão like.
     * Exemplo: "joao%"
     * @return Retorna uma lista de objetos persistidos da classe especificada.
     */    
    public <T> List <T> getAllLike(Class<T> classe, String propriedade, Object valor);
    
    /**
     * Retorna o numero de objetos persistidos desta classe.
     * @param classe Classe dos objetos persistentes a serem contados.
     * @return Um Integer com o numero de objetos persistidos.
     */
    public Integer getCount(Class classe);

}