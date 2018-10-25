/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication_awt;
import java.io.*;
import java.util.*;
import java.util.Scanner;
/**
 *
 * @author jsdan
 * to handle the techniques behind the code comparison and 
   coming up with percentages relating to the compared files
 */
public class CodeComparison {
    /*the constructor will set up the key variables that will be needed 
    to calculate the percentages to be used by the compare() function */
    
    /*The comparison function will run the comparison function and 
    display the percentages, using the Stage1 and Stage2 function */
    public static void Compare() {
        /*TODO: Compare needs to be set up so that it can take two files, and
        "set the stage" for the use of Stage1 and Stage2 comparison functions.
        This will NOT do any actual comparisons, but instead will just set up Stage1
        and Stage2 to do its comparison jobs
        */ 
    }
    
    public static void Stage1(String line1, String line2) {
        /*TODO: Stage1 will do the following type of comparisons:
        Line-by-Line: compares the lines of both files using Buffered Reader in order
        to come up with how many lines match the other in each file. 
        (Consider that the lines you are comparing may encounter formatting errors
        or may have cases where the lines are off by a single newline or something
        similar)
        
        */
    }
    
    public static void Stage2(String line1, String line2) {
        /*TODO: Stage2 will do the following type of comparisons:
        Function Pattern Matching: Once the keyword to one of the possible fundamental
        loops are found, find the next NESTED keyword, and then take these contained 
        loops/keywords for one file, and compare them against the loops/keywords for
        the other files, then store how many of those keyword structures match.
        (Try arrays or another Data Structure to hold the comparisons, and the 
        order matters here)
        */
    }
    
}
