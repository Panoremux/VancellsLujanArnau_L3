/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InFileFolder;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Carpeta de fitxers
 * @author Arnau Vancells
 */
public class CarpetaFitxers implements InFileFolder, Serializable {
    
    /**
     *ArrayList de fitxers de la classe.
     */
    protected ArrayList<File> carpetaFitxers= new ArrayList<File>() ;
    
    /**
     *Constructor de CarpetaFitxers amb limit a 100
     */
    public CarpetaFitxers(){//Constructor de la classe
        ArrayList carpetaFitxers = new ArrayList ();
        
    }
    
    /**
     *Usant metode d'arrayList retornem la mesura
     * @return mesura de la carpeta
     */
    public int getSize(){//
        return carpetaFitxers.size();
    }

    /**
     *Afegim un fitxer amb el metode d'arraylist, si està plena retornara error
     * @param fitxer Fitxer a afegir
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    public void	addFitxer(File fitxer)throws AplicacioException{//       
       if(!isFull()){
           carpetaFitxers.add(fitxer);
       }else{
           throw new AplicacioException("Estas excedint el nombre màxim de fitxers.");
       }
    }

    /**
     *Eliminem un fitxer amb el metode d'arrayList
     * @param fitxer Fitxer a eliminar
     */
    public void	removeFitxer(File fitxer){//
        carpetaFitxers.remove(fitxer);
    }

    /**
     *Amb el mètode d'arrayList retornem el fitxer a la posició position
     * @param position posició
     * @return File fitxer a la posició introduïda
     */
    public File getAt(int position){//obtenim el fitxer que hi ha a certa posició
        return carpetaFitxers.get(position);
        
    }

    /**
     *Netejem la carpeta deixant-la buida
     */
    public void clear(){//amb aquest metode deixarem en blanc la taula de fitxers
        carpetaFitxers.clear();
    }
    

    /**
     *Comprobem si la carpeta esta plena o no
     * @return booleà si esta plena o no
     */    
    public boolean isFull() {
        return false;
    }
    public String toString(){
        String output="";
        for(int i=0;i<getSize();i++){
            output+=("["+i+"]"+getAt(i).toString()+"\n");
        }
        return output;
    }
    
}
