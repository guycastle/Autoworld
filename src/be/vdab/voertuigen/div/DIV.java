/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

import java.text.DecimalFormat;

/**
 *
 * @author Guillaume
 */
public enum DIV {
    INSTANCE;
    private int numeralCounter = 1;
        
    public Nummerplaat getNummerplaat() {
        if (this.numeralCounter >= 1_000) this.numeralCounter = 1;
        return new Nummerplaat(String.format("AAA-%03d", this.numeralCounter++));    
    }    
}
