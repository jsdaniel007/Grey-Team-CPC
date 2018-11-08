/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcapplication;
/**
 *
 * @author kyle addy
 * 
 */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.nio.file.Paths;




public class  Library {
      public static void main(String[] args){
         File test = new File("C:\\Users\\kyle addy\\Desktop\\test.txt");
         
         addAFile(test);
    }
   
    Library() {//contructor
        
        String username = System.getProperty("user.name");
        
        File file=new File("C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");
        boolean exists = file.isDirectory();
        if (!exists){
            System.out.println("The Library does not exist");
                  
            if (file.mkdir()) {
                System.out.println("Library was created!");
                 System.out.println("Library Path: C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");
            } else {
                System.out.println("Failed to create the Library!");
            }
        }
        else{
        System.out.println("The Library exist");
        }
    }
    
    
    public static void addAFile(File file){//****WIP****//
        String username = System.getProperty("user.name");
        
       Path src = file.toPath();
       Path dest = Paths.get("C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");
       
       try{
       Files.copy(src, dest, REPLACE_EXISTING);
       } catch (IOException e)
       {
           System.out.println("Failed to Copy file");
       }
    }
}