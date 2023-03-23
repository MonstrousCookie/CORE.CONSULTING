import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dikanskiy.services.configuration.ParserConfig;
import ru.dikanskiy.services.impl.ParserImpl;
import ru.dikanskiy.services.interfaces.Parser;

public class ParserImplTest {
    private final ParserConfig config = Mockito.spy(ParserConfig.class);
    private final Parser parser = new ParserImpl(config);

    @Test
    void parse_success1() {
        final String postfixExpression = "5 4 +";
        String result = parser.parse("5 + 4");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_success2() {
        final String postfixExpression = "1 2 3 * +";
        String result = parser.parse("1 + 2 * 3");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_success3() {
        final String postfixExpression = "10 1 5 * + 12 -";
        String result = parser.parse("10 + 1 * 5 - 12");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_success4() {
        final String postfixExpression = "2 -3 *";
        String result = parser.parse("2 * ( -3 )");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_success5() {
        final String postfixExpression = "5 4 * 3 + 1 -";
        String result = parser.parse("5 * 4 + 3 - 1");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_success6() {
        final String postfixExpression = "5 4 * 3 1 - +";
        String result = parser.parse("5 * 4 + ( 3 - 1 )");

        Assertions.assertEquals(postfixExpression, result);
    }

    @Test
    void parse_unsupportedOperation_failure() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> parser.parse("2 + 5 % 3"));
    }
}
