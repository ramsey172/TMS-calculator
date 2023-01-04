package Validators;


import Tools.Calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorValidator {

    private Calculator calculator;

    private String regex;

    public void setCalculator(Calculator calculator){
        this.calculator = calculator;
        buildRegex();
    }

    public String getRegex(){
        return regex;
    }


    private void buildRegex(){
        if(calculator.getOPERATORS().length > 0){
            StringBuilder regex = new StringBuilder("(\\d+)(");
            String[] operators = calculator.getOPERATORS();
            for (int n = 0; n < operators.length; n++) {
                regex.append("\\"+operators[n]+"+");
                if(n + 1 < operators.length){
                    regex.append("|");
                }
            }
            regex.append(")(\\d+)");
            this.regex = regex.toString();
        }
    }

    public boolean isValid(){
        boolean isValid = false;
        if(regex.length() > 0){
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(calculator.getExpression());
            if(m.matches() && !(Double.parseDouble(m.group(3)) == 0 && m.group(2).equals("/"))) {
                isValid = true;
            }
        }
        return isValid;
    }
}
