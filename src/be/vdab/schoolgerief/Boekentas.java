/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Guillaume
 */
public class Boekentas implements Laadbaar, Comparable<Boekentas>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private Volume laadVolume;
    private String kleur;
    
    public Boekentas(String kleur, Volume volume) {
        if (checkForNull(volume)) this.laadVolume = volume;
        if (checkForNull(kleur)) this.kleur = kleur;
    }
    //Getters
    @Override
    public Volume getLaadvolume() {
        return this.laadVolume;
    }
    
    public String getKleur() {
        return kleur;
    }
    //setters
    @Override
    public void setLaadvolume(Volume volume) {
        if (checkForNull(volume)) this.laadVolume = volume;
    }
    
    public void setKleur(String kleur) {
        if (checkForNull(kleur)) this.kleur = kleur;
    }
    //equals, compare, tostring, etc
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Boekentas) {
            Boekentas temp = (Boekentas) obj;
            return ((this.laadVolume.equals(temp.laadVolume)) 
                    && this.kleur.equalsIgnoreCase(temp.kleur));
        }
        else return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.laadVolume);
        hash = 89 * hash + Objects.hashCode(this.kleur);
        return hash;
    }
    
    @Override
    public int compareTo(Boekentas bTas) {
        if (this.laadVolume.equals(bTas.laadVolume)) {
            return this.kleur.compareToIgnoreCase(bTas.kleur);
        }
        else return this.laadVolume.compareTo(bTas.laadVolume);
    }
    
    @Override
    public String toString() {
        return String.format("boekentas %s %s",kleur, laadVolume.toString());
    }
    //validatie
    private boolean checkForNull(Object obj) {
        if (obj == null) throw new IllegalArgumentException("Argument mag niet \"null\" zijn");
        else return true;
    }
}
