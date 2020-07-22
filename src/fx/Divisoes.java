/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;


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
 * Mostra todas as divisões disponiveis, ao qual, ao selecionar uma, irá poder visualizar os equipamentos nela instados
 * @author Diogo Venancio e André Gonçalves
 */
public class Divisoes extends BorderPane
{
    private HBox headerBox;
    private HBox bottomBox;
    private Text headerText;
    private GridPane buttonsGrid;
    private Button sala;
    private Button cozinha;
    private Button wc;
    private Button quarto1;
    private Button quarto2;
    private Button sair;
    
    public Divisoes()
    {
        setup();
    }
    
    private void setup()
    {
        headerBox = new HBox();  
        headerBox.setPadding(new Insets(15, 0, 15, 0));
        headerBox.setAlignment(Pos.CENTER);
        headerText = createText("Divisões", Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(headerText);
        buttonsGrid = createButtonsGrid();
        bottomBox = new HBox();
        bottomBox.setPadding(new Insets(15, 0, 15, 0));
        bottomBox.setAlignment(Pos.CENTER);
        sair = createButton("Seta.png");
        
        sair.setOnAction(e ->
        {
            Stage stage = new Stage();  
            stage.setTitle("Menu");
            MenuInicial menu = new MenuInicial();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(menu, 470, 400);
            stage.setScene(scene);
            stage.show();
            ((Stage) sair.getScene().getWindow()).close();
        });
        
        
        bottomBox.getChildren().add(sair);
    
        StackPane left = new StackPane();
        left.setPrefWidth(50);
        setLeft(left);
        StackPane right = new StackPane();
        right.setPrefWidth(25);
        setLeft(right);
    
        setTop(headerBox);
        setCenter(buttonsGrid);
        setBottom(bottomBox);
    }
    
    
    private Text createText(String texto, Color cor, String fontName, int fontSize)
    {        
        Text newTexto = new Text(texto);
        newTexto.setFill(cor);
        newTexto.setFont(Font.font (fontName, fontSize));
        return newTexto;
    }
    
    private GridPane createButtonsGrid()
    {
        GridPane newGrid = new GridPane();
        newGrid.setVgap(30);
        sala = createButton("Sala.png");
        sala.setOnAction(e ->
        {            
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja selecionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Sala"),"Atuadores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) sala.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Sala"),"Sensores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) sala.getScene().getWindow()).close();
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
        
        cozinha = createButton("Cozinha.png");
        cozinha.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja selecionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Cozinha"),"Atuadores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) cozinha.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Cozinha"),"Sensores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) cozinha.getScene().getWindow()).close();
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
        
        
        wc = createButton("WC.png");
        wc.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja selecionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Banho"),"Atuadores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) wc.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Banho"),"Sensores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) wc.getScene().getWindow()).close();
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
        
        quarto1 = createButton("Quarto 1.png");
        quarto1.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja selecionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Quarto1"),"Atuadores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) quarto1.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Quarto1"),"Sensores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) quarto1.getScene().getWindow()).close();
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
        
        quarto2 = createButton("Quarto 2.png");
        quarto2.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Qual equipamento que deseja selecionar?");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("Atuadores");
            botaoAt.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Quarto2"),"Atuadores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) quarto2.getScene().getWindow()).close();
            });
            
            Button botaoSen = new Button("Sensores");
            botaoSen.setOnAction(ex -> 
            {
                Stage stage1 = new Stage();  
                stage1.setTitle("Divisao");
                ViewDivisao menu = new ViewDivisao(ConsolaCentral.getInstance().getDiv("Quarto2"),"Sensores");
                stage1.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 800, 400);
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoSen.getScene().getWindow()).close();
                ((Stage) quarto2.getScene().getWindow()).close();
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
        
        newGrid.add(sala, 0, 0);
        newGrid.add(cozinha, 2, 0);
        newGrid.add(wc, 4, 0);
        newGrid.add(quarto1, 1, 1);
        newGrid.add(quarto2, 3, 1);
        return newGrid;
    }
    
    private Button createButton(String imageName)
    {
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/"+imageName), 60, 60, false, false);
        Button newButton = new Button("", new ImageView(imageButton));
        return newButton;
    }
}
