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

import com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_core.CV_32F;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSeqElem;
import com.googlecode.javacv.cpp.opencv_objdetect.*;
import static com.googlecode.javacv.cpp.opencv_objdetect.CV_HAAR_DO_CANNY_PRUNING;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;

/**
 * @since 23/03/2013, 20:02:06
 * @author pedro_ha@yahoo.com.br
 */
public class CvUtils {
    
    private CvPoint leftEyePoint;
    
    private CvPoint rightEyePoint;
    
    private IplImage leftEye;
    
    private IplImage rightEye;
    
    private CvRect rightEyeRect;
    
    private CvRect leftEyeRect;
    
    public void find(IplImage processedFace){
        
        CvMemStorage storage = CvMemStorage.create();
        
        CvSeq detectedRightEye = cvHaarDetectObjects(
                    processedFace
                    , HaarClassifierCascades.RIGHT_EYE_CASCADE
                    , storage, 1.1
                    , 4
                    , CV_HAAR_DO_CANNY_PRUNING
                    , new CvSize(40, 25)
                    , new CvSize(0, 0)
                    );
        
        rightEyeRect = new CvRect(cvGetSeqElem(detectedRightEye, 0));
        
        CvSeq detectedLeftEye = cvHaarDetectObjects(
                    processedFace
                    , HaarClassifierCascades.LEFT_EYE_CASCADE
                    , storage, 1.1
                    , 4
                    , CV_HAAR_DO_CANNY_PRUNING
                    , new CvSize(40, 25)
                    , new CvSize(0, 0)
                    );
        
        leftEyeRect = new CvRect(cvGetSeqElem(detectedLeftEye, 0));
        
        this.leftEyePoint = getRectCenterPoint(leftEyeRect);
        
        this.rightEyePoint = getRectCenterPoint(rightEyeRect);
        
    }
    
    private CvPoint getRectCenterPoint(CvRect roi){
        return new CvPoint(
			Math.round(roi.x() + roi.width()/2),
			Math.round(roi.y() + roi.height()/2)
		);
    }
    
    public IplImage rotateWithQuadrangle(IplImage src, double angle, CvPoint center) {
        
	float m[] = new float[6];
	CvMat M = CvMat.create(2, 3, CV_32F);
        int w = src.width();
        int h = src.height();
	double angleRadians = -angle * 2 * Math.PI / 180f;

	m[0] = (float)( Math.cos(angleRadians) );
	m[1] = (float)( Math.sin(angleRadians) );
	m[3] = -m[1];
	m[4] = m[0];
	m[2] = center.x();
	m[5] = center.y();
        
        M.put(0, m[0]);
        M.put(1, m[1]);
        M.put(2, m[2]);
        M.put(3, m[3]);
        M.put(4, m[4]);
        M.put(5, m[5]);
        
        CvSize sizeRotated = new CvSize();
        sizeRotated.width(Math.round(w));
        sizeRotated.height(Math.round(h));
        
        IplImage imageRotated = IplImage.create(sizeRotated, src.depth(), src.nChannels());
        
	cvGetQuadrangleSubPix(src, imageRotated, M);
    
        return imageRotated;
    }

    public CvPoint getLeftEyePoint() {
        return leftEyePoint;
    }

    public void setLeftEyePoint(CvPoint leftEyePoint) {
        this.leftEyePoint = leftEyePoint;
    }

    public CvPoint getRightEyePoint() {
        return rightEyePoint;
    }

    public void setRightEyePoint(CvPoint rightEyePoint) {
        this.rightEyePoint = rightEyePoint;
    }

    public IplImage getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(IplImage leftEye) {
        this.leftEye = leftEye;
    }

    public IplImage getRightEye() {
        return rightEye;
    }

    public void setRightEye(IplImage rightEye) {
        this.rightEye = rightEye;
    }

    public CvRect getRightEyeRect() {
        return rightEyeRect;
    }

    public void setRightEyeRect(CvRect rightEyeRect) {
        this.rightEyeRect = rightEyeRect;
    }

    public CvRect getLeftEyeRect() {
        return leftEyeRect;
    }

    public void setLeftEyeRect(CvRect leftEyeRect) {
        this.leftEyeRect = leftEyeRect;
    }
}
