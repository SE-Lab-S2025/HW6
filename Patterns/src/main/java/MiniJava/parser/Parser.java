package MiniJava.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

import MiniJava.Log.Log;
import MiniJava.codeGenerator.CodeGenerator;
import MiniJava.errorHandler.ErrorHandler;
import MiniJava.scanner.lexicalAnalyzer;
import MiniJava.scanner.token.Token;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Parser {
    private static final String PARSE_TABLE_PATH = "src/main/resources/parseTable";
    private static final String RULES_PATH = "src/main/resources/Rules";

    private ArrayList<Rule> rules;
    private Stack<Integer> parsStack;
    private ParseTable parseTable;
    private lexicalAnalyzer lexicalAnalyzer;
    private CodeGenerator codeGenerator;
    private Token lookAhead;
    private boolean isFinished;
    private Action currentAction;

    public Parser() {
        this.parsStack = new Stack<>();
        this.parsStack.push(0);
        this.parseTable = initializeParseTable();
        this.rules = initializeRules();
        this.codeGenerator = new CodeGenerator();
    }

    private ParseTable initializeParseTable() {
        try {
            return new ParseTable(Files.readAllLines(Paths.get(PARSE_TABLE_PATH)).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Rule> initializeRules() {
        ArrayList<Rule> initRules = new ArrayList<Rule>();
        try {
            for (String stringRule : Files.readAllLines(Paths.get(RULES_PATH))) {
                initRules.add(new Rule(stringRule));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return initRules;
    }

    public void startParse(java.util.Scanner sc) {
        this.setLexicalAnalyzer(new lexicalAnalyzer(sc));
        this.setLookAhead(lexicalAnalyzer.getNextToken());
        this.setFinished(false);

        while (!this.isFinished()) {
            try {
                Log.print(/*"lookahead : "+*/ this.getLookAhead().toString() + "\t" + this.getParsStack().peek());
                this.setCurrentAction(this.getParseTable().getActionTable(this.getParsStack().peek(), this.getLookAhead()));
                Log.print(this.getCurrentAction().toString());

                switch (getCurrentAction().action) {
                    case shift:
                        this.getParsStack().push(this.getCurrentAction().number);
                        this.setLookAhead(this.getLexicalAnalyzer().getNextToken());

                        break;
                    case reduce:
                        Rule rule = this.getRules().get(this.getCurrentAction().number);
                        for (int i = 0; i < rule.RHS.size(); i++) {
                            this.getParsStack().pop();
                        }

                        Log.print(/*"state : " +*/ this.getParsStack().peek() + "\t" + rule.LHS);
                        this.getParsStack().push(this.getParseTable().getGotoTable(this.getParsStack().peek(), rule.LHS));
                        Log.print(/*"new State : " + */this.getParsStack().peek() + "");
                        try {
                            this.getCodeGenerator().semanticFunction(rule.semanticAction, this.getLookAhead());
                        } catch (Exception e) {
                            Log.print("Code Genetator Error");
                        }
                        break;
                    case accept:
                        this.setFinished(true);
                        break;
                }
                Log.print("");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        if (!ErrorHandler.hasError) this.getCodeGenerator().printMemory();
    }
}
