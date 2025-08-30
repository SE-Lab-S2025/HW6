package MiniJava.parser;

import MiniJava.Log.Log;
import MiniJava.scanner.token.Token;

public abstract class ParserAction {
    public abstract void execute(Parser parser) throws Exception;
}

// ====================== Shift ======================
class ShiftAction extends ParserAction {
    private final int nextState;

    public ShiftAction(int nextState) {
        this.nextState = nextState;
    }

    @Override
    public void execute(Parser parser) {
        parser.getParsStack().push(nextState);
        parser.setLookAhead(parser.getLexicalAnalyzer().getNextToken());
    }

    @Override
    public String toString() {
        return "Shift to " + nextState;
    }
}

// ====================== Reduce ======================
class ReduceAction extends ParserAction {
    private final Rule rule;

    public ReduceAction(Rule rule) {
        this.rule = rule;
    }

    @Override
    public void execute(Parser parser) {
        for (int i = 0; i < rule.RHS.size(); i++) {
            parser.getParsStack().pop();
        }

        int newState = parser.getParseTable().getGotoTable(parser.getParsStack().peek(), rule.LHS);
        parser.getParsStack().push(newState);

        Log.print("Reduce by " + rule.LHS + " → " + rule.RHS + " , goto " + newState);

        try {
            parser.getCodeGenerator().semanticFunction(rule.semanticAction, parser.getLookAhead());
        } catch (Exception e) {
            Log.print("Code Generator Error");
        }
    }

    @Override
    public String toString() {
        return "Reduce using rule " + rule;
    }
}

// ====================== Accept ======================
class AcceptAction extends ParserAction {
    @Override
    public void execute(Parser parser) {
        parser.setFinished(true);
    }

    @Override
    public String toString() {
        return "Accept";
    }
}

