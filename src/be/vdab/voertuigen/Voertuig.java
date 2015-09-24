/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Guillaume
 */
public abstract class Voertuig implements Serializable, Comparable<Voertuig> {
    //declarations
    private static final long serialVersionUID = 1L;
    private final Nummerplaat nummerplaat = DIV.INSTANCE.getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruiknaam;
    private int aankoopprijs;
    private int zitplaatsen;
    private Mens bestuurder;
    private Set<Mens> ingezetenen = new TreeSet<>();
    
    //constructor
    public Voertuig(String merk, Datum datum, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... ingezetenen) throws MensException {
        if (zitplaatsen < 1) throw new IllegalArgumentException("Voertuig moet over minstens 1 zitplaats beschikken.");
        else {
            Set<Mens> temp = new TreeSet<>(Arrays.asList(ingezetenen));
            temp.add(bestuurder);
            if (checkDriversLicense(bestuurder) && checkForRoom(zitplaatsen, temp)) {
                this.ingezetenen = temp;
                this.bestuurder = bestuurder;
                this.zitplaatsen = zitplaatsen;
                this.merk = merk;
                this.aankoopprijs = aankoopprijs;
                this.datumEersteIngebruiknaam = datum;
            }
        }    
    }
    //getters
    protected abstract Rijbewijs[] getToegestaneRijbewijzen();
    
    public String getMerk() {
        return merk;
    }
    
    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruiknaam;
    }
    
    public int getAankoopprijs() {
        return aankoopprijs;
    }
    
    public int getZitplaatsen() {
        return zitplaatsen;
    }
    
    public Mens getBestuurder() {
        return bestuurder;
    }
    
    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }
    public Set<Mens> getIngezetenen() {
        return Collections.unmodifiableSet(ingezetenen);
    }
    
    public Set<Mens> getIngezeteneExclusiefBestuurder() {
        Set<Mens> temp = new TreeSet(ingezetenen);
        temp.remove(bestuurder);
        return Collections.unmodifiableSet(temp);
    }
    
    public interface SerializableComparator<T> extends Serializable, Comparator<T>{};
    
    public static SerializableComparator<Voertuig> getAankoopprijsComparator() {
        return (SerializableComparator<Voertuig>) (Voertuig vrtg1, Voertuig vrtg2) -> vrtg1.getAankoopprijs() - vrtg2.getAankoopprijs();
    }
    
    public static SerializableComparator<Voertuig> getMerkComparator() {
        return (SerializableComparator<Voertuig>) (Voertuig vrtg1, Voertuig vrtg2) -> vrtg1.getMerk().compareTo(vrtg2.getMerk());
    }
    
//    public static Comparator<Voertuig> getMerkComparator() {
//        class ComparatorMerk implements Serializable, Comparator<Voertuig> {
//            private static final long serialVersionUID = 1L;
//            @Override
//            public int compare(Voertuig v1, Voertuig v2) {
//                if (v1.merk.equalsIgnoreCase(v2.merk)) return v1.compareTo(v2);
//                else return v1.merk.compareToIgnoreCase(v2.merk);
//            }   
//        } 
//        return new ComparatorMerk();
//    }
    //setters
    public void setAankoopprijs(int aankoopprijs) {
        this.aankoopprijs = aankoopprijs;
    }
    
    public void setDatumEersteIngebruikname(Datum datum) {
        this.datumEersteIngebruiknaam = datum;
    }
    
    public void setMerk(String merk) {
        this.merk = merk;
    }
    
    public void setBestuurder(Mens bestuurder) throws MensException {        
        Set<Mens> temp = new TreeSet<>(getIngezetenen());
        temp.add(bestuurder);
        if (checkForRoom(zitplaatsen, temp) && checkDriversLicense(bestuurder)) {
            ingezetenen = temp;
            this.bestuurder = bestuurder;
        }    
    }
    
    public void addIngezetene(Mens mens) throws MensException {
        if (!isIngezetene(mens)) {
            if (checkForRoom(zitplaatsen - 1, ingezetenen)) ingezetenen.add(mens);
        }
    }
    //equals, hash & compare & isIngezetene
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Voertuig) {
            Voertuig voertuig = (Voertuig) obj;
            return this.nummerplaat.equals(voertuig.nummerplaat);
        }
        else return false;
    }
    
    @Override
    public int hashCode() {
        return this.nummerplaat.hashCode();
    }
    
    @Override
    public int compareTo(Voertuig voertuig) {
        return this.nummerplaat.compareTo(voertuig.nummerplaat);
    }
    
    public boolean isIngezetene(Mens mens) {
        return (getIngezetenen().contains(mens));
    }
    
    @Override //toString
    public String toString() {
        String temp = " " + getIngezeteneExclusiefBestuurder();
        if (getIngezeteneExclusiefBestuurder().isEmpty()) temp = "";
        return String.format("%s %s %s %s %s%s", nummerplaat, merk, datumEersteIngebruiknaam, aankoopprijs, bestuurder, temp);
        
    }
    //private validation methods
    private boolean checkForRoom(int zitplaatsen, Set<Mens> mensen) {
        boolean room = false;
        
        if (mensen.size() <= zitplaatsen) room = true;
        else throw new MensException("Maximaal aantal ingezetenen overschreden");
                
        return room;
    }
    
    private boolean checkDriversLicense(Mens bestuurder) {
        boolean validLicense = false;
        Set<Rijbewijs> temp = new TreeSet<>(Arrays.asList(bestuurder.getRijbewijs()));
        Set<Rijbewijs> temp2 = new TreeSet<>(Arrays.asList(getToegestaneRijbewijzen()));
        
        Iterator<Rijbewijs> itTemp = temp.iterator();
        while (!validLicense && itTemp.hasNext()) {
            if (temp2.contains(itTemp.next())) validLicense = true;
        }
        
        if (!validLicense) throw new MensException("Bestuurder met een geldig rijbewijs hebben");
        return validLicense;
    }
}
