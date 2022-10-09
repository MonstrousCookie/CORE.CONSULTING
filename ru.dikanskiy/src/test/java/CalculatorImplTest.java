import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import ru.dikanskiy.services.configuration.ParserConfig;
import ru.dikanskiy.services.impl.CalculatorImpl;
import ru.dikanskiy.services.impl.ParserImpl;
import ru.dikanskiy.services.interfaces.Calculator;
import ru.dikanskiy.services.interfaces.Parser;

public class CalculatorImplTest {
    private final ParserConfig config = Mockito.spy(ParserConfig.class);
    private final Parser parser = new ParserImpl(config);
    private final Calculator calculator = new CalculatorImpl(parser);

    @Test
    void calculate_success7() {
        Assertions.assertEquals(-6.0, calculator.calculate("2 * ( -3 )"));
    }

    @Test
    void calculate_success1() {
        Assertions.assertEquals(34.0, calculator.calculate("6 * 5 + 4"));
    }

    @Test
    void calculate_success2() {
        Assertions.assertEquals(-4.0, calculator.calculate("3 * 1 + 2 - 9"));
    }

    @Test
    void calculate_success3() {
        Assertions.assertEquals(3.0, calculator.calculate("10 + 1 * 5 - 12"));
    }

    @Test
    void calculate_success4() {
        Assertions.assertEquals(43.0, calculator.calculate("( 10 + 1 ) * 5 - 12 "));
    }

    @Test
    void calculate_success5() {
        Assertions.assertEquals(22.0, calculator.calculate("5 * 4 + 3 - 1"));
    }

    @Test
    void calculate_success6() {
        Assertions.assertEquals(34.0, calculator.calculate("5 * ( 4 + 3 ) - 1"));
    }
}
