package ru.dikanskiy.services.interfaces;

/**
 * <p>
 * Calculator interface
 * </p>
 *
 * @author anduser
 * @version 1.0
 */
public interface Calculator {
    /**
     * <p>
     * {@code calculate} implements "Evaluation of Postfix Expression" method.
     * </p>
     *
     * @param infix input math expression as a String
     * @return numeric result
     */
    double calculate(String infix);
}
