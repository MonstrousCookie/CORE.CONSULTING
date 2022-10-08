package ru.dikanskiy.services.interfaces;

import ru.dikanskiy.services.configuration.ParserConfig;

/**
 * <p>
 * Parser interface
 * </p>
 *
 * @author anduser
 * @version 1.0
 */
public interface Parser {
    /**
     * <p>
     * {@code parse} implements "Shunting yard algorithm".
     * </p>
     *
     * @param infix input math expression as a String
     * @return postfix math expression as a String
     */
    String parse(String infix);

    /**
     * @return current configuration of parser
     */
    ParserConfig getConfiguration();
}
