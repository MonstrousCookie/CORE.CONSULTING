import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import ru.dikanskiy.entities.Operator;
import ru.dikanskiy.services.configuration.ParserConfig;
import ru.dikanskiy.services.impl.CalculatorImpl;
import ru.dikanskiy.services.impl.ParserImpl;
import ru.dikanskiy.services.interfaces.Calculator;
import ru.dikanskiy.services.interfaces.Parser;

import java.util.function.BiFunction;

public class CalculatorImplTest {
    private final ParserConfig config = Mockito.spy(ParserConfig.class);
    private Parser parser = new ParserImpl(config);
    private Calculator calculator = new CalculatorImpl(parser);

    @Test
    void calculate_success1() {
        Assertions.assertEquals(34.0, calculator.calculate("6 * 5 + 4"));
    }

    @Test
    void calculate_success2() {
        Assertions.assertEquals(-4.0, calculator.calculate("3 * 1 + 2.0 - 9"));
    }

    @Test
    void calculate_success3() {
        Assertions.assertEquals(3.0, calculator.calculate("10.0 + 1.0 * 5.0 - 12.0"));
    }

    @Test
    void calculate_success4() {
        Assertions.assertEquals(43.0, calculator.calculate("( 10 + 1 ) * 5.0 - 12 "));
    }

    @Test
    void calculate_success5() {
        Assertions.assertEquals(22.0, calculator.calculate("5 * 4 + 3 - 1"));
    }

    @Test
    void calculate_success6() {
        Assertions.assertEquals(34.0, calculator.calculate("5 * ( 4 + 3 ) - 1"));
    }

    @Test
    void calculate_success7() {
        Assertions.assertEquals(-6.0, calculator.calculate("2 * ( -3.0 )"));
    }

    @Test
    void calculate_unsupportedOperation_failure() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.calculate("2 * 5 % 4"));
    }

    @Test
    void calculate_addNewOperator_success() {
        BiFunction<Double, Double, Double> pow = this::pow;
        ParserConfig config = new ParserConfig();
        config.addOperator(new Operator(3, "^"), pow);
        parser = new ParserImpl(config);
        calculator = new CalculatorImpl(parser);

        Assertions.assertEquals(-44.0, calculator.calculate("5 - ( 4.0 + 3.0 ) ^ 2"));
    }

    private double pow(double n, double base) {
        if (base == 0) return 1;
        return n * pow(n, base - 1);
    }
}