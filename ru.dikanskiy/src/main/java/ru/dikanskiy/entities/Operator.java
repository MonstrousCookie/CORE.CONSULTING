package ru.dikanskiy.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * Math operator with relevant precedence
 * </p>
 *
 * @author anduser
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public class Operator {
    /**
     * Precedence
     */
    private final int priority;

    /**
     * Math operator
     */
    private final String operator;
}

