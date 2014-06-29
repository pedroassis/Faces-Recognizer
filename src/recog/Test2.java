package recog;

import dao.GenericDAO;
import entity.Funcionario;
import java.util.List;

/**
 *
 * @author chamila
 */
public class Test2 {

    public static void main(String[] args) throws ClassNotFoundException {
        
        Class.forName("dao.FuncionarioDAO");
        GenericDAO dao  = GenericDAO.instances.get(Funcionario.class);
        
        for (int i = 0; i < 5; i++) {
            dao.save(new Funcionario("" + i, "" + i, "" + i));
        }
               
        List<Funcionario> funcionarios = dao.getAll();
        for (Funcionario funcionario : funcionarios) {
            System.out.print("id:\t" + funcionario.getId());
            System.out.println("\tnome:\t" + funcionario.getNome());
        }
    }
}