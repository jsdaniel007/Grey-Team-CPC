/*
 * Senior Portfolio Entry
 * Student Name: Chris McClure
 * Program Name: Code Plagiarism Checker
 * Creation Date: 
 * Last Modified Date:
 * CSCI Course: CSCI 495 Systems Analysis and Software Design
 * Grade Received: 100%
 * Comments regarding design:
 * The GUI code below is divided up into 3 screens:
 *   -Comparisons Page- staging area for comparing two files
 *   -Database Page
 *   -Results Page
 * Note: the "database" referred to in our code is not a true database, but it 
 * was left for room to expansions
 * 
 */
package cpcapplication;

import java.io.*; 
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;

/*
    --Grey Team Code Plagiarism Checker---
    This program will allow the user to compare 2 code files, with the option of one
    to be selected from a file browser, with the second file being from a file 
    browser as well or a textarea that converts it into a file within the first 
    page being dubbed the "comparisons page" in the program.
    The second page, the "Database page" does not actually have a true Database 
    such as SQL or the other options available, although this is a potential expansion 
    for the future. Instead of a database, it actually makes a folder within the 
    CPCApplication that will hold the files that are being saved.
    The Results Screen will display both files chosen from the Comparisons Page 
    and easily displays the results to the user. 
*/

/**
 * Github Contributers
 * @author jsdaniel007 (Chris McClure)-- Main GUI Design Lead/Programmer
 * @co-author skippercab (Chris Bell)-- GUI Designer/Ideas Department
 * @co-author N/A (Frederick Jeffers)-- GUI Designer/Ideas Department/Graphic Design
 * 
 * 
 */
public class CPCApplication extends Application {
    //these act as Member Variables
    File file1Selection; //Screen 1- File 1 file selection
    File file2Selection; //Screen 1- File 2 file selection
    File file3Selection; //Screen 2- File to add/rem to the Database
    File pastedCodeSelection; //Paste Box File Converter
    File pastedCodeSelection2; //Paste Box File Converter- Database page
    TextArea pastedCodeField, pastedCodeField2;
    Scene CompScene, DataScene, ResultScene;
    VBox ResultScreen;
    ColumnConstraints leftColumn;
    ColumnConstraints rightColumn;
    String file1name;
    String file2name;
    String ResultFileText1, ResultFileText2, ResultFileText3;
    int PercentageMatch;
    String PercentageMatchStr;
    Label PercentageLabel;
    TextArea file1CodeBoxA;
    TextArea comparisonFileCodeBoxA;
    TextArea file1CodeBoxB;
    GridPane resultPaneC;
    HBox ButtonHBox;
    Label SavePercentConfirm1 = new Label();
    Label SavePercentConfirm2 = new Label();
    
    
    /*
    *Initialize the program to show the primaryStage, that is the first initial
    *screen, labeled "Comparisons" at the top of the screen
    */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
    //Allow access to your other class objects
        CodeComparison CC = new CodeComparison();
        Library LIB = new Library();
        
    /*
        SCREEN 1: Comparisons 
        This screen is the staging area for the file selection for the two 
        code files to be compared to each other
    */
    //GridPane to have the elements placed in for later
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
        
    //GUI elements to be inserted into the GridPanes
        Button browseButton1 = new Button("Browse...");
        Button browseButton2 = new Button("Browse...");
        CheckBox saveBox = new CheckBox("Save to the Database");
            saveBox.setVisible(true);
        CheckBox saveBox2 = new CheckBox("Save to the Database");
            saveBox2.setVisible(true);
        CheckBox saveBox3 = new CheckBox("Save to the Database");
            saveBox3.setVisible(true);
        
    //TextArea's to take the text contents from later
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
            
    //FileChooser Setup and Options
        FileChooser file1Chooser = new FileChooser();
            file1Chooser.setTitle("Select File 1");
            file1Chooser.getExtensionFilters().addAll(
                //new ExtensionFilter("Text Files", "*.txt"),
                //new ExtensionFilter("Java Files", ".java"),
                new ExtensionFilter("All Files", "*.*"));
        
        FileChooser file2Chooser = new FileChooser();
            file2Chooser.setTitle("Select File 2");
            file2Chooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*")
                //new ExtensionFilter("Text Files", "*.txt"),
                //new ExtensionFilter("Java Files", ".java")
                );
        
/*
    SCREEN 1 CODE ===============================================
        Top Left Pane: File 1 Upload
        Top Right Pane: File 2 Upload
        Bottom Left Pane: Logo and Clear Fields 
        Bottom Right Pane: Pasted Code Field to act as File 2
*/
    //TOP LEFT PANEL ELEMENTS
        Label topLeftPanLabel = new Label("Choose File 1 Below to Upload");
        HBox topLeftPanHBox = new HBox(topLeftPanLabel);
        HBox BrowseButtonHBox = new HBox(browseButton1);
        HBox SaveCheckHBox = new HBox(saveBox);
        VBox topLeftPanVBox = 
            new VBox(20, xDivider, topLeftPanHBox, BrowseButtonHBox, SaveCheckHBox);
        
        
    //TOP RIGHT PANEL ELEMENTS
        Label topRightPanLabel = new Label("Choose File 2 Below to Upload");
        HBox topRightPanHBox = new HBox(30, topRightPanLabel);
        Button compareButton1 = new Button("Compare File 1 to File 2");
        VBox topRightPanVBox = 
            new VBox(20, xDivider2, topRightPanHBox, browseButton2, saveBox2, compareButton1);
        
    
    //BOTTOM LEFT PANEL OF THE PROGRAM
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        Button ClearAll = new Button("Clear All Fields");
        Image Logo = new Image("file:Logo.PNG");
            ImageView LogoView = new ImageView(Logo);
            LogoView.setImage(Logo);
            LogoView.setFitWidth(250);
            LogoView.setFitHeight(250);
            LogoView.setPreserveRatio(true);
            LogoView.setSmooth(true);
            LogoView.setCache(true);
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(20, xDivider3, botLeftPanHBox, LogoView, ClearAll);
        
        
    //BOTTOM RIGHT PANEL OF THE PROGRAM
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        TextField PastedFileName = new TextField();
        Button compareButton2 = new Button("Compare File 1 to Pasted Code");
        Label PastedCodeError = new Label("Enter Your Text!");
            PastedCodeError.setTextFill(Color.web("#ff0000"));
            PastedCodeError.setVisible(false);
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = new VBox(20, xDivider4, botRightPanHBox, 
                pastedCodeField, saveBox3, compareButton2, PastedCodeError);
        
    //SCENE 1: COMPARISON SCREEN HANDLERS
        //TOP LEFT PANEL HANDLERS
        //This handler allows the user to select File 1 in the top left pane
        browseButton1.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //have the file chooser appear, then have the filename
                file1Selection = file1Chooser.showOpenDialog(primaryStage);
                file1name = file1Selection.getName();
                if (file1name != null) {
                    browseButton1.setText(file1name);
                }
            }
        });
        
        //TOP RIGHT PANEL HANDLERS
        //This Handler allows the user to select File 2 in the top right pane
        browseButton2.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file2Selection = file2Chooser.showOpenDialog(primaryStage);
                file2name = file2Selection.getName();
                if (file2name != null) {
                    browseButton2.setText(file2name);
                }
            }
        });
        
        //clears all the fields to default values
        ClearAll.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                browseButton1.setText("Browse...");
                browseButton2.setText("Browse...");
                file2Selection = null;
                pastedCodeField.setText("");
                pastedCodeSelection = null;
                setPercentage(0);
                file1CodeBoxA.setText("");
                comparisonFileCodeBoxA.setText("");
            }
        });
        
        /*
        Runs the comparison function, while also setting up the Results Screen
        to print out the results of the comparison function
        */
        compareButton1.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override 
           public void handle(ActionEvent e) {
               /*
               Have the program check the pastedFileSelection to see if it has a
               file, if file1Selection is not chosen, and if so, do not proceed
               */
               setPercentage(CC.Stage1(file1Selection, file2Selection)); 
               System.out.println("Percentage Report: " + getPercentage());
               
               PercentageLabel.setText("Percentage Report: " + getPercentage());
               
               //get the text from the file into a string
               try { ResultFileText1 = new String(Files.readAllBytes(Paths.get(file1Selection.toString())));
               } catch (IOException ex) { Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
               }
               try { ResultFileText2 = new String(Files.readAllBytes(Paths.get(file2Selection.toString())));
               } catch (IOException ex) {Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
               }
               //Set the File Contents to the code boxes
               file1CodeBoxA.setText(ResultFileText1);
               comparisonFileCodeBoxA.setText(ResultFileText2);
               
               //if the save checkboxes are checked...
               if (saveBox.isSelected() == true) {
                   //Used Netbeans Hint system, complained otherwise
                   try {
                       //..add a file to the Library through the LIB object
                       LIB.addAFile(file1Selection);
                       SavePercentConfirm1.setText(file1name + " has been added to the database");
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               if (saveBox2.isSelected() == true) {
                   try {
                       LIB.addAFile(file2Selection);
                       SavePercentConfirm2.setText(file2name + " has been added to the database");
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               //...show the result screen that we prepped prior
               primaryStage.setScene(ResultScene);
               primaryStage.show();
               
           } 
        });
        
        //BOTTOM RIGHT PANEL HANDLERS
        /*
        This handler is similar to the top right panel, with the major difference
        being the TextArea acting as the File 2, regardless of whether the 
        save toggle boxes were checked
        */
        compareButton2.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override public void handle(ActionEvent e) {
               /*This should handle the file selection exception handling, such as
               if the file 1 is not selected, the file 2 IS selected, or if the 
               textArea is empty with nothing to save, DO NOT MOVE ON
               */
               //converts the content of the TextArea, then runs that into the comparison
               pastedCodeSelection = TextAreaGet(pastedCodeField, "Unnamed");
               
               setPercentage(CC.Stage1(file1Selection, pastedCodeSelection)); 
               System.out.println("Percentage Report: " + getPercentage());
               
               PercentageLabel.setText("Percentage Report: " + getPercentage());
               
               if (pastedCodeField.getText().isEmpty() || !file1Selection.exists() ) {
                   PastedCodeError.setVisible(true);
               } else {
                //Run a helper function to get the contents of the file from the path
                    try { ResultFileText1 = new String(Files.readAllBytes(Paths.get(file1Selection.toString())));
                    } catch (IOException ex) { Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { ResultFileText3 = new String(Files.readAllBytes(Paths.get(pastedCodeSelection.toString())));
                    } catch (IOException ex) {Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
               //Set the File Contents to the code boxes andreplace the reused items as new items 
               file1CodeBoxA.setText(ResultFileText1);
               comparisonFileCodeBoxA.setText(ResultFileText3);
               
                //if the save checkboxes are checked...
                if (saveBox.isSelected() == true) {
                   try {
                       LIB.addAFile(file1Selection);
                       SavePercentConfirm1.setText(file1name + " has been added to the database");
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
                if (saveBox3.isSelected() == true) {
                   try {
                       //pass the pasted contents to the addAFile
                       LIB.addAFile(pastedCodeSelection);
                       SavePercentConfirm2.setText(pastedCodeSelection.getName() + " has been added to the database");
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
                
                primaryStage.setScene(ResultScene);
                primaryStage.show();
               }
               
           } 
        });
        
    //Adding the elements to the gridPane sections for Screen 1 Comparisons Screen
        gridPane.add(topLeftPanVBox, 0, 0);
        gridPane.add(topRightPanVBox, 1, 0);
        gridPane.add(botLeftPanVBox, 0, 1);
        gridPane.add(botRightPanVBox, 1, 1);
        
/*
    SCREEN 2 GUI CODE ===============================================
    This code is for the "Database" screen, where the left column is used for 
    selecting an individual file and saving that to our database, or even removing it
    if desired. 
    
    The Left Column is used for copying and pasting a file to the TextArea so that
    it can be saved with a name to our database
*/
        FileChooser file3Chooser = new FileChooser();
        file3Chooser.setTitle("Select File");
        file3Chooser.getExtensionFilters().addAll(
            //new ExtensionFilter("Text Files", "*.txt"),
            //new ExtensionFilter("Java Files", ".java"),
            new ExtensionFilter("All Files", "*.*"));
            
    //LEFT COLUMN SETUP
        Label LeftColLabel = new Label("Choose File to Compare Below");
        Button browseButton3 = new Button("Browse...");
        Label LeftColLabel2 = new Label("Choose Database Interaction: ");
        Label AddedConfirmationL = new Label(" ");
        //Button File1DatabaseButton = new Button("Compare To The Database");
        Button AddToDatabase = new Button("Add To The Database");
        Button RemToDatabase = new Button("Remove From The Database");
        Button ClearAll2 = new Button("Clear All Fields");
        VBox LeftColVBox = new VBox(30, LeftColLabel, browseButton3, LeftColLabel2,
                /*File1DatabaseButton,*/ AddToDatabase, RemToDatabase, ClearAll2, AddedConfirmationL);

        /* CUT Feature-- Compare to the Database
        File1DatabaseButton.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //Use this to compare the passed in file to the database
                //CTL.CompareToLib(file3name);
            }
        });
        */
        
    //RIGHT COLUMN ELEMENTS
        Label RightColLabel = new Label("Paste Text Below for Database Addition");
        Label RightColLabel2 = new Label("Name Your File Before Adding to The Database:");
        Label PastedBoxError = new Label("Please Name Your Text Before Adding!");
            PastedBoxError.setTextFill(Color.web("#ff0000"));
            PastedBoxError.setVisible(false);
        Label AddedConfirmationR = new Label(" ");
        //Button PastedCompareButton = new Button("Compare Paste Contents to Database");
        Button AddPastedButton = new Button("Add Paste Contents to Database");
        TextField NamePasted = new TextField();
        VBox RightColVBox = new VBox(20, RightColLabel, pastedCodeField2, RightColLabel2,  
                /*PastedCompareButton,*/ NamePasted, AddPastedButton, PastedBoxError, AddedConfirmationR);
        
        //SCENE 2 HANDLERS FOR DATABASE SCREEN
        //Allows user to select a file to save to database
        browseButton3.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file3Selection = file3Chooser.showOpenDialog(primaryStage);
                String file3name = file3Selection.getName();
                if (file3name != null) {
                    browseButton3.setText(file3name);
                }
            }
        });
        
        //Allows the user to add a file and give feedback to it being saved
        AddToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                try {
                    if (file3Selection != null) {
                    String file3name = file3Selection.getName();
                    LIB.addAFile(file3Selection);
                    AddedConfirmationL.setText(file3name + " added!");
                    } else {
                        AddedConfirmationL.setText("No File Chosen!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //similar to AddToDatabase, but it just removes the file
        RemToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                if (file3Selection != null) {
                LIB.remAFile(file3Selection);
                String file3name = file3Selection.getName();
                AddedConfirmationL.setText(file3name + " removed!");
                } else {
                    AddedConfirmationL.setText("No File Chosen!");
                }
            }
        });
        
        //clears the fields to default vales
        ClearAll2.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file3Selection = null;
                NamePasted.setText("");
                AddedConfirmationL.setVisible(false);
                AddedConfirmationR.setVisible(false);
                pastedCodeField2.setText("");
                NamePasted.setText("");
                PastedBoxError.setVisible(false);
            }
        });
            
        //AddPastedButton Handler for TextArea File Conversion with name of new file
        AddPastedButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {
                    PastedBoxError.setVisible(false);
                    File fileName = new File(NamePasted.getText());
                    pastedCodeSelection2 = TextAreaGet(pastedCodeField2, NamePasted.getText());
                    
                    //if either the TextArea or the NamePasted Field is empty, return an error label
                    if (pastedCodeField2.getText().isEmpty() || NamePasted.getText().isEmpty()) {
                        PastedBoxError.setVisible(true);
                    } 
                    else {
                    //...otherwise add the file to the library with a default name
                        try {
                            //you should pass the file path
                            LIB.addAFile(pastedCodeSelection2);
                            System.out.println("pastedCodeSelection2 name: " + pastedCodeSelection2.getAbsolutePath());
                            System.out.println("pcs2: " + pastedCodeSelection2);
                            System.out.println("fileName: " + fileName);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        
    /*
        RESULT SCREEN GUI CODE
        Dividing up into two columns, left and right, this shows the contents of
        two files uploaded, notifying the user if the files were added to the 
        database at this point.
    */
    //A
        Label ResultTitle = new Label("Results");   
        Label rCodeTitle = new Label("Raw Code Comparison"); 
        Label File1Label = new Label("File 1 Code");
        Label File2Label = new Label("File 2 Code");
        HBox ATitle = new HBox(250, File1Label, File2Label);
            ATitle.setAlignment(Pos.CENTER);
        VBox rTitleBox = new VBox(20, ResultTitle, rCodeTitle, ATitle);
            rTitleBox.setAlignment(Pos.CENTER);
        GridPane resultPaneA = new GridPane(); //Will hold the TextArea's for the Raw Code Comparison
    
    //B -- Unsure if this is necessary
        //Label cStyleTitle = new Label("Code Style Comparison");
        //HBox rCenterTitle = new HBox(10, cStyleTitle);
            //rCenterTitle.setAlignment(Pos.CENTER);
        //GridPane resultPaneB = new GridPane();//Will hold the TextArea's for the Code Style Comparison
    
    //C 
        //The PercenatageLabel will be updated once the event handlers are called
        PercentageLabel = new Label();
        Button retToHome = new Button("Make Another Comparison");
        ButtonHBox = new HBox(250, retToHome);
            ButtonHBox.setAlignment(Pos.CENTER);
        //Make the HBox global, call and use it in the compare 1 function, or set it in
        //a method to just call it within the actual comparison handler
        //and any reference to the PercentageHBox should be in the method
        HBox PercentageHBox = new HBox(250, PercentageLabel);
           PercentageHBox.setAlignment(Pos.CENTER);
        VBox PercentageVBox = new VBox(10, PercentageHBox, SavePercentConfirm1, SavePercentConfirm2);
        resultPaneC = new GridPane();
        
    
    //Adding to the Gridpanes and formatting
        resultPaneA.getColumnConstraints().addAll(leftColumn, rightColumn);
        //resultPaneB.getColumnConstraints().addAll(leftColumn, rightColumn);
        resultPaneC.getColumnConstraints().addAll(leftColumn, rightColumn);
        
        resultPaneA.setVgap(20);
        resultPaneA.setHgap(20);
        resultPaneA.setPadding(new Insets(10, 10, 10, 10));
        
        //resultPaneB.setVgap(20);
        //resultPaneB.setHgap(20);
        //resultPaneB.setPadding(new Insets(10, 10, 10, 10));
        
        resultPaneC.setVgap(20);
        resultPaneC.setHgap(20);
        resultPaneC.setPadding(new Insets(10, 10, 10, 10));
        
        file1CodeBoxA = new TextArea(ResultFileText1);
            file1CodeBoxA.setWrapText(true);
            file1CodeBoxA.setPrefHeight(200);
            file1CodeBoxA.setPrefColumnCount(2);
            file1CodeBoxA.setPrefWidth(80);
        comparisonFileCodeBoxA = new TextArea(ResultFileText2);           
            comparisonFileCodeBoxA.setWrapText(true);
            comparisonFileCodeBoxA.setPrefHeight(200);
            comparisonFileCodeBoxA.setPrefColumnCount(2);
            comparisonFileCodeBoxA.setPrefWidth(80);
        TextArea file1CodeBoxB = new TextArea();
            file1CodeBoxB.setWrapText(true);
            file1CodeBoxB.setPrefHeight(150);
            file1CodeBoxB.setPrefColumnCount(2);
            file1CodeBoxB.setPrefWidth(80);
        TextArea comparisonFileCodeBoxB = new TextArea();
            comparisonFileCodeBoxB.setWrapText(true);
            comparisonFileCodeBoxB.setPrefHeight(150);
            comparisonFileCodeBoxB.setPrefColumnCount(2);
            comparisonFileCodeBoxB.setPrefWidth(80);
    
    //NOTE: resultPaneB may need its own handlers, meaning new textArea's
        resultPaneA.add(file1CodeBoxA, 0, 0);
        resultPaneA.add(comparisonFileCodeBoxA, 1, 0);
        
        //resultPaneB.add(file1CodeBoxB, 0, 0);
        //resultPaneB.add(comparisonFileCodeBoxB, 1, 0);
        
        resultPaneC.add(PercentageVBox, 0, 0);
        resultPaneC.add(ButtonHBox, 1,0);
        
    //Everything will be loaded into the VBox, even the footer
        ResultScreen = new VBox(30, rTitleBox, resultPaneA, 
            /*rCenterTitle, resultPaneB,*/ resultPaneC);
    
     
    //adding elements to the Gridpane
        gridPane2.add(LeftColVBox, 0,0);
        gridPane2.add(RightColVBox, 1,0);
        
    
    //RESULT SCREEN HANDLERS
    //returns user to the Comparisons Screen
    retToHome.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                primaryStage.setScene(CompScene);
                primaryStage.show();
            }
        });
        
    //DataPageButton Handler 
        //switches to the database page with a button
        DataPageButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {
                    primaryStage.setScene(DataScene);
                    primaryStage.show();
                }
            });
    //ComparisonPageButton Handler
        //switches the screen to the Comparisons Page
        ComparisonPageButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {   
                    primaryStage.setScene(CompScene);
                    primaryStage.show();
                }
            }); 
        
        //SCENES: Scene Handling and sizing
        CompScene = new Scene(CompTitleVBox, 600, 650);
        DataScene = new Scene(DataTitleVBox, 600, 650);
        ResultScene = new Scene(ResultScreen, 600, 650);
        
        primaryStage.setTitle("CPC Application");
        primaryStage.setScene(CompScene);
        primaryStage.show();
    }
    
    //HELPER METHOD FOR TEXT AREA 
    //This will take the contents of the TextArea, save it to the pastedTempLoc filepath
    //and return the file path
        public File TextAreaGet(TextArea textarea, String name) {
            String username = System.getProperty("user.name");
            name += ".txt";
            ObservableList<CharSequence> paragraph = textarea.getParagraphs();
            Iterator<CharSequence>  iter = paragraph.iterator();
            File filepath = new File("C:\\Users\\" + username +"\\Documents\\cpcapplication\\library\\" + name);
            try
            {
                BufferedWriter bf = 
                        new BufferedWriter(new FileWriter(filepath));
                while(iter.hasNext())
                {
                    CharSequence seq = iter.next();
                    bf.append(seq);
                    bf.newLine();
                }
                bf.flush();
                bf.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return filepath;
        }
        
        public void setPercentage(int percentage) {
            PercentageMatch = percentage;
        }
        
        public String getPercentage() {
            PercentageMatchStr = String.valueOf(PercentageMatch);
            return PercentageMatchStr;
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
