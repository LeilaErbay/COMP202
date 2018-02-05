public class Pixel {
  //private attributes of a pixel
  private int red;
  private int green;
  private int blue;
  
  //constructor 1: int values for RGB
  public Pixel (int colorR, int colorG, int colorB) {
    //if values of RGB are either greater than 255 or less than 0, then an exception will be thrown
    if (colorR > 255 || colorR < 0 || colorG > 255 || colorG < 0  || colorB > 255 || colorB <0){
      throw new IllegalArgumentException ("one or multiple colors have an intensity value less than 0 or greater than 255, please enter a new intensity value");
    }
    else{
      this.red = colorR;
      this.green = colorG;
      this.blue = colorB;
    }
  }
  //constructor 2: int value for 1 value intensity
  public Pixel (int intensity) {
    //if intensity is greater than 255 or less than 0, then program throws an error
    if (intensity > 255 || intensity < 0) {
      throw new IllegalArgumentException ("there is an intensity value less than 0 or greater than 255, please enter a new intensity value");
    }
    else {
      //set all three attributes to 1 value intensity
      this.red = intensity;
      this.green = intensity;
      this.blue = intensity;
    }
  }
  //getter method for red pixel value
  public int getRed() {
    return this.red;
  }
  //getter method for green pixel value
  public int getGreen() {
    return this.blue;
  }
  //getter method for blue pixel value
  public int getBlue() {
    return this.blue;
  }
  //method that returns the average of the RGB values
  public int grey(){
    double average = ((0.3*this.red + 0.59*this.green + 0.11*this.blue)/3);
    return (int) average;
  }
}