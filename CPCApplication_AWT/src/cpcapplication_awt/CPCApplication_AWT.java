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
import java.util.HashSet;
import java.util.Set;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
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
        //gridPane padding and gap settings
        gridPane.setVgap(20);
        gridPane.setHgap(40);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //gridPane.setGridLinesVisible(true);
        
        //TITLE- Create the title label
        Label ComparisonTitle = new Label("Comparisons");
        //Create the "Go to Database Page" Button-- HANDLE NEEDED
        Button DataPageButton = new Button("Go To Database Page");
        //Create a Hbox and for the ComparisonTitle
        HBox titleHBox = new HBox(20, ComparisonTitle, DataPageButton);
        //Create a VBox fo the TitleBox
        VBox titleVBox = new VBox(20, titleHBox, DataPageButton, ComparisonTitle, gridPane);
        
        titleVBox.setAlignment(Pos.TOP_CENTER);
        
        //FORMATTING- Use for padding out and dividing up the page
        Label xDivider = new Label("==========================");
        
        //REUSABLE- Create reusable elements for the GUI throughout the Program
        Button browseButton = new Button("Browse...");
        CheckBox saveBox = new CheckBox("Save to the Database");
        TextField pastedCodeField = new TextField();
        FileChooser file1Chooser = new FileChooser();
            file1Chooser.setTitle("Select File 1");
            //makes the fileChooser appear -file1Chooser.showOpenDialog(primaryStage);
        
        
        //create 4 panels with its label and HBoxes & VBoxes for the layout in the GridLayout
        //Top Left Panel of the Program
        Label topLeftPanLabel = new Label("Choose File 1 Below to Upload");
        HBox topLeftPanHBox = new HBox(30, topLeftPanLabel);
        //File Chooser should go here
        VBox topLeftPanVBox = 
            new VBox(30, xDivider, topLeftPanHBox, browseButton, saveBox);
        
        
        //Top Right Panel of the Program
        Label topRightPanLabel = new Label("Choose File 2 Below to Upload");
        HBox topRightPanHBox = new HBox(30, topRightPanLabel);
        //File Chooser should go here
        Button compareButton1 = new Button("Compare File 1 to File 2");
        VBox topRightPanVBox = 
            new VBox(30, xDivider, topRightPanHBox, browseButton, saveBox, compareButton1);
        
        //Bottom Left Panel of the Program
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(30, xDivider, botLeftPanHBox);
        
        //Bottom Right Panel of the Program
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        Button compareButton2 = new Button("Compare File 1 to Pasted Code");
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = 
            new VBox(30, xDivider, botRightPanHBox, pastedCodeField, saveBox, compareButton2);
        
        //Insert Handlers Here
        
        
        //Constraints for the left and right columns
        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setPercentWidth(100);
        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setPercentWidth(100);
        
        //Constraints for the First row (0,0 & 1,0) and Second Row (0,1 & 1,1)
        RowConstraints firstRow = new RowConstraints();
        firstRow.setPercentHeight(125);
        RowConstraints secondRow = new RowConstraints();
        secondRow.setPercentHeight(125);
        
        //each gridPane column specified with the parameters are applied with the column constraints
        gridPane.getColumnConstraints().addAll(leftColumn, rightColumn); 
        
        gridPane.add(topLeftPanVBox, 0, 0);
        gridPane.add(topRightPanVBox, 1, 0);
        gridPane.add(botLeftPanVBox, 0, 1);
        gridPane.add(botRightPanVBox, 1, 1);
        
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
