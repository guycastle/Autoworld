/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author Guillaume
 */
public class Datum implements Comparable<Datum>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final int yearMin = 1_583;
    private static final int yearMax = 4_099;
    private static final int dayMin = 1;
    private static final int monthMin = 1;
    private static final int monthMax = 12;
    protected int jaar;
    protected int maand;
    protected int dag;
    //default constructor
    public Datum() {
        this.jaar = 1_583;
        this.maand = 1;
        this.dag = 1;
    }
    //constructor met parameters
    public Datum(int dag, int maand, int jaar) throws DatumException {
        if (this.checkDatum(dag,maand,jaar)) {
            this.jaar = jaar;
            this.maand = maand;
            this.dag = dag;
        }
    }

    public int getDag() {
        return this.dag; 
    }

    public int getMaand() {
        return this.maand;
    }

    public int getJaar() {
        return this.jaar;
    }
    //Validatie  
    private boolean checkDatum(int dag, int maand, int jaar) throws DatumException {
        boolean result = false;
        if (jaar < yearMin || jaar > yearMax || maand < monthMin 
                || maand > monthMax || dag < dayMin || dag > this.getMaxDays(maand, jaar)) 
                    throw new DatumException("Ongeldige datum");
        else result = true;
        return result;
    }    
    //geeft het max aantal dagen in een bepaald maand van een bepaald jaar terug
    private int getMaxDays(int maand, int jaar) {
        switch (maand) {
            case 2:
                return isLeapYear(jaar) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;                    
            default:
                return 31; 
        }
    }        
    //bepaalt of een bepaald jaar een schrikkeljaar is
    private boolean isLeapYear(int jaar) {
        return ( ( (jaar % 4 == 0) && (jaar % 100 != 0) ) || (jaar % 400 == 0) );
    }
    
    @Override //override van de toString method
    public String toString() {
        return String.format("%02d/%02d/%04d",dag,maand,jaar);
    }
    
    @Override //override van de equals method
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Datum) {
            Datum dtm = (Datum) obj;
            result = ((this.jaar == dtm.jaar) && (this.maand == dtm.maand) 
                    && (this.dag == dtm.dag));
        }
        return result;
    }
    private int toInt() {
        return 1_0000*jaar+100*maand+dag;
    }

    @Override //override hash
    public int hashCode() {
        return this.jaar* 31 * 12 + this.maand * 31 + this.dag - yearMin * 31 * 12 - 31;
    }
    
    @Override //override compareTo
    public int compareTo(Datum dtm) {
        return toInt() - dtm.toInt();
    }
}
