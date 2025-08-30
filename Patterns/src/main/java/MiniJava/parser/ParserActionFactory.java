package MiniJava.parser;

import java.util.List;

public class ParserActionFactory {
    private final List<Rule> rules;

    public ParserActionFactory(List<Rule> rules) {
        this.rules = rules;
    }

    public ParserAction from(Action action) {
        switch (action.action) {
            case shift:
                return new ShiftAction(action.number);
            case reduce:
                return new ReduceAction(rules.get(action.number));
            case accept:
                return new AcceptAction();
            default:
                throw new IllegalArgumentException("Unknown action type: " + action.action);
        }
    }
}

