/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.VancellsLujanArnau.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;

/**
 *Classe video. Hereta de FitxerReproduible.
 * @author avancelu28.alumnes
 */
public class Video extends FitxerReproduible{
    
    private int alcada,amplada;
    private float fps;
    
    /**
     *Constructor de la classe Video.
     * @param cami cami del video
     * @param nom nom del video
     * @param codec codec del video
     * @param durada durada del video
     * @param alcada alçada del video
     * @param amplada amplada del video
     * @param fps fotogrames per segon del video
     * @param r ReproductorVisor
     */
    public Video(String cami, String nom, String codec, float durada, int alcada,int amplada, float fps, ReproductorVisor r) {
        super(cami, nom, codec, durada, r);
        this.alcada=alcada;
        this.amplada=amplada;
        this.fps=fps;
    }

    
    @Override
    public void reproduir() throws AplicacioException {
        r.play(this);
    }   
    
}
