package fx;

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
 * Login da aplicação
 * @author Diogo Venâncio e André Gonçalves
 */
public class Login extends BorderPane
{
    private HBox headerBox;
    private Text loginText;
    private GridPane grid;
    private Label idClienteLabel;
    private TextField idClienteTextField;
    private Button entrarButton;
    private Label registadoLabel;
    
    private Dados dados;

    public Login() 
    {
        setup();
    }
    
    private void setup()
    { 
        
        StackPane left = new StackPane();
        left.setPrefWidth(10);
        setLeft(left);
        StackPane right = new StackPane();
        right.setPrefWidth(10);
        setLeft(right);
        
        headerBox = new HBox();  
        headerBox.setAlignment(Pos.CENTER);
        loginText = createText("Login", Color.GREEN, "Arial", 30);
        headerBox.getChildren().add(loginText);
        
        idClienteLabel = new Label("ID Cliente:");
        idClienteTextField = new TextField();
        entrarButton = createButton("Entrar");
        
        entrarButton.setOnAction(e ->
        {
            int cod = 0;
            try
            {
                cod = Integer.parseInt(idClienteTextField.getText());
            }catch(NumberFormatException | NullPointerException ex)
            {
                return;
            }
            
            dados = ConsolaCentral.getInstance().getClient(cod);
            
            if(dados != null)
            {
                Stage stage = new Stage();
                stage.setTitle("Menu");
                ConsolaCentral.getInstance().loginClient(cod);
                
                MenuInicial menu = new MenuInicial();
                stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(menu, 470, 400);
                stage.setScene(scene);
                stage.show();
                ((Stage) entrarButton.getScene().getWindow()).close();
            }
            else
            {
                idClienteTextField.setText("Código Inválido");
            } 
        }); 
        
        registadoLabel = new Label("Não está registado?");
        
        registadoLabel.setOnMouseClicked(e ->
        {
            Stage stage = new Stage();
            stage.setTitle("Registar");
                
            Registar menu = new Registar();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(menu, 280, 250);
            stage.setScene(scene);
            stage.show();
            ((Stage) registadoLabel.getScene().getWindow()).close();
        });
        
        grid = createGridPane();
        grid.add(idClienteLabel, 0, 0);
        grid.add(idClienteTextField, 1, 0);
        grid.add(entrarButton, 1, 1);
        grid.add(registadoLabel, 1, 2);
        
        headerBox.setPrefHeight(50);
        setTop(headerBox);
        setCenter(grid);    
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
        newButton.setMinWidth(200);
        return newButton;
    }
    
}
