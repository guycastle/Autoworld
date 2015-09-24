/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;

/**
 *
 * @author Guillaume
 */
public class Volume implements Comparable<Volume>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private final int hoogte;
    private final int breedte;
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) {
        if (((long) hoogte)*breedte*diepte < 0) throw new VolumeException("Volume mag niet negatief zijn");
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.diepte = diepte;
        this.maat = maat;    
    }
    
    public long getVolume() {
        return ((long)hoogte )* breedte * diepte * this.maat.getMagnitude();
    }
    
    private double getVolumeInMaat(Maat maat) { 
            return getVolume()/maat.getMagnitude();
    }
    //getters
    public int getDiepte() {
        return this.diepte;
    }
    
    public int getHoogte() {
        return this.hoogte;
    }
    
    public int getBreedte()  {
        return this.breedte;
    }
    
    public Maat getMaat() {
        return this.maat;
    }
    
    @Override
    public String toString() {
        return String.format("%s(h)x%s(b)x%s(d) %s", this.hoogte, this.breedte, this.diepte, this.maat.toString());
    }
    
    @Override 
    public boolean equals(Object obj) {
        if (obj instanceof Volume) {
            Volume vol = (Volume) obj;
            return this.getVolume() == vol.getVolume();
        }
        else return false;
    }
    
    @Override
    public int hashCode() {
        String tmp = String.format("%s%s%s", hoogte*maat.getMagnitude(),breedte*maat.getMagnitude(),diepte*maat.getMagnitude());
        return Integer.parseInt(tmp);
    }
    
    @Override
    public int compareTo(Volume vol) {
        return ((Long)this.getVolume()).compareTo(vol.getVolume());
    } 
}
