package ru.dikanskiy.services.impl;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.math.NumberUtils;
import ru.dikanskiy.services.configuration.ParserConfig;
import ru.dikanskiy.entities.Operator;
import ru.dikanskiy.services.interfaces.Parser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@RequiredArgsConstructor
public class ParserImpl implements Parser {
    static Stack<Operator> stack = new Stack<>();
    static Queue<String> queue = new LinkedList<>();
    private final ParserConfig config;

    @Override
    public String parse(String infix) {
        String[] tokens = infix.split(" ");
        StringBuilder postfix = new StringBuilder();
        for (String token : tokens) {
            if (NumberUtils.isCreatable(token)) {
                queue.add(token);
                continue;
            }
            if (config.isSupport(token)) {
                if (token.equals("(")) {
                    stack.push(config.getOperatorByToken(token));
                    continue;
                }
                if (token.equals(")")) {
                    while (!stack.empty() && stack.peek() != config.getOperatorByToken("(")) {
                        queue.add(stack.pop().getOperator());
                    }
                    stack.pop();
                    continue;
                }
                if (!stack.empty() && config.getOperatorByToken(token).getPriority() <= stack.peek().getPriority()) {
                    while (!stack.empty() && config.getOperatorByToken(token).getPriority() <= stack.peek().getPriority()) {
                        queue.add(stack.pop().getOperator());
                    }
                    stack.push(config.getOperatorByToken(token));
                } else {
                    stack.push(config.getOperatorByToken(token));
                }
            } else {
                clear();
                throw new UnsupportedOperationException(String.format("%s is not supported", token));
            }
        }
        while (!queue.isEmpty()) {
            postfix.append(queue.poll()).append(" ");
        }

        while (!stack.empty()) {
            postfix.append(stack.pop().getOperator()).append(" ");
        }
        return postfix.toString().trim();
    }

    @Override
    public ParserConfig getConfiguration() {
        return config;
    }

    private void clear() {
        stack.clear();
        queue.clear();
    }
}
