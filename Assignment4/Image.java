public class Image {
  //private attributes of an image class
  private String metadata;
  private int maxRange;
  private Pixel [][] data;
  
  //constructor: takes in metaData (ie. comments), maxRange, and a 2D pixel array
  public Image (String metaData, int maxRangeInput, Pixel [][] inputArray) {
    //throws an exception if maxRange is negative
    if (maxRangeInput < 0) {
      throw new IllegalArgumentException ("your range is less than zero, please enter a new range");
    }
    else {
      this.metadata = metaData;
      this.maxRange = maxRangeInput;
      
      //making a copy of the Pixel [][] inputArray
      Pixel [][] copyArray = new Pixel [inputArray.length][inputArray[0].length];
      for (int i = 0; i < inputArray.length; i++) {
        for (int j = 0; j < inputArray[0].length;j++){
          copyArray[i][j]= inputArray[i][j];
        }
      }
      copyArray = inputArray;
      
      //set this data to inputArray
      this.data = inputArray; 
      
    }
  }
  //getter method for metaData
  public String getMetadata () {
    return this.metadata;
  }
  //getter method for maxRange
  public int getMaxRange () {
    return this.maxRange;
  }
  //determines the height of the 2D Pixel Array -- if first column is of certain length,
  //all following columns will be of same length, thus only need length of one column (first column)
  public int getHeight() {
    return this.data.length;
  }
  //determines the width of 2D Pixel Array - all rows will be same length as first row, thus only need the length of the first row
  public int getWidth () {
    return this.data[0].length;
  }
  //return a specific Pixel object at i,j
  public Pixel getPixel (int i, int j) { 
    return this.data[i][j];
  }
  
  //method to flip array either horizontally or vertically
  public void flip(boolean horizontal) {
    int height = getHeight();
    int width = getWidth();
    Pixel [][] swappedArray = new Pixel [height][width];
    
    //if boolean horizontal is true, then array will be flipped horizontally 
    if (horizontal == true) {
      swappedArray = horizontalFlip();
      
    }
    //if boolean horizontal is false, then array will be flipped vertically
    else if (horizontal == false) {
      swappedArray = verticalFlip();
    }
    //original array has the values of the swappedArray
    this.data = swappedArray;
  }
  
  //private method that was used to flip array horizontally
  private  Pixel[][] horizontalFlip() {
    int height = getHeight();
    int width = getWidth();
    Pixel [][] flippedArray = new Pixel [height][width];
    
    for (int i = 0; i < height; i++) {
      for (int j = 0; j< width; j++) {
        //flipped positions of array will store values of original array
        flippedArray[i][width-j-1] = this.data[i][j];
      }
    }
    return flippedArray;
  }
  
  //private method that was used to flip array vertically
  private Pixel [][] verticalFlip() {
    int height = getHeight();
    int width = getWidth();
    Pixel [][] flippedArray = new Pixel [height][width];
    
    //flipped positions of array will store values of original array
    for (int i = 0; i < height; i++){
      for (int j = 0; j < width; j++) {
        flippedArray[height-i-1][j] = this.data[i][j];
      }
    }
    return flippedArray;
  }
  
  //grey method to convert all values in Pixel 2D array into 1 intensity
  public void toGrey(){
    int height = getHeight();
    int width = getWidth();
    Pixel [][] greyArray = new Pixel[height][width];
    
    for (int i=0; i < height; i++) {
      for (int j=0; j< width; j++) {  
        //at every index of greyPGM, create a new pixel that now has value of averages of this.data at corresponding index
        greyArray[i][j]= new Pixel (this.data[i][j].grey()); 
      }
    }
    // original array now has values of the greyArray 
    this.data = greyArray;
  }
  //crop method
  public void crop(int startX, int startY, int endX, int endY) {
    //if inputs are negative or greater than the dimensions of the original array, crop will not be possible
    if(startX < 0 || startY < 0 ||endX > getWidth() || endY > getHeight()) {
     throw new IllegalArgumentException("invalid inputs for cropping. Starting coordinates can not be less than 0 and end coordinates can not be greater than the height of the array.");
      
    }
    
    else{
      //height of cropped array is the distance from the endY coordinate to the startY coordinate (y2-y1)
      int heightCrop = (endY - startY);
      //width of cropped is the distance from the endX coordinate to the startX coordinate (x2-x1)
      int widthCrop = (endX - startX);
      Pixel [][] croppedArray = new Pixel [heightCrop][widthCrop];
      
      //start looping at the startY point and ends at the endY point for the height
      //start looping at the startX point and ends at the endX point for the width
      for (int i = startY; i < endY; i++) {
        for (int j = startX; j < endX; j++) {
          croppedArray[i-startY][j-startX] = this.data[i][j];
        }
      }
      //change this.data by setting it to croppedArray
      this.data = croppedArray;
    }
  }
}





