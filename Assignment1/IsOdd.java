public class IsOdd {
  public static void main(String [] args){
    int x = Integer.parseInt(args[0]);
    int remainder = x % 2;
    
    System.out.println("Is it odd? ->" + (remainder != 0)); //remainder != 0 is a boolean statement
  }
}