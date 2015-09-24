/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;


/**
 *
 * @author Guillaume
 */
public class Vrachtwagen extends Voertuig implements Laadbaar{
    //declarations
    private static final long serialVersionUID = 1L;
    private Volume laadVolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    private static final byte maxZitPlaatsen = 3;
    //constructor
    public Vrachtwagen(String merk, Datum datumEersteInGebruikname, 
            int aankoopPrijs, int zitPlaatsen, Volume volume, 
            int maximaalToegelatenMassa, int aantalAssen, Mens bestuurder, Mens... inzittenden) 
            throws MensException, IllegalArgumentException, VolumeException {
        
        super(merk, datumEersteInGebruikname, aankoopPrijs, 
                checkZitPlaatsen(zitPlaatsen), bestuurder, inzittenden);
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
        this.laadVolume = volume;
    }
    //validatie
    private static int checkZitPlaatsen(int zitPlaatsen) 
            throws IllegalArgumentException{
        if (zitPlaatsen > maxZitPlaatsen) {
            throw new IllegalArgumentException("Max aantal zitplaatsen (" 
                + maxZitPlaatsen + ") voor vrachtwagen overschreden");
        }    
        else return zitPlaatsen;
    }
    //setter & getter
    @Override
    public Volume getLaadvolume() {
        return this.laadVolume;
    }
    @Override
    public void setLaadvolume(Volume volume) throws VolumeException {
        this.laadVolume = volume;
    }
    public int getMaximaalToegelatenMassa() {
        return this.maximaalToegelatenMassa;
    }
    
    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }
    
    public int getAantalAssen() {
        return this.aantalAssen;
    }
    
    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }
    
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen()  {
        return new Rijbewijs[]{Rijbewijs.C, Rijbewijs.CE};
    }
    
    @Override
    public String toString() {
        return super.toString()
                + String.format(" assen:%s, maximaal toegelaten massa:%s, laadvolume:%s", 
                        this.aantalAssen, this.maximaalToegelatenMassa, this.laadVolume);
    }
}
