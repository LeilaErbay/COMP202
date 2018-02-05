public class YearlyBudget {
  
  public static void main(String [] args) {
    
    //five command line inputs in the main method
    double yearlyIncome = Double.parseDouble(args[0]);
    double creditCardBalance = Double.parseDouble(args[1]);
    double yearlyInterestRate = Double.parseDouble (args[2]);
    long creditCardNumber = Long.parseLong(args[3]);
    double monthlyRent = Double.parseDouble(args[4]);
    
    
    //used for buildPayments
    double postTaxIncome = yearlyIncome - calculateTax(yearlyIncome,10000, 20, 20000, 30, 45000, 50); 
    
    //Used for monthly savings method
    double monthlyIncome = postTaxIncome/12.0;
    
    //if the creditCardNumber is not valid, exit 
    boolean isCardValid = validateCreditCard(creditCardNumber);
    if (isCardValid == false){
      System.out.println("Invalid credit Card");
      return;
    }
    
    /*create a double array that stores the return values of buildExpenses and 
     create a expensePayment array that stores the return value of  buildPayments */
    double [] expenseMonth = buildExpenses(monthlyRent);
    double [] expensePayment = buildPayments(postTaxIncome);
    
    //Otherwise, display monthly credit card balance using Question 6 method  
    printBalance(creditCardBalance, yearlyInterestRate, expenseMonth, expensePayment);
    
    //display monthly additions to savings account 
    double perMonthSavings = 0;
    double yearlySavings = 0;
    
    //calling monthlySavings with arguments array expensePayment (declared above) and monthlyIncome
    //used idea that savings is the monthly income - the expense payed each month, which are the values derived from the buildPayments method
    for (int i = 0; i < 12; i++) {
      perMonthSavings = monthlySavings(expensePayment[i],monthlyIncome);
      System.out.println("Month " +(i+1) + " savings: " + perMonthSavings);
      
      //yearlySavings is the cumulative sum of perMonthSavings
      yearlySavings += perMonthSavings;
    }
    System.out.println("Your yearly savings is: " + yearlySavings);
  }
  
  
  
  //create new method calculateTax that takes in 7 input arguments and returns the amount payed in taxes.
  public static double calculateTax(double yearlyIncome, double bracket1Dollars, double bracket1Rate, 
                                    double bracket2Dollars, double bracket2Rate, double bracket3Dollars,
                                    double bracket3Rate) {
    
    
    //calculate the amount of money taxed between bracket 1 and bracket 2; 
    //calculate the amount of money taxed between bracket 2 and bracket 3;
    double taxBracket1_2 = (bracket2Dollars - bracket1Dollars) * bracket1Rate * 0.01;
    double taxBracket2_3 = (bracket3Dollars - bracket2Dollars) * bracket2Rate * 0.01;
    
    //calculate amount of tax payed at each bracket
    double taxBracket0 = 0.0;
    double taxBracket1 = (yearlyIncome - bracket1Dollars) * bracket1Rate * 0.01;
    double taxBracket2 = taxBracket1_2 + (yearlyIncome - bracket2Dollars) * bracket2Rate * 0.01;
    double taxBracket3 = taxBracket1_2 + taxBracket2_3 + (yearlyIncome - bracket3Dollars) * bracket3Rate * 0.01;
    
    //determine the confinds of each bracket in relation to the yearlyIncome
    if (yearlyIncome > bracket3Dollars) {
      return taxBracket3;
    }
    
    if ((yearlyIncome < bracket3Dollars) && (yearlyIncome > bracket2Dollars)){
      return taxBracket2;
    }
    
    if((yearlyIncome < bracket2Dollars) && (yearlyIncome > bracket1Dollars)){
      return taxBracket1;
    }
    
    return taxBracket0;
  }
  
  
//Method that takes two inputs: 1 input = monthly expenses & 2nd input = monthly income after tax
//Method returns the amount that you saved per month
  public static double monthlySavings(double monthlyExpenses,double monthlyIncome){
    double amountSaved = (monthlyIncome - monthlyExpenses);
    return amountSaved;
  }
  
//method takes in long and returns boolean
//method determines whether long values have a valid card number or not accd to checksum
  public static boolean validateCreditCard(long creditCardNumber){
    
    boolean cardValid = true;
    int[] digitsOfCard = new int[16];
    
    for (int i = 0; i < 16; i ++){
      /*code divides credit card by 10^i-value and modulus by 10, castes as an int
       and stores that int in the correlating spot in the array*/
      digitsOfCard [i] = (int)((creditCardNumber / Math.pow (10,i)) % 10);
    }
    
    int sumEvenDigits = 0;
    //set counter i to 0 and increment by 2 to extract even places
    for(int i = 0; i <16; i+=2){
      //new value of sumEven will be sum of the values that the counter increments to added to previous value of sumEvenDigits
      sumEvenDigits = sumEvenDigits + digitsOfCard[i];
    }
    
    int sumOddDigits = 0;
    //set counter i to 1 and increment by 2 to extract odd places
    for (int i = 1; i < 16; i+= 2){
      //new value of sumOdd is the sum of each values after the counter incremented to, doubled, and modulo by 9 added to previous value of sumOddDigits
      sumOddDigits = sumOddDigits + (digitsOfCard[i] *2)%9;
    }
    
    //sum of the values of sumEven and sumOdd and 
    int result = sumEvenDigits + sumOddDigits;
    
    //if statement claims that if sum of result is equal to 0 after modulo by 10, then the credit card is valid
    if (result % 10 == 0){
      return true;
    }
    else{
      return false;
    }
  }
  
  //new method that takes as input double value of monthly rent and returns a double array of expenses in each month 
  public static double[] buildExpenses (double monthlyRent){ 
    double [] numMonths = new double [12];
    
    for (int i = 0; i < 12; i++){
      
//every month, you must add rent and 600$ to expenses
      numMonths[i] = numMonths [i] + 600 + monthlyRent;
      
      //within for loop, check if month is Jan or Jun, if it is add additional $200 to those months
      if(i == 0 || i == 5){
        numMonths[i] = numMonths[i] + 200;
      }
      
      //within for loop, check if month is Sept. and if it is add additional $400 to that month
      if (i == 8){
        numMonths[i] = numMonths[i] + 400;
      }
      
      /*within for loop, check if month is April, July or Sept, 
       and if i = 3,6, or 8, then add additional $100 to those months*/
      if (i == 3 || i == 6 || i == 8){
        numMonths[i] = numMonths[i] + 100;
      }
      
      /*within for loop, check if month is Dec. and if it is, then add additional $200 to that month*/
      if (i == 11) {
        numMonths [i] = numMonths[i] + 200;
      }
    }
    
    return numMonths;
  }
  
//new method BuildPayments takes an input that's a double called postTaxIncome and returns double array of monthly Payments
  public static double [] buildPayments (double postTaxIncome){
    
    double [] numMonths = new double [12];
    
    for (int i = 0; i < 12; i++){
      //every month, 10% of postTaxIncome is spent; to get 12 portions of postTaxIncome, divide postTaxIncome by 12
      double monthlyIncome = postTaxIncome/12.0;
      numMonths[i] = (monthlyIncome* 0.1);
      
      //check if month is Dec. and if it is, you add an additional $150 to pay off credit card
      if (i == 11) {
        numMonths [i] = numMonths[i] + 150;
      }
      
      //check if month is Sept and if it is, you add an additional $200 to pay off credit card
      if (i == 8) {
        numMonths [i] = numMonths[i] + 200;
      }
      
    }
    return numMonths; 
  }
  /*New method printBalance takes in creditCardBalance, yearlyInterestRate, expense array, 
   payments array and prints balance owed at end each month*/
  public static void printBalance (double creditCardBalance, double yearlyInterestRate, 
                                   double [] expenseMonth, double [] paymentMonth){
    
    double monthlyRate = ((yearlyInterestRate/100)/12);
    double balancePerMonth = 0;
    
    //start with January and add initial credit card balance to month 1
    expenseMonth [0] +=  creditCardBalance ;
    
    for (int i = 0; i< 12; i++){
      /*reassign balance value to formula: the credit card balance + the expense of that month - the payments of that month*/
      balancePerMonth +=  ((creditCardBalance+ expenseMonth[i] - paymentMonth[i])+(monthlyRate+1)) ;
      
      System.out.println("Month " + (i+1) + " balance: " + balancePerMonth);  
    }
    
  }
}






