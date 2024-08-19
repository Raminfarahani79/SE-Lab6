package MiniJava.parser;

import MiniJava.scanner.token.Token;

public interface ActionStrategy {
    void executeAction(Action currentAction, Token lookAhead);
}