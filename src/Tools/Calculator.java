package Tools;

import Printers.Printer;
import Validators.CalculatorValidator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final String START_MESSAGE = "Введите операцию сложения/умножения/деления/вычитания (пример - 34*85)";
    private static final String ERROR_MESSAGE = "Ошибка ввода, повторите";
    private final CalculatorValidator VALIDATOR = new CalculatorValidator();
    private final Scanner SCANNER = new Scanner(System.in);
    private final String[] OPERATORS = {"+", "-", "*", "/"};
    private final Printer PRINTER;
    private String expression;
    private String operator;
    private double a;
    private double b;


    public Calculator(Printer printer) {
        this.PRINTER = printer;
    }

    public String getExpression() {
        return expression;
    }

    public String[] getOPERATORS() {
        return OPERATORS;
    }

    public void run() {
        PRINTER.print(START_MESSAGE);
        expression = SCANNER.nextLine();
        VALIDATOR.setCalculator(this);
        if (!VALIDATOR.isValid()) {
            PRINTER.printError(ERROR_MESSAGE);
            run();
        }
        showResult();
    }

    private void showResult() {
        prepare();
        double calculationResult = getCalculationResult();
        PRINTER.print(String.valueOf(calculationResult));
    }

    private double getCalculationResult() {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }

    private void prepare() {
        Pattern pattern = Pattern.compile(VALIDATOR.getRegex());
        Matcher m = pattern.matcher(expression);
        if (m.matches()) {
            operator = m.group(2);
            a = Double.parseDouble(m.group(1));
            b = Double.parseDouble(m.group(3));
        }
    }

}
