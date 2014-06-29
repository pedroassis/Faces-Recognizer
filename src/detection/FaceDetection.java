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

import com.googlecode.javacpp.Loader;
import static com.googlecode.javacv.cpp.opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING;
import com.googlecode.javacv.cpp.opencv_objdetect.*;
import com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;
import static com.googlecode.javacv.cpp.opencv_core.CV_FONT_HERSHEY_PLAIN;
import static com.googlecode.javacv.cpp.opencv_core.cvRectangle;
import static com.googlecode.javacv.cpp.opencv_core.cvSetImageROI;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvEqualizeHist;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;
import com.googlecode.javacv.cpp.opencv_objdetect;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @since 16/03/2013, 19:52:20
 * @author pedro_ha@yahoo.com.br
 */
public class FaceDetection {
    
    private static BufferedImage CROSS;
    
    private static final String ROOT = new java.io.File("").getAbsolutePath();
    private static final String CASCADE_PATH = ROOT + "\\data\\haarcascades\\haarcascade_frontalface_alt_tree.xml";
    
    private final CvUtils cvUtils = new CvUtils();
    public static final CvHaarClassifierCascade CASCADE_FILE = new CvHaarClassifierCascade(cvLoad(CASCADE_PATH));

    static {
        try {
            CROSS = ImageIO.read(new File(ROOT + "\\crosshair.png"));
        } catch (IOException ex) {
            Logger.getLogger(FaceDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public IplImage detectFace(IplImage image) {
        CvSeq faces;
        Loader.load(opencv_objdetect.class);
        IplImage grayImage;
        IplImage detectedFace = null;
        try {
            grayImage = IplImage.create(image.width(), image.height(), IPL_DEPTH_8U, 1);
            cvCvtColor(image, grayImage, CV_BGR2GRAY);
            CvMemStorage storage = CvMemStorage.create();
            faces = cvHaarDetectObjects(
                    grayImage
                    , CASCADE_FILE
                    , storage, 1.1
                    , 3
                    , CV_HAAR_DO_CANNY_PRUNING
                    , new CvSize((int)(grayImage.width() * 0.25), (int)(grayImage.height() * 0.25))
                    , new CvSize(0, 0)
                    );
            CvRect r = null;
            if (faces.total() == 1) {
                r = new CvRect(cvGetSeqElem(faces, 0));
            } else if (faces.total() > 1) {
                int[] detectedFaces = new int[faces.total()];

                for (int i = 0; i < faces.total(); i++) {
                    r = new CvRect(cvGetSeqElem(faces, i));
                    detectedFaces[i] = r.height() + r.width();
                }

                int maxValue = detectedFaces[0];
                int index = 0;
                for (int i = 1; i < detectedFaces.length; i++) {
                    if (detectedFaces[i] > maxValue) {
                        maxValue = detectedFaces[i];
                        index = i;
                    }
                }
                
                r = new CvRect(cvGetSeqElem(faces, index));

            }

            if (r != null) {
                detectedFace = processImage(image, r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detectedFace;
    }
    
    public IplImage processImage(IplImage image, CvRect r) {
        IplImage gray = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
        IplImage returnImg = IplImage.create(150, 200, IPL_DEPTH_8U, 1);
        CvRect r1 = getProportional(r);
        cvCvtColor(image, gray, CV_BGR2GRAY);
        cvSetImageROI(gray, r1);
        IplImage roi = IplImage.create(r1.width(), r1.height(), IPL_DEPTH_8U, 1);
        cvResize(gray, roi, CV_INTER_LINEAR);
        cvEqualizeHist(roi, roi);
        cvResize(roi, returnImg, CV_INTER_LINEAR);
        return returnImg;
    }

    public BufferedImage highLightFace(IplImage originalImage) {
        IplImage highLighted = originalImage.clone();
        
        IplImage grayImage = IplImage.create(highLighted.width(), highLighted.height(), IPL_DEPTH_8U, 1);

        cvCvtColor(highLighted, grayImage, CV_BGR2GRAY);

        CvMemStorage storage = CvMemStorage.create();

        CvSeq faces = cvHaarDetectObjects(
                    grayImage
                    , CASCADE_FILE
                    , storage, 1.1
                    , 3
                    , CV_HAAR_DO_CANNY_PRUNING
                    , new CvSize((int)(grayImage.width() * 0.15), (int)(grayImage.height() * 0.15))
                    , new CvSize(0, 0)
                    );
        CvRect r;
        
        BufferedImage b = originalImage.getBufferedImage();
        
        for (int i = 0; i < faces.total(); i++) {
            r = new CvRect(cvGetSeqElem(faces, i));
            
//            cvSetImageROI(newi, r);
//            cvCopy(cross, newi);
//            cvResetImageROI(newi);
            
            overlayImages(b, CROSS, r);
            
//            cvRectangle(highLighted, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CvScalar.BLUE, 1, CV_AA, 0);
        }
        return b;
    }

    public IplImage putName(IplImage originalImage, String nome) {
        IplImage highLighted = originalImage.clone();
        
        IplImage grayImage = IplImage.create(highLighted.width(), highLighted.height(), IPL_DEPTH_8U, 1);

        cvCvtColor(highLighted, grayImage, CV_BGR2GRAY);

        CvMemStorage storage = CvMemStorage.create();

        CvSeq faces = cvHaarDetectObjects(
                    grayImage
                    , CASCADE_FILE
                    , storage, 1.1
                    , 3
                    , CV_HAAR_DO_CANNY_PRUNING
                    , new CvSize((int)(grayImage.width() * 0.15), (int)(grayImage.height() * 0.15))
                    , new CvSize(0, 0)
                    );
        
        CvRect r;
        
        if (faces.total() == 1) {
            r = new CvRect(cvGetSeqElem(faces, 0));
        } else if (faces.total() > 1) {
            int[] detectedFaces = new int[faces.total()];

            for (int i = 0; i < faces.total(); i++) {
                r = new CvRect(cvGetSeqElem(faces, i));
                detectedFaces[i] = r.height() + r.width();
            }

            int maxValue = detectedFaces[0];
            int index = 0;
            for (int i = 1; i < detectedFaces.length; i++) {
                if (detectedFaces[i] > maxValue) {
                    maxValue = detectedFaces[i];
                    index = i;
                }
            }

            r = new CvRect(cvGetSeqElem(faces, index));

        } else {
            return originalImage;
        }
        
        CvFont font = new CvFont(CV_FONT_HERSHEY_PLAIN, 2, 2, 0, 1, 8);
        cvPutText(originalImage, nome, cvPoint(r.x() +10, r.y() +10), font, CvScalar.RED);
        
        r = getProportional(r);
        
        cvRectangle(originalImage, cvPoint(r.x(), r.y()), cvPoint(r.x() + r.width(), r.y() + r.height()), CvScalar.BLUE, 1, CV_AA, 0);

        return originalImage;
    }

    public CvRect getProportional(CvRect rect) {
        double proportion = 0.75;

        int height = rect.height();
        int width = rect.width();

        double actualProportion = width / height;

        double value = actualProportion - proportion;

        if (width >= height) {
            width = width - (int) (width * value);
        } else {
            height = width + width / 3;
        }

        width -= width * 0.20;
        height -= height * 0.20;

        int heightDistance = height - rect.height();
        int widthDistance = width - rect.width();

        int x = rect.x() - widthDistance / 2;
        int y = rect.y() - heightDistance;
        
        return new CvRect(x, y, width, height);
    }
    
    private BufferedImage overlayImages(BufferedImage bgImage,
            BufferedImage fgImage, CvRect r) {

        Graphics2D g = bgImage.createGraphics();
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(bgImage, 0, 0, null);

        g.drawImage(fgImage, r.x(), r.y(), r.width(), r.height(), null);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));
        
        g.dispose();
        return bgImage;
    }
//    CanvasFrame f2 = new CanvasFrame(CV_VERSION);
//    CanvasFrame canvasFrame = new CanvasFrame("");
    public IplImage eyeDetection(IplImage face){
        
        CvPoint leftEye;
        CvPoint rightEye;
        
        cvUtils.find(face);
        
        leftEye = cvUtils.getLeftEyePoint();
        rightEye = cvUtils.getRightEyePoint();
        
//        cvRectangle(face, cvPoint(rectright.x(), rectright.y()), cvPoint(rectright.x() + rectright.width()
//                , rectright.y() + rectright.height()), CvScalar.BLUE, 1, CV_AA, 0);
//        
//        cvRectangle(face, cvPoint(rectLeft.x(), rectLeft.y()), cvPoint(rectLeft.x() + rectLeft.width()
//                , rectLeft.y() + rectLeft.height()), CvScalar.BLUE, 1, CV_AA, 0);
        
	float distanceY = leftEye.y() - rightEye.y();
	float distanceX = leftEye.x() - rightEye.x();
        
        double baseAngle = Math.atan2(distanceX, distanceY);
        
	double angle = baseAngle * 180 / Math.PI;
	angle = angle + ((180 - angle) / 2);        

//        f2.showImage(face);
        CvPoint center = cvPoint(face.width() / 2, face.height() / 2);
//            canvasFrame.showImage(face);
        if (angle < -45.) {
            angle += 90.0;
        }
        face = cvUtils.rotateWithQuadrangle(face, angle, center);


        return face;
    }
    
    private void draw(IplImage image, CvPoint point, CvPoint point2){
        cvLine(
                image,
                point,
                point2,
                CvScalar.RED,
                8, 8, 0
                );
    }
    
    private void draw(IplImage image, CvPoint point){
        		cvLine(
			image,
			cvPoint(point.x()-50, point.y()),
			cvPoint(point.x()+50, point.y()),
			CvScalar.RED,
			2, 2, 0
		);
		cvLine(
			image,
			cvPoint(point.x(), point.y()-50),
			cvPoint(point.x(), point.y()+50),
			CvScalar.RED,
			2, 2, 0
		);
    }
    
}
