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
import javafx.scene.control.TextField;
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
 * Executa todos os módulos do sistema
 * @author Diogo Venancio e André Gonçalves
 */
public class Modulos extends BorderPane {

    private HBox headerBox;
    private HBox bottomBox;
    private Text headerText;
    private GridPane buttonsGrid;
    private Button luminosidade;
    private Button temperatura;
    private Button alarme;
    private Button sair;

    public Modulos() 
    {
        setup();
    }

    private void setup() 
    {
        headerBox = new HBox();
        headerBox.setPadding(new Insets(15, 0, 15, 0));
        headerBox.setAlignment(Pos.CENTER);
        headerText = createText("Módulos", Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(headerText);
        buttonsGrid = createButtonsGrid();
        bottomBox = new HBox();
        bottomBox.setPadding(new Insets(15, 0, 15, 0));
        bottomBox.setAlignment(Pos.CENTER);
        sair = createButton("Seta.png");

        sair.setTranslateX(5);
        
        luminosidade.setPrefWidth(200);
        luminosidade.setTranslateX(25);
        luminosidade.setTranslateY(40);
        
        temperatura.setPrefWidth(200);
        temperatura.setTranslateX(25);
        temperatura.setTranslateY(100);
        
        alarme.setPrefWidth(200);
        alarme.setTranslateX(25);
        alarme.setTranslateY(100);
        
        sair.setOnAction(e
                -> {
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

    private Text createText(String texto, Color cor, String fontName, int fontSize) {
        Text newTexto = new Text(texto);
        newTexto.setFill(cor);
        newTexto.setFont(Font.font(fontName, fontSize));
        return newTexto;
    }

    private GridPane createButtonsGrid() 
    {
        GridPane newGrid = new GridPane();
        newGrid.setVgap(30);
        
        luminosidade = new Button("Modulo Luminosidade");
        luminosidade.setStyle("-fx-font-size: 10pt;");
        luminosidade.setOnAction(e -> 
        {
            try 
            {
                ConsolaCentral.getInstance().moduloLuminosidade();
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Modulo aplicado com sucesso!");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoAt = new Button("OK!");
                botaoAt.setOnAction(ex -> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoAt.getScene().getWindow()).close();
                    ((Stage) luminosidade.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoAt);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
                stage.setTitle("Modulo Luminosidade");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) 
            {
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Ocorreu um erro a aplicar o Modulo!");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoError = new Button("OK!");
                botaoError.setOnAction(exx-> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoError.getScene().getWindow()).close();
                    ((Stage) luminosidade.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoError);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
                stage.setTitle("Error");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            }    
        });

        temperatura = new Button("Modulo Temperatura");
        temperatura.setStyle("-fx-font-size: 10pt;");
        temperatura.setOnAction(e -> 
        {
            try 
            {
                ConsolaCentral.getInstance().moduloTemp();
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Modulo aplicado com sucesso!");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoAt = new Button("OK!");
                botaoAt.setOnAction(ex -> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoAt.getScene().getWindow()).close();
                    ((Stage) luminosidade.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoAt);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
                stage.setTitle("Modulo Temperatura");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) 
            {
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Ocorreu um erro a aplicar o Modulo!");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");

                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoError = new Button("OK!");
                botaoError.setOnAction(exx-> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoError.getScene().getWindow()).close();
                    ((Stage) luminosidade.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoError);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem, painelBotoes);
            
                stage.setTitle("Error");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            } 
        });

        alarme = new Button("Modulo alarme");
        alarme.setStyle("-fx-font-size: 10pt;");
        alarme.setOnAction(e -> 
        {
            if(ConsolaCentral.getInstance().getPin() == 0)
            {  
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Insira um pin a definir");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
                TextField valuePin = new TextField();
            
                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoAt = new Button("OK");
                botaoAt.setOnAction(ex -> 
                {
                    ConsolaCentral.getInstance().setPin(Integer.parseInt(valuePin.getText()));
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoAt.getScene().getWindow()).close();
                    ((Stage) alarme.getScene().getWindow()).close(); 
                });
            
                Button botaoSen = new Button("Cancel");
                botaoSen.setOnAction(ex -> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoSen.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoAt, botaoSen);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem,valuePin,painelBotoes);
            
                stage.setTitle("Definir Pin");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            }else
            {
                Stage stage = new Stage();  

                VBox painelPrincipal = new VBox(10);
                painelPrincipal.setPadding(new Insets(10));
                painelPrincipal.setAlignment(Pos.CENTER);
                Label apresentacaoMensagem = new Label("Módulo ativo! Escolha uma das opções.");
                apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
                TextField valueSlider = new TextField();
            
                final HBox painelBotoes = new HBox(10);
                painelBotoes.setAlignment(Pos.CENTER);
                Button botaoAt = new Button("Ligar");
                botaoAt.setOnAction(ex -> 
                {
                    ConsolaCentral.getInstance().moduloAlarme(ConsolaCentral.getInstance().getPin());
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoAt.getScene().getWindow()).close();
                    ((Stage) alarme.getScene().getWindow()).close(); 
                });
            
                Button botaoAlt = new Button("Alterar");
                botaoAlt.setOnAction(ex -> 
                {
                    Stage stage1 = new Stage();  

                    VBox painelPrincipal1 = new VBox(10);
                    painelPrincipal1.setPadding(new Insets(10));
                    painelPrincipal1.setAlignment(Pos.CENTER);
                    Label apresentacaoMensagem1 = new Label("Insira um pin a definir");
                    apresentacaoMensagem1.setStyle("-fx-font-weight: bold;");
                    TextField valuePin1 = new TextField();
            
                    final HBox painelBotoes1 = new HBox(10);
                    painelBotoes1.setAlignment(Pos.CENTER);
                    Button botaoAt1 = new Button("OK");
                    botaoAt1.setOnAction(exx -> 
                    {
                        ConsolaCentral.getInstance().setPin(Integer.parseInt(valuePin1.getText()));
                        Stage stage2 = new Stage();
                        stage2.setTitle("Menu");                
                        MenuInicial menu = new MenuInicial();
                        stage2.initModality(Modality.APPLICATION_MODAL);
                        Scene scene2 = new Scene(menu, 470, 400);
                        stage2.setScene(scene2);
                        stage2.show();
                        ((Stage) botaoAt1.getScene().getWindow()).close();
                        ((Stage) alarme.getScene().getWindow()).close(); 
                    });
            
                    Button botaoSen1 = new Button("Cancel");
                    botaoSen1.setOnAction(exx -> 
                    {
                        Stage stage2 = new Stage();
                        stage2.setTitle("Menu");                
                        MenuInicial menu = new MenuInicial();
                        stage2.initModality(Modality.APPLICATION_MODAL);
                        Scene scene2 = new Scene(menu, 470, 400);
                        stage2.setScene(scene2);
                        stage2.show();
                        ((Stage) botaoSen1.getScene().getWindow()).close();
                    });
            
                    painelBotoes1.getChildren().addAll(botaoAt1, botaoSen1);
                    painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,valuePin1,painelBotoes1);
            
                    stage1.setTitle("Definir Pin");
                    Scene scene1 = new Scene(painelPrincipal1);
                    stage1.setResizable(false);
                    stage1.initStyle(stage1.getStyle().UTILITY);
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    stage1.setIconified(false);
                    stage1.centerOnScreen();
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoAlt.getScene().getWindow()).close();
                });
                
                Button botaoSen = new Button("Cancel");
                botaoSen.setOnAction(ex -> 
                {
                    Stage stage1 = new Stage();
                    stage1.setTitle("Menu");                
                    MenuInicial menu = new MenuInicial();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Scene scene1 = new Scene(menu, 470, 400);
                    stage1.setScene(scene1);
                    stage1.show();
                    ((Stage) botaoSen.getScene().getWindow()).close();
                });
            
                painelBotoes.getChildren().addAll(botaoAt, botaoAlt, botaoSen);
                painelPrincipal.getChildren().addAll(apresentacaoMensagem,painelBotoes);
            
                stage.setTitle("Módulo ativo");
                Scene scene = new Scene(painelPrincipal);
                stage.setResizable(false);
                stage.initStyle(stage.getStyle().UTILITY);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            }
        });

        newGrid.add(luminosidade, 0, 0);
        newGrid.add(temperatura, 0, 0);
        newGrid.add(alarme, 0, 1);
        return newGrid;
    }

    private Button createButton(String imageName) {
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/" + imageName), 60, 60, false, false);
        Button newButton = new Button("", new ImageView(imageButton));
        return newButton;
    }
}