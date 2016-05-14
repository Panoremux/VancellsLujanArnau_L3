/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.VancellsLujanArnau.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;

/**
 *Classe fitxerReproduible. Hereta de FitxerMultimedia.
 * @author avancelu28.alumnes
 */
public abstract class FitxerReproduible extends FitxerMultimedia {
    private String nom,codec; 
    private float durada;

    /**
     *Atribut de tipus reproductor.
     */
    protected transient ReproductorVisor r;
    
    /**
     *Constructor de FitxerReproduible.
     * @param cami cami del fitxer
     * @param nom nom del fitxer
     * @param codec codec del fitxer
     * @param durada durada del fitxer
     * @param r ReproductorVisor
     */
    protected FitxerReproduible(String cami, String nom, String codec, float durada,ReproductorVisor r) {
        super(cami);
        this.nom=nom;
        this.codec=codec;
        this.durada=durada;
        this.r=r;
    }
    
    /**
     *Mètode per a reproduir.
     * @throws AplicacioException Llança una excepció del tipus AplicacioException
     */
    public abstract void reproduir() throws AplicacioException;

    /**
     *Assignem el nou reproductorVisor
     * @param repVis Reproductor Visor
     */
    public void setRep(ReproductorVisor repVis){
        this.r=repVis;
    }
        
    
}
