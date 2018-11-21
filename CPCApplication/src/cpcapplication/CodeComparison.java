/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication;

/**
 *
 * @author jsdan- High level Design
 * @author cbell- High Level Design + Code Implementation
 */
  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CodeComparison {
    
    public static int Stage1(File f1, File f2) {
        try (BufferedReader parser1 = new BufferedReader(new FileReader(f1));
            BufferedReader parser2 = new BufferedReader(new FileReader(f2)) ){

            HashSet<String> file1 = new HashSet<String>(); // This set's us up later for our reading of line by line. Storying it as a hash makes it more efficient.
            int linesInCommon = 0; // All of these integers line us up for the percentages given later. 
            int linesTotalFile1 = 0;
            int linesTotalFile2 = 0;
            int percentageMatch = 0;
            boolean areEqual = false;

            String currentLine = null; // Creates the current line to constantly rewrite it in a sec.
            while((currentLine = parser1.readLine()) != null){ // As long as the file still has something in it...
                if(currentLine.length() > 0) { // If the line isn't blank...
                    file1.add(currentLine); // Add the current line from the one file into the array so that it can compare it later.
                }
                linesTotalFile1++; // Keeps track for percentages later. 
            }

            while( (currentLine = parser2.readLine()) != null ){ // As long as the file still has something in it...
                linesTotalFile2++; // Keeps track for percentages later.
                if(currentLine.length() > 0) { // As long as the line isn't empty...
                    if(file1.contains(currentLine)){ // If the current line in file 2 exists in the file 1 array from earlier...
                    linesInCommon++; // Keeps track for percentages later. 
                    }
                }
            }
            
            int lesserAmmount = 0; // For the percentages, I realized that the denominator needs to be whatever file has less lines because if 50/52 lines are copied from the file, it doesn't matter if the bigger file has 300 lines. The lesser file has still been completely plagarised. 
            if (linesTotalFile1 < linesTotalFile2){
                lesserAmmount = linesTotalFile1; // Programs the Lesser to be related to F1
            } else {
                lesserAmmount = linesTotalFile2; // Programs the Lesser to be related to F2
            }
                        
            if (linesTotalFile1 != linesInCommon || linesTotalFile2 != 
                    linesInCommon){ // If either file has been completely ripped off
                areEqual = true;
            }
            percentageMatch = (linesInCommon/lesserAmmount) * 100;
            
            parser1.close(); //Tie up loose ends.
            parser2.close();
            
            System.out.println(" ");
            if(areEqual){
                // System.out.println("The compared files are identical.");
                percentageMatch = 100;
                return percentageMatch; //Return the percentageMatch
            } else {
//                System.out.println("The two files are not the same but have " + 
//                        linesInCommon + " lines in common (not counting blank "
//                        + "lines)."); // This line shows how many lines are in common.
//                System.out.println("Item 1 has " + linesTotalFile1 + " total "
//                        + "lines and Item 2 has " + linesTotalFile2 + " total "
//                        + "lines."); // Shows how many lines are in each file.
//                System.out.println("That means " + linesInCommon + " out of " + 
//                        lesserAmmount + " lines have been coppied from " + 
//                        lesserFile + " (not counting blank lines) giving a Match"
//                        + " Percentage of " + percentageMatch + "." ); // This is the basis for how we find our percentage. We take the lesser amount from above and we put the amount of matched lines over it. 

                if (percentageMatch > 80){
                    return percentageMatch;
                } else {
                    percentageMatch = Stage2(f1, f2, percentageMatch);
                    return percentageMatch;
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return 0;
    }
    
    public static int Stage2(File f1, File f2, int percentageMatch) {
        try (BufferedReader parser1 = new BufferedReader(new FileReader(f1));
            BufferedReader parser2 = new BufferedReader(new FileReader(f2)) ){
            
            // A funpit full of integers for percentages later.
            int F1forCount = 0;
            int F1ifCount = 0;
            int F1whileCount = 0;
            int F1size = 0;
            int F2forCount = 0;
            int F2ifCount = 0;
            int F2whileCount = 0;
            int F2size = 0;
            int addedPercentage = 0;
            
            String currentLine = null; // Creates the current line to constantly rewrite it in a sec.
            while((currentLine = parser1.readLine()) != null){ // As long as the file still has something in it...
                StringTokenizer st = new StringTokenizer(currentLine, " ");
                F1size += st.countTokens(); // Gets total number of tokens of the line.
                for (int i = 0; i < F1size; i++){
                    String word = st.nextToken(); // Gets the next word.
                    if (word.equals("for")){
                        F1forCount++; // Increase count by 1 // Keeps track for percentages later. 
                    } else if (word.equals("if")){
                        F1ifCount++;
                    } else if (word.equals("while")){
                        F1whileCount++;
                    }
                }
            }
            parser1.close(); //Tie up loose ends.
            
            while( (currentLine = parser2.readLine()) != null ){ // As long as the file still has something in it...
                StringTokenizer st = new StringTokenizer(currentLine, " ");
                F2size += st.countTokens(); 
                for (int i = 0; i < F2size; i++){
                    String word = st.nextToken();
                    if (word.equals("for")){
                        F2forCount++; 
                    } else if (word.equals("if")){
                        F2ifCount++;
                    } else if (word.equals("while")){
                        F2whileCount++;
                    }
                }
            }
            parser2.close();
            
            // A funpit full of integers for percentages.
            int F1forUsage = F1forCount/F1size;
            int F1ifUsage = F1ifCount/F1size;
            int F1whileUsage = F1whileCount/F1size;
            int F2forUsage = F2forCount/F2size;
            int F2ifUsage = F2ifCount/F2size;
            int F2whileUsage = F2whileCount/F2size;
            
            // If the percentages match from F1 to F2, add some match percentage.
            if (F1forUsage == F2forUsage){
                addedPercentage += 5;
            }
            if (F1ifUsage == F2ifUsage){
                addedPercentage += 5;
            }
            if (F1whileUsage == F2whileUsage){
                addedPercentage += 5;
            }
            
            // However much addedPercentage needs to be added in.
            percentageMatch += addedPercentage;
            
            // Go home.
            return percentageMatch; 
        }
        catch(IOException e){
            System.out.println(e);
        }
        
        return 0;
    }
    
}
