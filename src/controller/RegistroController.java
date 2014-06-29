/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.GenericDAO;
import dao.RegistroDAO;
import entity.Funcionario;
import entity.Registro;
import java.util.List;
import model.EntityManager;

/**
 *
 * @author Carlos
 */
public class RegistroController {
    
    private EntityManager   manager = EntityManager.INSTANCE;
    private RegistroDAO     dao     = (RegistroDAO) GenericDAO.instances.get(Registro.class);
    
    public List<Registro> getAll(){
        return manager.getAll(Registro.class);
    }
    
    public List<Registro> getByParameter(String propriedade, Object valor){
        return manager.getByParameter(Registro.class, propriedade, valor);
    }
    
    public Registro getLast(Funcionario funcionario){
        return dao.getLast(funcionario);
    }
    
    public boolean save(Registro registro){
        return manager.save(registro);
    }
}
