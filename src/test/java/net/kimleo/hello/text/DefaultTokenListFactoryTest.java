package net.kimleo.hello.text;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DefaultTokenListFactoryTest {

    private TokenListFactory factory;
    private TokenVisitor visitor;

    @Before
    public void setUp() throws Exception {
        factory = DefaultTokenListFactory.getInstance();
        visitor = mock(TokenVisitor.class);
    }

    @Test
    public void should_create_new_token_list() throws Exception {
        TokenList tokens = factory.create(asList("hello", "world"));

        assertThat(tokens, is(TokenList.class));
    }

    @Test
    public void should_create_token_list_accepts_visitors() throws Exception {
        TokenList tokens = factory.create(asList("hello", "world"));

        tokens.accept(visitor);

        verify(visitor).visit("hello");
        verify(visitor).visit("world");
    }

    @Test
    public void should_return_token_list_iterator() throws Exception {
        List<String> strings = mock(List.class);
        Iterator iterator = mock(Iterator.class);
        when(strings.iterator()).thenReturn(iterator);

        TokenList tokens = factory.create(strings);

        assertThat(tokens.iterator(), is(iterator));
    }
}