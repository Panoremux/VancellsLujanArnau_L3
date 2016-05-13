/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.model;

import edu.ub.prog2.VancellsLujanArnau.controlador.EscoltadorReproduccio;
import edu.ub.prog2.VancellsLujanArnau.controlador.ReproductorVisor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arnau Vancells
 */
public class Dades implements Serializable{
    private BibliotecaFitxersMultimedia bibliotecaFitxers;
    private ArrayList<AlbumFitxersMultimedia> llistaAlbums;
    /**
     *Classe Dades. Contindrà l'informació que l'usuari va introduint.
     */
    public Dades(){
        bibliotecaFitxers=new BibliotecaFitxersMultimedia();
        llistaAlbums = new ArrayList();
        
    }
    

    /**
     *Afegeix  un fitxer de tipus File a la biblioteca.
     * @param fitxer Fitxer a afegir
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */

    public void addFile(File fitxer)throws AplicacioException{
       bibliotecaFitxers.addFitxer(fitxer);
    }

    /**
     *Mostra els fitxers de la biblioteca
     * @return output
     */
    public List<String> mostrarFitxers(){        
        List<String> output=new ArrayList<>();
        for(int i=0;i<bibliotecaFitxers.getSize();i++){
            output.add(("["+(i+1)+"]"+bibliotecaFitxers.getAt(i).toString()));
        }
        return output;
    }  

    /**
     *Afegeix un album a la llista d'albums.
     * @param titol titol de l'album a afegir
     */
    public void addAlbum(String titol){
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titol);
        boolean trobat=false;
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                System.out.println("Aquest album ja existeix.");
                trobat=true;
                break;
            }
        }
        if(!trobat){
            llistaAlbums.add(album);
        }
        
    }

    /**
     *Mostra el contingut de la llista d'albums.
     * @return output Llista de strings amb els diferents albums
     */
    public List<String> mostraAlbums(){
        List<String> output=new ArrayList<>();
        for(int i=0;i<llistaAlbums.size();i++){
            output.add(("["+(i+1)+"]"+llistaAlbums.get(i).getTitle()));
        }
        return output;
    }

    /**
     *Mètode per mostrar l'album(el seu contingut).
     * @param titolAlbum Titol de l'album a mostrar
     * @return output Llista de strings
     */
    public List<String> mostraAlbum(String titolAlbum){
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        List<String> output=new ArrayList<>();
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                System.out.println("Mostrant Album...");
                output.add((llistaAlbums.get(i).toString()));
                break;
            }
        }      
        
        return output;
    }

    /**
     *Mètode per eliminar un fitxer de la biblioteca i alhora dels albums.
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    public void eliminaFitxer(int id) throws AplicacioException{
        if(id>bibliotecaFitxers.getSize()){
            throw new AplicacioException("No hi ha tants elements a la biblioteca.");
        }
        File fitxer=bibliotecaFitxers.getAt(id);
        bibliotecaFitxers.removeFitxer(fitxer);
        for(int i=0;i<llistaAlbums.size();i++){
            llistaAlbums.get(i).removeFitxer(fitxer);
        }
    }

    /**
     *Mètode per eliminar un album de la llista d'albums.
     * @param titolAlbum títol de l'album
     */
    public void eliminaAlbum(String titolAlbum){
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                llistaAlbums.remove(llistaAlbums.get(i));
            }
        }
    }

    /**
     *Mètode per comprobar si un àlbum ja existeix.
     * @param titolAlbum títol de l'album
     * @return trobat
     */
    public boolean albumExists(String titolAlbum){
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        boolean trobat=false;
        for(int i=0;i<llistaAlbums.size() && !trobat;i++){
            if(llistaAlbums.get(i).equals(album)){
                trobat=true;
                //break;
            }
        }
        return trobat;
    }
  
    /**
     *Retorna la biblioteca de dades.
     * @return bibliotecaFitxers
     */
    public BibliotecaFitxersMultimedia getBiblioteca(){
        return bibliotecaFitxers;
    }

    /**
     *Mètode per afegir un fitxer reproduible de biblioteca a album.
     * @param titolAlbum títol de l'album  
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    public void afegirFitxerAlbum(String titolAlbum,int id)throws AplicacioException{
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                llistaAlbums.get(i).addFitxer(bibliotecaFitxers.getAt(id));
                break;
            }
        }
        
    }
   
    /**
     *Mètode per eliminar un fitxer reproduible de biblioteca a album.
     * @param titolAlbum títol de l'album
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    public void eliminarFitxerAlbum(String titolAlbum,int id)throws AplicacioException{
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                if(id>llistaAlbums.get(i).getMida()){
                    throw new AplicacioException("No hi ha tants elements a l'album.");
                }
                llistaAlbums.get(i).removeFitxer(bibliotecaFitxers.getAt(id));
                break;
            }
        }
        
    }

    /**
     *Mètode per canviar el títol d'un album.
     * @param titolAlbum títol de l'album
     * @param titolN nou títol de l'album
     */
    public void canviarTitol(String titolAlbum,String titolN){
       AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
       for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                llistaAlbums.get(i).setTitle(titolN);
                break;
            }
        }
   }

    /**
     *Mètode per canviar la mida d'un album.
     * @param titolAlbum títol de l'album
     * @param mida nova mida de l'album 
     */
    public void canviarMida(String titolAlbum, int mida){
       AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
       for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                llistaAlbums.get(i).setLength(mida);
                break;
            }
        }
   }

    /**
     *Mètode per carregar les dades d'un fitxer .dat.
     * @param camiOrigen  Path des d'on es carreguen les dades
     * @return dades Dades importades del fitxer
     * @throws FileNotFoundException Llança una excepció del tipus FileNotFoundException
     * @throws IOException Llança una excepció del tipus IOException
     * @throws ClassNotFoundException Llança una excepció del tipus ClassNotFoundException
     */
    public Dades carregarDades(String camiOrigen) throws FileNotFoundException, IOException,ClassNotFoundException{
       FileInputStream fin;
       File fitxerCargar = new File(camiOrigen);
       fin = new FileInputStream(fitxerCargar);
       ObjectInputStream ois = new ObjectInputStream(fin);
       Dades dades=(Dades)ois.readObject();
       ois.close();
       fin.close(); 
       
       return dades;
   }

    /**
     * Mètode per a guardar les dades del programa en un fitxer .dat.
     * @param camiDesti Path on es guardarà la informació
     * @throws FileNotFoundException Llança una excepció del tipus FileNotFoundException
     * @throws IOException Llança una excepció del tipus IOException
     */
    public void guardarDades(String camiDesti) throws FileNotFoundException, IOException{
       FileOutputStream fout;        
       File fitxerDesar = new File(camiDesti);
       fout = new FileOutputStream(fitxerDesar);
       ObjectOutputStream oos = new ObjectOutputStream(fout);
       oos.writeObject(this);
       oos.close();             
       fout.close();
   }

    /**
     * Mètode per retornar el titol d'un album amb l'id d'aquest.
     * @param id posició
     * @return titol 
     */
    public String getTitolAlbum(int id){
       return llistaAlbums.get(id).getTitle();
    }
    public AlbumFitxersMultimedia getAlbum(String titolAlbum){
        AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(titolAlbum);
        for(int i=0;i<llistaAlbums.size();i++){
            if(llistaAlbums.get(i).equals(album)){
                album= llistaAlbums.get(i);
            }
        }
        return album;
    }
    public void setRep(ReproductorVisor repVis)throws AplicacioException{
        if(bibliotecaFitxers.getSize()==0){
            throw new AplicacioException("les dades carregades són buides.");
        }
          
        for(int i=0;i<bibliotecaFitxers.getSize();i++){
            if(bibliotecaFitxers.getAt(i) instanceof FitxerMostrable){
                ((FitxerMostrable)bibliotecaFitxers.getAt(i)).setRep(repVis);
            }else{
                ((FitxerReproduible)bibliotecaFitxers.getAt(i)).setRep(repVis);
            }
                
        }
    }
    
}
