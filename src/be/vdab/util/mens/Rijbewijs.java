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
public enum Rijbewijs {
    A, B, BE, C, CE, D, DE;
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        if (super.toString().length() > 1) return super.toString().charAt(0) + "+" + super.toString().charAt(super.toString().length()-1);
        else return super.toString();
    }
}

