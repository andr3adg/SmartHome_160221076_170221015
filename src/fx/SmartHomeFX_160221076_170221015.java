/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetopoo.ConsolaCentral;

/**
 *
 * @author Diogo Venancio e André Gonçalves
 */
public class SmartHomeFX_160221076_170221015 extends Application
{
    @Override
    public void start(Stage primaryStage) 
    {
        File f = new File("sistema.txt");
        try 
        {
            ConsolaCentral.load(f);
        }catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(SmartHomeFX_160221076_170221015.class.getName()).log(Level.WARNING, ex.getMessage());
            ConsolaCentral.reporSistema();
        }          
        
        Login root = new Login();
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
