/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.controlador;

import edu.ub.prog2.VancellsLujanArnau.model.CarpetaFitxers;
import edu.ub.prog2.VancellsLujanArnau.model.FitxerReproduible;
import edu.ub.prog2.VancellsLujanArnau.model.Imatge;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *Classe EscoltadorReproduccio
 * @author Arnau
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic{
    
    boolean premium,ciclic,noPubli;
    int ptr;
    CarpetaFitxers llistaReproduint;
    Imatge publicitat;

    /**
     *Constructor de la classe EscoltadorReproduccio
     */
    public EscoltadorReproduccio(){
        this.premium=true;        
    }

    /**
     *Mètode que es crida al acabar de reproduir un fitxer, inicia la reproducció
     * dels seguents fitxers a reproduir
     */
    @Override
    protected void onEndFile() {
        if(!noPubli){
            next();
            if(!premium)noPubli=true;
        }else if(!premium){
            nextPubli();
            if(!premium)noPubli=false;
        }
    }

    /**
     *Reprodueix el seguent fitxer 
     */
    @Override
    protected void next() {
        if(hasNext()){
            System.out.println("Reproduint el seguent fitxer.");
            try {
                ((FitxerReproduible) llistaReproduint.getAt(ptr)).reproduir();
                ptr++;
            } catch (AplicacioException ex) {
                Logger.getLogger(EscoltadorReproduccio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(ciclic){
            System.out.println("Reproducció cíclica activada. Tornant a reproduir.");
            try {
                ptr=0;
                ((FitxerReproduible) llistaReproduint.getAt(ptr)).reproduir();
                ptr++;
            } catch (AplicacioException ex) {
                Logger.getLogger(EscoltadorReproduccio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("S'ha acabat la reproducció de la carpeta.");
            
        }
    }

    /**
     *Retorna true si hi ha més elements a reproduir
     * @return booleà
     */
    @Override
    protected boolean hasNext() {
        return ptr<llistaReproduint.getSize();
    }

    /**
     *Reprodueix la publicitat
     */
    @Override
    protected void nextPubli() {
        System.out.println("--PUBLICITAT--");
        try {
            publicitat.mostrar(5);
        } catch (AplicacioException ex) {
            Logger.getLogger(EscoltadorReproduccio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Inicia la reproducció d'una carpeta
     * @param llistaReproduint Carpeta a reproduir
     * @param reproduccioCiclica Booleà reproducció cíclica
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    public void iniciarReproduccio(CarpetaFitxers llistaReproduint, boolean reproduccioCiclica) throws AplicacioException{
        System.out.println("Iniciant la reproducció");
        this.llistaReproduint=llistaReproduint;
        this.ciclic=reproduccioCiclica;
        ptr=0;
        ((FitxerReproduible) llistaReproduint.getAt(ptr)).reproduir();
        ptr++;
    }
    
    /**
     *Assigna el booleà premium a false i crea la imatge que s'utilitzarà entre fitxer i fitxer
     * @param repVis
     */
    public void setPremium(ReproductorVisor repVis){
        premium=false;
        publicitat=new Imatge("ub.jpg","Publicitat",200,200,repVis);
    }
}