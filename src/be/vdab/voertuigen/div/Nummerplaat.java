/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

import java.io.Serializable;

/**
 *
 * @author Guillaume
 */
public class Nummerplaat implements Comparable<Nummerplaat>, Serializable {
    //declarations
//    private static final long serialVersionUID = 1L;
    private final String plaat;
    //constructor
    Nummerplaat(String plaat) {
        this.plaat = plaat;
    }
    //getter
    public String getPlaat() {
        return this.plaat;
    }
    @Override
    public String toString() {
        return this.plaat;
    }
    @Override
    public final boolean equals(Object obj) {        
        return obj instanceof Nummerplaat && ((Nummerplaat) obj).plaat.equals(this.plaat);
    }
    @Override
    public int hashCode() {
        return this.plaat.hashCode();
    }
    @Override
    public int compareTo(Nummerplaat nrplaat) {
        return this.plaat.compareTo(nrplaat.plaat);
    }
    
    
}
