package ru.dikanskiy.services.configuration;

import ru.dikanskiy.entities.Operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * <p>
 * Base configuration class which supports following math operators:
 * </p>
 * <p>
 * <strong>"+"</strong> - plus with precedence <strong>1</strong>
 * <p>
 * <strong>"-"</strong> - minus with precedence <strong>1</strong>
 * </p>
 * <strong>"*"</strong> - mul with precedence <strong>2</strong>
 * <p>
 * <strong>"/"</strong> - div with precedence <strong>2</strong>
 * </p>
 * <strong>"("</strong> - "open" bracket with precedence <strong>1</strong>
 * <p>
 * <strong>")"</strong> - "close bracket" with precedence <strong>1</strong>
 * </p>
 * </p>
 *
 * @author anduser
 * @version 1.0
 */
public class ParserConfig {
    private Map<Operator, BiFunction<Double, Double, Double>> _operators = new HashMap<>();

    {
        _operators.put(new Operator(1, "+"), (x, y) -> x + y);
        _operators.put(new Operator(1, "-"), (x, y) -> x - y);
        _operators.put(new Operator(2, "*"), (x, y) -> x * y);
        _operators.put(new Operator(2, "/"), (x, y) -> x / y);
        _operators.put(new Operator(1, "("), (x, y) -> null);
        _operators.put(new Operator(1, ")"), (x, y) -> null);
    }

    public Map<Operator, BiFunction<Double, Double, Double>> getOperators() {
        return _operators;
    }

    /**
     * @param op               math operator to add
     * @param operatorFunction math function related to the given operator
     */
    public void addOperator(Operator op, BiFunction<Double, Double, Double> operatorFunction) {
        _operators.put(op, operatorFunction);
    }

    /**
     * @param operators math operators to add
     */
    public void addOperators(Map<Operator, BiFunction<Double, Double, Double>> operators) {
        _operators.putAll(operators);
    }

    /**
     * @param operator math operator
     * @return {@code true} if configuration contains the given math operator
     */
    public boolean isSupport(String operator) {
        return _operators.keySet().stream()
                .anyMatch(op -> op.getOperator().equals(operator));
    }

    /**
     * @param token math operator
     * @return math function for given operator
     */
    public BiFunction<Double, Double, Double> getFunctionByToken(String token) {
        Optional<Operator> operator = _operators.keySet().stream()
                .filter(op -> op.getOperator().equals(token))
                .findAny();
        return _operators.get(operator.orElseThrow(UnsupportedOperationException::new));
    }

    /**
     * @param token math operator
     * @return {@link Operator} entity with relevant precedence
     */
    public Operator getOperatorByToken(String token) {
        return _operators.keySet().stream()
                .filter(op -> op.getOperator().equals(token))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }
}
