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

package controller;

import static com.googlecode.javacv.cpp.opencv_core.IplImage;
import detection.FaceDetection;
import entity.Funcionario;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import recog.FaceRecognizer;

/**
 * @since 02/06/2013, 21:53:20
 * @author pedro_ha@yahoo.com.br
 */
public class RecognizerController {
    
    private final static FaceRecognizer recognizer = FaceRecognizer.getInstance();
    
    private static final FaceDetection  DETECTION = new FaceDetection();

    public Serializable recognize(Object foto){
        return recognizer.recognize(foto);
    }
    
    public Object getBiggestFace(Object foto){
        return DETECTION.detectFace((IplImage) foto);
    }
    
    public void learn(Serializable entity){
        recognizer.learnFace(((Funcionario) entity).getId());
    }
    
    public BufferedImage highLightFace(Object foto){
        return DETECTION.highLightFace((IplImage)foto);
    }
    
}