package MiniJava;

import MiniJava.errorHandler.ErrorHandler;
import MiniJava.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MiniJavaFacade {
    public void runProgram(String code) {
        Parser parser = new Parser();
        try {
            parser.startParse(new Scanner(new File(code)));
        } catch (FileNotFoundException e) {
            ErrorHandler.printError(e.getMessage());
        }
    }
}
