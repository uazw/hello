package net.kimleo.hello.text;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WordsTokenizerTest {

    private Tokenizer tokenizer;
    private TokenListFactory tokenListFactory;

    @Before
    public void setUp() throws Exception {
        tokenListFactory = mock(TokenListFactory.class);


        tokenizer = new WordsTokenizer(tokenListFactory);
    }

    @Test
    public void should_tokenize_single_word() throws Exception {
        tokenizer.tokenize("hello");

        verify(tokenListFactory).create(singletonList("hello"));
    }

    @Test
    public void should_tokenize_multiple_words() throws Exception {
        tokenizer.tokenize("hello world");

        verify(tokenListFactory).create(asList("hello", "world"));
    }
}