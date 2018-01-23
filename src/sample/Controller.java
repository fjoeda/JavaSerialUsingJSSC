package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    public TextField SendSerialText;
    public Button SendButton;
    public TextArea ConsoleText;
    public ComboBox PortList;
    public ComboBox BaudList;
    public Button ConnectButton;

    public void SendToSerial(ActionEvent actionEvent){
    }

    public void ConnectToSerial(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void appendText(String inputText){
        ConsoleText.setText(ConsoleText.getText()+inputText+System.lineSeparator());
    }
}
