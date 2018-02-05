import java.util.Scanner;

public class WeatherUtilities{
  /*method1: takes in array of doubles (temperatures in C) and array of booleans (status if day is sunny or not)
  * counts how many days meet the  "isGoodWeather" method criteria and returns amount of good days*/
  public static int countGoodDays (double [] tempInCelsius, boolean [] sunnyStatus){
    //must have same number of inputs to array of temperature values as array of day statuses or else an exception is thrown
    if (tempInCelsius.length != sunnyStatus.length){
      throw new IllegalArgumentException("the lengths of your inputs are not the same");
    }
    //counter counts how many days are both sunny days and with temperatures above -30 C 
    int howManyDays = 0;
    for (int i =0; i < tempInCelsius.length; i++) {
      for (int j = 0; j < sunnyStatus.length; j++) {
        if (sunnyStatus[j] == true && tempInCelsius[i] > -30.0) {
          howManyDays++;
        }
      }
    }
    return howManyDays;
  }
  
  /*method 2: takes in array of WeatherEntry objects (which have attributes of double temperatureInCelsius and boolean isSunny)
  * uses isGoodWeather method  to count and return number of good days (above -30C and sunny) in array*/
  public static int countGoodDays(WeatherEntry [] inputValues){
    int counter = 0;
    for(int i = 0; i< inputValues.length; i++) {
      //if an instance of a WeatherEntry meets criteria of isGoodWeather (i.e. temp > -30C and is sunny) then counter increases
      if (inputValues[i].isGoodWeather() == true){
        counter++;
      }
      //skip the instances that do not meet isGoodWeather criteria
      else if(inputValues[i].isGoodWeather() != true) {
        continue;
      }
    }
    return counter;
  }
  
  //private helper method to compute max temperature value; takes in an array of WeatherEntry objects
  private static double maxVal(WeatherEntry [] inputEntry) {
    /*comparison value for max temperature is the first instance of WeatherEntry array
    * to compare temp values of the instance, must use getTemperatureCelsius method from WeatherEntry class*/
    double maxTemp = inputEntry[0].getTemperatureCelsius();
    
    //loops through array of WeatherEntry objects "inputEntry" and compares current maxTemp to temperature of each element
    for (int i = 0; i < inputEntry.length; i++) {
      //if an instance has a greater temp than maxTemp, that is the new max temperature 
      if(inputEntry[i].getTemperatureCelsius() > maxTemp){
        maxTemp = inputEntry[i].getTemperatureCelsius();
      }
    }
    return maxTemp;
  }
  
  //private helper method to compute min temperature value; takes in an array of WeatherEntry objects
  private static double minVal(WeatherEntry [] inputEntry) {
    /*comparison value for min temp is the first instance of WeatherEntry array
     * to compare temp values of the instane, must use getTemperatureCelsius method from WeatherEntry class*/
    double minTemp = inputEntry[0].getTemperatureCelsius();
    
    //loops through array of WeatherEntry objects "inputEntry" and compares current minTemp to temperature of each element
    for (int i = 0; i < inputEntry.length; i++) {
      //if an instance has a lower temp than minTemp then that is the new min temperature
      if(inputEntry[i].getTemperatureCelsius() < minTemp){
        minTemp = inputEntry[i].getTemperatureCelsius();
      }
    }
    return minTemp;
  }
  
  //Main Method:create n number of WeatherEntry objects -- n is specified by user via command 
  public static void main(String [] args) {
    Scanner sc = new Scanner (System.in);
    int n = Integer.parseInt(args[0]);
    
    //create an array of WeatherEntry objects with a size of n (specified by user)
    WeatherEntry [] arrayOfObjects = new WeatherEntry [n];
    
    /*loop for the scanner. It will ask the user for a temperature, to enter if it is a sunny day.
    * it stores the input value of temperature in "tempValue" and input value of true of false of a sunny day in "isSunny"
    * for each iteration of the array, it will store the inputs in a new WeatherEntry object
    * then it will store that new object in the corresponding index of the array
    * */
    for (int i = 0; i< n; i++){
      System.out.println("Enter a temperature value");
      double tempValue = sc.nextDouble();
      
      System.out.println("Is it a sunny day? True or False.");
      boolean isSunny = sc.nextBoolean();
      
      WeatherEntry temp = new WeatherEntry(tempValue, isSunny);
      arrayOfObjects[i] = temp;
      
      System.out.println("new WeatherEntry object created!");
    }
    sc.close();
    
    /*once the scanner has completed the required iterations, the final ouputs will show the number of good days, 
    *the max temperature of the input values and the min temperature of the input values
    */
    System.out.println("num of good days" + countGoodDays(arrayOfObjects));
    System.out.println("max temp of inputs is " + maxVal(arrayOfObjects));
    System.out.println("min temp of inputs is " + minVal(arrayOfObjects));
  }
}





