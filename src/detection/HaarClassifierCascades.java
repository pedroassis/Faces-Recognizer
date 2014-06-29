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

package detection;

import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import com.googlecode.javacv.cpp.opencv_objdetect.*;

/**
 * @since 25/03/2013, 19:03:32
 * @author pedro_ha@yahoo.com.br
 */
public class HaarClassifierCascades {

    public static String CASCADE_DIR = new java.io.File("").getAbsolutePath() + "\\data\\haarcascades\\";
    
    public static final CvHaarClassifierCascade LEFT_EYE_CASCADE
            = new CvHaarClassifierCascade(cvLoad(CASCADE_DIR + "haarcascade_righteye_2splits.xml"));
    
    public static final CvHaarClassifierCascade RIGHT_EYE_CASCADE
            = new CvHaarClassifierCascade(cvLoad(CASCADE_DIR + "haarcascade_lefteye_2splits.xml"));
    
    public static final CvHaarClassifierCascade FRONTAL_FACE_ALT_TREE = 
            new CvHaarClassifierCascade(cvLoad(CASCADE_DIR + "haarcascade_frontalface_alt_tree.xml"));
    
}
