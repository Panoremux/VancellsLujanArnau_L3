/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.VancellsLujanArnau.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;

/**
 *
 * @author Arnau
 */
public class Imatge extends FitxerMostrable{

    /**
     *Constructor de la classe Imatge
     * @param path Camí de la imatge
     * @param nom Nom de la imatge
     * @param alcada Alçada de la imatge
     * @param amplada Amplada de la imatge
     * @param repVis Reproductor de la imatge
     */
    public Imatge(String path,String nom, int alcada, int amplada, ReproductorVisor repVis) {
        super(path,nom,alcada,amplada,repVis);
    }

    /**
     *Mètode que mostra la imatge(la reprodueix)
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void mostrar() throws AplicacioException{
        repVis.show(this);
    }

    /**
     *Mètode que mostra la imatge(la reprodueix) durant un temps concret
     * @param secs Segons
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    public void mostrar(int secs) throws AplicacioException{
        repVis.show(this,secs);
    }
    
    @Override
    public String toString(){
        String line;
        line=super.toString()+". Mesures: ("+this.amplada+"x"+this.alcada+")";
        return line;
    }
}
