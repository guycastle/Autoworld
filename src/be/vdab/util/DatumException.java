/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

/**
 *
 * @author Guillaume
 */
public class DatumException extends Exception {

    public DatumException() {}

    public DatumException(String descrip) {
        super(descrip);
    }
    
    public DatumException(String descrip, Throwable cause) {
        super(descrip, cause);
    }

    public DatumException(Throwable cause) {
        super(cause);
    }    
}
