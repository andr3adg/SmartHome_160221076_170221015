/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import projetopoo.Divisao;
import Atuadores.*;
import Sensores.Sensor;

/**
 * Visualiza os equipamentos dentro da divisão escolhida 
 * @author Diogo Venancio e André Gonçalves
 */
public class ViewDivisao extends BorderPane {

    private HBox headerBox;
    private Text headerText;
    private Label tituloLabel;
    private Label nomeLabel;
    private TextField nomeTextField;
    private Label isOnLabel;
    private TextField isOnTextField;
    private Label wifiLabel;
    private TextField wifiTextField;
    private Label energiaLabel;
    private TextField energiaTextField;
    private HBox centerBox;
    private VBox gridVBox;
    private GridPane grid;
    private HBox bottomBox;
    private Button sairButton;

    private Label tempLabel;
    private TextField tempTextField;
    private Label luzLabel;
    private TextField luzTextField;
    private Label volumeLabel;
    private TextField volumeTextField;
    private Label temporizadorLabel;
    private TextField temporizadorTextField;

    private Label movimentoLabel;
    private TextField movimentoTextField;
    private Label portaLabel;
    private TextField portaTextField;
    
    private Button alterarButton;
    private Button eliminarButton;

    public ViewDivisao(Divisao divisao, String equipamento) {
        setup(divisao, equipamento);
    }

    private void setup(Divisao divisao, String equipamento) {
        StackPane left = new StackPane();
        left.setPrefWidth(50);
        setLeft(left);
        StackPane right = new StackPane();
        right.setPrefWidth(25);
        setLeft(right);

        headerText = createText(divisao.getNome(), Color.GREEN, "Arial", 30);
        headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.getChildren().add(headerText);
        headerBox.setPadding(new Insets(15, 0, 0, 0));
        setTop(headerBox);

        tituloLabel = new Label("");
        nomeLabel = new Label("Nome: ");
        isOnLabel = new Label("Is On: ");
        wifiLabel = new Label("Wi-Fi: ");
        energiaLabel = new Label("Energia: ");
        tempLabel = new Label("Temperatura: ");
        luzLabel = new Label("Luminosidade: ");
        volumeLabel = new Label("Volume: ");
        temporizadorLabel = new Label("Temporizador: ");
        movimentoLabel = new Label("Movimento: ");
        portaLabel = new Label("Porta: ");
        nomeTextField = new TextField();
        isOnTextField = new TextField();
        wifiTextField = new TextField();
        energiaTextField = new TextField();
        tempTextField = new TextField();
        luzTextField = new TextField();
        volumeTextField = new TextField();
        temporizadorTextField = new TextField();
        movimentoTextField = new TextField();
        portaTextField = new TextField();
        
        bottomBox = new HBox();

        gridVBox = new VBox();
        grid = createGridPane();
        grid.add(tituloLabel, 0, 1);
        grid.add(nomeLabel, 0, 2);
        grid.add(nomeTextField, 1, 2);
        grid.add(isOnLabel, 0, 3);
        grid.add(isOnTextField, 1, 3);
        grid.add(wifiLabel, 2, 3);
        grid.add(wifiTextField, 3, 3);
        grid.add(energiaLabel, 0, 4);
        grid.add(energiaTextField, 1, 4);
        centerBox = new HBox();
        centerBox.setAlignment(Pos.CENTER);

        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(0, 0, 15, 0));
        Image imageButton = new Image(getClass().getResourceAsStream("/Imagens/Seta.png"), 60, 60, false, false);
        sairButton = new Button("", new ImageView(imageButton));
        sairButton.setTranslateX(20);
        sairButton.setOnAction(e
                -> {
            Stage stage = new Stage();
            stage.setTitle("Divisoes");
            Divisoes div = new Divisoes();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(div, 430, 400);
            stage.setScene(scene);
            stage.show();
            ((Stage) sairButton.getScene().getWindow()).close();
        });

        bottomBox.getChildren().add(sairButton);
        setBottom(bottomBox);
        
        
        if (equipamento.equals("Atuadores")) 
        {
            alterarButton = new Button("Alterar");
            alterarButton.setTranslateX(-360);
        
            eliminarButton = new Button("Apagar");
            eliminarButton.setTranslateX(-350);
            
            ObservableList<Atuador> equip = FXCollections.observableArrayList();
            equip.addAll(ConsolaCentral.getInstance().getDivEquipAtuador(divisao.getNome()));

            final ListView<Atuador> itemsListView = new ListView<>(equip);
            itemsListView.getSelectionModel().select(0);
            itemsListView.setTranslateX(20);
            itemsListView.setPrefWidth(300);

            if (!equip.isEmpty()) 
            {
                nomeTextField.setText(itemsListView.getSelectionModel().selectedItemProperty().get().getNome());

                if (itemsListView.getSelectionModel().selectedItemProperty().get().isOn()) {
                    isOnTextField.setText("true");
                } else {
                    isOnTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().haveWifi()) {
                    wifiTextField.setText("true");
                } else {
                    wifiTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().needEnergia()) {
                    energiaTextField.setText("true");
                } else {
                    energiaTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Ar Condicionado")) 
                {
                    tempTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getTemp()));
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(volumeLabel);
                    grid.getChildren().remove(volumeTextField);
                    grid.getChildren().remove(temporizadorLabel);
                    grid.getChildren().remove(temporizadorTextField);
                    grid.add(tempLabel, 0, 5);
                    grid.add(tempTextField, 1, 5);
                    bottomBox.getChildren().remove(alterarButton);
                    bottomBox.getChildren().remove(eliminarButton);
                    alterarButton.setOnAction(e ->
                    {
                        itemsListView.getSelectionModel().selectedItemProperty().get().setTemp(Double.parseDouble(tempTextField.getText()));
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) alterarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Lâmpada"))
                {
                    luzTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getLuz()));
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(volumeLabel);
                    grid.getChildren().remove(volumeTextField);
                    grid.getChildren().remove(temporizadorLabel);
                    grid.getChildren().remove(temporizadorTextField);
                    grid.add(luzLabel, 0, 5);
                    grid.add(luzTextField, 1, 5);
                    bottomBox.getChildren().remove(alterarButton);
                    bottomBox.getChildren().remove(eliminarButton);
                    alterarButton.setOnAction(e ->
                    {
                        itemsListView.getSelectionModel().selectedItemProperty().get().setLuz(Integer.parseInt(luzTextField.getText()));
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Sirene")) 
                {
                    volumeTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getVolume()));
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(volumeLabel);
                    grid.getChildren().remove(volumeTextField);
                    grid.getChildren().remove(temporizadorLabel);
                    grid.getChildren().remove(temporizadorTextField);
                    grid.add(volumeLabel, 0, 5);
                    grid.add(volumeTextField, 1, 5);
                    bottomBox.getChildren().remove(alterarButton);
                    bottomBox.getChildren().remove(eliminarButton);
                    alterarButton.setOnAction(e ->
                    {
                        itemsListView.getSelectionModel().selectedItemProperty().get().setVolume(Integer.parseInt(volumeTextField.getText()));
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Tomada")) {
                    temporizadorTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getTemporizador()));
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(volumeLabel);
                    grid.getChildren().remove(volumeTextField);
                    grid.getChildren().remove(temporizadorLabel);
                    grid.getChildren().remove(temporizadorTextField);
                    grid.add(temporizadorLabel, 0, 5);
                    grid.add(temporizadorTextField, 1, 5);
                    bottomBox.getChildren().remove(alterarButton);
                    bottomBox.getChildren().remove(eliminarButton);
                    alterarButton.setOnAction(e ->
                    {
                        itemsListView.getSelectionModel().selectedItemProperty().get().setTemporizador(Integer.parseInt(temporizadorTextField.getText()));
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Camara Fotografica") || itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Camara Video")) {
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(volumeLabel);
                    grid.getChildren().remove(volumeTextField);
                    grid.getChildren().remove(temporizadorLabel);
                    grid.getChildren().remove(temporizadorTextField);
                    bottomBox.getChildren().remove(alterarButton);
                    bottomBox.getChildren().remove(eliminarButton);
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().add(eliminarButton);
                }

            }

            itemsListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Atuador> observable, Atuador oldValue, Atuador newValue)
                    -> 
            {
                if (oldValue != null) {
                    tituloLabel.setText(newValue.getTipoEquipamento().toString());
                    nomeTextField.textProperty().unbindBidirectional(newValue.getNome());
                    nomeTextField.setText(newValue.getNome());
                    isOnTextField.textProperty().unbindBidirectional(newValue.isOn());
                    if (newValue.isOn()) {
                        isOnTextField.setText("true");
                    } else {
                        isOnTextField.setText("false");
                    }

                    wifiTextField.textProperty().unbindBidirectional(newValue.haveWifi());
                    if (newValue.haveWifi()) {
                        wifiTextField.setText("true");
                    } else {
                        wifiTextField.setText("false");
                    }

                    energiaTextField.textProperty().unbindBidirectional(newValue.needEnergia());
                    if (newValue.needEnergia()) {
                        energiaTextField.setText("true");
                    } else {
                        energiaTextField.setText("false");
                    }

                    if (newValue.getNome().equals("Ar Condicionado")) 
                    {
                        tempTextField.setText(String.valueOf(newValue.getTemp()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(tempLabel, 0, 5);
                        grid.add(tempTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            newValue.setTemp(Double.parseDouble(tempTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (newValue.getNome().equals("Lâmpada")) 
                    {
                        luzTextField.setText(String.valueOf(newValue.getLuz()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(luzLabel, 0, 5);
                        grid.add(luzTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            newValue.setLuz(Integer.parseInt(luzTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (newValue.getNome().equals("Sirene")) 
                    {
                        volumeTextField.setText(String.valueOf(newValue.getVolume()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(volumeLabel, 0, 5);
                        grid.add(volumeTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            newValue.setVolume(Integer.parseInt(volumeTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (newValue.getNome().equals("Tomada")) 
                    {
                        temporizadorTextField.setText(String.valueOf(newValue.getTemporizador()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(temporizadorLabel, 0, 5);
                        grid.add(temporizadorTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            newValue.setTemporizador(Integer.parseInt(temporizadorTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (newValue.getNome().equals("Camara Fotografica") || newValue.getNome().equals("Camara Video")) {
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }

                    itemsListView.refresh();
                } else 
                {
                    tituloLabel.setText(oldValue.getTipoEquipamento().toString());
                    nomeTextField.textProperty().unbindBidirectional(oldValue.getNome());
                    nomeTextField.setText(oldValue.getNome());
                    isOnTextField.textProperty().unbindBidirectional(oldValue.isOn());
                    if (oldValue.isOn()) {
                        isOnTextField.setText("true");
                    } else {
                        isOnTextField.setText("false");
                    }

                    wifiTextField.textProperty().unbindBidirectional(oldValue.haveWifi());
                    if (oldValue.haveWifi()) {
                        wifiTextField.setText("true");
                    } else {
                        wifiTextField.setText("false");
                    }

                    energiaTextField.textProperty().unbindBidirectional(oldValue.needEnergia());
                    if (oldValue.needEnergia()) {
                        energiaTextField.setText("true");
                    } else {
                        energiaTextField.setText("false");
                    }

                    if (oldValue.getNome().equals("Ar Condicionado")) 
                    {
                        tempTextField.setText(String.valueOf(oldValue.getTemp()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(tempLabel, 0, 5);
                        grid.add(tempTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            oldValue.setTemp(Double.parseDouble(tempTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, oldValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (oldValue.getNome().equals("Lâmpada")) 
                    {
                        luzTextField.setText(String.valueOf(oldValue.getLuz()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(luzLabel, 0, 5);
                        grid.add(luzTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            oldValue.setLuz(Integer.parseInt(luzTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, oldValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (oldValue.getNome().equals("Sirene")) 
                    {
                        volumeTextField.setText(String.valueOf(oldValue.getVolume()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(volumeLabel, 0, 5);
                        grid.add(volumeTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            oldValue.setVolume(Integer.parseInt(volumeTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, oldValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (oldValue.getNome().equals("Tomada")) 
                    {
                        temporizadorTextField.setText(String.valueOf(oldValue.getTemporizador()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        grid.add(luzLabel, 0, 5);
                        grid.add(luzTextField, 1, 5);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        alterarButton.setOnAction(e ->
                        {
                            oldValue.setTemporizador(Integer.parseInt(temporizadorTextField.getText()));
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, oldValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }
                    if (oldValue.getNome().equals("Camara Fotografica") || oldValue.getNome().equals("Camara Video")) 
                    {
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(volumeLabel);
                        grid.getChildren().remove(volumeTextField);
                        grid.getChildren().remove(temporizadorLabel);
                        grid.getChildren().remove(temporizadorTextField);
                        bottomBox.getChildren().remove(alterarButton);
                        bottomBox.getChildren().remove(eliminarButton);
                        
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoAtuador(divisao, oldValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().addAll(alterarButton,eliminarButton);
                    }

                    itemsListView.refresh();
                }
            });

            setLeft(itemsListView);

        } else 
        {
        
            eliminarButton = new Button("Apagar");
            eliminarButton.setTranslateX(-380);
            
            ObservableList<Sensor> equip = FXCollections.observableArrayList();
            equip.addAll(ConsolaCentral.getInstance().getDivEquipSensor(divisao.getNome()));

            final ListView<Sensor> itemsListView = new ListView<>(equip);
            itemsListView.getSelectionModel().select(0);
            itemsListView.setTranslateX(30);
            itemsListView.setPrefWidth(300);

            if (!equip.isEmpty()) {
                nomeTextField.setText(itemsListView.getSelectionModel().selectedItemProperty().get().getNome());

                if (itemsListView.getSelectionModel().selectedItemProperty().get().isOn()) {
                    isOnTextField.setText("true");
                } else {
                    isOnTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().haveWifi()) {
                    wifiTextField.setText("true");
                } else {
                    wifiTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().needEnergia()) {
                    energiaTextField.setText("true");
                } else {
                    energiaTextField.setText("false");
                }

                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Temperatura"))
                {
                    tempTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getTemp()));
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(movimentoLabel);
                    grid.getChildren().remove(movimentoTextField);
                    grid.getChildren().remove(portaLabel);
                    grid.getChildren().remove(portaTextField);
                    grid.add(tempLabel, 0, 5);
                    grid.add(tempTextField, 1, 5);
                    bottomBox.getChildren().remove(eliminarButton);
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoSensor(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().add(eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Luminosidade")) {
                    luzTextField.setText(String.valueOf(itemsListView.getSelectionModel().selectedItemProperty().get().getLuz()));
                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(movimentoLabel);
                    grid.getChildren().remove(movimentoTextField);
                    grid.getChildren().remove(portaLabel);
                    grid.getChildren().remove(portaTextField);
                    grid.add(luzLabel, 0, 5);
                    grid.add(luzTextField, 1, 5);
                    bottomBox.getChildren().remove(eliminarButton);
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoSensor(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().add(eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Movimento")) {
                    if (itemsListView.getSelectionModel().selectedItemProperty().get().isMovimento()) {
                        movimentoTextField.setText("true");
                    } else {
                        movimentoTextField.setText("false");
                    }

                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(portaLabel);
                    grid.getChildren().remove(portaTextField);
                    grid.add(movimentoLabel, 0, 5);
                    grid.add(movimentoTextField, 1, 5);
                    bottomBox.getChildren().remove(eliminarButton);
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoSensor(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().add(eliminarButton);
                }
                if (itemsListView.getSelectionModel().selectedItemProperty().get().getNome().equals("Porta aberta")) {
                    if (itemsListView.getSelectionModel().selectedItemProperty().get().isAberta()) {
                        portaTextField.setText("true");
                    } else {
                        portaTextField.setText("false");
                    }

                    grid.getChildren().remove(tempLabel);
                    grid.getChildren().remove(tempTextField);
                    grid.getChildren().remove(luzLabel);
                    grid.getChildren().remove(luzTextField);
                    grid.getChildren().remove(movimentoLabel);
                    grid.getChildren().remove(movimentoTextField);
                    grid.add(portaLabel, 0, 5);
                    grid.add(portaTextField, 1, 5);
                    bottomBox.getChildren().remove(eliminarButton);
                    
                    eliminarButton.setOnAction(e ->
                    {
                        itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                        ConsolaCentral.getInstance().removeDivisaoSensor(divisao, itemsListView.getSelectionModel().selectedItemProperty().get());
                        Stage stage = new Stage();
                        stage.setTitle("Divisoes");
                        Divisoes div = new Divisoes();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        Scene scene = new Scene(div, 430, 400);
                        stage.setScene(scene);
                        stage.show();
                        ((Stage) eliminarButton.getScene().getWindow()).close();
                        ((Stage) sairButton.getScene().getWindow()).close(); 
                    });
                    bottomBox.getChildren().add(eliminarButton);
                }

            }

            itemsListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Sensor> observable, Sensor oldValue, Sensor newValue)
                    -> {
                if (oldValue != null) {

                    tituloLabel.setText(newValue.getTipoEquipamento().toString());
                    nomeTextField.textProperty().unbindBidirectional(newValue.getNome());
                    nomeTextField.setText(newValue.getNome());
                    isOnTextField.textProperty().unbindBidirectional(newValue.isOn());
                    if (newValue.isOn()) {
                        isOnTextField.setText("true");
                    } else {
                        isOnTextField.setText("false");
                    }

                    wifiTextField.textProperty().unbindBidirectional(newValue.haveWifi());
                    if (newValue.haveWifi()) {
                        wifiTextField.setText("true");
                    } else {
                        wifiTextField.setText("false");
                    }

                    energiaTextField.textProperty().unbindBidirectional(newValue.needEnergia());
                    if (newValue.needEnergia()) {
                        energiaTextField.setText("true");
                    } else {
                        energiaTextField.setText("false");
                    }

                    if (newValue.getNome().equals("Temperatura")) {
                        tempTextField.setText(String.valueOf(newValue.getTemp()));
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(tempLabel, 0, 5);
                        grid.add(tempTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (newValue.getNome().equals("Luminosidade")) {
                        luzTextField.setText(String.valueOf(newValue.getLuz()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(luzLabel, 0, 5);
                        grid.add(luzTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (newValue.getNome().equals("Movimento")) {
                        if (newValue.isMovimento()) {
                            movimentoTextField.setText("true");
                        } else {
                            movimentoTextField.setText("false");
                        }

                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(movimentoLabel, 0, 5);
                        grid.add(movimentoTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (newValue.getNome().equals("Porta aberta")) {
                        if (newValue.isAberta()) {
                            portaTextField.setText("true");
                        } else {
                            portaTextField.setText("false");
                        }
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.add(portaLabel, 0, 5);
                        grid.add(portaTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }

                    itemsListView.refresh();
                } else {
                    tituloLabel.setText(oldValue.getTipoEquipamento().toString());
                    nomeTextField.textProperty().unbindBidirectional(oldValue.getNome());
                    nomeTextField.setText(oldValue.getNome());
                    isOnTextField.textProperty().unbindBidirectional(oldValue.isOn());
                    if (oldValue.isOn()) {
                        isOnTextField.setText("true");
                    } else {
                        isOnTextField.setText("false");
                    }

                    wifiTextField.textProperty().unbindBidirectional(oldValue.haveWifi());
                    if (oldValue.haveWifi()) {
                        wifiTextField.setText("true");
                    } else {
                        wifiTextField.setText("false");
                    }

                    energiaTextField.textProperty().unbindBidirectional(oldValue.needEnergia());
                    if (oldValue.needEnergia()) {
                        energiaTextField.setText("true");
                    } else {
                        energiaTextField.setText("false");
                    }

                    if (oldValue.getNome().equals("Temperatura")) {
                        tempTextField.setText(String.valueOf(oldValue.getTemp()));
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(tempLabel, 0, 5);
                        grid.add(tempTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (oldValue.getNome().equals("Luminosidade")) {
                        luzTextField.setText(String.valueOf(oldValue.getLuz()));
                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(luzLabel, 0, 5);
                        grid.add(luzTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (oldValue.getNome().equals("Movimento")) {
                        if (oldValue.isMovimento()) {
                            movimentoTextField.setText("true");
                        } else {
                            movimentoTextField.setText("false");
                        }

                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(portaLabel);
                        grid.getChildren().remove(portaTextField);
                        grid.add(movimentoLabel, 0, 5);
                        grid.add(movimentoTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }
                    if (oldValue.getNome().equals("Porta aberta")) {
                        if (oldValue.isAberta()) {
                            portaTextField.setText("true");
                        } else {
                            portaTextField.setText("false");
                        }

                        grid.getChildren().remove(tempLabel);
                        grid.getChildren().remove(tempTextField);
                        grid.getChildren().remove(luzLabel);
                        grid.getChildren().remove(luzTextField);
                        grid.getChildren().remove(movimentoLabel);
                        grid.getChildren().remove(movimentoTextField);
                        grid.add(portaLabel, 0, 5);
                        grid.add(portaTextField, 1, 5);
                        bottomBox.getChildren().remove(eliminarButton);
                    
                        eliminarButton.setOnAction(e ->
                        {
                            itemsListView.getItems().remove(itemsListView.getSelectionModel().getSelectedIndex());
                            ConsolaCentral.getInstance().removeDivisaoSensor(divisao, newValue);
                            Stage stage = new Stage();
                            stage.setTitle("Divisoes");
                            Divisoes div = new Divisoes();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(div, 430, 400);
                            stage.setScene(scene);
                            stage.show();
                            ((Stage) eliminarButton.getScene().getWindow()).close();
                            ((Stage) sairButton.getScene().getWindow()).close(); 
                        });
                        bottomBox.getChildren().add(eliminarButton);
                    }

                    itemsListView.refresh();
                }
            });

            setLeft(itemsListView);
        }

        centerBox.getChildren().addAll(grid);
        setCenter(centerBox);
    }

    private GridPane createGridPane() {
        GridPane newGrid = new GridPane();
        newGrid.setVgap(10);
        newGrid.setHgap(10);
        newGrid.setPadding(new Insets(15, 0, 0, 0));
        return newGrid;
    }

    private Text createText(String texto, Color cor, String fontName, int fontSize) {
        Text newTexto = new Text(texto);
        newTexto.setFill(cor);
        newTexto.setFont(Font.font(fontName, fontSize));
        return newTexto;
    }
}
