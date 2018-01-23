package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import jssc.SerialPort;
import jssc.SerialPortException;


public class Controller implements Initializable{


    public TextField SendSerialText;
    public Button SendButton;
    public TextArea ConsoleText;
    public ComboBox<String> PortList;
    public ComboBox<String> BaudList;
    public Button ConnectButton;

    public SerialPort serialPort;

    private String PortName = null;
    private String BaudRate = "9600";

    public void SendToSerial(ActionEvent actionEvent){
    }

    public void ConnectToSerial(ActionEvent actionEvent) throws SerialPortException {

        if(PortName != null){
            serialPort = new SerialPort(PortName);

            serialPort.setParams(Integer.parseInt(BaudRate),8,1,0);

        }else{


        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PortList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> PortName = newValue);
        BaudList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> BaudRate = newValue);


    }

    public void appendText(String inputText){
        ConsoleText.setText(ConsoleText.getText()+inputText+System.lineSeparator());
    }
}
