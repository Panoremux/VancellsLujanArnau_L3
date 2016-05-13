/*
 Menú que serà iniciat des de IniciadorAplicacioUB on hi ha les diferents opcions. Programació II
 */
package edu.ub.prog2.VancellsLujanArnau.vista;

import edu.ub.prog2.VancellsLujanArnau.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.utils.Menu;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe encarregada de manejar el menú i fer les crides corresponents
 * @author Arnau Vancells Lujan
 */
public class AplicacioUB3 {
    private Controlador controlador;
    List<String> mostrar;
    String titol;
    // Declarem les opcions per a referir-se a les opcions del menú.
    static private enum OpcionsMenuPrincipal {MENU_PRINCIPAL_GESTIO_BIBLIOTECA,MENU_PRINCIPAL_GESTIO_ALBUMS,MENU_GESTIO_REPRODUIR,MENU_PRINCIPAL_SAVE,MENU_PRINCIPAL_RECOVER,MENU_PRINCIPAL_SORTIR};
    static private enum OpcionsSubmenuBiblioteca {MENU_BIBLIOTECA_ADD_L,MENU_BIBLIOTECA_SHOW_L,MENU_BIBLIOTECA_REMOVE_L,MENU_BIBLIOTECA_BACK_L};
    static private enum OpcionsSubmenuBiblioteca2 {MENU_BIBLIOTECA2_ADD_VIDEO,MENU_BIBLIOTECA2_AUDIO,MENU_BIBLIOTECA2_IMAGE,MENU_BIBLIOTECA2_BACK_L2};
    static private enum OpcionsSubmenuAlbum {MENU_ALBUM_ADD_A,MENU_ALBUM_SHOW_A,MENU_ALBUM_REMOVE_A,MENU_ALBUM_MANAGE_A,MENU_ALBUM_BACK_A};
    static private enum OpcionsSubmenuAlbum2 {MENU_ALBUM2_ADD_FM,MENU_ALBUM2_SHOW_ALBUM,MENU_ALBUM2_REMOVE_FM,MENU_ALBUM2_CHANGE_NAME,MENU_ALBUM2_CHANGE_SIZE,MENU_ALBUM_BACK_A2};
    static private enum OpcionsReproductor  {REPRODUIR_FR,REPRODUIR_IMAGE,REPRODUIR_ALL_BIBLIOTECA,REPRODUIR_ALBUM,REPRODUIR_CICLE,REPRODUIR_PREMIUM,REPRODUIR_MANAGE,REPRODUIR_BACK_R};
    static private enum OpcionsReproductor2 {REPRODUIR2_PLAY,REPRODUIR2_PAUSE,REPRODUIR2_STOP,REPRODUIR2_NEXT,REPRODUIR2_BACK_R2};
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal={"Gestio Biblioteca",
                                               "Gestio Album",
                                               "Gestio Reproducció",
                                               "Guardar Dades",
                                               "Recuperar dades",
                                               "Sortir"};
    static private String[] descSubmenuBiblioteca={"Afegir fitxer multimèdia a la biblioteca",
                                                    "Mostrar Biblioteca",
                                                    "Eliminar fitxer multimèdia",
                                                    "Menú Anterior"};
    static private String[] descSubmenuBiblioteca2={"Afegir Video",
                                                    "Afegir Audio",
                                                    "Afegir Imatge",
                                                    "Menú Anterior"};
    static private String[] descSubmenuAlbum={"Afegir Àlbum",
                                                "Mostar Àlbums",
                                                "Eliminar àlbum",
                                                "Gestionar Àlbum",
                                                "Menú Anterior"};
    static private String[] descSubmenuAlbum2={"Afegir fitxer multimèdia",
                                                "Mostrar àlbum",
                                                "Eliminar fitxer multimèdia",
                                                "Canviar títol",
                                                "Canviar mida màxima",
                                                "Menú Anterior"};
    static private String[] descSubmenuReproductor={"Reproduir un fitxer reproduï",
                                                "Visualitza una imatge",
                                                "Reproduir tota la biblioteca",
                                                "Reproduir un àlbum",
                                                "Activar/Desactivar reproducció cíclica",
                                                "Desactivar reproducció Premium",
                                                "Gestio reproducció en curs",
                                                "Menú Anterior"};
    static private String[] descSubmenuReproductor2={"Re-empèn",
                                                "Pausa",
                                                "Atura",
                                                "Salta",
                                                "Menú Anterior"};
    /**
     * We could use either TaulaFitxers or CarpetaFitxers.
     * The user will be able to select it.
     */
    public AplicacioUB3(){
        controlador=new Controlador();
    }
    
    /**
     * From here we will call the methods from controlador needed to make the Player work
     * 
     * @throws ClassNotFoundException
     */
    public void gestioAplicacioUB() throws ClassNotFoundException  {
        Scanner sc=new Scanner(System.in);
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuPrincipal> menu=new Menu<>("Menu Principal",OpcionsMenuPrincipal.values());
        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case MENU_PRINCIPAL_GESTIO_BIBLIOTECA:
                    gestioSubmenuBiblioteca(sc);
                    break;
                case MENU_PRINCIPAL_GESTIO_ALBUMS:
                    gestioSubmenuAlbum(sc);
                    break;
                case MENU_PRINCIPAL_SAVE:
                    System.out.println("On ho vols guardar?");
                    {
                        try {
                            controlador.guardarDadesDisc(sc.nextLine());
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_PRINCIPAL_RECOVER:
                    System.out.println("Quin fitxer de dades vols carregar?");
                    {
                        try {
                            controlador.carregarDadesDisc(sc.nextLine());
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_GESTIO_REPRODUIR:
                    gestioReproductor(sc);
                    break;
                case MENU_PRINCIPAL_SORTIR:
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
    }
    
    private void gestioSubmenuBiblioteca(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenuBiblioteca> menu=new Menu<>("Submenu Biblioteca",OpcionsSubmenuBiblioteca.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuBiblioteca);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuBiblioteca opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            switch(opcio) {
                case MENU_BIBLIOTECA_ADD_L:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Afegint");
                    gestioSubmenuBiblioteca2(sc);
                    break;
                case MENU_BIBLIOTECA_SHOW_L:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Mostrant");
                    mostrar=controlador.mostrarBiblioteca();
                    for (String item : mostrar) {
                    System.out.println(item);
                    }                    
                    break;
                case MENU_BIBLIOTECA_REMOVE_L:
                    System.out.println("Borrant");
                    mostrar=controlador.mostrarBiblioteca();
                    for (String item : mostrar) {
                    System.out.println(item);
                    }
                    System.out.println("Introdueix l'id de l'element que vols eliminar.");
                    int id=sc.nextInt();
                    {
                        try {
                            controlador.esborrarFitxer(id-1);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_BIBLIOTECA_BACK_L:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsSubmenuBiblioteca.MENU_BIBLIOTECA_BACK_L);
    }
    
    private void gestioSubmenuBiblioteca2(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenuBiblioteca2> menu=new Menu<>("Submenu Afegir a Biblioteca",OpcionsSubmenuBiblioteca2.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuBiblioteca2);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuBiblioteca2 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            switch(opcio) {
                case MENU_BIBLIOTECA2_ADD_VIDEO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Introdueix les dades del video a afegir");                  
                    System.out.println("Escriu el camí");
                    String camiV=sc.nextLine();
                    System.out.println("Escriu el nom del video");
                    String nomVideo=sc.nextLine();
                    System.out.println("Escriu el codec");
                    String codecV=sc.nextLine();
                    System.out.println("Escriu la durada");
                    float duradaV=sc.nextFloat();
                    System.out.println("Escriu l'alcada");
                    int alcada=sc.nextInt();
                    System.out.println("Escriu l'amplada");
                    int amplada=sc.nextInt();
                    System.out.println("Escriu els fps");
                    float fps=sc.nextFloat();
                    System.out.println("...Afegint video...");
                    {
                        try {
                            controlador.afegirVideo(camiV,nomVideo,codecV,duradaV,alcada,amplada,fps);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_BIBLIOTECA2_AUDIO:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Introdueix les dades del audio a afegir");                  
                    System.out.println("Escriu el camí");
                    String camiA=sc.nextLine();
                    System.out.println("Escriu el path de la imatge");
                    String camiImatge=sc.nextLine();
                    System.out.println("Escriu el nom de l'audio");
                    String nomAudio=sc.nextLine();
                    System.out.println("Escriu el codec");
                    String codecA=sc.nextLine();
                    System.out.println("Escriu la durada");
                    float duradaA=sc.nextFloat();
                    System.out.println("Escriu els kbps");
                    int kbps=sc.nextInt();                   
                    System.out.println("...Afegint audio...");
                    {
                       try {
                    controlador.afegirAudio(camiA,camiImatge,nomAudio,codecA,duradaA,kbps);
                    } catch (AplicacioException ex) {
                        ex.getMessage();
                    }
                    }
                    break;
                case MENU_BIBLIOTECA2_IMAGE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Introdueix les dades de la imatge a afegir");
                    System.out.println("Escriu el path de la imatge");
                    String camiImg=sc.nextLine();
                    System.out.println("Escriu el nom de la imatge");
                    String nomImg=sc.nextLine();
                    System.out.println("Escriu l'alçada de la imatge");
                    int alcadaImg=sc.nextInt();
                    System.out.println("Escriu l'amplada de la imatge");
                    int ampladaImg=sc.nextInt();
           
                    try{
                        controlador.afegirImatge(camiImg, nomImg, alcadaImg, ampladaImg);
                    } catch (AplicacioException ex) {
                        Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case MENU_BIBLIOTECA2_BACK_L2:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsSubmenuBiblioteca2.MENU_BIBLIOTECA2_BACK_L2);
    }
    
    private void gestioSubmenuAlbum(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenuAlbum> menu=new Menu<>("Submenu Album",OpcionsSubmenuAlbum.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuAlbum);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuAlbum opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            switch(opcio) {
                case MENU_ALBUM_ADD_A:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Afegint Album");
                    System.out.println("Escriu el títol de l'album que vols crear");
                    String titolAlbumAfegir=sc.nextLine();
                    {
                        try {
                            controlador.afegirAlbum(titolAlbumAfegir);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM_SHOW_A:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    //System.out.println("Mostrar Albums");
                    System.out.println("Mostrant");                    
                    for (String item : mostrar=controlador.mostrarLlistatAlbums()) {
                        System.out.println(item);
                    }  
                    break;
                case MENU_ALBUM_REMOVE_A:
                    System.out.println("Eliminant");
                    for (String item : mostrar=controlador.mostrarLlistatAlbums()) {
                        System.out.println(item);
                    }  
                    System.out.println("Escriu el nombre de l'album que vols eliminar");
                    {
                        try {
                            controlador.esborrarAlbum(controlador.getTitolAlbum(sc.nextInt()-1));
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM_MANAGE_A:
                    System.out.println("Ha escollit gestionar Album");
                    gestioSubmenuAlbum2(sc);
                    break;
                case MENU_ALBUM_BACK_A:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsSubmenuAlbum.MENU_ALBUM_BACK_A);
    }
    private void gestioSubmenuAlbum2(Scanner sc) {

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsSubmenuAlbum2> menu=new Menu<>("Submenu Gestionar Album",OpcionsSubmenuAlbum2.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuAlbum2);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsSubmenuAlbum2 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            switch(opcio) {
                case MENU_ALBUM2_ADD_FM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    for (String item : mostrar=controlador.mostrarBiblioteca()) {
                    System.out.println(item);
                    }  
                    System.out.println("Quin element vols afegir?");                                   
                    int idAfegirG=sc.nextInt();
                    for (String item : mostrar=controlador.mostrarLlistatAlbums()) {
                        System.out.println(item);
                    }
                    System.out.println("Id album?");                                                            
                    {
                        try {
                            controlador.afegirFitxer(controlador.getTitolAlbum(sc.nextInt()-1),idAfegirG-1);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM2_SHOW_ALBUM:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    for (String item : mostrar=controlador.mostrarBiblioteca()) {
                    System.out.println(item);
                    }  
                    System.out.println("Quin album vols mostrar?");                     
                    {
                        try {
                            mostrar=controlador.mostrarAlbum(controlador.getTitolAlbum(sc.nextInt()-1));
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    for (String item : mostrar) {
                    System.out.println(item);
                    }  
                    break;
                case MENU_ALBUM2_REMOVE_FM:
                    for (String item : mostrar=controlador.mostrarBiblioteca()) {
                    System.out.println(item);
                    }  
                    System.out.println("Quin album vols mostrar?");                     
                    {
                        try {
                            mostrar=controlador.mostrarAlbum(controlador.getTitolAlbum(sc.nextInt()-1));
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    for (String item : mostrar) {
                    System.out.println(item);
                    }  
                    System.out.println("Quin element vols eliminar?");                                   
                    int idEliminarG=sc.nextInt();                    
                    System.out.println("Id album?");                    
                    {
                        try {
                            controlador.esborrarFitxer(controlador.getTitolAlbum(sc.nextInt()-1), idEliminarG-1);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM2_CHANGE_NAME:
                    System.out.println("Canvi de titol");
                    System.out.println("A quin album?");
                    titol=sc.nextLine();
                    System.out.println("Quin nom vols posar-li?");
                    String nouTitol=sc.nextLine();
                    {
                        try {
                            controlador.canviarTitolAlbum(titol, nouTitol);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM2_CHANGE_SIZE:
                    System.out.println("Canvi de mida");
                    System.out.println("A quin album?");
                    String titolAlbumMidaG=sc.nextLine();
                    System.out.println("Quina mida vols posar-li?");
                    int novaMida=sc.nextInt();
                    {
                        try {
                            controlador.canviarMidaAlbum(titolAlbumMidaG, novaMida);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case MENU_ALBUM_BACK_A2:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsSubmenuAlbum2.MENU_ALBUM_BACK_A2);
    }
    
    private void gestioReproductor(Scanner sc){
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsReproductor> menu=new Menu<>("Submenu Reproductor",OpcionsReproductor.values());
        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuReproductor);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsReproductor opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            switch(opcio) {
                case REPRODUIR_FR:
                    System.out.println("Què vols reproduir?");
                        for (String item : mostrar=controlador.mostrarBiblioteca()) {
                    System.out.println(item);
                    }  
                    int idr=sc.nextInt();
                    controlador.obrirFinestraReproductor();
                    {
                        try {
                            controlador.reproduirFitxer(idr-1);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case REPRODUIR_IMAGE:
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    System.out.println("Què vols mostrar?");
                        for (String item : mostrar=controlador.mostrarBiblioteca()) {
                    System.out.println(item);
                    }  
                    int idi=sc.nextInt();
                    System.out.println("Introdueix els segons");
                    
                    int secs=sc.nextInt();
                    {
                        try {
                            controlador.mostrarFitxer(idi-1,secs);
                        } catch (AplicacioException ex) {
                            ex.getMessage();
                        }
                    }
                    break;
                case REPRODUIR_ALL_BIBLIOTECA:
                    try {
                        controlador.reproduirCarpeta();
                        
                    } catch (AplicacioException ex) {
                        ex.getMessage();
                    }
                    break;
                case REPRODUIR_ALBUM:
                    for(String s:controlador.mostrarBiblioteca()){
                        System.out.println(s);
                    }
                    System.out.println("Escriu el nom de l'album a reproduir: ");
                    try {
                        controlador.reproduirCarpeta(sc.nextLine());
                    } catch (AplicacioException ex) {
                        Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case REPRODUIR_CICLE:
                    controlador.setCiclic();
                    break;
                case REPRODUIR_PREMIUM:
                    controlador.setPremium();
                    break;
                case REPRODUIR_MANAGE:
                    gestioReproductor2(sc);
                    break;
                case REPRODUIR_BACK_R:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsReproductor.REPRODUIR_BACK_R);
    }
    
    private void gestioReproductor2(Scanner sc){
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsReproductor2> menu=new Menu<>("Submenu Reproductor",OpcionsReproductor2.values());
        // Assignem la descripció de les opcions
        menu.setDescripcions(descSubmenuReproductor2);

        // Obtenim una opció des del menú i fem les accions pertinents
        OpcionsReproductor2 opcio = null;
        do {
            // Mostrem les opcions del menú
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessàries
            //REPRODUIR2_PLAY,REPRODUIR2_PAUSE,REPRODUIR2_STOP,REPRODUIR2_NEXT,REPRODUIR2_BACK_R2
            switch(opcio) {
                case REPRODUIR2_PLAY:
                {
                try {
                    controlador.reemprenReproduccio();
                } catch (AplicacioException ex) {
                    Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    break;
                case REPRODUIR2_PAUSE:
                {
                try {
                    // Mostrem un missatge indicant que s'ha triat aquesta opció
                    controlador.pausaReproduccio();
                } catch (AplicacioException ex) {
                    Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    break;
                case REPRODUIR2_STOP:
                {
                try {
                    controlador.tancarFinestraReproductor();
                } catch (AplicacioException ex) {
                    Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    break;
                case REPRODUIR2_NEXT:
                {
                try {
                    controlador.saltaReproduccio();
                } catch (AplicacioException ex) {
                    Logger.getLogger(AplicacioUB3.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    break;
                case REPRODUIR2_BACK_R2:
                    System.out.println("Menu anterior");
                    break;
            }

        } while(opcio!=OpcionsReproductor2.REPRODUIR2_BACK_R2);
    }
}
