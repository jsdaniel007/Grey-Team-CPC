/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.io.*; 
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import javafx.collections.ObservableList;

/*
Ways to clean up the gui code, convert each screen into classes, and turning different
features and actions into functions that can be called from the start function,
seeing your start as a "main" of sorts, calling the functions as needed.
For example, a handler is a great example of compressing code down,
    SCREEN 1
    -setupTitle()- sets up the "Title" section of Screen 1 "Comparisons"
    -
    
*/

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
    TextArea pastedCodeField, pastedCodeField2;
    Scene CompScene, DataScene, ResultScene;
    VBox ResultScreen;
    ColumnConstraints leftColumn;
    ColumnConstraints rightColumn;
    String file1name;
    String file2name;
    String ResultFileText1;
    String ResultFileText2;
    int PercentageMatch;
    String PercentageMatchStr;
    Label PercentageLabel;
    //Label PercentageLabel2;
    TextArea file1CodeBoxA;
    TextArea comparisonFileCodeBoxA;
    TextArea file1CodeBoxB;
    GridPane resultPaneC;
    HBox ButtonHBox;
    
    
    //start of the Program from a GUI perspective
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
    //Allow access to your other classes
        CodeComparison CC = new CodeComparison();
        Library LIB = new Library();
        
    /*
        SCREEN 1 GUI CODE Pane Setup
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
        
/*
    SCREEN 1 CODE ===============================================
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
        //Problem: LogoView will not show
        Label botLeftPanLabel = new Label("Plagiarism Checker");
        Image Logo = new Image("file:C:\\CSCI_495\\Grey-Team-CPC\\CPCApplication\\src\\cpcapplication\\Pictures\\Logo.PNG");
            ImageView LogoView = new ImageView(Logo);
            LogoView.setImage(Logo);
            LogoView.setFitWidth(250);
            LogoView.setFitHeight(250);
            LogoView.setPreserveRatio(true);
            LogoView.setSmooth(true);
            LogoView.setCache(true);
        HBox botLeftPanHBox = new HBox(30, botLeftPanLabel);
        VBox botLeftPanVBox = new VBox(20, xDivider3, botLeftPanHBox, LogoView);
        
        
    //BOTTOM RIGHT PANEL OF THE PROGRAM
        Label botRightPanLabel = new Label("Paste Code Below for File 1 Comparison");
        TextField PastedFileName = new TextField();
        Button compareButton2 = new Button("Compare File 1 to Pasted Code");
        Label PastedCodeError = new Label("Name Your File:");
            PastedCodeError.setTextFill(Color.web("#ff0000"));
            PastedCodeError.setVisible(false);
        HBox botRightPanHBox = new HBox(30, botRightPanLabel);
        VBox botRightPanVBox = new VBox(20, xDivider4, botRightPanHBox, 
                pastedCodeField, saveBox3, compareButton2, PastedCodeError);
        
    //SCENE 1 HANDLERS
        //TOP LEFT PANEL HANDLERS
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
        browseButton2.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file2Selection = file2Chooser.showOpenDialog(primaryStage);
                file2name = file2Selection.getName();
                if (file2name != null) {
                    browseButton2.setText(file2name);
                }
            }
        });
        compareButton1.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override 
           public void handle(ActionEvent e) {
               /*
               Have the program check the pastedFileSelection to see if it has a
               file, if file1Selection is not chosen, and if so, do not proceed
               */
               setPercentage(CodeComparison.Stage1(file1Selection, file2Selection)); 
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
        
        //BOTTOM RIGHT PANEL HANDLERS
        compareButton2.setOnAction(new EventHandler<ActionEvent>( ) {
           @Override public void handle(ActionEvent e) {
               //converts the content of the TextArea, then runs that into the comparison
               
               /*This should handle the file selection exception handling, such as
               if the file 1 is not selected, the file 2 IS selected, or if the 
               textArea is empty with nothing to save, DO NOT MOVE ON
               */
               if (pastedCodeField.getText().isEmpty() || PastedFileName.getText().isEmpty() ||
                       file2Selection.exists()) {
                   PastedCodeError.setVisible(true);
               } else {
                
                   //can't tell if this will work, but it likely won't since the 
                   //path isn't given, just the text
                File pastedCodeSelection = TextAreaGet(pastedCodeField, "fakename");
                setPercentage(CC.Stage1(file1Selection, pastedCodeSelection));
                
                PercentageLabel.setText("Percentage Report: " + getPercentage());
                
                //Run a helper function to get the contents of the file from the path
                    try { ResultFileText1 = new String(Files.readAllBytes(Paths.get(file1Selection.toString())));
                    } catch (IOException ex) { Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try { ResultFileText2 = new String(Files.readAllBytes(Paths.get(pastedCodeSelection.toString())));
                    } catch (IOException ex) {Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                    }
               //Set the File Contents to the code boxes
               //replace the reused items as new items 
               file1CodeBoxA.setText(ResultFileText1);
               comparisonFileCodeBoxA.setText(ResultFileText2);
               
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
                       //pass the pasted contents to the addAFile
                       LIB.addAFile(pastedCodeSelection);
                   } catch (IOException ex) {
                       Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                   }
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
        //Button File1DatabaseButton = new Button("Compare To The Database");
        Button AddToDatabase = new Button("Add To The Database");
        Button RemToDatabase = new Button("Remove From The Database");
        VBox LeftColVBox = new VBox(30, LeftColLabel, browseButton3, LeftColLabel2,
                /*File1DatabaseButton,*/ AddToDatabase, RemToDatabase);

        /*
        File1DatabaseButton.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                //Use this to compare the passed in file to the database
                //CTL.CompareToLib(file3name);
            }
        });
        */
        
    //RIGHT COLUMN ELEMENTS
        Label RightColLabel = new Label("Paste Text Below for Database Comparison");
        Label RightColLabel2 = new Label("Name Your File Before Adding to The Database:");
        Label PastedBoxError = new Label("Please Name Your Text Before Adding!");
            PastedBoxError.setTextFill(Color.web("#ff0000"));
            PastedBoxError.setVisible(false);
        //Button PastedCompareButton = new Button("Compare Paste Contents to Database");
        Button AddPastedButton = new Button("Add Paste Contents to Database");
        TextField NamePasted = new TextField();
        VBox RightColVBox = new VBox(20, RightColLabel, pastedCodeField2, RightColLabel2,  
                /*PastedCompareButton,*/ NamePasted, AddPastedButton, PastedBoxError);
        
        //SCENE 2 HANDLERS
        browseButton3.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                file3Selection = file3Chooser.showOpenDialog(primaryStage);
                String file3name = file3Selection.getName();
                if (file3name != null) {
                    browseButton3.setText(file3name);
                }
            }
        });
    
        AddToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                try {
                    System.out.println("file3Selection: \n" + file3Selection);
                    
                    LIB.addAFile(file3Selection);
                } catch (IOException ex) {
                    Logger.getLogger(CPCApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        RemToDatabase.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                LIB.remAFile(file3Selection);
            }
        });
            
        //AddPastedButton Handler for TextArea File Conversion
        AddPastedButton.setOnAction(new EventHandler<ActionEvent>( ) {
                @Override public void handle(ActionEvent e) {
                    File fileName = new File(NamePasted.getText());
                    File pastedCodeSelection2 = TextAreaGet(pastedCodeField2, NamePasted.getText());
                    
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
        
        resultPaneC.add(PercentageHBox, 0, 0);
        resultPaneC.add(ButtonHBox, 1,0);
        
    //Everything will be loaded into the VBox, even the footer
        ResultScreen = new VBox(30, rTitleBox, resultPaneA, 
            /*rCenterTitle, resultPaneB,*/ resultPaneC);
    
     
    //adding elements to the Gridpane
        gridPane2.add(LeftColVBox, 0,0);
        gridPane2.add(RightColVBox, 1,0);
        
    
    //RESULT SCREEN HANDLERS
    retToHome.setOnAction(new EventHandler<ActionEvent>( ) {
            @Override public void handle(ActionEvent e) {
                primaryStage.setScene(CompScene);
                primaryStage.show();
            }
        });
        
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
        
        //SCENES: Scene Handling
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
        
//        public void setPercentageHBox() {
//            HBox PercentageHBox = new HBox(250, PercentageLabel);
//            PercentageHBox.setAlignment(Pos.CENTER);
//            resultPaneC = new GridPane();
//            resultPaneC.add(PercentageHBox, 0, 0);
//            resultPaneC.add(ButtonHBox, 1,0);
//            resultPaneC.setVgap(20);
//            resultPaneC.setHgap(20);
//            resultPaneC.setPadding(new Insets(10, 10, 10, 10));
//            resultPaneC.getColumnConstraints().addAll(leftColumn, rightColumn);
//            
//        }
        
        public void setPercentage(int percentage) {
            PercentageMatch = percentage;
        }
        
        public String getPercentage() {
            PercentageMatchStr = String.valueOf(PercentageMatch);
            return PercentageMatchStr;
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
