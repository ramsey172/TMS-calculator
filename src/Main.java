import Printers.Printer;
import Printers.SystemOutPrinter;
import Tools.Calculator;

public class Main {
    public static void main(String[] args) {

        Printer printer = new SystemOutPrinter();
        Calculator calculator = new Calculator(printer);
        calculator.run();

    }
}