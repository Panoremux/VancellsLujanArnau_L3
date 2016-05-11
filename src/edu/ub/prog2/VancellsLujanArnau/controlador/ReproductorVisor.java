/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.controlador;


import edu.ub.prog2.VancellsLujanArnau.model.FitxerMostrable;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import edu.ub.prog2.utils.ReproductorVisorBasic;

/**
 *Classe ReproductorVisor
 * @author arnau
 */
public class ReproductorVisor extends ReproductorVisorBasic{
    
    /**
     *Constructor de la classe ReproductorVisor
     * @param controlador controlador
     */
    public ReproductorVisor(String vlcPath,EscoltadorReproduccioBasic controlador) {
        super(vlcPath,controlador);
    }
    
    /**
     *Mètode que reproduirà un fitxer mostrable
     * @param fmo fitxer a mostrar
     * @param secs duració de la reproducció
     * @throws AplicacioException Llança una excepció del tipus AplicacioException
     */
    public void mostra(FitxerMostrable fmo,int secs) throws AplicacioException{
        super.show(fmo,secs);
    }
}

