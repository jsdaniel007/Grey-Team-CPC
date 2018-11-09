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
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;


public class  Library {
      public static void main(String[] args) throws IOException{
          
          String username = System.getProperty("user.name");
          
           File test = new File("C:\\Users\\"+ username +"\\Desktop\\boop.txt");  
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
    
    
    private static void addAFile(File src) throws FileNotFoundException, IOException {
                 
        if (src.exists()){
            String username = System.getProperty("user.name");
            File dest=new File("C:\\Users\\" + username +"\\Documents\\cpcapplication\\library\\" + src.getName());

            dest.createNewFile();

            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

             Files.copy(src.toPath(), dest.toPath(), options);
             System.out.println("File: " +dest.getName() + " has been copied to the Library");
        } else
        {
            System.out.println("File does not exist or path is bad");
        }
        
    }
}
    
     