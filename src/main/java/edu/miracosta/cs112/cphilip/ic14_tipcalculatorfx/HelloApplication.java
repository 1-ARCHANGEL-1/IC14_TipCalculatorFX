package edu.miracosta.cs112.cphilip.ic14_tipcalculatorfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class HelloApplication extends Application {


    //define fields (instance varuables for each of
    //the nodes that change or interacted with
    private TextField billAmountTF = new TextField();
    private Label tipPercentLabel = new Label("15%");
    private Slider tipPercentSlider = new Slider(0, 30,15);
    private TextField tipAmountTF = new TextField();
    private TextField totalAmountTF = new TextField();
    private Button clearButton = new Button("Clear");
    private Button  calculateButton = new Button("Calculate");
    //private TextField  = new TextField();
    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        //let's place "Bill Amount: " Label to gridane
        gridPane.add(new Label("Bill Amount: "), 0,0);
        gridPane.add(billAmountTF,1,0);

        gridPane.add(tipPercentLabel,0,1);
        gridPane.add(tipPercentSlider,1,1);

        gridPane.add(new Label("Tip Amount: "), 0,2);
        gridPane.add(tipAmountTF,1,2);

        gridPane.add(new Label("Total Amount: "), 0,3);
        gridPane.add(totalAmountTF,1,3);



        //HBox - horizontal box of Nodes
        HBox hBox = new HBox(clearButton,calculateButton);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        gridPane.add(hBox, 1, 4);

        //Disable the text fields
        tipAmountTF.setEditable(false);
        tipAmountTF.setFocusTraversable(false);
        totalAmountTF.setEditable(false);
        totalAmountTF.setFocusTraversable(false);
        //tipAmountTF.setDisable(true);
        

        //Let's set the horizontal gap AND vertical gap of GridPane (affects all Nodes inside)
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Let's set the alignment to center (default
        gridPane.setAlignment(Pos.CENTER);

        //Let's configure the Slider (tick marks)
        tipPercentSlider.setShowTickMarks(true);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setBlockIncrement(5);
        tipPercentSlider.setSnapToTicks(true);
        tipPercentSlider.setMajorTickUnit(5);

        //Wire up the clearButton with the clear() method
        //using a lambda lesson
        //Parameter of the setOnAction method will be
        // A new, anonymous inner class
        //that implements the Handle() method
        //handle() method will call our clear() method

        //Lambda expression is a shortcut for code:
        //Syntax:
        //parameter -> method call
        clearButton.setOnAction( e -> clear());
        calculateButton.setOnAction( e -> calculate());

        //let's wired up slider and bill amount text field
        //for a slider, we are listening to its value
        //Listener is an interface, watches for changes in a node
        tipPercentSlider.valueProperty().addListener((obsVal, oldVal, newVal) -> calculate());
        billAmountTF.textFormatterProperty().addListener((obsVAL,oldVal, newVal)-> calculate());

        Scene scene = new Scene(gridPane, 320, 240);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }
    //Clear method which will be "wired up" to clearButton
    public void clear()
    {
        billAmountTF.clear();
        tipAmountTF.clear();
        totalAmountTF.clear();
        //Reset slider back to 15%
        tipPercentSlider.setValue(15);

        //Return focus back to billAmountTF
        billAmountTF.requestFocus();
    }

    //Calculate method will be to calculate " wired  up" to Button, billAmountTF and tipPercentSlider
    public void calculate()
    {
        //Let's update the label with the slider;s value
        tipPercentLabel.setText((int)tipPercentSlider.getValue()+ "%");
        //Let's get the Amount
        if(billAmountTF.getText().isEmpty())
            return;
        double billAmount = Double.parseDouble(billAmountTF.getText());
        //let's get the tipPercent
        double tipPercent = tipPercentSlider.getValue()/100.0;
        //Calculate tip amount
        double tipAmount = billAmount*tipPercent;
        //Calculate the total Amount
        double totalAmount = billAmount + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        //Set the text of both text fields
        tipAmountTF.setText(currency.format(tipAmount));
        totalAmountTF.setText(currency.format(totalAmount));

    }

    public static void main(String[] args)
    {
        launch();
    }
}