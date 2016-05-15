/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.VancellsLujanArnau.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;

/**
 *Classe Audio. Hereta de FitxerReproduible.
 * @author avancelu28.alumnes
 */
public class Audio extends FitxerReproduible {
    
    
    File fitxerImatge;    
    int kbps;
    
    /**
     *Constructor de la classe Audio.
     * @param cami Camí de l'audio
     * @param fitxerImatge File de la imatge
     * @param nom nom de l'audio
     * @param codec codec de l'audio
     * @param durada durada de l'audio
     * @param kbps Kilo Bytes per segon de l'audio
     * @param r ReproductorVisor
     */
    public Audio(String cami,File fitxerImatge, String nom, String codec, float durada, int kbps, ReproductorVisor r) {
        super(cami, nom, codec, durada, r);
        this.fitxerImatge=fitxerImatge;
        this.kbps=kbps;
    }

    @Override
    public void reproduir() throws AplicacioException  {
        if(this.exists()){
            r.play(this,fitxerImatge);
        }else{
            throw new AplicacioException("Error al reproduir el fitxer");
        }
        
    }
        
    
}
