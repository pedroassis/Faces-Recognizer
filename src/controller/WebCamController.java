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

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*;
import java.awt.image.BufferedImage;

/**
 * @since 02/06/2013, 21:53:53
 * @author pedro_ha@yahoo.com.br
 */
public class WebCamController {
    
    private static final WebCamController INSTANCE = new WebCamController(1);
    
    public static WebCamController getDefaultInstance(){
        return INSTANCE;
    }

    public WebCamController() {
        initializeGrabber();
    }

    public WebCamController(int webcam) {
        this.webcamDevice = webcam;
        initializeGrabber();
    }
    
    private void initializeGrabber(){
        grabber = new OpenCVFrameGrabber(webcamDevice);
    }

    public void initWebCam(Runnable runnable){
        callback = runnable;
        continuePlaying = true;
        thread = new Thread(normalCapture);
        thread.start();
    }
    
    public void stopWebCam(){
        continuePlaying = false;
        if(thread != null && !thread.isInterrupted()){
            thread.interrupt();
        }
    }
    
    public void shutDownWebCam(){
        continuePlaying = false;
        if(thread != null && thread.isAlive()){
            thread.interrupt();
        }
        try {
            grabber.release();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter e Setters - running, webcamDevice, selectedImage">
    public boolean isRunning(){
        return continuePlaying;
    }

    public Integer getWebcamDevice() {
        return webcamDevice;
    }

    public void setWebcamDevice(Integer webcamDevice) {
        this.webcamDevice = webcamDevice;
        initializeGrabber();
    }

    public Object getSelectedImage() {
        return selectedImage;
    }

    public BufferedImage getBufferedImage() {
        return selectedImage.getBufferedImage();
    }
    
    //</editor-fold>    
    
    private final Runnable normalCapture = new Runnable() {
            @Override
            public void run() {
                try {
                    grabber.start();
                    while (continuePlaying) {
                        selectedImage = grabber.grab();
                        callback.run();
                    }
                    grabber.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    
    private FrameGrabber grabber;
    private boolean continuePlaying = false;
    private IplImage selectedImage;
    private Runnable callback;
    private Integer webcamDevice = 0;
    private Thread thread;
}