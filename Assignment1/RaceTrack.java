/*This code is used for determining the total bill of covering a track field with asphalt.
The radius value must be a POSITIVE NUMBER and number of lanes must be a POSITIVE WHOLE NUMBER!

Given: Asphalt is sold at $7.49/sq m
Given: Each lane is 2.8 m wide */

//Naming of new class: RaceTrack.
public class RaceTrack {
    
  //Defining main method; return type: void 
  public static void main(String []args) {
   
    double radius = Double.parseDouble (args[0]); //Converting String (args[0]) to type double, variable holding new value = radius
    int numLanes = Integer.parseInt (args [1]);   //Converting String (args[1]) to type int, variable holding new value = numLanes
    
    getPriceOfTrack(radius, numLanes);            //Calling method getPriceOfTrack and (arguments) 
    
  } 
  
  //Declaring new method named getPriceOfTrack; return type: double; parameters of type double, type int
  public static double getPriceOfTrack(double radius, int numLanes) { 
    double areaTrack = Math.PI * radius * radius;                                             //Calculate area of racetrack (i.e. area of big circle)
    double areaLanes = Math.PI * (radius - (numLanes * 2.8)) * (radius - (numLanes * 2.8));   //Calulalate area of lanes (i.e. area little circle)
    double totalArea = areaTrack - areaLanes;                                                 //Calculate area of race track that is paved (i.e. area of track = bigger circle - little circle)
      
    double subTotal = totalArea * 7.49;                         //Calculate cost of paved track                             
    double salesTax = subTotal * 0.15;                          //Calculate cost of sales tax
   
    double preciseTotal =  Math.ceil(subTotal + salesTax);      //Calculate precisePrice as a double that is then rounded up
    int totalPrice = (int) preciseTotal;                        //Cast double preciseTotal to  integer totalPrice 
    
    System.out.println("Total area: " + totalArea);             
    System.out.println("Subtotal: $" + subTotal);               
    System.out.println("Sales Tax (15%): $" + salesTax);        
    System.out.println("Total: $" + totalPrice);                //Displays total bill 
    
    return areaTrack;
   
  }
     
}
  


  