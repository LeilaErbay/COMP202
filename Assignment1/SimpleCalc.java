//calculates expression 

public class SimpleCalc {
  public static void main(String []args) {
    int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[2]);
    double result;
  
  
  //public static boolean doCalculations (int x, int y, String operation) {
    
    if (args[1].equals("+")) {
      result = x + y;
      System.out.println(" = " + result); 
    }
    else if (args[1].equals("-")) {
      result = x - y;
      System.out.println("=" + result);
    }
    else if (args[1].equals("*")) {
      result = x * y;
      System.out.println("=" + result);
    }
    else if (args[1].equals("/")) {
      result = x / y;
      System.out.println("= " + result);
    }
    else if (args[1].equals("%")) {
      result = x % y;
      System.out.println("=" + result);
    }
    else {
      System.out.println("this operation can not be implemented");
    }
  }
}
    
       
    