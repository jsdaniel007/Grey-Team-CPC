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
import javafx.geometry.Pos;
import javax.swing.*;
import java.awt.*;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jsdan
 */
public class CPCApplication_AWT extends Application {
    
    private Label CompareTitleLabel;
    
    //start of the Program from a GUI perspective
    @Override
    public void start(Stage primaryStage) {
        
        //Create a Gridpane to add panels to, for setting up the page.
        GridPane gridPane = new GridPane();
        //ColumnConstraints topLeftColumn = new ColumnConstraints();
        gridPane.getColumnConstraints().add(new ColumnConstraints(100));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        
        //Create the title label
        Label ComparisonTitle = new Label("Comparisons");
        //Create the "Go to Database Page" Button
        Button DataPageButton = new Button("Go To Database Page");
        //Create a Hbox and for the ComparisonTitle
        HBox titleHBox = new HBox(30, ComparisonTitle, DataPageButton);
        //Create a VBox fo the TitleBox
        VBox titleVBox = new VBox(20, titleHBox, DataPageButton, ComparisonTitle, gridPane);
        
        //titleVBox.setPadding(new Insets(3));
        
        titleVBox.setAlignment(Pos.TOP_CENTER);
        
        //create 4 panels with its label and such for the layout in the GridLayout
        Label topLeftPanLabel = new Label("Choose File 1 Below to Upload");
        HBox topLeftPanHBox = new HBox(30, topLeftPanLabel);
        VBox topLeftPanVBox = new VBox(30, topLeftPanHBox);
        
        Label topRightPanLabel = new Label("Choose File 2 Below to Upload");
        HBox topRightPanHBox = new HBox(30, topRightPanLabel);
        VBox topRightPanVBox = new VBox(30, topRightPanHBox);
        
        
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(30, botLeftPanHBox);
        
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = new VBox(30, botRightPanHBox);
        
        //add to the panels
        //gridpane additions with (component, col, row)
        gridPane.add(topLeftPanVBox, 0, 0);
        gridPane.add(topRightPanVBox, 1, 0);
        gridPane.add(botLeftPanVBox, 2,1);
        gridPane.add(botRightPanVBox, 2, 2);
        //topLeftPan.add(topLeftPanLabel);
        
        //Top Right Panel additions
        //topRightPan.add(topRightPanLabel);
        
        //Bottom Left Panel additions
        //botLeftPan.add(botLeftPanLabel);
        
        //Bottom Right Panel additions
        //botRightPan.add(botRightPanLabel);
        
        Scene scene = new Scene(titleVBox, 600, 600);
        
        
        //problem: resize default size
        primaryStage.setTitle("CPC Application");
        //primaryStage.setWidth(400);
        primaryStage.setScene(scene);
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
