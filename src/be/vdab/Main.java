/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.VolumeException;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Guillaume
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Set<Voertuig> voertuigen = new TreeSet<>();
        try {
        voertuigen.add(new Personenwagen("Peugeot", new Datum(22, 2, 2_004), 12_000, 5, Color.LIGHT_GRAY, new Mens("Guillaume", Rijbewijs.A, Rijbewijs.B), new Mens("Aurore", Rijbewijs.CE)));
        voertuigen.add(new Personenwagen("Alfa-Romeo", new Datum(22,10, 1_981), 19_000, 6, Color.blue, new Mens("Andy", Rijbewijs.BE)));
        voertuigen.add(new Pickup("Dodge", new Datum(17, 3, 2_012), 30_000, 4, Color.ORANGE, new Volume(1, 2, 4, Maat.meter), new Mens("Wouter", Rijbewijs.BE, Rijbewijs.C)));
        voertuigen.add(new Pickup("SUV", new Datum(13, 10, 1_999), 23_000, 4, Color.blue, new Volume(2, 3, 5, Maat.meter), new Mens("Laurent", Rijbewijs.BE)));
        voertuigen.add(new Vrachtwagen("Peugeot", new Datum(31, 8, 2_009), 50_000, 2, new Volume (3, 5, 12, Maat.meter), 10_000, 3, new Mens("Koen", Rijbewijs.CE)));
        voertuigen.add(new Vrachtwagen("Mercedes", new Datum(29, 02, 2_000), 70_000, 3, new Volume(5, 6, 20, Maat.meter), 55_000, 6, new Mens("Gilles", Rijbewijs.C)));
        voertuigen.add(new Personenwagen("Volvo", new Datum(13, 7, 2_008), 21_000, 7, Color.white, new Mens("Emilie", Rijbewijs.B), new Mens("Milo"), new Mens("Vladi")));
        
        System.out.println("*** Test passagiers en bestuurders toevoegen ***");
        Voertuig bedrijfsWagen = new Personenwagen("Volvo", new Datum(13, 7, 2_008), 21_000, 7, Color.white, new Mens("Emilie", Rijbewijs.B), new Mens("Vladi"), new Mens("Milo"));
        System.out.println(bedrijfsWagen + "\n");
        bedrijfsWagen.setBestuurder(new Mens("Jonas", Rijbewijs.A, Rijbewijs.B));
        System.out.println(bedrijfsWagen + "\n");
        bedrijfsWagen.addIngezetene(new Mens("Annelies", Rijbewijs.B));
        }
        catch (DatumException | VolumeException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        
        Comparator<Voertuig> comp = Collections.reverseOrder(Voertuig.getAankoopprijsComparator());
        Set<Voertuig> voertuigen2 = new TreeSet<>(comp);
        voertuigen2.addAll(voertuigen);
        
        System.out.println("*** Voertuigen gesorteerd op nummerplaat ***");
        voertuigen.stream().forEach((voertuig) -> {System.out.println(voertuig);});
        
        System.out.println("\n\n*** Voertuigen gesorteerd op aankoopprijs (dalend) ***");
        voertuigen2.stream().forEach((voertuig) -> {System.out.println(voertuig);});
        
        System.out.println("\n\n*** Voertuigen gesorteerd op merk ***");
        Set<Voertuig> voertuigen3 = new TreeSet<>(Voertuig.getMerkComparator());
        voertuigen3.addAll(voertuigen);
        
        voertuigen3.stream().forEach((voertuig) -> {System.out.println(voertuig);});

        File bestand = new File("wagenpark.ser");
        
        try (FileOutputStream fos = new FileOutputStream(bestand);
            ObjectOutputStream oos = new ObjectOutputStream(fos);) {            
            
            for (Voertuig voertuig:voertuigen3) oos.writeObject(voertuig);
        }
        catch (IOException ex) {
            System.err.println(ex.getLocalizedMessage());
        }
        
        try (FileInputStream fis = new FileInputStream(bestand);
            ObjectInputStream ois = new ObjectInputStream(fis);) {

            Set<Voertuig> voertuigen4 = new TreeSet<>(Voertuig.getMerkComparator());
            try {
                while (true) {
                    voertuigen4.add((Voertuig)ois.readObject());
                }
            }
            catch (EOFException ex) {}
            System.out.println("\n\n*** Voertuigen uit een bestand gelezen en op merk gesorteerd");
            voertuigen4.stream().forEach((voertuig) -> {System.out.println(voertuig);});
        }
        catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getLocalizedMessage());
        }        
    }
}
