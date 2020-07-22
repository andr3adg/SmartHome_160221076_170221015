/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import Atuadores.*;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
import projetopoo.Equipamento;

/**
 * Selecionar um atuador para inserir e a respetiva divisão
 * @author Diogo Venancio e André Gonçalves
 */
public class Atuadores extends BorderPane
{
    private HBox headerBox;
    private HBox bottomBox;
    private Text headerText;
    private GridPane buttonsGrid;
    private Button lampada;
    private Button condicionado;
    private Button sirene;
    private Button tomada;
    private Button camaraFoto;
    private Button camaraVideo;
    private Button sair;
    
    public Atuadores()
    {
        setup();
    }
    
    private void setup()
    {
        headerBox = new HBox();  
        headerBox.setPadding(new Insets(15, 0, 15, 0));
        headerBox.setAlignment(Pos.CENTER);
        headerText = createText("Atuadores", Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(headerText);
        buttonsGrid = createButtonsGrid();
        bottomBox = new HBox();
        bottomBox.setPadding(new Insets(15, 0, 15, 0));
        bottomBox.setAlignment(Pos.CENTER);
        sair = createButton("Seta.png");
        
        lampada.setTranslateX(15);
        condicionado.setTranslateX(75);
        sirene.setTranslateX(135);
        
        tomada.setTranslateX(15);
        tomada.setTranslateY(30);
        camaraFoto.setTranslateX(75);
        camaraFoto.setTranslateY(30);
        camaraVideo.setTranslateX(135);
        camaraVideo.setTranslateY(30);
        
        
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
        
        List<Equipamento> eq = new ArrayList<>();
        
        lampada = createButton("luminosidade.png");
        lampada.setOnAction(e ->
        {            
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Nível de Luminosidade");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
            Slider slider = new Slider(0, 20, 10);
            slider.setLayoutX(50);
            slider.setLayoutY(150);
            slider.setMinorTickCount(2);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            TextField valueSlider = new TextField();
            
            slider.valueProperty().addListener((observable, oldValue, newValue) -> 
            {
                if (oldValue != null) 
                {
                    valueSlider.textProperty().unbindBidirectional(oldValue.intValue());
                    valueSlider.setText(String.valueOf(oldValue.intValue()));                    
                }
                else
                {
                    valueSlider.textProperty().unbindBidirectional(newValue.intValue());
                    valueSlider.setText(String.valueOf(newValue.intValue()));  
                }
            });
            
            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("OK");
            botaoAt.setOnAction(ex -> 
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
                salabotao.setOnAction(a -> 
                {   
                    Atuador lamp = new Lampada(ConsolaCentral.getInstance().getDiv("Sala"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(lamp);
                    ConsolaCentral.getInstance().addEquipamento(lamp);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), lamp);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz("Sala", Integer.parseInt(valueSlider.getText()));
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
                    ((Stage) lampada.getScene().getWindow()).close();
                });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador lamp = new Lampada(ConsolaCentral.getInstance().getDiv("Cozinha"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(lamp);
                    ConsolaCentral.getInstance().addEquipamento(lamp);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), lamp);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz("Cozinha", Integer.parseInt(valueSlider.getText()));
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
                    ((Stage) lampada.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador lamp = new Lampada(ConsolaCentral.getInstance().getDiv("Banho"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(lamp);
                    ConsolaCentral.getInstance().addEquipamento(lamp);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), lamp);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz("Banho", Integer.parseInt(valueSlider.getText()));
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
                    ((Stage) lampada.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador lamp = new Lampada(ConsolaCentral.getInstance().getDiv("Quarto1"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(lamp);
                    ConsolaCentral.getInstance().addEquipamento(lamp);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), lamp);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz("Quarto1", Integer.parseInt(valueSlider.getText()));
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
                    ((Stage) lampada.getScene().getWindow()).close(); 
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador lamp = new Lampada(ConsolaCentral.getInstance().getDiv("Quarto2"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(lamp);
                    ConsolaCentral.getInstance().addEquipamento(lamp);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), lamp);
                    
                    ConsolaCentral.getInstance().getDivisaoLuz("Quarto2", Integer.parseInt(valueSlider.getText()));
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
                    ((Stage) lampada.getScene().getWindow()).close(); 
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) lampada.getScene().getWindow()).close(); 
            });
            
            Button botaoSen = new Button("Cancel");
            botaoSen.setOnAction(ex -> 
            {
                ((Stage) botaoSen.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem,slider,valueSlider,painelBotoes);
            
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
        
        condicionado = createButton("condicionado.png");
        condicionado.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Nível de Temperatura");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
            Slider slider = new Slider(16, 28, 23);
            slider.setLayoutX(50);
            slider.setLayoutY(150);
            slider.setMinorTickCount(2);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            TextField valueSlider = new TextField();
            
            slider.valueProperty().addListener((observable, oldValue, newValue) -> 
            {
                if (oldValue != null) 
                {
                    valueSlider.textProperty().unbindBidirectional(oldValue.doubleValue());
                    valueSlider.setText(String.valueOf(oldValue.doubleValue()));                    
                }
                else
                {
                    valueSlider.textProperty().unbindBidirectional(newValue.doubleValue());
                    valueSlider.setText(String.valueOf(newValue.doubleValue()));  
                }
            });
            
            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("OK");
            botaoAt.setOnAction(ex -> 
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
                salabotao.setOnAction(a -> 
                {   
                    Atuador cond = new ArCondicionado(ConsolaCentral.getInstance().getDiv("Sala"),Double.parseDouble(valueSlider.getText()));
                    
                    eq.add(cond);
                    ConsolaCentral.getInstance().addEquipamento(cond);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), cond);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp("Sala", Double.parseDouble(valueSlider.getText()));
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
                    ((Stage) condicionado.getScene().getWindow()).close();
                });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador cond = new ArCondicionado(ConsolaCentral.getInstance().getDiv("Cozinha"),Double.parseDouble(valueSlider.getText()));
                    
                    eq.add(cond);
                    ConsolaCentral.getInstance().addEquipamento(cond);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), cond);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp("Cozinha", Double.parseDouble(valueSlider.getText()));
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
                    ((Stage) condicionado.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador cond = new ArCondicionado(ConsolaCentral.getInstance().getDiv("Banho"),Double.parseDouble(valueSlider.getText()));
                    
                    eq.add(cond);
                    ConsolaCentral.getInstance().addEquipamento(cond);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), cond);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp("Banho", Double.parseDouble(valueSlider.getText()));
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
                    ((Stage) condicionado.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador cond = new ArCondicionado(ConsolaCentral.getInstance().getDiv("Quarto1"),Double.parseDouble(valueSlider.getText()));
                    
                    eq.add(cond);
                    ConsolaCentral.getInstance().addEquipamento(cond);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), cond);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp("Quarto1", Double.parseDouble(valueSlider.getText()));
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
                    ((Stage) condicionado.getScene().getWindow()).close();
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador cond = new ArCondicionado(ConsolaCentral.getInstance().getDiv("Quarto2"),Double.parseDouble(valueSlider.getText()));
                    
                    eq.add(cond);
                    ConsolaCentral.getInstance().addEquipamento(cond);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), cond);
                    
                    ConsolaCentral.getInstance().getDivisaoTemp("Quarto2", Double.parseDouble(valueSlider.getText()));
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
                    ((Stage) condicionado.getScene().getWindow()).close();
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) condicionado.getScene().getWindow()).close(); 
            });
            
            Button botaoSen = new Button("Cancel");
            botaoSen.setOnAction(ex -> 
            {
                ((Stage) botaoSen.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem,slider,valueSlider,painelBotoes);
            
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
        
        
        sirene = createButton("sirene.png");
        sirene.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Nível do Som");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
            Slider slider = new Slider(0, 10, 5);
            slider.setLayoutX(50);
            slider.setLayoutY(150);
            slider.setMinorTickCount(2);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            TextField valueSlider = new TextField();
            
            slider.valueProperty().addListener((observable, oldValue, newValue) -> 
            {
                if (oldValue != null) 
                {
                    valueSlider.textProperty().unbindBidirectional(oldValue.intValue());
                    valueSlider.setText(String.valueOf(oldValue.intValue()));                    
                }
                else
                {
                    valueSlider.textProperty().unbindBidirectional(newValue.intValue());
                    valueSlider.setText(String.valueOf(newValue.intValue()));  
                }
            });
            
            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("OK");
            botaoAt.setOnAction(ex -> 
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
                salabotao.setOnAction(a -> 
                {   
                    Atuador sire = new Sirene(ConsolaCentral.getInstance().getDiv("Sala"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(sire);
                    ConsolaCentral.getInstance().addEquipamento(sire);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), sire);
                    
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
                    ((Stage) sirene.getScene().getWindow()).close();
                });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador sire = new Sirene(ConsolaCentral.getInstance().getDiv("Cozinha"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(sire);
                    ConsolaCentral.getInstance().addEquipamento(sire);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), sire);
                    
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
                    ((Stage) sirene.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador sire = new Sirene(ConsolaCentral.getInstance().getDiv("Banho"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(sire);
                    ConsolaCentral.getInstance().addEquipamento(sire);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), sire);
                    
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
                    ((Stage) sirene.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador sire = new Sirene(ConsolaCentral.getInstance().getDiv("Quarto1"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(sire);
                    ConsolaCentral.getInstance().addEquipamento(sire);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), sire);
                    
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
                    ((Stage) sirene.getScene().getWindow()).close();
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador sire = new Sirene(ConsolaCentral.getInstance().getDiv("Quarto2"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(sire);
                    ConsolaCentral.getInstance().addEquipamento(sire);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), sire);
                    
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
                    ((Stage) sirene.getScene().getWindow()).close();
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) sirene.getScene().getWindow()).close(); 
            });
            
            Button botaoSen = new Button("Cancel");
            botaoSen.setOnAction(ex -> 
            {
                ((Stage) botaoSen.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem,slider,valueSlider,painelBotoes);
            
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
        
        tomada = createButton("tomada.png");
        tomada.setOnAction(e ->
        {
            Stage stage = new Stage();  

            VBox painelPrincipal = new VBox(10);
            painelPrincipal.setPadding(new Insets(10));
            painelPrincipal.setAlignment(Pos.CENTER);
            Label apresentacaoMensagem = new Label("Tempo para o temporizador");
            apresentacaoMensagem.setStyle("-fx-font-weight: bold;");
            Slider slider = new Slider(0, 10, 5);
            slider.setLayoutX(50);
            slider.setLayoutY(150);
            slider.setMinorTickCount(2);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            TextField valueSlider = new TextField();
            
            slider.valueProperty().addListener((observable, oldValue, newValue) -> 
            {
                if (oldValue != null) 
                {
                    valueSlider.textProperty().unbindBidirectional(oldValue.intValue());
                    valueSlider.setText(String.valueOf(oldValue.intValue()));                    
                }
                else
                {
                    valueSlider.textProperty().unbindBidirectional(newValue.intValue());
                    valueSlider.setText(String.valueOf(newValue.intValue()));  
                }
            });
            
            final HBox painelBotoes = new HBox(10);
            painelBotoes.setAlignment(Pos.CENTER);
            Button botaoAt = new Button("OK");
            botaoAt.setOnAction(ex -> 
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
                salabotao.setOnAction(a -> 
                {   
                    Atuador tomad = new Tomada(ConsolaCentral.getInstance().getDiv("Sala"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(tomad);
                    ConsolaCentral.getInstance().addEquipamento(tomad);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), tomad);
                    
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
                    ((Stage) tomada.getScene().getWindow()).close();
                });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador tomad = new Tomada(ConsolaCentral.getInstance().getDiv("Cozinha"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(tomad);
                    ConsolaCentral.getInstance().addEquipamento(tomad);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), tomad);
                    
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
                    ((Stage) tomada.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador tomad = new Tomada(ConsolaCentral.getInstance().getDiv("Banho"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(tomad);
                    ConsolaCentral.getInstance().addEquipamento(tomad);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), tomad);
                    
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
                    ((Stage) tomada.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador tomad = new Tomada(ConsolaCentral.getInstance().getDiv("Quarto1"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(tomad);
                    ConsolaCentral.getInstance().addEquipamento(tomad);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), tomad);
                    
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
                    ((Stage) tomada.getScene().getWindow()).close();
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador tomad = new Tomada(ConsolaCentral.getInstance().getDiv("Quarto2"),Integer.parseInt(valueSlider.getText()));
                    
                    eq.add(tomad);
                    ConsolaCentral.getInstance().addEquipamento(tomad);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), tomad);
                    
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
                    ((Stage) tomada.getScene().getWindow()).close();
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) botaoAt.getScene().getWindow()).close();
                ((Stage) tomada.getScene().getWindow()).close(); 
            });
            
            Button botaoSen = new Button("Cancel");
            botaoSen.setOnAction(ex -> 
            {
                ((Stage) botaoSen.getScene().getWindow()).close();
            });
            
            painelBotoes.getChildren().addAll(botaoAt, botaoSen);
            painelPrincipal.getChildren().addAll(apresentacaoMensagem,slider,valueSlider,painelBotoes);
            
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
        
        camaraFoto = createButton("camara.jpg");
        camaraFoto.setOnAction(e ->
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
            salabotao.setOnAction(a -> 
            {   
                Atuador camFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv("Sala"));
                    
                eq.add(camFoto);
                ConsolaCentral.getInstance().addEquipamento(camFoto);
                ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), camFoto);
                    
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
                ((Stage) camaraFoto.getScene().getWindow()).close();
            });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv("Cozinha"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), camFoto);
                    
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
                    ((Stage) camaraFoto.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv("Banho"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), camFoto);
                    
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
                    ((Stage) camaraFoto.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv("Quarto1"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), camFoto);
                    
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
                    ((Stage) camaraFoto.getScene().getWindow()).close();
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraFotografica(ConsolaCentral.getInstance().getDiv("Quarto2"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), camFoto);
                    
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
                    ((Stage) camaraFoto.getScene().getWindow()).close();
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) camaraFoto.getScene().getWindow()).close(); 
        });
        
        camaraVideo = createButton("camaraVideo.png");
        camaraVideo.setOnAction(e ->
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
            salabotao.setOnAction(a -> 
            {   
                Atuador camFoto = new CamaraVideo(ConsolaCentral.getInstance().getDiv("Sala"));
                    
                eq.add(camFoto);
                ConsolaCentral.getInstance().addEquipamento(camFoto);
                ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Sala"), camFoto);
                    
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
                ((Stage) camaraVideo.getScene().getWindow()).close();
            });
                Button cozinhabotao = new Button("Cozinha");
                cozinhabotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraVideo(ConsolaCentral.getInstance().getDiv("Cozinha"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Cozinha"), camFoto);
                    
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
                    ((Stage) camaraVideo.getScene().getWindow()).close();
                });
                Button wcbotao = new Button("WC");
                wcbotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraVideo(ConsolaCentral.getInstance().getDiv("Banho"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Banho"), camFoto);
                    
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
                    ((Stage) camaraVideo.getScene().getWindow()).close();
                });
                Button quartobotao = new Button("Quarto1");
                quartobotao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraVideo(ConsolaCentral.getInstance().getDiv("Quarto1"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto1"), camFoto);
                    
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
                    ((Stage) camaraVideo.getScene().getWindow()).close();
                });
                Button quarto2botao = new Button("Quarto2");
                quarto2botao.setOnAction(a -> 
                {   
                    Atuador camFoto = new CamaraVideo(ConsolaCentral.getInstance().getDiv("Quarto2"));
                    
                    eq.add(camFoto);
                    ConsolaCentral.getInstance().addEquipamento(camFoto);
                    ConsolaCentral.getInstance().addDivisaoAtuador(ConsolaCentral.getInstance().getDiv("Quarto2"), camFoto);
                    
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
                    ((Stage) camaraVideo.getScene().getWindow()).close();
                });

                painelBotoes1.getChildren().addAll(salabotao, cozinhabotao,wcbotao,quartobotao,quarto2botao);
                painelPrincipal1.getChildren().addAll(apresentacaoMensagem1,painelBotoes1);
            
                stage1.setTitle("Equipamento");
                Scene scene = new Scene(painelPrincipal1);
                stage1.setResizable(false);
                stage1.initStyle(stage1.getStyle().UTILITY);
                stage1.initModality(Modality.APPLICATION_MODAL);
                stage1.setIconified(false);
                stage1.centerOnScreen();
                stage1.setScene(scene);
                stage1.show();
                ((Stage) camaraVideo.getScene().getWindow()).close(); 
        });
        
        newGrid.add(lampada, 0, 0);
        newGrid.add(condicionado, 2, 0);
        newGrid.add(sirene, 4, 0);
        newGrid.add(tomada, 0, 1);
        newGrid.add(camaraFoto, 2, 1);
        newGrid.add(camaraVideo, 4, 1);
        return newGrid;
    }
    
    private Button createButton(String imageName)
    {
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/"+imageName), 60, 60, false, false);
        Button newButton = new Button("", new ImageView(imageButton));
        return newButton;
    }
    
}
