/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication_awt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jsdan
 */
public class CPCApplication_AWT extends Application {
    
    //start of the Program from a GUI perspective
    @Override
    public void start(Stage primaryStage) {
        //GUI code goes here
        //Hbox def
        
        //Label def
        Label ComparisonLabel = new Label("Comparison");
        
        //button definition + the Event Handler
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        //Button Event Handler 
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //What happens when the button is pushed
                System.out.println("Hello World!");
            }
        });
        
        //StackPane is???
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 600, 500);
        
        //primaryStage is what is shown, when the curtain is pulled back
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        //curtain being pulled back
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * 
     */
    //Don't touch this
    public static void main(String[] args) {
        launch(args);
    }
    
}
