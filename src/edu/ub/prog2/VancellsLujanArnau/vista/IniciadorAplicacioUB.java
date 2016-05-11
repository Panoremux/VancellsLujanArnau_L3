/*
Iniciador del projecte de programació II
 */
package edu.ub.prog2.VancellsLujanArnau.vista;

import edu.ub.prog2.VancellsLujanArnau.vista.AplicacioUB3;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
//import java.util.Scanner;

/**
 *
 * @author Arnau Vancells Lujan
 */
public class IniciadorAplicacioUB {
    /**
     * Fitxer principal. Crea el menú principal i un menú secundari
     * @param args the command line arguments
     * @throws java.io.IOException Llança una excepció del tipus IOException
     * @throws java.lang.ClassNotFoundException Llança una excepció del tipus ClassNotFoundException
     * @throws java.io.FileNotFoundException Llança una excepció del tipus FileNotFoundException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // Creem un objecte per llegir des del teclat
        //Scanner sc= new Scanner(System.in);

        // Creem un objecte principal
        
        AplicacioUB3 repro=new AplicacioUB3();

        // Iniciem la gestió del menú principal de l'aplicació
        
        repro.gestioAplicacioUB();
    }
   
}
