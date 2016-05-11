/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.util.ArrayList;

/**
 *Classe contenidora de dades.Guardarà tots els fitxers que "importi" l'usuari.
 * @author Arnau Vancells
 */

public class BibliotecaFitxersMultimedia extends CarpetaFitxers  {  
    /**
     *Constructor de la classe BibliotecaFitxersMultimedia
     */
    public BibliotecaFitxersMultimedia(){//Constructor de la classe
        carpetaFitxers=new ArrayList<>();
        
    }
    @Override
    public void addFitxer(File fitxer) throws AplicacioException{
        if((!included(fitxer))&&(fitxer.exists())){
            super.addFitxer(fitxer);
        }else if(included(fitxer)){
            throw new AplicacioException("Aquest fitxer ja existeix a la biblioteca.");
            //System.out.println("No s'ha pogut afegir el fitxer");
        }else{
            throw new AplicacioException("Aquest fitxer no existeix a disc.");
        }
    }

    /**
     *Comproba si un fitxer existeix a memòria
     * @param fitxer Fitxer a comprobar
     * @return boolean
     */
    public boolean included(File fitxer){
        for(int i=0;i<super.getSize();i++){
            if(getAt(i).equals(fitxer)){
                return true;               
            }
        }
        return false;
    }
    
    
}
