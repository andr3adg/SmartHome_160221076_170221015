/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import Exceptions.ClienteIllegalArgumentException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projetopoo.ConsolaCentral;
import projetopoo.Dados;

/**
 * Alterar os dados de um utilizador
 * @author Diogo Venancio e André Gonçalves
 */
public class AlterarUtilizador extends BorderPane
{
    private HBox headerBox;
    private HBox bottomBox;
    private GridPane grid;
    private Text registarText;
    private Label nomeLabel;
    private TextField nomeTextField;
    private Label moradaLabel;
    private TextField moradaTextField;
    private Label emailLabel;
    private TextField emailTextField;
    private Button registarButton;
    private Button cancelarButton;
    
    public AlterarUtilizador() 
    {
        setup();
    }
    
    private void setup()
    {
        StackPane left = new StackPane();
        left.setPrefWidth(50);
        setLeft(left);
        StackPane right = new StackPane();
        right.setPrefWidth(25);
        setLeft(right);
        
        Dados user = ConsolaCentral.getInstance().getCurrentClient();
        
        headerBox = new HBox();  
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(15, 0, 0, 0) );
        registarText = createText("Alterar dados - " + user.getName(), Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(registarText);
        
        nomeLabel = new Label("Nome: ");
        nomeTextField = new TextField();
        nomeTextField.setText(user.getName());
        moradaLabel = new Label("Morada: ");
        moradaTextField = new TextField();
        moradaTextField.setText(user.getAddress());
        emailLabel = new Label("E-Mail: ");
        emailTextField = new TextField();
        emailTextField.setText(user.getEmail());
        
        grid = createGridPane();
        grid.add(nomeLabel, 0, 0);
        grid.add(nomeTextField, 1, 0);
        grid.add(moradaLabel, 0, 1);
        grid.add(moradaTextField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailTextField, 1 , 2);
        
        registarButton = createButton("Alterar");
        
        registarButton.setOnAction(e ->
        {
            try 
            {
               user.setName(nomeTextField.getText());
               user.setAddress(moradaTextField.getText());
               user.setEmail(emailTextField.getText());
               
               Stage stage = new Stage();
               stage.setTitle("Menu");                
               MenuInicial menu = new MenuInicial();
               stage.initModality(Modality.APPLICATION_MODAL);
               Scene scene = new Scene(menu, 470, 400);
               stage.setScene(scene);
               stage.show();
               ((Stage) registarButton.getScene().getWindow()).close();
               
            } catch (ClienteIllegalArgumentException ex) 
            {
                System.out.println(ex.getMessage());
            }
        });
        
        cancelarButton = createButton("Cancelar");
        
        cancelarButton.setOnAction(e ->
        {
            Stage stage = new Stage();
            stage.setTitle("Menu");                
            MenuInicial menu = new MenuInicial();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(menu, 470, 400);
            stage.setScene(scene);
            stage.show();
            ((Stage) cancelarButton.getScene().getWindow()).close();
        });
        
        bottomBox = new HBox();
        bottomBox.setSpacing(35);
        bottomBox.setPadding(new Insets (0, 0 ,15, 0));
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().add(registarButton);
        bottomBox.getChildren().add(cancelarButton);
        
        setTop(headerBox);
        setCenter(grid);
        setBottom(bottomBox);
    }

    
    private Text createText(String texto, Color cor, String fontName, int fontSize){        
        Text newTexto = new Text(texto);
        newTexto.setFill(cor);
        newTexto.setFont(Font.font (fontName, fontSize));
        return newTexto;
    }
    
    private GridPane createGridPane(){
        GridPane newGrid = new GridPane();
        newGrid.setVgap(10);
        newGrid.setHgap(10);
        newGrid.setPadding(new Insets(15, 0, 0, 0));
        return newGrid;
      }
     private Button createButton(String text){
        Button newButton = new Button(text);
        newButton.setMinWidth(100);
        return newButton;
    }
    
}
