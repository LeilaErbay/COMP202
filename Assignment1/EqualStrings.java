public class EqualStrings {
  public static void main (String []args){
    String string1, string2;
    string1 = args [0];
    string2 = args [1];
    
    System.out.println("Are the two strings the same? ->" + EqualityCheck(string1,string2));
  }
  
  public static boolean EqualityCheck(String string1, String string2){
    boolean result = string1.equals(string2);
    return result;
  }
}