/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import Sensores.*;
import java.util.ArrayList;
import java.util.List;
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
 * Selecionar um sensor para inserir e a respetiva divisão
 * @author Diogo Venancio e André Gonçalves
 */
public class Sensores extends BorderPane {

    private HBox headerBox;
    private HBox bottomBox;
    private Text headerText;
    private GridPane buttonsGrid;
    private Button luminosidade;
    private Button temperatura;
    private Button porta;
    private Button movimento;
    private Button sair;

    public Sensores() {
        setup();
    }

    private void setup() {
        headerBox = new HBox();
        headerBox.setPadding(new Insets(15, 0, 15, 0));
        headerBox.setAlignment(Pos.CENTER);
        headerText = createText("Sensores", Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(headerText);
        buttonsGrid = createButtonsGrid();
        bottomBox = new HBox();
        bottomBox.setPadding(new Insets(15, 0, 15, 0));
        bottomBox.setAlignment(Pos.CENTER);
        sair = createButton("Seta.png");

        luminosidade.setTranslateX(90);
        temperatura.setTranslateX(140);
        
        porta.setTranslateX(90);
        porta.setTranslateY(30);
        movimento.setTranslateX(140);
        movimento.setTranslateY(30);
        
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

    private GridPane createButtonsGrid() {
        GridPane newGrid = new GridPane();
        newGrid.setVgap(30);
        
        List<Sensor> eq = new ArrayList<>();
        
        luminosidade = createButton("luminosidade.png");
        luminosidade.setOnAction(e
                -> 
        {
            Stage stage1 = new Stage();

            VBox painelPrincipal1 = new VBox(10);
            painelPrincipal1.setPadding(new Insets(10));
            painelPrincipal1.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem1 = new Label("Escolha qual a divisão a inserir");
            apresentacaoMensagem1.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes1 = new HBox(10);
            painelBotoes1.setAlignment(Pos.CENTER);
            Button salabotao = new Button("Sala");
            salabotao.setOnAction(a
                    -> 
            {
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv("Sala"));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Sala"), lumino);
                
                ConsolaCentral.getInstance().getDivisao("Sala", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) salabotao.getScene().getWindow()).close();
                ((Stage) luminosidade.getScene().getWindow()).close();
            });
            Button cozinhabotao = new Button("Cozinha");
            cozinhabotao.setOnAction(a
                    -> 
            {
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv("Cozinha"));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Cozinha"), lumino);
                
                ConsolaCentral.getInstance().getDivisao("Cozinha", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) cozinhabotao.getScene().getWindow()).close();
                ((Stage) luminosidade.getScene().getWindow()).close();
            });
            Button wcbotao = new Button("WC");
            wcbotao.setOnAction(a
                    -> 
            {
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv("Banho"));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Banho"), lumino);
                
                ConsolaCentral.getInstance().getDivisao("Banho", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) wcbotao.getScene().getWindow()).close();
                ((Stage) luminosidade.getScene().getWindow()).close();
            });
            Button quartobotao = new Button("Quarto1");
            quartobotao.setOnAction(a
                    -> 
            {
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv("Quarto1"));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto1"), lumino);
                
                ConsolaCentral.getInstance().getDivisao("Quarto1", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quartobotao.getScene().getWindow()).close();
                ((Stage) luminosidade.getScene().getWindow()).close();
            });
            Button quarto2botao = new Button("Quarto2");
            quarto2botao.setOnAction(a
                    -> 
            {
                Sensor lumino = new Luminosidade(ConsolaCentral.getInstance().getDiv("Quarto2"));
                
                ConsolaCentral.getInstance().addEquipamento(lumino); 
                eq.add(lumino);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto2"), lumino);
                
                ConsolaCentral.getInstance().getDivisao("Quarto2", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quarto2botao.getScene().getWindow()).close();
                ((Stage) luminosidade.getScene().getWindow()).close();
            });

            painelBotoes1.getChildren().addAll(salabotao, cozinhabotao, wcbotao, quartobotao, quarto2botao);
            painelPrincipal1.getChildren().addAll(apresentacaoMensagem1, painelBotoes1);

            stage1.setTitle("Equipamento");
            Scene scene = new Scene(painelPrincipal1);
            stage1.setResizable(false);
            stage1.initStyle(stage1.getStyle().UTILITY);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setIconified(false);
            stage1.centerOnScreen();
            stage1.setScene(scene);
            stage1.show();
            ((Stage) luminosidade.getScene().getWindow()).close();
        });

        temperatura = createButton("temperatura.png");
        temperatura.setOnAction(e
                -> 
        {
            Stage stage1 = new Stage();

            VBox painelPrincipal1 = new VBox(10);
            painelPrincipal1.setPadding(new Insets(10));
            painelPrincipal1.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem1 = new Label("Escolha qual a divisão a inserir");
            apresentacaoMensagem1.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes1 = new HBox(10);
            painelBotoes1.setAlignment(Pos.CENTER);
            Button salabotao = new Button("Sala");
            salabotao.setOnAction(a
                    -> 
            {
                Sensor temp = new Temperatura(ConsolaCentral.getInstance().getDiv("Sala"));
                
                ConsolaCentral.getInstance().addEquipamento(temp); 
                eq.add(temp);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Sala"), temp);
                
                ConsolaCentral.getInstance().getDivisao("Sala", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) salabotao.getScene().getWindow()).close();
                ((Stage) temperatura.getScene().getWindow()).close();
            });
            Button cozinhabotao = new Button("Cozinha");
            cozinhabotao.setOnAction(a
                    -> 
            {
                Sensor temp = new Temperatura(ConsolaCentral.getInstance().getDiv("Cozinha"));
                
                ConsolaCentral.getInstance().addEquipamento(temp); 
                eq.add(temp);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Cozinha"), temp);
                
                ConsolaCentral.getInstance().getDivisao("Cozinha", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) cozinhabotao.getScene().getWindow()).close();
                ((Stage) temperatura.getScene().getWindow()).close();
            });
            Button wcbotao = new Button("WC");
            wcbotao.setOnAction(a
                    -> 
            {
                Sensor temp = new Temperatura(ConsolaCentral.getInstance().getDiv("Banho"));
                
                ConsolaCentral.getInstance().addEquipamento(temp); 
                eq.add(temp);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Banho"), temp);
                
                ConsolaCentral.getInstance().getDivisao("Banho", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) wcbotao.getScene().getWindow()).close();
                ((Stage) temperatura.getScene().getWindow()).close();
            });
            Button quartobotao = new Button("Quarto1");
            quartobotao.setOnAction(a
                    -> 
            {
                Sensor temp = new Temperatura(ConsolaCentral.getInstance().getDiv("Quarto1"));
                
                ConsolaCentral.getInstance().addEquipamento(temp); 
                eq.add(temp);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto1"), temp);
                
                ConsolaCentral.getInstance().getDivisao("Quarto1", eq.get(0));
                eq.remove(eq.get(0)); 


                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quartobotao.getScene().getWindow()).close();
                ((Stage) temperatura.getScene().getWindow()).close();
            });
            Button quarto2botao = new Button("Quarto2");
            quarto2botao.setOnAction(a
                    -> 
            {
                Sensor temp = new Temperatura(ConsolaCentral.getInstance().getDiv("Quarto2"));
                
                ConsolaCentral.getInstance().addEquipamento(temp); 
                eq.add(temp);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto2"), temp);
                
                ConsolaCentral.getInstance().getDivisao("Quarto2", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quarto2botao.getScene().getWindow()).close();
                ((Stage) temperatura.getScene().getWindow()).close();
            });

            painelBotoes1.getChildren().addAll(salabotao, cozinhabotao, wcbotao, quartobotao, quarto2botao);
            painelPrincipal1.getChildren().addAll(apresentacaoMensagem1, painelBotoes1);

            stage1.setTitle("Equipamento");
            Scene scene = new Scene(painelPrincipal1);
            stage1.setResizable(false);
            stage1.initStyle(stage1.getStyle().UTILITY);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setIconified(false);
            stage1.centerOnScreen();
            stage1.setScene(scene);
            stage1.show();
            ((Stage) temperatura.getScene().getWindow()).close();
        });

        porta = createButton("porta.png");
        porta.setOnAction(e
                -> 
        {
            Stage stage1 = new Stage();

            VBox painelPrincipal1 = new VBox(10);
            painelPrincipal1.setPadding(new Insets(10));
            painelPrincipal1.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem1 = new Label("Escolha qual a divisão a inserir");
            apresentacaoMensagem1.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes1 = new HBox(10);
            painelBotoes1.setAlignment(Pos.CENTER);
            Button salabotao = new Button("Sala");
            salabotao.setOnAction(a
                    -> 
            {
                Sensor port = new Porta(ConsolaCentral.getInstance().getDiv("Sala"));
                
                ConsolaCentral.getInstance().addEquipamento(port); 
                eq.add(port);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Sala"), port);
                
                ConsolaCentral.getInstance().getDivisao("Sala", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) salabotao.getScene().getWindow()).close();
                ((Stage) porta.getScene().getWindow()).close();
            });
            Button cozinhabotao = new Button("Cozinha");
            cozinhabotao.setOnAction(a
                    -> 
            {
                Sensor port = new Porta(ConsolaCentral.getInstance().getDiv("Cozinha"));
                
                ConsolaCentral.getInstance().addEquipamento(port); 
                eq.add(port);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Cozinha"), port);
                
                ConsolaCentral.getInstance().getDivisao("Cozinha", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) cozinhabotao.getScene().getWindow()).close();
                ((Stage) porta.getScene().getWindow()).close();
            });
            Button wcbotao = new Button("WC");
            wcbotao.setOnAction(a
                    -> 
            {
                Sensor port = new Porta(ConsolaCentral.getInstance().getDiv("Banho"));
                
                ConsolaCentral.getInstance().addEquipamento(port); 
                eq.add(port);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Banho"), port);
                
                ConsolaCentral.getInstance().getDivisao("Banho", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) wcbotao.getScene().getWindow()).close();
                ((Stage) porta.getScene().getWindow()).close();
            });
            Button quartobotao = new Button("Quarto1");
            quartobotao.setOnAction(a
                    -> 
            {
                Sensor port = new Porta(ConsolaCentral.getInstance().getDiv("Quarto1"));
                
                ConsolaCentral.getInstance().addEquipamento(port); 
                eq.add(port);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto1"), port);
                
                ConsolaCentral.getInstance().getDivisao("Quarto1", eq.get(0));
                eq.remove(eq.get(0)); 


                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quartobotao.getScene().getWindow()).close();
                ((Stage) porta.getScene().getWindow()).close();
            });
            Button quarto2botao = new Button("Quarto2");
            quarto2botao.setOnAction(a
                    -> 
            {
                Sensor port = new Porta(ConsolaCentral.getInstance().getDiv("Quarto2"));
                
                ConsolaCentral.getInstance().addEquipamento(port); 
                eq.add(port);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto2"), port);
                
                ConsolaCentral.getInstance().getDivisao("Quarto2", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quarto2botao.getScene().getWindow()).close();
                ((Stage) porta.getScene().getWindow()).close();
            });

            painelBotoes1.getChildren().addAll(salabotao, cozinhabotao, wcbotao, quartobotao, quarto2botao);
            painelPrincipal1.getChildren().addAll(apresentacaoMensagem1, painelBotoes1);

            stage1.setTitle("Equipamento");
            Scene scene = new Scene(painelPrincipal1);
            stage1.setResizable(false);
            stage1.initStyle(stage1.getStyle().UTILITY);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setIconified(false);
            stage1.centerOnScreen();
            stage1.setScene(scene);
            stage1.show();
            ((Stage) porta.getScene().getWindow()).close();
        });

        movimento = createButton("movimento.jpg");
        movimento.setOnAction(e
                -> 
        {
            Stage stage1 = new Stage();

            VBox painelPrincipal1 = new VBox(10);
            painelPrincipal1.setPadding(new Insets(10));
            painelPrincipal1.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem1 = new Label("Escolha qual a divisão a inserir");
            apresentacaoMensagem1.setStyle("-fx-font-weight: bold;");

            final HBox painelBotoes1 = new HBox(10);
            painelBotoes1.setAlignment(Pos.CENTER);
            Button salabotao = new Button("Sala");
            salabotao.setOnAction(a
                    -> 
            {
                Sensor move = new Movimento(ConsolaCentral.getInstance().getDiv("Sala"));
                
                ConsolaCentral.getInstance().addEquipamento(move); 
                eq.add(move);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Sala"), move);
                
                ConsolaCentral.getInstance().getDivisao("Sala", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) salabotao.getScene().getWindow()).close();
                ((Stage) movimento.getScene().getWindow()).close();
            });
            Button cozinhabotao = new Button("Cozinha");
            cozinhabotao.setOnAction(a
                    -> 
            {
                Sensor move = new Movimento(ConsolaCentral.getInstance().getDiv("Cozinha"));
                
                ConsolaCentral.getInstance().addEquipamento(move); 
                eq.add(move);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Cozinha"), move);
                
                ConsolaCentral.getInstance().getDivisao("Cozinha", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) cozinhabotao.getScene().getWindow()).close();
                ((Stage) movimento.getScene().getWindow()).close();
            });
            Button wcbotao = new Button("WC");
            wcbotao.setOnAction(a
                    -> 
            {
                Sensor move = new Movimento(ConsolaCentral.getInstance().getDiv("Banho"));
                
                ConsolaCentral.getInstance().addEquipamento(move); 
                eq.add(move);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Banho"), move);
                
                ConsolaCentral.getInstance().getDivisao("Banho", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) wcbotao.getScene().getWindow()).close();
                ((Stage) movimento.getScene().getWindow()).close();
            });
            Button quartobotao = new Button("Quarto1");
            quartobotao.setOnAction(a
                    -> 
            {
                Sensor move = new Movimento(ConsolaCentral.getInstance().getDiv("Quarto1"));
                
                ConsolaCentral.getInstance().addEquipamento(move); 
                eq.add(move);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto1"), move);
                
                ConsolaCentral.getInstance().getDivisao("Quarto1", eq.get(0));
                eq.remove(eq.get(0)); 


                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quartobotao.getScene().getWindow()).close();
                ((Stage) movimento.getScene().getWindow()).close();
            });
            Button quarto2botao = new Button("Quarto2");
            quarto2botao.setOnAction(a
                    -> 
            {
                Sensor move = new Movimento(ConsolaCentral.getInstance().getDiv("Quarto2"));
                
                ConsolaCentral.getInstance().addEquipamento(move); 
                eq.add(move);
                    
                ConsolaCentral.getInstance().addDivisaoSensor(ConsolaCentral.getInstance().getDiv("Quarto2"), move);
                
                ConsolaCentral.getInstance().getDivisao("Quarto2", eq.get(0));
                eq.remove(eq.get(0)); 

                Stage stage2 = new Stage();
                stage2.setTitle("Menu");
                MenuInicial menu = new MenuInicial();
                stage2.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage2.setScene(scene);
                stage2.show();
                ((Stage) quarto2botao.getScene().getWindow()).close();
                ((Stage) movimento.getScene().getWindow()).close();
            });

            painelBotoes1.getChildren().addAll(salabotao, cozinhabotao, wcbotao, quartobotao, quarto2botao);
            painelPrincipal1.getChildren().addAll(apresentacaoMensagem1, painelBotoes1);

            stage1.setTitle("Equipamento");
            Scene scene = new Scene(painelPrincipal1);
            stage1.setResizable(false);
            stage1.initStyle(stage1.getStyle().UTILITY);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.setIconified(false);
            stage1.centerOnScreen();
            stage1.setScene(scene);
            stage1.show();
            ((Stage) movimento.getScene().getWindow()).close();
        });

        newGrid.add(luminosidade, 0, 0);
        newGrid.add(temperatura, 4, 0);
        newGrid.add(porta, 0, 2);
        newGrid.add(movimento, 4, 2);
        return newGrid;
    }

    private Button createButton(String imageName) {
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/" + imageName), 60, 60, false, false);
        Button newButton = new Button("", new ImageView(imageButton));
        return newButton;
    }
}
