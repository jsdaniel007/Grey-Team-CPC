/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class CPCApplication extends Application {
    private Label CompareTitleLabel;
    
    //start of the Program from a GUI perspective
    @Override
    public void start(Stage primaryStage) {
    //Allow access to your other classes
        CodeComparison CC = new CodeComparison();
        CompareToLib CTL = new CompareToLib();
        Database DB = new Database();
        
    //Create a Gridpane to add panels to, for setting up the page.
        GridPane gridPane = new GridPane();
        GridPane gridPane2 = new GridPane();
        //gridPane padding and gap settings
        gridPane.setVgap(20);
        gridPane2.setVgap(20);
        gridPane.setHgap(40);
        gridPane2.setHgap(40);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
        //gridPane.setGridLinesVisible(true);
        //gridPane2.setGridLinesVisible(true);
        
    //FORMATTING- Use for padding out and dividing up the page
        Label xDivider = new Label("==========================");
        
    //TITLE- Create the title labels for the screens
        Label ComparisonTitle = new Label("Comparisons");
        Label DatabaseTitle = new Label("Database");
    //Create the Navigation Buttons-- HANDLE NEEDED
        Button DataPageButton = new Button("Go To Database Page");
        Button ComparisonPageButton = new Button("Go To Comparisons Page");
    //Create a Hbox and for the ComparisonTitle
        HBox titleHBox = new HBox(20, ComparisonTitle);
    //Create a VBox fo the TitleBox
        VBox CompTitleVBox = new VBox(20, ComparisonTitle, DataPageButton, gridPane);
        VBox DataTitleVBox = new VBox(20, DatabaseTitle, ComparisonPageButton, gridPane2);
        
        CompTitleVBox.setAlignment(Pos.TOP_CENTER);
        DataTitleVBox.setAlignment(Pos.TOP_CENTER);
        
        
    //REUSABLE- Create reusable elements for the GUI throughout the Program
        Button browseButton1 = new Button("Browse...");
        Button browseButton2 = new Button("Browse...");
        CheckBox saveBox = new CheckBox("Save to the Database");
            saveBox.setVisible(true);
        TextField pastedCodeField = new TextField();
        
    //FileChooser Setup and Options
        FileChooser file1Chooser = new FileChooser();
            file1Chooser.setTitle("Select File 1");
            file1Chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Java Files", ".java"),
                new ExtensionFilter("All Files", "*.*"));
        
        FileChooser file2Chooser = new FileChooser();
            file2Chooser.setTitle("Select File 2");
            file2Chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Java Files", ".java"),
                new ExtensionFilter("All Files", "*.*"));
        
            
    //create 4 panels with its label and HBoxes & VBoxes for the layout in the GridLayout
    //Top Left Panel of the Program
        Label topLeftPanLabel = new Label("Choose File 1 Below to Upload");
        HBox topLeftPanHBox = new HBox(topLeftPanLabel);
        HBox BrowseButtonHBox = new HBox(browseButton1);
        HBox SaveCheckHBox = new HBox(saveBox);
        VBox topLeftPanVBox = 
            new VBox(30, topLeftPanHBox, BrowseButtonHBox, SaveCheckHBox);
    //Top Left Panel Handlers
        browseButton1.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //have the file chooser appear, then have the filename
                File file1Selection = file1Chooser.showOpenDialog(primaryStage);
                String file1name = file1Selection.getName();
                if (file1name != null) {
                    browseButton1.setText(file1name);
                }
            }
        });
        
        
    //Top Right Panel of the Program
        Label topRightPanLabel = new Label("Choose File 2 Below to Upload");
        HBox topRightPanHBox = new HBox(30, topRightPanLabel);
        Button compareButton1 = new Button("Compare File 1 to File 2");
        VBox topRightPanVBox = 
            new VBox(30, xDivider, topRightPanHBox, browseButton2, saveBox, compareButton1);   
    //Top Right Panel Handlers
        browseButton2.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                File file2Selection = file1Chooser.showOpenDialog(primaryStage);
                String file1name = file2Selection.getName();
                if (file1name != null) {
                    browseButton1.setText(file1name);
                }
            }
        });
        compareButton1.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override public void handle(ActionEvent e) {
               /*
               Call the compare function and pass it the file1 and file2 from the
               file choosers and have the comparison class run its course
               */
               //CC.Compare();
           } 
        });
        
        
    //Bottom Left Panel of the Program
        //Problem: LogoView will not show
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        Image Logo = new Image("file:Logo.PNG");
            ImageView LogoView = new ImageView();
            LogoView.setImage(Logo);
            LogoView.setFitWidth(100);
            LogoView.setPreserveRatio(true);
            LogoView.setSmooth(true);
            LogoView.setCache(true);
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(30, xDivider, botLeftPanHBox, LogoView);
        
        
    //Bottom Right Panel of the Program
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        Button compareButton2 = new Button("Compare File 1 to Pasted Code");
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = 
            new VBox(30, xDivider, botRightPanHBox, pastedCodeField, saveBox, compareButton2);
        
    //Adding the elements to the gridPane sections
        gridPane.add(topLeftPanVBox, 0, 0);
        gridPane.add(topRightPanVBox, 1, 0);
        gridPane.add(botLeftPanVBox, 0, 1);
        gridPane.add(botRightPanVBox, 1, 1);
        
    /*
        RESULTS SCREEN GUI CODE
    */
        FileChooser file3Chooser = new FileChooser();
            file3Chooser.setTitle("Select File");
            file3Chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Java Files", ".java"),
                new ExtensionFilter("All Files", "*.*"));
            
    //Left Column Setup
        Label LeftColLabel = new Label("Choose File to Compare Below");
        Button browseButton3 = new Button("Browse...");
        Label LeftColLabel2 = new Label("Choose Database Interaction: ");
        Button File1DatabaseButton = new Button("Compare To The Database");
        Button AddToDatabase = new Button("Add To The Database");
        Button RemToDatabase = new Button("Remove From The Database");
        VBox LeftColVBox = new VBox(30, LeftColLabel, browseButton3, LeftColLabel2,
                File1DatabaseButton, AddToDatabase, RemToDatabase);
        
    //Left Column Handlers
        browseButton3.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                File file3Selection = file3Chooser.showOpenDialog(primaryStage);
                String file1name = file3Selection.getName();
                if (file1name != null) {
                    browseButton3.setText(file1name);
                }
            }
        });
        
        
    //Right Column Setup
        Label RightColLabel = new Label("Paste Text Below for Database Comparison");
        Button PastedCompareButton = new Button("Compare Paste Contents to Database");
        Button AddPastedButton = new Button("Add Paste Contents to Database");
        VBox RightColVBox = new VBox(30, RightColLabel, pastedCodeField, LeftColLabel2, 
                PastedCompareButton, AddPastedButton);
        
        
    //adding elements to the Gridpane
        gridPane2.add(LeftColVBox, 0,0);
        gridPane2.add(RightColVBox, 1,0);
        
    //Constraints for the left and right columns
        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setPercentWidth(100);
        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setPercentWidth(100);
        
        //Constraints for the First row (0,0 & 1,0) and Second Row (0,1 & 1,1)
        //RowConstraints firstRow = new RowConstraints();
        //firstRow.setPercentHeight(150);
        //RowConstraints secondRow = new RowConstraints();
        //secondRow.setPercentHeight(150);
        
    //each gridPane column specified with the parameters are applied with the column constraints
        gridPane.getColumnConstraints().addAll(leftColumn, rightColumn); 
        gridPane2.getColumnConstraints().addAll(leftColumn, rightColumn);
        //gridPane.getRowConstraints().addAll(firstRow, secondRow);
        
        
    //SCENES: Scene Handling
        Scene Compscene = new Scene(CompTitleVBox, 600, 600);
        Scene DataScene = new Scene(DataTitleVBox, 600, 600);
        
    //DataPageButton Handler 
        DataPageButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {

                    primaryStage.setScene(DataScene);
                    primaryStage.show();
                }
            });
    //ComparisonPageButton Handler
        ComparisonPageButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {   
                    primaryStage.setScene(Compscene);
                    primaryStage.show();
                }
            }); 
        
        primaryStage.setTitle("CPC Application");
        primaryStage.setScene(Compscene);
        primaryStage.show();
    }
    
    //Class for the second screen of the program
    /*
    public class DatabaseScreen extends Application {
        
    }
    
    public class ResultScreen extends Application {
        
    }
    */
    
    
    /**
     * @param args the command line arguments
     * 
     */
    //Don't touch this
    public static void main(String[] args) {
        launch(args);
    }
    
}
