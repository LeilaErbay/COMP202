//import classes java.util and java.io to access scanner, exceptions, and writing files
import java.util.*;
import java.io.*;

public class ImageFileUtilities {
  public static Image read(String filename) throws IOException {
    //create a file using the string filename and create a scanner that reads in the file 
    Scanner sc = new Scanner(new File(filename));
    
      //read in format-- either P2 or P3
      String format = sc.nextLine();
      
      //check format -- if format is not equal to P2, p2, P3, or p3 then throw an error
      if(!format.equalsIgnoreCase("P2") && !format.equalsIgnoreCase("P3")) {
        System.err.println("format is wrong");
      }
      
      //read in comments - create a string for comments. As long has the next line has a #
      //it is a comment line and that line can be added to the string comments 
      String comment = sc.nextLine();
      while(sc.hasNext("#")){
        comment += sc.nextLine();
      }
      
      //read in attributes - width first, then height, then maxRange
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxRange = sc.nextInt();
      
      //create 2D pixel array using the height and width that was just read in
      Pixel [][] pixelArray = new Pixel [height][width];
      
      //read in numbers and store numbers a pixel 2D array of values with the same intensity (will be grey)
      //or store into a pixel 2D array of values RGB -- depends on format P2 vs. P3
      //reading in a P2 image
      if(format.equalsIgnoreCase("P2")){
        while(sc.hasNextInt()){
          for (int i = 0; i <height; i++){
            for(int j =0; j <width; j++){
              
              //each int read in with scanner is a stored as variable grey
              int grey = sc.nextInt();
              //after creating grey, create a pixel "greyScale" that has a single intensity, thus the image will be grey sclae
              Pixel greyScale = new Pixel(grey);
              //At every iteration, you store each Pixel into an index
              pixelArray[i][j] = greyScale;
            }
          }     
        }
      }
      //reading in a P3 image
      else if(format.equalsIgnoreCase("P3")){
        for(int i = 0; i < height; i++){
          for(int j = 0; j < width; j++){
            //first int from reading = red; second int from reading = green; third int from reading = blue;
            int red = sc.nextInt();
            int green = sc.nextInt();
            int blue = sc.nextInt();
            
            //at each iteration, create a pixel with int values with red, green, blue 
            Pixel pixelRGB = new Pixel(red, green, blue);
            //fill each index with created colored pixel
            pixelArray[i][j] = pixelRGB;
          }
        }
      }
      //close scanner
      sc.close();
      
      //method populates an image: uses comment, maxRange, and either pixelArray created to populate an image
      Image newImage = new Image(comment, maxRange, pixelArray);
      return newImage;
    }
  
  
//method to write  PNM files
  public static void writePnm(Image imageInput, String filename) throws IOException{
    //FileWriter and buffered writer are created
    FileWriter fw = new FileWriter (filename);
    BufferedWriter bw = new BufferedWriter(fw);
   
    
    //write format code
    bw.write("P3");
    bw.newLine();
    
    //extract metaData from imageInput
    String metaData = imageInput.getMetadata();
    //write metadata to file
    bw.write(metaData);
    bw.newLine();
    
    //extract width and height from image
    int width = imageInput.getWidth();
    int height = imageInput.getHeight();
    
    //write width and height to file
    bw.write(width + " " + height);
    bw.newLine();
    
    //extract max range from imageInput
    int maxRange = imageInput.getMaxRange();
    //write maxRange onto file
    bw.write(""+ maxRange);
    bw.newLine();
    
    //loop through the array of pixels
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        //make a pixel at each index of the array
        Pixel color = imageInput.getPixel(i,j);
        
        //at every index create red, green, blue values
        int red =  color.getRed();
        int green =  color.getGreen();
        int blue =  color.getBlue();
        
        //write red, green, blue values to file
        bw.write(red + " " + green + " " + blue);
        bw.newLine();
      }
    }
    
    //close buffered writer and file writer
    bw.close();
    fw.close();
  }
  
  //method to write Pgm files
  public static void writePgm(Image imageInput, String filename) throws IOException{
    //create a file writer and buffered
    FileWriter fw = new FileWriter (filename);
    BufferedWriter bw = new BufferedWriter(fw);
    
    //write P2 to file
    bw.write("P2");
    bw.newLine();
    
    //extract comments from image
    String comment = imageInput.getMetadata();
    //write comment to file
    bw.write(comment);
    bw.newLine();
    
    //extract width and height
    int width = imageInput.getWidth();
    int height = imageInput.getHeight();
    
    //write width and height to file
    bw.write(width + " " + height);
    bw.newLine();
    
    //extract maxRange from imageInput
    int maxRange = imageInput.getMaxRange();
    //write max range to file
    bw.write("" + maxRange);
    bw.newLine();
    
    
    //loop through the array of pixels
    for (int i = 0; i < imageInput.getHeight(); i++) {
      for (int j = 0; j < imageInput.getWidth(); j++) {
        
        //get pixel at each index
        Pixel newPix = imageInput.getPixel(i,j);
        //convert each pixel into a grey
        int grey = newPix.grey();
        
        //write grey ints to file
        bw.write(""+grey);
        bw.newLine();
      }
    }
   //close bw
    bw.close();
    fw.close();
    
  }
  
}




