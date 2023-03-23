package ru.dikanskiy.services.impl;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.math.NumberUtils;
import ru.dikanskiy.services.interfaces.Calculator;
import ru.dikanskiy.services.interfaces.Parser;

import java.util.Stack;

@RequiredArgsConstructor
public class CalculatorImpl implements Calculator {
    private final Parser parser;
    static Stack<String> tokens = new Stack<>();

    @Override
    public double calculate(String infix) {
        double result = 0.0;
        String postfix = parser.parse(infix);
        String[] postfixTokens = postfix.split(" ");
        for (String token : postfixTokens) {
            if (NumberUtils.isCreatable(token)) {
                tokens.push(token);
            } else {
                double d = Double.parseDouble(tokens.pop());
                result = parser.getConfiguration().getFunctionByToken(token).apply(Double.parseDouble(tokens.pop()), d);
                tokens.push(String.valueOf(result));
            }
        }
        return result;
    }
}
