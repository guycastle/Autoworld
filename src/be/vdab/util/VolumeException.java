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
public class VolumeException extends RuntimeException {
    public VolumeException() {}
    
    public VolumeException(String descrip) {
        super(descrip);
    }
    
    public VolumeException(String descrip, Throwable cause) {
        super(descrip, cause);
    }
    
    public VolumeException(Throwable cause) {
        super(cause);
    }
    
 }
