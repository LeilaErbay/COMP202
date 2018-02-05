import java.io.*;
import java.util.*;

public class Comp202Photoshop {
  public static void main (String[] args) {
    try{
      //if not enough inputs throw an exception
      if (args.length <4) {
        throw new IllegalArgumentException ("Not enough inputs. Please enter the correct number of inputs.");
      }
      
      //list of command-line inputs
      String inputFile = args[0];
      String outputFile = args[1];
      String formatOut = args[2];
      String operation = args[3];
      
      //update image: readIn is now set to the read values of the input file
      Image readIn = ImageFileUtilities.read(inputFile);
      
      
      //if third arguments are not pgm and are not pnm then print an error message
      if (!(formatOut.equals("pgm")) && !(formatOut.equals("pnm"))) {
        System.err.println("invalid format for output image. Please type in either pgm or pnm for your third input.");
      }
      
      // if an operation is not -fh, -fv, -gs,-cr, or -id then 
      if (!(operation.equals("-fh")) && !(operation.equals("-fv")) && !(operation.equals("-gs"))  && !(operation.equals("-cr"))){
        System.err.println("invalid operation. Please type in -fh, -fv, -gs, or -fv for your fourth input.");
      }
      //if third argument is pgm, then enter the following if statement and check inner if statements to apply one of the following changes 
      //-- will change original image according to one of the following conditions and then turn it to pgm
      if (formatOut.equals("pgm")){
        
        //if user want to flip horizontally
        if(operation.equals("-fh")){
          readIn.flip(true);
          ImageFileUtilities.writePgm(readIn, outputFile);
          System.out.println("Your original image " + inputFile + " has been flipped horizontally, converted to greyscale, and can be found in the file, " + outputFile);
        }
        
        //if user wants to flip vertically
        if (operation.equals("-fv")) {
          readIn.flip(false);
          ImageFileUtilities.writePgm(readIn,outputFile);
          System.out.println("Your original image " + inputFile + " has been flipped vertically, converted to greyscale, and can be found in the file, " + outputFile);
        }
        
        //if user wants to greyscale
        if(operation.equals("-gs")) {
          ImageFileUtilities.writePgm(readIn, outputFile);  
          System.out.println("Your original image " + inputFile + " has been converted to greyscale and can be found in the file, " + outputFile);
        }
        
        //if user wants to crop the image
        if(operation.equals("-cr")) {
          //the necessary inputs for the crop to occur - 4 extra command line arguments
          int startX = Integer.parseInt(args[4]);
          int startY = Integer.parseInt(args[5]);
          int endX = Integer.parseInt(args[6]);
          int endY = Integer.parseInt(args[7]);
          
          //if the end coordinate of the crop are less than the initial coordinate, then it would induce a negative crop
          if (startX > endX || startY > endY) {
            throw new NegativeArraySizeException();
          }
          //if not enough command line inputs for crop, throw exception
          if (args.length < 8) {
            throw new ArrayIndexOutOfBoundsException();
          }

          //else crop will continue
          readIn.crop(startX, startY, endX, endY);
          ImageFileUtilities.writePgm(readIn, outputFile);
          System.out.println("Your original image " + inputFile + " has been cropped, converted to greyscale, and can be found in the file, " + outputFile);
        } 
      }
      
      //if third argument is pnm, then enter the following if statement and check inner if statements to apply one of the following changes 
      //-- will change original image according to one of the following conditions and then turn it to pnm file
      if (formatOut.equals("pnm")){
        
        //if 4th command-line argument is -fh: flip image horizontally and convert to colored image
        if(operation.equals("-fh")) {
          readIn.flip(true);
          ImageFileUtilities.writePnm(readIn, outputFile);
          System.out.println("Your original image " + inputFile + " has been flipped horizontally, converted to color, and can be found in the file, " + outputFile);
        } 
        
        //if 4th command-line argument is -fv: flip image vertically and convert to colored image
        if (operation.equals("-fv")) {
          readIn.flip(false);
          ImageFileUtilities.writePnm(readIn,outputFile);
          System.out.println("Your original image " + inputFile + " has been flipped vertically, converted to color, and can be found in the file, " + outputFile);
        }
        
        //if 4th command-line argument is -gs: convert image to a greyscale image
        if(operation.equals("-gs")) {
          readIn.toGrey();
          ImageFileUtilities.writePnm(readIn, outputFile);  
          System.out.println("Your original image " + inputFile + " has been converted to greyscale and can be found in the file, " + outputFile);
        }
        
        //if user wants to crop the image
        if(operation.equals("-cr")) {
          //the necessary inputs for the crop to occur - 4 extra command line arguments
          int startX = Integer.parseInt(args[4]);
          int startY = Integer.parseInt(args[5]);
          int endX = Integer.parseInt(args[6]);
          int endY = Integer.parseInt(args[7]);
          
          //if the end coordinate of the crop are less than the initial coordinate, then it would induce a negative crop
          if (startX > endX || startY > endY) {
            throw new NegativeArraySizeException();
          }
          //if not enough command line inputs for crop, throw exception
          if (args.length < 8) {
            throw new ArrayIndexOutOfBoundsException ();
          }
          
          //else crop will continue
          readIn.crop(startX, startY, endX, endY);
          ImageFileUtilities.writePnm(readIn, outputFile);
          System.out.println("Your original image " + inputFile + " has been cropped, converted to color, and can be found in the file, " + outputFile);
        }
      }
    } 
    //if user does not enter enough inputs for crop method
    catch (ArrayIndexOutOfBoundsException arrayE) {
      System.err.println("Not enough inputs for cropping. Please enter the correct number of inputs.");
    }
    //catch IOException thrown inside try block -- not enough command line arguments for code to run
   catch (IllegalArgumentException commandLine){
     System.err.println(commandLine.getMessage());  
    }
    //catch exception thrown by cropping mistake - coordinates are not appropriate
    catch (NegativeArraySizeException negsize){
      System.err.println("inputs 7 and 8 must be greater than inputs 5 and 6");
    }
    //if file is not in directory, catch the exception and tell user
    catch (FileNotFoundException fnfe) {
      System.err.println("file is not in your directory. Please choose a file within the directory");
    }
    //for any other thrown exception that could occur, give the user a message
    catch (IOException general) {
      System.err.println(general.getMessage());
    }
  }
}