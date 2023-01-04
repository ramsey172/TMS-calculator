package Printers;

public class SystemOutPrinter implements Printer{
    public void print(String string){
        System.out.println(string);
    }

    public void printError(String string){
        System.err.println(string);
    }
}
