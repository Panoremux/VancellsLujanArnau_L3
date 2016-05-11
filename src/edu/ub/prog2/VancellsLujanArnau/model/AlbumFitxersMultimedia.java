/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;

/**
 *Classe contenidora. Contindrà els fitxers elegits per l'usuari.
 * @author avancelu28.alumnes
 */
public class AlbumFitxersMultimedia extends CarpetaFitxers{
    
    private String titol;
    private int mida=10;
    
    /**
     *Constructor de la classe. Assigna el titol entrat per paramatres a l'atribut.
     * @param titol titol de l'album
     */
    public AlbumFitxersMultimedia(String titol){
        this.titol=titol;
    }
    public void addFitxer(File fitxer)throws AplicacioException{
        if(!isFull()){
            super.addFitxer(fitxer);
        }else{
            System.out.println("L'album ja està ple");
        }
    }

    /**
     *Canvia la mida inicial de l'album.
     * @param l longitud de l'album
     */
    public void setLength(int l){
        this.mida=l;
    }

    /**
     *Canvia el titol de l'album.
     * @param n Nou nom de l'album
     */
    public void setTitle(String n){
        this.titol=n;
    }
    
    public boolean isFull(){
       return this.getSize()>=mida;
    }

    /**
     *Retorna l'atribut titol de l'album.
     * @return titol
     */
    public String getTitle(){
        return this.titol;
    }
    /**
     *Retorna l'atribut mida de l'album.
     * @return mida
     */
    public int getMida(){
        return this.mida;
    }

    /**
     *Compara el titol de l'album entrat per paràmetres amb el de this.
     * @param album Album a comparar
     * @return boolean
     */
    public boolean equals(AlbumFitxersMultimedia album){
        return (this.titol.equals(album.getTitle()));
    }
    
}
