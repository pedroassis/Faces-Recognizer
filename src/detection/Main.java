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

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;

/**
 * @since 17/03/2013, 11:20:51
 * @author pedro_ha@yahoo.com.br
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        IplImage foto = cvLoadImage("C:\\Users\\Pedro\\Desktop\\pedro0.jpg");
        
        FaceDetection detection = new FaceDetection();
        
        IplImage face = detection.detectFace(foto);
        
        face = detection.eyeDetection(face);
        
        CanvasFrame frame = new CanvasFrame("");
        
        frame.showImage(face);
    }

}
