/*
 * Classe FitxerMultimedia del projecte de programació II
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import java.io.File;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Arnau Vancells Lujan
 */
public class FitxerMultimedia extends File {
    private String nom;
    
    /**
     *
     * @param cami Cami del fitxer
     */
    public FitxerMultimedia(String cami){
        super(cami);
    }
    //getters

    /**
     *
     * @return la data d'última modificació del fitxer usant la classe date
     */
    public Date getUltimaModificacio(){
        long tempsMillisecs = super.lastModified(); // super és la classe pare File
        Date data = new Date(tempsMillisecs);
        return data;
    }

    /**
     *
     * @return el path del fitxer utilitzant un mètode de la classe File
     */
    public String getCamiAbsolut() {
        return getAbsolutePath();
    }
    
    /**
     *
     * @return amb el metode getName de File, retallem el que ens retorna per tal de treure l'extensió
     */
    public String getNomFitxer(){
        int pos = getName().lastIndexOf('.');
        String nom = getName().substring(0,pos);
        return nom;
    }

    /**
     *
     * @return amb el metode getName de File, retallem el que ens retorna per tal de treure el nom
     */
    public String getExtensio(){
        int pos = getName().lastIndexOf('.');
        String extensio = getName().substring(pos+1);
        return extensio;
    }

    /**
     *
     * @return l'atribut nom d'aquesta classe
     */
    public String getNom(){
        return nom;
    }
    
    /**
     * Assignem el nom entrat per parametre a l'atribut
     * @param nom Nou nom
     */

    public void setNom(String nom){
        this.nom= nom;        
    }
    
    /**
     *Demanem a l'usuari el nom que vol afegir i utilitzem el mètode setNom 
     * @return un booleà
     */
    public boolean demanaDadesTeclat(){
        Scanner sc= new Scanner(System.in);
        String nomF;
        System.out.println("Introdueix el nom del fitxer.");
        nomF= sc.nextLine();
        if(nomF.length() !=0){//Com que és l'usuari el que posa el nom, pot ser que el deixi en blanc, de moment no utilitzo el boolea, ja que em sembla que l'usuari ha de poder triar deixar en blanc el nom.
            setNom(nomF);
            return true;
        }else{
            return false;
        }
        
    }

    /**
     * Comparació de fitxers
     * @param fitxerImportat Object a comparar
     * @return true o false si hi ha coincidencia de les característiques dels fitxers
     */
    @Override
    public boolean equals(Object fitxerImportat){//Aquesta funció ens servirà per comparar diferents fitxers.
        //return ((this.getExtensio()==fitxerImportat.getExtensio()) && (fitxerImportat.getNomFitxer()==this.getNomFitxer()));
        return super.equals(fitxerImportat);
    }
    
    @Override
    public String toString(){ //Utilitzarem aquesta funció per imprimir els fitxers que anirem introduint i afegint a la taula de fitxers.
        String nom, cami, nomFitxer, extensio;
        Date modificacio;
        nom= getNom();
        cami= getCamiAbsolut();
        nomFitxer= getNomFitxer();
        extensio= getExtensio();
        modificacio= getUltimaModificacio();
        return ("Nom: "+nom+" Data modif.: "+ modificacio + " Nom Fitxer: "+ nomFitxer+" Extensio: "+extensio+" Camí complet: "+ cami);
    }
}
