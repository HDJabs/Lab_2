/**
 * The Calculator class provides functionality for parsing input strings
 * that contain simple expressions and for computing the result of the expression.
 *
 */

public class Calculator 
{
    public Calculator()
    {
        // Constructor does nothing
    }

    /**
     * Compute the result of the expression encoded in a sequence of tokens.
     * 
     * Here are the different cases:
     * 0 tokens: IllegalInputException: "Illegal Token Length"
     * 1 token: 
     *      "quit" (any casing): QuitException
     *      All other cases: IllegalInputException: "Illegal Argument"
     * 2 tokens:
     *      "-" and any number: return negative of number
     *      "-" and not a number: IllegalInputException: "Illegal Argument"
     *      other string and any string: IllegalInputException: "Illegal Operator"
     * 3 tokens:
     *1     number1, "+", number2   : return sum of two numbers
     *2     number1, "/", zero      : DivideByZeroException
     *3     number1, "/", not zero  : return number1/number2
     *4     number1, anything, nan  : IllegalInputException: "Illegal Argument" 
     *5     number1, na op, number2 : IllegalInputException: "Illegal Operator"
     *6     nan, anything, anything : IllegalInputException: "Illegal Argument"
     * 4 or more tokens: IllegalInputException: "Illegal Token Length"
     *     
     * Note: all numbers are integers
     * 
     * @param tokens The array of tokens to evaluate
     * @return int result of evaluating the expression
     * @throws CalculatorException If some form of error has been generated or
     * "quit" has been typed. Throws one of the several child classes of CalculatorException
     * in order to give more information on what the error is.
     */
    public static int compute(String[] tokens) throws CalculatorException, IllegalInputException, QuitException, DivideByZeroException {
        // Condition on the number of tokens
        
        switch(tokens.length)
        {
            case 0:
                throw new IllegalInputException("Illegal token length");

            case 1:
                // Only case: quit
                if (tokens[0].equals("quit")){
                    throw new QuitException();
                }
                else{
                    throw new IllegalInputException("Illegal argument");
                }
            case 2:
                // Only case: unary operator
                try{
                    int number1 = Integer.valueOf(tokens[1]);
                }
                catch(NumberFormatException e){
                    throw new IllegalInputException("Illegal Argument");
                }
                if((Integer.valueOf(tokens[1])*0) == 0){
                    if(tokens[0].equals("-")){
                        return (Integer.valueOf(tokens[1]) * -1);
                    }
                    else if(tokens[0].equals("+")){
                        return Integer.valueOf(tokens[1]);
                    }
                    else{
                        throw new IllegalInputException("Illegal Operator");
                    }
                }
                else{
                    throw new IllegalInputException("Illegal Argument");
                }
                
            case 3:
                // Binary operator
                try{
                    int number1 = Integer.valueOf(tokens[0]);
                    int number2 = Integer.valueOf(tokens[2]);
                }
                catch(NumberFormatException e){
                    throw new IllegalInputException("Illegal Argument");
                }
                if((Integer.valueOf(tokens[0])*0)==0 ){ //number1
                    int number1 = Integer.valueOf(tokens[0]);

                    if(Integer.valueOf(tokens[2])*0!=0){ //number2 nan
                        throw new IllegalInputException("Illegal argument");//4
                    }
                    else{ //number2 
                        int number2 = Integer.valueOf(tokens[2]);

                        if(tokens[1].equals("*"))
                            return number1*number2;//1
                        else if(tokens[1].equals("/")){
                            if(number2 == 0)
                                throw new DivideByZeroException(); //2
                            else
                                return number1/number2;//3
                        }
                        else{
                            throw new IllegalInputException("Illegal Operator"); //5
                        }
                    }
                }
                else{
                    throw new IllegalInputException("Illegal argument");//6
                }
                
            default:
                // 4 or more tokens
                throw new IllegalInputException("Illegal token length");
                
        }
        

    }

    /**
     * Parse the input string as an expression and evaluate it.
     * 
     * If the input is a legal expression, then the result is printed
     * 
     * Otherwise a CalculatorException has occurred.  Print a message based on
     * what exception type it is.
     * 
     * Always print out what the input was. Use a finally block for this.
     * 
     * Always prints out two lines, following the rules:
     * 1st line:
     * -No Exception: "The result is: " + result
     * -QuitException: "Quitting"
     * -DivideByZeroException: "Tried to divide by zero"
     * -IllegalInputException: "Illegal input: " + illegalinputtype(given to constructor)
     * -CalculatorException: <should never happen> e.getMessage()
     * 2nd line:
     * "Input was: " + input
     * 
     * @param input A String possibly containing a mathematical expression
     * @return true if the String is equal to "quit"; false otherwise
     */
    public static boolean parseAndCompute(String input)
    {
        // Pull out the tokens
        String[] tokens = input.split(" ");
        
        try 
        {

            System.out.println("The result is: " + compute(tokens));
            return false;
           
        }
        catch (QuitException e)
        {
            System.out.println("Quitting");
            return true;
        }
        catch (DivideByZeroException e){
            System.out.println("Tried to divide by zero");
            return false;
        }
        catch (IllegalInputException e)
        {
            System.out.println("Illegal input: " + e.getIllegalType());
            return false;
        }
        catch (CalculatorException e)
        {
            // This catches the remaining CalculatorException case: DivideByZeroException
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            System.out.println("Input was: " + input);
        }


        // Quit has not been specified
        
    }
}
