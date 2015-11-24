package net.kimleo.hello.message;

import net.kimleo.hello.text.Tokenizer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TokenizedMessageParserTest {

    public static final String HELLO_WORLD_MESSAGE = "hello world";
    private TokenizedMessageParser parser;
    private Tokenizer tokenizer;

    @Before
    public void setUp() throws Exception {
        tokenizer = mock(Tokenizer.class);
        parser = new TokenizedMessageParser(tokenizer);
    }

    @Test
    public void should_tokenize_message() throws Exception {
        parser.parse(HELLO_WORLD_MESSAGE);

        verify(tokenizer).tokenize(HELLO_WORLD_MESSAGE);
    }
}