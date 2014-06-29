/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ResourceBundle;

/**
 *
 * @author Pedro Assis
 */
public class CaptionUtils {
    
    private static ResourceBundle CAPTIONS;
    
    static {
        try {
            CAPTIONS = ResourceBundle.getBundle("Language");
        } catch (Throwable throwable){
            System.err.println("Could not load i18n file");
            throwable.printStackTrace();
            System.exit(1);
        }
    }
    
    public static String getString(String key){
        String value = key;
        try {            
            value = CAPTIONS.getString(key);
        } catch (Exception e) {
            System.err.println("Could not find key " + key);
        }
        return value;
    }
}
