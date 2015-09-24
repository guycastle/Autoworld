/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

/**
 *
 * @author Guillaume
 */
public class MensException extends RuntimeException {
    public MensException() {}
    
    public MensException(String descrip) {
        super(descrip);
    }
    
    public MensException(String descrip, Throwable cause) {
        super(descrip, cause);
    }
    
    public MensException(Throwable cause) {
        super(cause);
    }
    
}
