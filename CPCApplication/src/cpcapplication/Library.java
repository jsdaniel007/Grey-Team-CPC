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
import java.nio.file.Path;
import java.nio.file.Paths;



public class  Library {
      public static void main(String[] args) throws IOException{
          
          String username = System.getProperty("user.name");
         // String please = ("C:\\Users\\"+ username +"\\Desktop\\test.txt");
          // Path test = new Paths.get("C:\\Users\\"+ username +"\\Desktop\\test.txt");
          Path please = Paths.get("C:\\Users\\"+ username +"\\Desktop\\test.txt");

          File src = please.toFile();
          boolean exist = src.exists();
          System.out.println(exist);
          //File dest=new File("C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");
          
            //File dest = new File("Library Path: C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");
         //addAFile(test);
         //fileCopy(test,dest);
         //copydir(src,dest);
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
        
        String username = System.getProperty("user.name");
          
            File dest = new File("Library Path: C:\\Users\\" + username +"\\Documents\\cpcapplication\\library");

//            if(!srcdir.exists()){
//                System.out.println("Directory not found");
//            }
//            else{
//                Library fileDemo = new Library();
//                fileDemo.copydir(srcdir, destdir);
//                System.out.println("Coppied success");
//            }
             InputStream in = null;
            OutputStream out = null; 
            
            try
            {
                in = new FileInputStream(src);
                out = new FileOutputStream(dest);
                
                byte[] buffer = new byte[1024];
                
                int length; 
                while ((length = in.read(buffer)) > 0 )
                {
                    out.write(buffer,0, length);
                }
            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
                if(out != null)
                {
                    out.close();
                }
            }
            System.out.println("File copied from " + src + "to " + dest);           
        }
    
     public static void copydir(File src, File dest) throws IOException
        {
//            if(src.isDirectory())
//            {
//                if(!dest.exists())
//                {
//                    dest.mkdir();
//                    System.out.print("Directory copied from " + src + "to " + dest);
//                }
//                String files[] = src.list();
//                
//                for (String fileName : files)
//                {
//                    File srcFile = new File(src, fileName);
//                    File destFile = new File(dest, fileName);
//                    copydir(srcFile, destFile);
//                   
//                }
//            }
//            else{
                fileCopy(src, dest);
            //}
          }
     
        private static void fileCopy(File src, File dest)
                throws FileNotFoundException, IOException
                
        {
            InputStream in = null;
            OutputStream out = null; 
            
            try
            {
                in = new FileInputStream(src);
                out = new FileOutputStream(dest);
                
                byte[] buffer = new byte[1024];
                
                int length; 
                while ((length = in.read(buffer)) > 0 )
                {
                    out.write(buffer,0, length);
                }
            }
            finally
            {
                if (in != null)
                {
                    in.close();
                }
                if(out != null)
                {
                    out.close();
                }
            }
            System.out.println("File copied from " + src + "to " + dest);
        }
}
    
    
    


