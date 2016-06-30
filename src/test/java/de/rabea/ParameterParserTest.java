package de.rabea;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParameterParserTest {

    @Test
    public void returnsParametersInHumanReadableForm() {
        ParameterParser parser = new ParameterParser();
        String requestUri = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String parsedParams = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?\n\nvariable_2 = stuff\n\n";
        assertEquals(parsedParams, parser.parse(requestUri));
    }

    @Test
    public void splitsUriIntoTwoParameterStrings() {
        ParameterParser parser = new ParameterParser();
        String requestUri = "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String var1 = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        String var2 = "variable_2=stuff";
        List<String> params = parser.split(requestUri);
        assertEquals(var1, params.get(0));
        assertEquals(var2, params.get(1));
    }

    @Test(expected = ParameterParser.UrlDecodingException.class)
    public void throwsException() throws UnsupportedEncodingException {
        ParameterParserWithException parser = new ParameterParserWithException();
        parser.parse("something");
    }

    private class ParameterParserWithException extends ParameterParser {

        @Override
        public String decode(String parameter) throws UnsupportedEncodingException {
            throw new UnsupportedEncodingException();
        }
    }
}