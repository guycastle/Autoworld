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
public enum Maat {
    centimeter(1), decimeter(1000), meter(1000000);
    
    private static final long serialVersionUID = 1L;
    private final int magnitude;
    
    private Maat(int magnitude) {
        this.magnitude = magnitude;
    }
    
    public int getMagnitude() {
        return this.magnitude;
    }
} 
