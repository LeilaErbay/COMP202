public class WeatherEntry {
  private double temperatureInCelsius;
  private boolean isSunny;
  
  /*Q2: Constructor Method -- because data fields to WeatherEntry class are private, 
  * constructor method is needed to access those attributes (indirectly)*/
  public WeatherEntry(double tempInCel, boolean isSunny){
    this.temperatureInCelsius = tempInCel;
    this.isSunny = isSunny;
  }
  
  //Q2: Method 1: returns the temperature in Celsius
  public double getTemperatureCelsius(){
    return this.temperatureInCelsius;
  }
  //Q2: Method 2: returns whether or not the day is a nice day. A nice day = (1) above -30 degrees C and (2) sunny [must have both qualities]
  public boolean isGoodWeather(){
    /*call to private method isAbove30 which determines if the weather is above -30 degrees C and is a sunny day
    * if the expressions in the if statement are both true then it is a nice day*/
    if (isAbove30() == true && this.isSunny == true){
      return true;
    }
    return false;
  }
  
  /*A private method to help method 2 
   * if temperature of the instance is greater than -30 degrees C then boolean is true (and is a good day)
   if temperature of the instance is less than or equal to -30 degrees C then boolean is false (and is not a good day)*/
  private boolean isAbove30(){
    boolean above30 = true;
    if (this.temperatureInCelsius > -30){
      return above30;
    }
    else if(this.temperatureInCelsius <= -30) {
      above30 = false;
    }
    return above30;
  }
  
  //Q2: Method 3: takes in boolean idicating whether user would like to see information in C or F; print method thus void return
  public void display (boolean isCelsius){
    //if user wants info in C and it is a nice day, then print the following 
    if(isCelsius == true && isGoodWeather() == true) {
      System.out.println("It is " + this.temperatureInCelsius + " degrees celsius and is sunny");
      System.out.println("It is a nice day");
    }
    //if user wants info in C and it is a bad day, then print the following
    else if (isCelsius == true && isGoodWeather() == false) {
      System.out.println("It is " + this.temperatureInCelsius + " degrees celsius and is not sunny");
      System.out.println("It is a bad day");
    }
    //if user wants info in F and it is a good day, convert temp from C to F and print the following
    else if(isCelsius == false && isGoodWeather() == true) {
      int farenheitTemp = (int)(this.temperatureInCelsius*1.8+32);
      System.out.println("It is " + farenheitTemp+ " degrees farenheit and it is sunny");
      System.out.println("It is a nice day");
    }
    //if user wants info in F and it is a bad day, convert temp from C to F and print the following
    else if(isCelsius == false && isGoodWeather() == false) {
      int farenheitTemp = (int)(this.temperatureInCelsius*1.8+32);
      System.out.println("It is " + farenheitTemp+ " degrees farenheit and it is not sunny");
      System.out.println("It is a bad day");
      
    }
  }
}