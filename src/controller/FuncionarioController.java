/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import entity.Funcionario;
import java.util.ArrayList;
import java.util.List;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import entity.Foto;
import java.io.File;
import model.EntityManager;

/**
 *
 * @author Pedro
 */
public class FuncionarioController {
    
    private Funcionario     funcionario = new Funcionario();
    private List<IplImage>  fotos = new ArrayList<IplImage>();
    private IplImage        fotoPrincipal;
    private EntityManager   manager = EntityManager.INSTANCE;
    
    private static final String PASTA_FOTOS = new java.io.File("").getAbsolutePath() + "\\fotos\\";
    
    public boolean save(){
        boolean resultado = manager.save(funcionario);
        int i = 0;
        File pastaFuncionario = new File(PASTA_FOTOS + funcionario.getId());
        pastaFuncionario.mkdir();
        for(IplImage foto : fotos){
            String path = PASTA_FOTOS + funcionario.getId() + "\\" + funcionario.getId() + "-" + i++ + ".png";
            cvSaveImage(path, foto);
            manager.save(new Foto(path, funcionario));
        }
        String path = PASTA_FOTOS + funcionario.getId() + "\\" + funcionario.getId() + ".png";
        cvSaveImage(path, fotoPrincipal);
        return resultado;
    }
    
    public boolean update(){
        boolean resultado = manager.update(funcionario);
        return resultado;
    }  
    
    public boolean delete(){
        boolean resultado = manager.delete(funcionario);
        return resultado;
    }
    
    public void addFoto(Object foto){
        fotos.add((IplImage) foto);
    }
    
    public void flush(){
        fotos.clear();
        funcionario = new Funcionario();
    }
    
    public List<Funcionario> getAll(){
        return manager.getAll(Funcionario.class);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter e Setters - funcionario">

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public List getFotos() {
        return fotos;
    }

    public void setFotos(List fotos) {
        this.fotos = fotos;
    }

    public Object getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(Object foto) {
        this.fotoPrincipal = (IplImage) foto;
    }
    
    //</editor-fold>
    
}
