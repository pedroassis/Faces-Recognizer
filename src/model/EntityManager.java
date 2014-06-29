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

import dao.FotoDAO;
import dao.FuncionarioDAO;
import dao.GenericDAO;
import dao.RegistroDAO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @since 21/05/2013, 09:15:31
 * @author pedro_ha@yahoo.com.br
 */
public class EntityManager implements IEntityManager{
    
    public static final EntityManager INSTANCE = new EntityManager();
    private static final Map<Class, GenericDAO> DAOS = GenericDAO.instances;
    
    private EntityManager(){
        try {
            Class.forName(FuncionarioDAO.class.getName());
            Class.forName(FotoDAO.class.getName());
            Class.forName(RegistroDAO.class.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EntityManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
 * List<Funcionario> funcionarios = gerenciador.getAll(Funcionario.class);
 */
    @Override
    public <T> List<T> getAll(Class<T> classe) {
        return DAOS.get(classe).getAll();
    }

    @Override
    public <T> List<T> getAll(Class<T> classe, int start, int offset) {
        throw new UnsupportedOperationException("Ainda não implementado!");
    }

    @Override
    public <T> T getById(Class<T> classe, Serializable id) {
        return (T) DAOS.get(classe).getByID(id);
    }

    @Override
    public boolean delete(Serializable entity) {
        
        return DAOS.get(entity.getClass()).delete(entity);
        
    }

    @Override
    public boolean merge(Serializable entity) {
        throw new UnsupportedOperationException("Ainda não implementado!");
    }

    @Override
    public boolean save(Serializable entity) {
        return DAOS.get(entity.getClass()).save(entity);
    }

    @Override
    public boolean update(Serializable entity) {
        return DAOS.get(entity.getClass()).update(entity);
    }

    @Override
    public <T> List<T> getByParameter(Class<T> classe, String propriedade, Object valor) {
        return DAOS.get(classe).getByParameter(propriedade, valor);
    }

    @Override
    public <T> List<T> getByParameter(Class<T> type, String propriedade, Object valor, int start, int offset) {
        throw new UnsupportedOperationException("Ainda não implementado!");
    }

    @Override
    public <T> List<T> getAllBetween(Class<T> classe, String propriedade, Date inicio, Date fim) {
        throw new UnsupportedOperationException("Ainda não implementado!");
    }

    @Override
    public <T> List<T> getAllLike(Class<T> classe, String propriedade, Object valor) {
        throw new UnsupportedOperationException("Ainda não implementado!");
    }

    @Override
    public Integer getCount(Class classe) {
        return DAOS.get(classe).getCount();
    }

}
