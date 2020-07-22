/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Venancio e André Gonçalves
 */
public class SmartHome_160221076_170221015 
{

    public static void main(String[] args)
    {
        File f = new File("sistema.txt");
        try 
        {
            ConsolaCentral.load(f);
        }catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(SmartHome_160221076_170221015.class.getName()).log(Level.WARNING, ex.getMessage());
            ConsolaCentral.reporSistema();
        }
        do
        {
            ConsolaCentral.getInstance().iniciarUI();
        }while(true);  
    }
    
}
