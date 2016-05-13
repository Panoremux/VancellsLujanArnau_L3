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
 * @author arnau
 */
public abstract class FitxerMostrable extends FitxerMultimedia{
    int alcada;
    int amplada;
    protected transient ReproductorVisor repVis;

    /**
     *Constructor de FitxerMostrable
     * @param path Camí del fitxer
     * @param nom Nom del fitxer
     * @param alcada Alçada del fitxer
     * @param amplada Amplada del fitxer
     * @param repVis Reproductor del fitxer
     */
    public FitxerMostrable(String path,String nom, int alcada, int amplada,ReproductorVisor repVis) {
        super(path);
        this.alcada=alcada;
        this.amplada=amplada;
        this.repVis=repVis;
        super.setNom(nom);
    }
    
    /**
     *Mostra el fitxer mostrable
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    public abstract void mostrar() throws AplicacioException;
    public abstract void mostrar(int secs) throws AplicacioException;
    public void setRep(ReproductorVisor repVis){
        this.repVis=repVis;
    }
}
