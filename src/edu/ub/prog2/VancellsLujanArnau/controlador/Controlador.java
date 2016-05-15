/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.VancellsLujanArnau.controlador;

import edu.ub.prog2.VancellsLujanArnau.model.Audio;
import edu.ub.prog2.VancellsLujanArnau.model.Dades;
import edu.ub.prog2.VancellsLujanArnau.model.FitxerMostrable;
import edu.ub.prog2.VancellsLujanArnau.model.FitxerReproduible;
import edu.ub.prog2.VancellsLujanArnau.model.Imatge;
import edu.ub.prog2.VancellsLujanArnau.model.Video;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.InControlador;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 *Classe Controlador, encarregada de relacionar les dades i la vista
 * @author Arnau Vancells
 */
public class Controlador implements InControlador{
    private Dades dades;

    /**
     *Atribut del tipus reproductor
     */
    private transient ReproductorVisor reproductor;

    /**
     *
     */
    public EscoltadorReproduccio escoltador;
    private boolean ciclic=false;
    
    /**
     *Constructor de Controlador, inicia una biblioteca de dades i el reproductor VLC
     */
    public Controlador(){
        escoltador=new EscoltadorReproduccio();        
        dades=new Dades();
        reproductor = new ReproductorVisor("C:/Program Files/VideoLAN/VLC",escoltador);
        //"C:/Program Files/VideoLAN/VLC"
        //"/usr/bin/vlc/"
    }
    
    /**
     *Mètode per a crear un objecte de tipus video i afegir-lo a dades.
     * @param path camí del video
     * @param nomVideo nom del vídeo
     * @param codec codec del vídeo
     * @param durada durada del vídeo
     * @param alcada alçada del vídeo
     * @param amplada amplada del vídeo
     * @param fps fotogrames per segon del vídeo
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void afegirVideo(String path, String nomVideo, String codec, float durada, int alcada, int amplada, float fps)throws AplicacioException{
        File v= new Video(path,nomVideo,codec,durada,alcada,amplada,fps,reproductor);
        dades.addFile(v);
    }

    /**
     *Mètode per a crear un objecte de tipus audio i afegir-lo a dades.
     * @param cami path de l'audio
     * @param camiImatge path de l'imatge
     * @param nomAudio nom de l'audio
     * @param codec codec de l'audio
     * @param durada durada de l'audio
     * @param kbps Kbytes per segon de l'audio
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void afegirAudio(String cami, String camiImatge, String nomAudio, String codec, float durada, int kbps)throws AplicacioException{
        File imatge=new File(camiImatge);
        File a= new Audio(cami,imatge,nomAudio,codec,durada,kbps,reproductor);
        dades.addFile(a);
    }

    /**
     * Mètode per a retornar el contingut de la biblioteca de l'atribut dades.
     * @return Llista de Strings amb el contingut de bibliotecaFitxers
     */
    @Override
    public List<String> mostrarBiblioteca(){
    // llista dels retorns de toString() dels fitxers
        return dades.mostrarFitxers();
    }

    /**
     *Mètode per a esborrar un Fitxer de la biblioteca.
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void esborrarFitxer(int id) throws AplicacioException{
        // id és la posició a llista de getBiblioteca()
        dades.eliminaFitxer(id);
    }

    /**
     *Mètode per a reproduir un fitxer del tipus fitxerReproduible
     * @param id posició
     * @throws AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void reproduirFitxer(int id) throws AplicacioException{ // id és la posició a llista de getBiblioteca()
        if(dades.getBiblioteca().getAt(id) instanceof FitxerMostrable){
            ((FitxerMostrable) dades.getBiblioteca().getAt(id)).mostrar(-1);
        }else{
            ((FitxerReproduible) dades.getBiblioteca().getAt(id)).reproduir();
        }
        //dades.reproduir(id);
    }
    
    /**
     *Mètode per a afegir un àlbum a la llista d'àlbums de dades.
     * @param titolAlbum títol de l'album
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void afegirAlbum(String titolAlbum) throws AplicacioException{
        if(!existeixAlbum(titolAlbum)){
            dades.addAlbum(titolAlbum);
        }else{
            throw new AplicacioException("Aquest album ja existeix");
        }
        
    }

    /**
     * Mètode que retorna el contingut de la llista d'albums.
     * @return Llista de Strings amb el contingut de la llista d'albums
     */
    @Override
    public List<String> mostrarLlistatAlbums(){
        return dades.mostraAlbums();
    }

    /**
     *Mètode per esborrar un àlbum de la llista d'albums.
     * @param titolAlbum títol de l'album
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void esborrarAlbum(String titolAlbum) throws AplicacioException{
        if(existeixAlbum(titolAlbum)){
            dades.eliminaAlbum(titolAlbum);
        }else{
            throw new AplicacioException("Aquest album no existeix");
        }
        
    }

    /**
     *Booleà que mira si l'album introduit per parametres ja existeix a dades.
     * @param titolAlbum títol de l'album
     * @return boolean
     */
    @Override
    public boolean existeixAlbum(String titolAlbum){
        return dades.albumExists(titolAlbum);
    }

    /**
     *Mètode per a afegir un fitxer a un àlbum.
     * @param titolAlbum títol de l'album
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void afegirFitxer(String titolAlbum, int id) throws AplicacioException{ // id és la posició a llista de    getBiblioteca()
        if(existeixAlbum(titolAlbum)){
            dades.afegirFitxerAlbum(titolAlbum, id);
        }else{
            throw new AplicacioException("Aquest album no existeix");
        }
        
    }

    /**
     *Mostra els fitxers que conté un album en concret.
     * @param titolAlbum títol de l'album
     * @return List
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public List<String> mostrarAlbum(String titolAlbum) throws AplicacioException{ // mostra informació del    àlbum
        if(existeixAlbum(titolAlbum)){
            return dades.mostraAlbum(titolAlbum);
        }else{
            throw new AplicacioException("Aquest album no existeix");
            
        }
        
    }

    /**
     *Esborra un fitxer d'un album en concret.
     * @param titolAlbum títol de l'album
     * @param id posició
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void esborrarFitxer(String titolAlbum, int id) throws AplicacioException{ //sobrecàrrega del mètode per esborrar un fitxer d’un àlbum. id és la posició a llista de getBiblioteca()
        if(existeixAlbum(titolAlbum)){
            dades.eliminarFitxerAlbum(titolAlbum, id);
        }else{
            throw new AplicacioException("Aquest album no existeix");            
        }
        
    }

    /**
     *Canvia el titol d'un album en concret.
     * @param titolAlbum títol de l'album
     * @param nouTitol nou títol de l'album
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void canviarTitolAlbum(String titolAlbum, String nouTitol) throws AplicacioException{
        
        if(existeixAlbum(titolAlbum)){
            dades.canviarTitol(titolAlbum, nouTitol);
        }else{
            throw new AplicacioException("Aquest album no existeix");
        }
    }

    /**
     *Canvia la mida d'un album en concret.
     * @param titolAlbum títol de l'album
     * @param mida nova mida de l'album
     * @throws edu.ub.prog2.utils.AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void canviarMidaAlbum(String titolAlbum, int mida) throws AplicacioException{
        
        if(existeixAlbum(titolAlbum)){
            dades.canviarMida(titolAlbum,mida);
        }else{
            throw new AplicacioException("Aquest album no existeix");
        }
    }

    /**
     *Guarda les dades a memòria.
     * @param camiDesti path del fitxer a guardar
     * @throws AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void guardarDadesDisc(String camiDesti)throws AplicacioException{
        try {
            dades.guardarDades(camiDesti);
        } catch (IOException ex) {
            throw new AplicacioException("Error al guardar les dades.");
        }
            
        
    }
    
    /**
     *Carrega les dades de memòria.
     * @param camiOrigen path del fitxer a carregar
     * @throws AplicacioException Llança una excepció del tipus AplicacioException
     */
    @Override
    public void carregarDadesDisc(String camiOrigen) throws AplicacioException{
        try {
            dades=dades.carregarDades(camiOrigen);
            dades.setRep(reproductor);
            
        } catch (IOException | ClassNotFoundException ex ) {
            throw new AplicacioException("Error al carregar les dades.");
        }      
    }

    /**
     *Retorna el titol d'un album
     * @param id posició
     * @return titol
     */
    public String getTitolAlbum(int id){
        return dades.getTitolAlbum(id);
    }

    /**
     *Mètode per a afegir una imatge a la biblioteca
     * @param path Cami
     * @param nom Nom
     * @param alcada Alcada
     * @param amplada Amplada
     * @throws AplicacioException Llança una excepcio del tipus AplicacioException
     */
    @Override
    public void afegirImatge(String path, String nom, int alcada, int amplada) throws AplicacioException {
        File a= new Imatge(path,nom,alcada,amplada,reproductor);
        dades.addFile(a);
    }

    /**
     *Mètode que obra una finestra per a reproduir els fitxers
     */
    @Override
    public void obrirFinestraReproductor() {
        this.reproductor.open();
    }

    /**
     *Mètode que tanca una finestra per a reproduir els fitxers
     * @throws AplicacioException Llança una excepcio del tipus AplicacioException
     */
    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        this.reproductor.close();
    }

    /**
     *Mètode que ens mostrarà un fitxer
     * @param id Posició
     * @param segons Temps
     * @throws AplicacioException Llança una excepcio del tipus AplicacioException
     */
    @Override
    public void mostrarFitxer(int id, int segons) throws AplicacioException {
        this.obrirFinestraReproductor();
        if(dades.getBiblioteca().getAt(id) instanceof FitxerMostrable){
            FitxerMostrable fitxer = (FitxerMostrable) dades.getBiblioteca().getAt(id);
            fitxer.mostrar();
        }else{
            throw new AplicacioException("L'element seleccionat no és un fitxer mostrable.");
        }
    }

    /**
     *Mètode que reproduuirà una carpeta
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void reproduirCarpeta() throws AplicacioException {
        this.obrirFinestraReproductor();
        escoltador.iniciarReproduccio(dades.getBiblioteca(), ciclic);        
    }

    /**
     *Reprodueix una carpeta a partir d'un string donat
     * @param titolAlbum Titol de l'album
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void reproduirCarpeta(String titolAlbum) throws AplicacioException {
        if(!dades.albumExists(titolAlbum))throw new AplicacioException("Aquest album no existeix.");
        else if(dades.getAlbum(titolAlbum).getSize()!=0){
            this.obrirFinestraReproductor();
            escoltador.iniciarReproduccio(dades.getAlbum(titolAlbum), ciclic);
        }else{
            throw new AplicacioException("Aquest album no té fitxers a reproduir.");
        }
        
    }

    /**
     *Reempren la reproduccio
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void reemprenReproduccio() throws AplicacioException {
        this.reproductor.resume();
    }

    /**
     *Pausa la reproduccio
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void pausaReproduccio() throws AplicacioException {
        this.reproductor.pause();
    }

    /**
     *Atura la reproduccio
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void aturaReproduccio() throws AplicacioException {
        this.reproductor.stop();
    }

    /**
     *Salta la reproduccio
     * @throws AplicacioException Llança una excepcio de tipus AplicacioException
     */
    @Override
    public void saltaReproduccio() throws AplicacioException {
        escoltador.saltaRep();
    }

    /**
     *Mètode que ens nega el booleà cíclic
     */
    public void setCiclic(){
        this.ciclic=(!ciclic);
    }

    /**
     *
     */
    public void setPremium(){
        escoltador.setPremium(new Imatge("ub.jpg","Publicitat",200,200,reproductor));
    }

    /**
     *
     */
    public void setRep(){
        for(int i=0;i<dades.getBiblioteca().getSize();i++){
            
        }
    }
    
}
