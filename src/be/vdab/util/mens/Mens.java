/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Guillaume
 */
public class Mens implements Comparable<Mens>, Serializable {
    
    private static final long serialVersionUID = 1L;
    private final String naam;
    private EnumSet<Rijbewijs> rijbewijzen;

    public Mens(String naam, Rijbewijs... rijbewijzen) {
        this.naam = naam;
        this.rijbewijzen.addAll(Arrays.asList(rijbewijzen));
    }

    //getters
    public Rijbewijs[] getRijbewijs() {
        return this.rijbewijzen.toArray(new Rijbewijs[this.rijbewijzen.size()]);
    }

    public String getNaam() {
        return this.naam;
    }

//    @Override
//    public String toString() {
//        String txt = this.rijbewijzen.toString().replace("[", "(");
//        txt = txt.replace("]", ")");
//        if (txt.length() == 2) txt = "";
//        return this.naam + txt;
//    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(naam);
        if (!rijbewijzen.isEmpty())
        {
            sb.append("(").append(StringUtils.join(rijbewijzen, ", ")).append(")");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mens) {
            Mens mens = (Mens) obj;
            return (this.naam.equalsIgnoreCase(mens.naam) && (this.rijbewijzen.equals(mens.rijbewijzen)));
        }
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.naam);
        hash = 41 * hash + Objects.hashCode(this.rijbewijzen);
        return hash;
    }


    @Override
    public int compareTo(Mens mens) {
        return this.naam.compareToIgnoreCase(mens.naam);
    }
}
