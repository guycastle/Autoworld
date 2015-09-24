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
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;

/**
 *
 * @author Guillaume
 */
public class Pickup extends Personenwagen implements Laadbaar {
    //declaration
    private static final long serialVersionUID = 1L;
    private Volume laadVolume;
    //constructor
    public Pickup(String merk, Datum datumEersteInGebruikname, int aankoopPrijs, 
            int zitPlaatsen, Color kleur, Volume volume, Mens bestuurder,
            Mens... inzittenden) throws VolumeException {
        
        super(merk, datumEersteInGebruikname, aankoopPrijs, zitPlaatsen, kleur, bestuurder, inzittenden);
        this.laadVolume = volume;
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
    
    @Override
    public String toString() {
        return super.toString() + " " + this.laadVolume.toString();
    }
    
    @Override
    public Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.BE, Rijbewijs.CE, Rijbewijs.DE};
    }
}
