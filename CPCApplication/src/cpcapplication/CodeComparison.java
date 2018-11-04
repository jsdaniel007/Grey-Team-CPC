/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication;

/**
 *
 * @author jsdan
 * @author cbell
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CodeComparison {
    
    public static void Stage1(File f1, File f2) {
        try (BufferedReader lineParser1 = new BufferedReader(new 
            FileReader(f1));
            BufferedReader lineParser2 = new BufferedReader(new 
            FileReader(f2)) ){

            HashSet<String> file1 = new HashSet<String>(); // This set's us up later for our reading of line by line. Storying it as a hash makes it more efficient.
            int linesInCommon = 0; // All of these integers line us up for the percentages given later. 
            int linesTotalFile1 = 0;
            int linesTotalFile2 = 0;
            int percentageMatch = 0;
            boolean areEqual = false;

            String currentLine = null; // Creates the current line to constantly rewrite it in a sec.
            while((currentLine = lineParser1.readLine()) != null){ // As long as the file still has something in it...
                if(currentLine.length() > 0) { // If the line isn't blank...
                    file1.add(currentLine); // Add the current line from the one file into the array so that it can compare it later.
                }
                linesTotalFile1++; // Keeps track for percentages later. 
            }
            System.out.println("------------------ Lines of Duplicated Code " // DELETE THIS IF NOT WANTED
                    + "------------------"); // This is something that I had an idea for. This prints out ONLY the lines repeated if we wanted to display them in some way or highlight them. If you don't want to display this, delete ONLY the lines I indicate. 
            while( (currentLine = lineParser2.readLine()) != null ){ // As long as the file still has something in it...
                linesTotalFile2++; // Keeps track for percentages later.
                if(currentLine.length() > 0) { // As long as the line isn't empty...
                    if(file1.contains(currentLine)){ // If the current line in file 2 exists in the file 1 array from earlier...
                    linesInCommon++; // Keeps track for percentages later. 
                    System.out.println(currentLine); // DELETE THIS IF NOT WANTED - Prints the line that has been duplicated.
                }
            }
                }
            System.out.println("---------------- End Lines of Duplicated Code "
                    + "----------------"); // DELETE THIS IF NOT WANTED
            
            int lesserAmmount = 0; // For the percentages, I realized that the denominator needs to be whatever file has less lines because if 50/52 lines are copied from the file, it doesn't matter if the bigger file has 300 lines. The lesser file has still been completely plagarised. 
            String lesserFile = null;
            if (linesTotalFile1 < linesTotalFile2){
                lesserAmmount = linesTotalFile1; // Programs the Lesser to be related to F1
                lesserFile = "Item 1"; 
            } else {
                lesserAmmount = linesTotalFile2; // Programs the Lesser to be related to F2
                lesserFile = "Item 2"; 
            }
                        
            if (linesTotalFile1 != linesInCommon || linesTotalFile2 != 
                    linesInCommon){ // If either file has been completely ripped off
                areEqual = true;
            }
            
            System.out.println(" ");
            if(areEqual){
                System.out.println("The compared files are identical.");
            } else {
                percentageMatch = (linesInCommon/lesserAmmount)*100;
                System.out.println("The two files are not the same but have " + 
                        linesInCommon + " lines in common (not counting blank "
                        + "lines)."); // This line shows how many lines are in common.
                System.out.println("Item 1 has " + linesTotalFile1 + " total "
                        + "lines and Item 2 has " + linesTotalFile2 + " total "
                        + "lines."); // Shows how many lines are in each file.
                System.out.println("That means " + linesInCommon + " out of " + 
                        lesserAmmount + " lines have been coppied from " + 
                        lesserFile + " (not counting blank lines) giving a Match"
                        + " Percentage of " + percentageMatch + "." ); // This is the basis for how we find our percentage. We take the lesser amount from above and we put the amount of matched lines over it. 
            
                // Code that moves us on to Stage 2 for a more finite matching system. 
                // Will be working on this like we talked about as more time arrises. 
                // What we have so far is the Minimum Viable Product.  
            }
            lineParser1.close();
            lineParser2.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void Stage2(String line1, String line2) {
        
    }
    
}
