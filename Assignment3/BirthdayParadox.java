import java.util.Arrays;

public class BirthdayParadox {
  
  /*Method 1: takes in two integer values and returns an int array filled 
   * with random values (values between 0 and range-1)*/
  public static int [] generateArray (int size, int range) {
    int [] randomArray = new int[size];
    for (int i = 0; i < size; i++) {
      randomArray[i] = (int)(Math.random() *(range-1));
    }
    return randomArray;
  }
  
  /*Method 2: takes in three int values (iterations, size, range) and fills a 2-D array using generateArray (Method 1
   * Number of rows of 2-D array = value of "iterations"; number of columns of 2-D array = value of "size" */
  public static int [][] generateAllData(int iterations, int size, int range) {
    int [][] methodArray = new int[iterations][size];
    for (int i = 0; i < iterations; i++){
      for (int j = 0; j < size; j++){
        methodArray[i] = generateArray(size, range);
      }
    }
    return methodArray;
  }
  
  //Method 3: takes in a 2-D array and an element returns number of times a certain element appears
  //
  public static  int countElement(int [][] countElementArray, int element){
    /*Assuming no values will be less than 0, 
     * however if element is -1 then frequency of 2-D will not be checked*/
    if(element == -1) {
      return 0;
    }
    /* loops through 2-D array an compares value at every index to value of the input, "element"
     * if the value at an index is equal to the value of the input, "element", the frequency ("counter") will increase*/
    else{ 
      int counter = 0;
      for (int i = 0; i < countElementArray.length; i++) {
        for (int j = 0; j < countElementArray[i].length; j++) {
          if (countElementArray[i][j] == element){
            counter++;
          }
        }
      }
    return counter;
    }
  }
  
  
  //Method 4: Takes as input a 2-D array and returns the mode
  public static int maxDay(int [][] inputArray){
    /*set mode to -1 because 0 could be a value and thus countElement would check frequency of 0 
    * and once it finds a mode greater than 0 it will stop checking 
    * though there could be another index with a greater frequency */
    int mode = -1; 
    
    //mode is the value in the 2-D array with the greatest frequency; must use countElement to find frequency
    for(int i = 0; i < 365; i++){
      if(countElement(inputArray, i) > countElement(inputArray, mode)){
        mode = i;
      }
    }
    return mode;
  }
  
  
  //Method 5: takes as input int array and returns boolean -- whether there are any duplicates in the array
  public static boolean hasDuplicates (int [] inputArray) {
    boolean hasDuplicate = false;
    //create a 2-D because methods that will be called take in 2-D arrays
    int [][] doubleArray = new int [2][inputArray.length]; 
    
    /*Set first row of 2-D array to values at index i, set values of second row to -1 so that 
    * they will not be considered in countElement and maxDay methods (reason stated in Method 4)*/
    for(int i = 0; i < inputArray.length; i++){
      doubleArray[0][i] = inputArray[i];
      doubleArray[1][i] = -1;
    }
    
    //if frequency of the mode of the double array is greater than 1, then there is a duplicate; else there is no duplicate
    if(countElement(doubleArray, maxDay(doubleArray)) > 1){
      hasDuplicate = true;
    }
    else{
      hasDuplicate = false;
    }
    return hasDuplicate;
  }
  
  //Method 6: takes in integer corresponding to the size of each of sub-arrays
  public static double runExperiment(int sizeOfSubArrays) {
    if (sizeOfSubArrays < 1) {
      throw new  IllegalArgumentException ("your input size is too small");
    }
    else {
      //use generateAll data with iterations = 200, size = sizeOfSubArrays, range = 365
      int [][] generatedArray =  generateAllData(200, sizeOfSubArrays, 365);
      double portionOfDuplicates = 0;
      
      //use hasDuplicates to see if there are any duplicate values in each row of 2-D array
      for (int i = 0; i < generatedArray.length; i++) {
        if (hasDuplicates(generatedArray[i]) == true) {
          portionOfDuplicates ++;
        }  
      }
      return (portionOfDuplicates/200); 
    }
  }
  
  public static void main(String [] args) {
    /*size values start at 1 and go up to and includes 100
     * runExperiment will be called 100 times with inputSize as input into runExperiment method */
    for (int inputSize = 1; inputSize < 101; inputSize++) {
      System.out.println(inputSize + " " + runExperiment(inputSize));
      if(inputSize == 100) {
        System.out.println();
        System.out.println("The end :D");
        System.out.println("Have a good day");
      }
    }
  }
}










