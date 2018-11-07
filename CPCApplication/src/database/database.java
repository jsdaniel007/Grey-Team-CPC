/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
/**
 *
 * @author kyle addy
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;


public class  database {
      public static void main(String[] args){
      DBCheck();
    }
   
    public static void DBCheck() {
        
        File file=new File("C:\\Users\\Public\\database");
        boolean exists = file.isDirectory();
        if (!exists){
            System.out.println("The file does not exist");
                  
            if (file.mkdir()) {
                System.out.println("Database is created!");
            } else {
                System.out.println("Failed to create Database!");
            }
        }
        else{
        System.out.println("The file does exist");
        }
    }
    
    
    public static void addToDatabase(Path file){//****WIP****//
            //Path dest = C:\\Users\\Public\\database;
    //Files.copy(file, "C:\\Users\\Public\\database",REPLACE_EXISTING);
     //copy(file, "C:\\Users\\Public\\database") 
    }
}