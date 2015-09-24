/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;

/**
 *
 * @author Guillaume
 */
public class Personenwagen extends Voertuig {
    //declaration
    private static final long serialVersionUID = 1L;
    private static final int maxZitPlaatsen = 8;

    //validatie
    private static int checkZitPlaatsen(int zitPlaatsen) throws IllegalArgumentException {
        if (zitPlaatsen > maxZitPlaatsen) throw new IllegalArgumentException("Max aantal zitplaatsen ("
                + maxZitPlaatsen + ") voor personenwagen overschreden");
        else return zitPlaatsen;
    }
    private Color kleur;
    //Constructor    
    public Personenwagen(String merk, Datum datumEersteInGebruikname, int aankoopPrijs, int zitPlaatsen, Color kleur, Mens bestuurder, Mens... inzittenden) {
        super(merk, datumEersteInGebruikname, aankoopPrijs, checkZitPlaatsen(zitPlaatsen), bestuurder, inzittenden);
        this.kleur = kleur;
    }
    //setter
    public void setKleur(Color kleur){
        this.kleur = kleur;
    }
    //getters
    public Color getKleur() {
        return this.kleur;
    }
    @Override
    public Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE, Rijbewijs.C, Rijbewijs.CE, Rijbewijs.D, Rijbewijs.DE};
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + this.getZitplaatsen();
    }
    
    
}
