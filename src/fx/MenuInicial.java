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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projetopoo.ConsolaCentral;

/**
 * Corresponde ao menu inicial da aplicação
 * @author Diogo Venancio e André Gonçalves
 */
public class MenuInicial extends BorderPane
{
    private HBox headerBox;
    private HBox bottomBox;
    private Text headerText;
    private GridPane buttonsGrid;
    private Button divisoes;
    private Button atuadoresSensores;
    private Button modulos;
    private Button dados;
    private Button sair;
    
    
    public MenuInicial() 
    {
        setup();
    }
    
    private void setup()
    {        
        headerBox = new HBox();  
        headerBox.setPadding(new Insets(15, 0, 15, 0));
        headerBox.setAlignment(Pos.CENTER);
        headerText = createText("Bem-Vindo " + ConsolaCentral.getInstance().getCurrentClient(), Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(headerText);
        buttonsGrid = createButtonsGrid();
        bottomBox = new HBox();
        bottomBox.setPadding(new Insets(15, 0, 15, 0));
        bottomBox.setAlignment(Pos.CENTER);       
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/Seta.png"), 60, 60, false, false);
        sair = new Button("", new ImageView(imageButton));  
        
        sair.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Deseja guardar o sistema?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Sim");
            botaoAt.setOnAction(ex -> 
            {
                try 
                {
                    ConsolaCentral.getInstance().save(new File("sistema.txt"));
                } catch (IOException ex1) 
                {
                    Logger.getLogger(MenuInicial.class.getName()).log(Level.SEVERE, null, ex1);
                }
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) sair.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Não");
            botaoSen.setOnAction(ex -> 
            {
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) sair.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
            stage.setTitle("Sistema");
            Scene scene = new Scene(painelPrincipal);
            stage.setResizable(false);
            stage.initStyle(stage.getStyle().UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        });
        
        bottomBox.getChildren().add(sair);
    
        setTop(headerBox);
        setCenter(buttonsGrid);
        setBottom(bottomBox);
    
        StackPane left = new StackPane();
        left.setPrefWidth(50);
        setLeft(left);
        StackPane right = new StackPane();
        right.setPrefWidth(25);
        setLeft(right);   
    }
    
    private Text createText(String texto, Color cor, String fontName, int fontSize)
    {        
        Text newTexto = new Text(texto);
        newTexto.setFill(cor);
        newTexto.setFont(Font.font (fontName, fontSize));
        return newTexto;
    }
     
      
    private Button createButton(String text)
    {
        Button newButton = new Button(text);
        newButton.setMinWidth(200);
        newButton.setMinHeight(75);
        return newButton;
    }
    
    private GridPane createButtonsGrid()
    {
        
        GridPane newGrid = new GridPane();
        newGrid.setVgap(30);
        newGrid.setHgap(20);
        divisoes = createButton("Divisões");
        
        divisoes.setOnAction(e ->
        {
            Stage stage = new Stage();
            stage.setTitle("Divisoes");
            Divisoes div = new Divisoes();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(div, 430, 400);
            stage.setScene(scene);
            stage.show();
            ((Stage) divisoes.getScene().getWindow()).close();
        });
        
        atuadoresSensores = createButton("Adicionar Equipamento");
        atuadoresSensores.setOnAction(e ->
        {         
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja adicionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Atuadores");
                Atuadores menu = new Atuadores();
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 430, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) atuadoresSensores.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Sensores");
                Sensores menu = new Sensores();
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 430, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) atuadoresSensores.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
            stage.setTitle("Equipamento");
            Scene scene = new Scene(painelPrincipal);
            stage.setResizable(false);
            stage.initStyle(stage.getStyle().UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
        });
        
        modulos = createButton("Módulos");
        modulos.setOnAction(e -> 
        {
            Stage stage = new Stage();
            stage.setTitle("Módulos");
            Modulos div = new Modulos();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(div, 300, 400);
            stage.setScene(scene);
            stage.show();
            ((Stage) dados.getScene().getWindow()).close();
        });
        
        dados = createButton("Dados");
        dados.setOnAction(e -> 
        {
            Stage stage = new Stage();
            stage.setTitle("Dados");
            AlterarUtilizador div = new AlterarUtilizador();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(div, 320, 250);
            stage.setScene(scene);
            stage.show();
            ((Stage) dados.getScene().getWindow()).close();
        });
        
        newGrid.add(divisoes, 0, 0);
        newGrid.add(atuadoresSensores, 1, 0);
        newGrid.add(modulos, 0, 1);
        newGrid.add(dados, 1, 1);
        newGrid.setPadding(new Insets(25, 0, 0, 0));
        return newGrid;
    }
}
