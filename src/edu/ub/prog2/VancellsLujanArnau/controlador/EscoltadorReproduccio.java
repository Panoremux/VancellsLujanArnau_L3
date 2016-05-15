/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.controlador;

import edu.ub.prog2.VancellsLujanArnau.model.CarpetaFitxers;
import edu.ub.prog2.VancellsLujanArnau.model.FitxerMostrable;
import edu.ub.prog2.VancellsLujanArnau.model.FitxerReproduible;
import edu.ub.prog2.VancellsLujanArnau.model.Imatge;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
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
        this.noPubli=false;
    }

    /**
     *Mètode que es crida al acabar de reproduir un fitxer, inicia la reproducció
     * dels seguents fitxers a reproduir
     */
    @Override
    protected void onEndFile() {
        if(!noPubli){
            if(!premium)noPubli=true;
            next();
            
        }else if(!premium){
            if(!premium)noPubli=false;
            nextPubli();
            
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
                    if(llistaReproduint.getAt(ptr) instanceof FitxerMostrable){
                        ((FitxerMostrable) llistaReproduint.getAt(ptr)).mostrar(5);
                    }else{
                        ((FitxerReproduible) llistaReproduint.getAt(ptr)).reproduir();        
                    }
                    ptr++;
                } catch (AplicacioException ex) {
                    ex.getMessage();
                }
        }else if(ciclic){
                System.out.println("Reproducció cíclica activada. Tornant a reproduir.");
                try {
                    ptr=0;
                    if(llistaReproduint.getAt(ptr) instanceof FitxerMostrable){
                        ((FitxerMostrable) llistaReproduint.getAt(ptr)).mostrar(5);
                    }else{
                        ((FitxerReproduible) llistaReproduint.getAt(ptr)).reproduir();        
                    }
                    ptr++;
                } catch (AplicacioException ex) {
                    ex.getMessage();
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
            ex.getMessage();
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
        if(!premium)noPubli=true;
        next();
        
    }
    
    /**
     *Assigna el booleà premium a false i crea la imatge que s'utilitzarà entre fitxer i fitxer
     * @param publi Imatge de publicitat
     */
    public void setPremium(Imatge publi){
        premium=false;
        publicitat=publi;
    }
    
    protected void saltaRep() {                   
        if(noPubli){
            if(!premium){
                if(!premium)noPubli=false;
                nextPubli();
            
            }
        }
        
    }

}