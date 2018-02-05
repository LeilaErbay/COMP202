//assume that there are two inputes: a double and an integer


public class TruncateAfterNDecimalPlaces {
  public static void main(String [] args){
    double x = Double.parseDouble(args[0]);
    int n = Integer.parseInt(args[1]);
    int y = (int) x;
    
    System.out.printLn("integer part " +y);
    double frac = x - y;
    System.out.println("fractional part " + frac);
    
    //casting happens after (frac*Math.pow(10,n) and then it is divided
    frac = (int) (frac * Math.pow(10,n))/Math.pow(10,n);
    System.out.println("fractional part truncated: " + frac);
    x = y + frac;

      