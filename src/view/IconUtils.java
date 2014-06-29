/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class IconUtils {
    
    private static List<Image> iconPrincipal;
    private static Image icon16;
    private static Image icon32;
    private static Image icon64;
    private static Image icon128;
    private static Map<String, Icon> icons = new HashMap<String, Icon>();
    
    static {
        iconPrincipal = new ArrayList();
        try {
            icon16 = ImageIO.read(new File("icons/icon16.png"));
            icon32 = ImageIO.read(new File("icons/icon32.png"));
            icon64 = ImageIO.read(new File("icons/icon64.png"));
            icon128 = ImageIO.read(new File("icons/icon128.png"));
            iconPrincipal.add(icon16);
            iconPrincipal.add(icon32);
            iconPrincipal.add(icon64);
            iconPrincipal.add(icon128);
            
            icons.put("cadastro", new ImageIcon("icons/cadastro.png"));
            icons.put("off", new ImageIcon("icons/off.png"));
            icons.put("funcionarios", new ImageIcon("icons/funcs.png"));
            icons.put("registros", new ImageIcon("icons/registros.png"));
            
        } catch (Exception ex) {
        }
    }
    
    public static Icon getIcon(String nome){
        return icons.get(nome);
    }
    
    public static List<Image> getIcons(){
        return iconPrincipal;
    }
}
