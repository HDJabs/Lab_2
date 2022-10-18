public class IllegalInputException extends CalculatorException{
    
    private String illegalType;

    IllegalInputException(String illegalType){
        this.illegalType = illegalType;
    }

    public String getIllegalType(){
        return this.illegalType;
    }
}