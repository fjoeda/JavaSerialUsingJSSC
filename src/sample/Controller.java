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

import javafx.scene.input.MouseEvent;
import jssc.*;


public class Controller implements Initializable{


    public TextField SendSerialText;
    public Button SendButton;
    public TextArea ConsoleText;
    public ComboBox<String> PortList;
    public ComboBox<String> BaudList;
    public Button ConnectButton;

    public static SerialPort serialPort;

    private String PortName = null;
    private String BaudRate = "9600";

    public void SendToSerial(ActionEvent actionEvent){
    }

    public void ConnectToSerial(ActionEvent actionEvent) throws SerialPortException {

        if(PortName != null){
            serialPort = new SerialPort(PortName);
            serialPort.openPort();
            serialPort.setParams(Integer.parseInt(BaudRate),8,1,0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN|SerialPort.FLOWCONTROL_RTSCTS_OUT);
            int mask  = SerialPort.MASK_RXCHAR+SerialPort.MASK_CTS+SerialPort.MASK_DSR;
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent event) {
                    if(event.isRXCHAR()){
                        try{
                            if(event.isRXCHAR()){
                                if(serialPort.readString()!= null){
                                    appendText(serialPort.readString(32));


                                }
                            }
                        }catch(SerialPortException e){

                        }
                    }
                }
            });


        }else{


        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PortList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> PortName = newValue);
        BaudList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> BaudRate = newValue);

        // Bukan kode yang layak ditiru
        BaudList.getItems().add("4800");
        BaudList.getItems().add("9600");
        BaudList.getItems().add("9600");
        BaudList.getItems().add("19200");
        BaudList.getItems().add("115200");


    }

    public void appendText(String inputText){
        ConsoleText.setText(ConsoleText.getText()+inputText+System.lineSeparator());
    }


    public void RefreshPortList(MouseEvent mouseEvent) {

        String[] serialList = SerialPortList.getPortNames();
        for(String str :serialList){
            PortList.getItems().add(str);
            System.out.println(str);
        }
        System.out.println("Mouse Enterred");
    }
}

