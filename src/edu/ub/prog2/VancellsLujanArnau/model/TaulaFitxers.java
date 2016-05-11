/*
Classe TaulaFitxers del projecte de programació II
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.utils.InFileFolder;
import java.io.File;
import java.io.Serializable;

/**
 *Taula de fitxers
 * @author Arnau Vancells Lujan
 */
public class TaulaFitxers implements InFileFolder, Serializable {
    private int tamanyActual=0;//Utilitzarem aquest valor per tal de controlar la mesura de la taula.
    private File [] taulaFitxers;
    
    /**
     *Constructor de TaulaFitxers, amb limit a 100 i iniciant el tamanyActual a 0
     */
    public TaulaFitxers(){//Constructor de la classe
        taulaFitxers= new File [100];
        tamanyActual=0;
    }
    
    /**
     *Obtenir mesura de la taula(quantitat de fitxers)
     * @return tamanyActual
     */
    public int getSize(){//ja que tamanyActual es un private, hem de crear un mètode que ens permeti "llegir" el valor des d'altres llocs
       return tamanyActual; 
    }

    /**
     *Comproba si està plena la taula, si no ho esta afegeix el fitxer a la posició tamanyActual i augmenta aquesta en 1
     * @param fitxer Fitxer a afegir
     */
    public void	addFitxer(File fitxer){//amb l'ús de isFull comprobarem si hi ha la possibilitat d'afegir un fitxer i si és possible s'afegirà el fitxer a la taula
       if(!isFull()){
           taulaFitxers[tamanyActual]=fitxer;
           tamanyActual++;
       }else{
           System.out.println("ERROR--Estas excedint el nombre màxim de fitxers.--ERROR");
       }
    }

    /**
     *Busca un fitxer usant equals i si el troba l'elimina i re-ordena la llista per omplir l'espai buit
     * @param fitxer Fitxer a eliminar
     */
    public void	removeFitxer(File fitxer){//Podrem borrar un fitxer comparant els diferents fitxers de la taula, i, si són iguals, borrar aquest i desplaçar tots els fitxers que estàn a la dreta del borrat
        boolean noTrobat=false;
        int index=0;
        while(!fitxer.equals(this.taulaFitxers[index])){
            
            if(index==tamanyActual){                
                noTrobat=true;
                break;
            }else{
                index++;
                
            }
        }
        //taulaFitxers[index]=null;(innecessari)
        if(!noTrobat){
            for(int i=index+1;i<tamanyActual;i++){
                taulaFitxers[i-1]=taulaFitxers[i];
            }
            taulaFitxers[tamanyActual]=null;
            tamanyActual--;//cal tenir en compte que la longitud de la taula disminueix al borrar el fitxer
        }else{
            System.out.println("ERROR--No s'ha trobat el fitxer introduït.--ERROR");
        }
    }

    /**
     *Amb la posició obtenim el fitxer en la posició indicada
     * @param position Posició
     * @return fitxer en la posició position
     */
    public File getAt(int position){//obtenim el fitxer que hi ha a certa posició
        if(position<tamanyActual){
            return taulaFitxers[position];
        }else{
            System.out.println("ERROR--No hi ha tants fitxers.--ERROR");
            return null;
        }
        
    }

    /**
     *Neteja la taula i ho posa tot a null
     */
    public void clear(){//amb aquest metode deixarem en blanc la taula de fitxers
        for(int i=0;i<taulaFitxers.length;i++){
            taulaFitxers[i]=null;
    }
    }

    /**
     *Comproba si esta plena o no
     * @return booleà si està plena o no
     */
    public boolean isFull(){//comprobem si la taula està plena o no
       return tamanyActual>=100;
    }
}
