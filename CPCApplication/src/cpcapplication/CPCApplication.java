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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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
import javafx.scene.control.ContentDisplay;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jsdan
 */
public class CPCApplication extends Application {
    //these act as Member Variables
    private Label CompareTitleLabel;
    File file1Selection; //Screen 1- File 1 file selection
    File file2Selection; //Screen 1- File 2 file selection
    File file3Selection; //Screen 2- File to add/rem to the Database
    File filePastedSelection; //Paste Box File Converter
    File filePastedSelection2; //Paste Box File Converter- Database page
    TextArea pastedCodeField, pastedCodeField2, pastedCodeField3;
    Scene CompScene, DataScene, ResultScene;
    VBox ResultScreen;
    ColumnConstraints leftColumn;
    ColumnConstraints rightColumn;
    
    
    //start of the Program from a GUI perspective
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    //Allow access to your other classes
        CodeComparison CC = new CodeComparison();
        Library LIB = new Library();
        
    /*
        SCREEN 1 GUI CODE
    */  
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
        //26 characters
        Label xDivider = new Label("==========================");
        Label xDivider2 = new Label("==========================");
        Label xDivider3 = new Label("==========================");
        Label xDivider4 = new Label("===========  OR  ===========");
        //Constraints for the left and right columns
        leftColumn = new ColumnConstraints();
            leftColumn.setPercentWidth(100);
        rightColumn = new ColumnConstraints();
            rightColumn.setPercentWidth(100);
        
        //each gridPane column specified with the parameters are applied with the column constraints
        gridPane.getColumnConstraints().addAll(leftColumn, rightColumn); 
        gridPane2.getColumnConstraints().addAll(leftColumn, rightColumn);
        
        
    //TITLE- Create the title labels for the screens
        Label ComparisonTitle = new Label("Comparisons");
        Label DatabaseTitle = new Label("Database");
    //Create the Navigation Buttons
        Button DataPageButton = new Button("Go To Database Page");
        Button ComparisonPageButton = new Button("Go To Comparisons Page");
    //Create a Hbox and for the ComparisonTitle
        HBox titleHBox = new HBox(10, ComparisonTitle);
    //Create a VBox fo the TitleBox
        VBox CompTitleVBox = new VBox(10, ComparisonTitle, DataPageButton, gridPane);
        VBox DataTitleVBox = new VBox(10, DatabaseTitle, ComparisonPageButton, gridPane2);
        
        CompTitleVBox.setAlignment(Pos.TOP_CENTER);
        DataTitleVBox.setAlignment(Pos.TOP_CENTER);
    
        
    /*
        RESULT SCREEN GUI CODE
        Problem: Titles and Labels not centering correctly
    */
        Label ResultTitle = new Label("Results");
            ResultTitle.setAlignment(Pos.CENTER);
        Label rCodeTitle = new Label("Raw Code Comparison"); 
            rCodeTitle.setAlignment(Pos.CENTER);
        GridPane resultPaneA = new GridPane(); //Will hold the TextArea's for the Raw Code Comparison
        Label cStyleTitle = new Label("Code Style Comparison");
            cStyleTitle.setAlignment(Pos.CENTER);
        GridPane resultPaneB = new GridPane();//Will hold the TextArea's for the Code Style Comparison
        GridPane resultPaneC = new GridPane();
    
    //Adding to the Gridpanes and formatting
        resultPaneA.getColumnConstraints().addAll(leftColumn, rightColumn);
        resultPaneB.getColumnConstraints().addAll(leftColumn, rightColumn);
        resultPaneC.getColumnConstraints().addAll(leftColumn, rightColumn);
    
        TextArea file1CodeBoxA = new TextArea();
            file1CodeBoxA.setWrapText(true);
            file1CodeBoxA.setPrefHeight(100);
            file1CodeBoxA.setPrefColumnCount(2);
            file1CodeBoxA.setPrefWidth(100);
        TextArea comparisonFileCodeBoxA = new TextArea();
            comparisonFileCodeBoxA.setWrapText(true);
            comparisonFileCodeBoxA.setPrefHeight(100);
            comparisonFileCodeBoxA.setPrefColumnCount(2);
            comparisonFileCodeBoxA.setPrefWidth(100);
        TextArea file1CodeBoxB = new TextArea();
            file1CodeBoxA.setWrapText(true);
            file1CodeBoxA.setPrefHeight(100);
            file1CodeBoxA.setPrefColumnCount(2);
            file1CodeBoxA.setPrefWidth(100);
        TextArea comparisonFileCodeBoxB = new TextArea();
            comparisonFileCodeBoxA.setWrapText(true);
            comparisonFileCodeBoxA.setPrefHeight(100);
            comparisonFileCodeBoxA.setPrefColumnCount(2);
            comparisonFileCodeBoxA.setPrefWidth(100);
    
    //NOTE: resultPaneB may need its own handlers, meaning new textArea's
        resultPaneA.add(file1CodeBoxA, 0, 0);
        resultPaneA.add(comparisonFileCodeBoxA, 1, 0);
        resultPaneB.add(file1CodeBoxB, 0, 0);
        resultPaneB.add(comparisonFileCodeBoxB, 1, 0);
    
    //Everything will be loaded into the VBox, even the footer
        ResultScreen = new VBox(30, ResultTitle, rCodeTitle, resultPaneA, 
            cStyleTitle, resultPaneB, resultPaneC);
    
    //SCENES: Scene Handling
        CompScene = new Scene(CompTitleVBox, 600, 600);
        DataScene = new Scene(DataTitleVBox, 600, 600);
        ResultScene = new Scene(ResultScreen, 600, 600);
        
    //REUSABLE GUI ELEMENTS
        Button browseButton1 = new Button("Browse...");
        Button browseButton2 = new Button("Browse...");
        CheckBox saveBox = new CheckBox("Save to the Database");
            saveBox.setVisible(true);
        CheckBox saveBox2 = new CheckBox("Save to the Database");
            saveBox2.setVisible(true);
        CheckBox saveBox3 = new CheckBox("Save to the Database");
            saveBox3.setVisible(true);
        
    //TEXTAREA CREATION
        pastedCodeField = new TextArea();
            pastedCodeField.setWrapText(true);
            pastedCodeField.setPrefHeight(100);
            pastedCodeField.setPrefColumnCount(2);
            pastedCodeField.setPrefWidth(100);
        pastedCodeField2 = new TextArea();
            pastedCodeField2.setWrapText(true);
            pastedCodeField2.setPrefHeight(100);
            pastedCodeField2.setPrefColumnCount(2);
            pastedCodeField2.setPrefWidth(100);
        pastedCodeField3 = new TextArea();
            pastedCodeField3.setWrapText(true);
            pastedCodeField3.setPrefHeight(100);
            pastedCodeField3.setPrefColumnCount(2);
            pastedCodeField3.setPrefWidth(100);
            
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
                new ExtensionFilter("All Files", "*.*"),
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Java Files", ".java")
                );
        
            
    //TOP LEFT PANEL ELEMENTS
        Label topLeftPanLabel = new Label("Choose File 1 Below to Upload");
        HBox topLeftPanHBox = new HBox(topLeftPanLabel);
        HBox BrowseButtonHBox = new HBox(browseButton1);
        HBox SaveCheckHBox = new HBox(saveBox);
        VBox topLeftPanVBox = 
            new VBox(20, xDivider, topLeftPanHBox, BrowseButtonHBox, SaveCheckHBox);
    //TOP LEFT PANEL HANDLERS
        browseButton1.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //have the file chooser appear, then have the filename
                file1Selection = file1Chooser.showOpenDialog(primaryStage);
                String file1name = file1Selection.getName();
                if (file1name != null) {
                    browseButton1.setText(file1name);
                }
            }
        });
        
        
    //TOP RIGHT PANEL ELEMENTS
        Label topRightPanLabel = new Label("Choose File 2 Below to Upload");
        HBox topRightPanHBox = new HBox(30, topRightPanLabel);
        Button compareButton1 = new Button("Compare File 1 to File 2");
        VBox topRightPanVBox = 
            new VBox(20, xDivider2, topRightPanHBox, browseButton2, saveBox2, compareButton1);   
    //TOP RIGHT PANEL HANDLERS
        browseButton2.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file2Selection = file2Chooser.showOpenDialog(primaryStage);
                String file2name = file2Selection.getName();
                if (file2name != null) {
                    browseButton2.setText(file2name);
                }
            }
        });
        compareButton1.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override public void handle(ActionEvent e) {
               CC.Stage1(file1Selection, file2Selection);
               
               //if the save checkboxes are checked...
               if (saveBox.isSelected() == true) {
                   //Used Netbeans Hint system, complained otherwise
                   try {
                       LIB.addAFile(file1Selection);
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               if (saveBox2.isSelected() == true) {
                   try {
                       LIB.addAFile(file2Selection);
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               
               primaryStage.setScene(ResultScene);
               primaryStage.show();
           } 
        });
        
        
        
    //BOTTOM LEFT PANEL OF THE PROGRAM
        //Problem: LogoView will not show
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        //FileInputStream inputstream = new FileInputStream("file:CPCApplication\\src\\cpcapplication\\Logo.png");
        Image Logo = new Image("file:/CPCApplication/src/cpcapplication/Logo.PNG");
            ImageView LogoView = new ImageView(Logo);
            LogoView.setImage(Logo);
            LogoView.setFitWidth(100);
            LogoView.setFitHeight(100);
            //LogoView.setPreserveRatio(true);
            //LogoView.setSmooth(true);
            //LogoView.setCache(true);
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(20, xDivider3, botLeftPanHBox, LogoView);
        
        
    //BOTTOM RIGHT PANEL OF THE PROGRAM
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        Button compareButton2 = new Button("Compare File 1 to Pasted Code");
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = 
            new VBox(20, xDivider4, botRightPanHBox, pastedCodeField2, saveBox3, compareButton2);
    //BOTTOM RIGHT PANEL HANDLERS
        compareButton2.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override public void handle(ActionEvent e) {
               //Stage1 needs a second parameter, that being the pastedCodeField2
               //and needs the contents passed as type File.
               
               //if the save checkboxes are checked...
               if (saveBox.isSelected() == true) {
                   try {
                       LIB.addAFile(file1Selection);
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               if (saveBox3.isSelected() == true) {
                   try {
                       
                       LIB.addAFile(TextAreaGet(pastedCodeField2));
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   
               }
               
               primaryStage.setScene(ResultScene);
               primaryStage.show();
           } 
        });
        
        
    //Adding the elements to the gridPane sections-- SCREEN 1
        gridPane.add(topLeftPanVBox, 0, 0);
        gridPane.add(topRightPanVBox, 1, 0);
        gridPane.add(botLeftPanVBox, 0, 1);
        gridPane.add(botRightPanVBox, 1, 1);
        
    /*
        SCREEN 2 GUI CODE ===============================================
    */
        FileChooser file3Chooser = new FileChooser();
            file3Chooser.setTitle("Select File");
            file3Chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Java Files", ".java"),
                new ExtensionFilter("All Files", "*.*"));
            
    //LEFT COLUMN SETUP
        Label LeftColLabel = new Label("Choose File to Compare Below");
        Button browseButton3 = new Button("Browse...");
        Label LeftColLabel2 = new Label("Choose Database Interaction: ");
            Label LeftColLabel3 = new Label("Choose Database Interaction: ");
        //Button File1DatabaseButton = new Button("Compare To The Database");
        Button AddToDatabase = new Button("Add To The Database");
        Button RemToDatabase = new Button("Remove From The Database");
        VBox LeftColVBox = new VBox(30, LeftColLabel, browseButton3, LeftColLabel2,
                /*File1DatabaseButton,*/ AddToDatabase, RemToDatabase);
        
    //LEFT COLUMN HANDLERS
        browseButton3.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file3Selection = file3Chooser.showOpenDialog(primaryStage);
                String file3name = file3Selection.getName();
                if (file3name != null) {
                    browseButton3.setText(file3name);
                }
            }
        });
        /*
        File1DatabaseButton.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //Use this to compare the passed in file to the database
                //CTL.CompareToLib(file3name);
            }
        });
        */
        AddToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                try {
                    //make it so that redundant files are detected?
                    LIB.addAFile(file3Selection);
                } catch (IOException ex) {
                    Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        RemToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //DB.RemToLib(file3Selection);
            }
        });
        
        
    //RIGHT COLUMN ELEMENTS
        Label RightColLabel = new Label("Paste Text Below for Database Comparison");
        //Button PastedCompareButton = new Button("Compare Paste Contents to Database");
        Button AddPastedButton = new Button("Add Paste Contents to Database");
        VBox RightColVBox = new VBox(30, RightColLabel, pastedCodeField, LeftColLabel3, 
                /*PastedCompareButton,*/ AddPastedButton);
        //AddPastedButton Handler for TextArea File Conversion
        AddPastedButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {
                    //DB.addAFile();
                }
            });
        
        
    //adding elements to the Gridpane
        gridPane2.add(LeftColVBox, 0,0);
        gridPane2.add(RightColVBox, 1,0);
        
        
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
                    primaryStage.setScene(CompScene);
                    primaryStage.show();
                }
            }); 
        
        primaryStage.setTitle("CPC Application");
        primaryStage.setScene(CompScene);
        primaryStage.show();
    }
    
    //HELPER METHOD FOR TEXT AREA 
        public File TextAreaGet(TextArea textarea) {
            String textHolder = textarea.getText().replaceAll("\n", System.getProperty("line.separator"));
            File file = new File(textHolder);
            File rename = new File("PastedContents");
                rename.renameTo(file);
            return file;
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
