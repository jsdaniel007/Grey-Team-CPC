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
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CodeComparison {
    
    public static void Compare() {
        try (BufferedReader lineParser1 = new BufferedReader(new 
            FileReader("/Users/chrisbell/Desktop/duplicate.cpp"));
            BufferedReader lineParser2 = new BufferedReader(new 
            FileReader("/Users/chrisbell/Desktop/somewhatDuplicated.cpp")) ){

            HashSet<String> file1 = new HashSet<String>();
            int linesInCommon = 0;
            int linesTotalFile1 = 0;
            int linesTotalFile2 = 0;
            boolean areEqual = true;

            String currentLine = null;
            while((currentLine = lineParser1.readLine()) != null){
                if(currentLine.length() > 0) {
                    file1.add(currentLine);
                }
                linesTotalFile1++;
            }
            System.out.println("------------------ Lines of Duplicated Code "
                    + "------------------");
            while( (currentLine = lineParser2.readLine()) != null ){
                linesTotalFile2++;
                if(currentLine.length() > 0) {
                    if(file1.contains(currentLine)){
                    linesInCommon++;
                    System.out.println(currentLine);
                }
            }
                }
            System.out.println("---------------- End Lines of Duplicated Code "
                    + "----------------");
            
            int lesserAmmount = 0;
            String lesserFile = null;
            if (linesTotalFile1 < linesTotalFile2){
                lesserAmmount = linesTotalFile1;
                lesserFile = "Item 1"; 
            } else {
                lesserAmmount = linesTotalFile2;
                lesserFile = "Item 2"; 
            }
                        
            if (linesTotalFile1 != linesInCommon || linesTotalFile2 != 
                    linesInCommon){
                areEqual = false;
            }
            
            System.out.println(" ");
            if(areEqual){
                System.out.println("The compared files are identical.");
            } else {
                System.out.println("The two files are not the same but have " + 
                        linesInCommon + " lines in common (not counting blank "
                        + "lines).");
                System.out.println("Item 1 has " + linesTotalFile1 + " total "
                        + "lines and Item 2 has " + linesTotalFile2 + " total "
                        + "lines.");
                System.out.println("That means " + linesInCommon + " out of " + 
                        lesserAmmount + " lines have been coppied from " + 
                        lesserFile + " (not counting blank lines).");
            }
            lineParser1.close();
            lineParser2.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public static void Stage1(String line1, String line2) {
        
    }
    
    public static void Stage2(String line1, String line2) {
        
    }
    
}
