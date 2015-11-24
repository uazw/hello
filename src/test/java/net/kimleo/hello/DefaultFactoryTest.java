package net.kimleo.hello;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DefaultFactoryTest {

    public static final String HELLO_WORLD_MESSAGE = "hello world!";
    private StrategyFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = DefaultFactory.getInstance();
    }

    @Test
    public void should_get_same_instance() throws Exception {
        assertThat(factory, is(DefaultFactory.getInstance()));
    }

    @Test
    public void should_create_message_strategy() throws Exception {
        MessageStrategy strategy = factory.createStrategy(mock(MessageBody.class), mock(PrintStream.class));

        assertNotNull(strategy);
    }

    @Test
    public void should_use_message_payload() throws Exception {
        MessageBody message = mock(MessageBody.class);
        PrintStream out = mock(PrintStream.class);
        when(message.getPayload()).thenReturn(HELLO_WORLD_MESSAGE);

        MessageStrategy strategy = factory.createStrategy(message, out);
        strategy.sendMessage();

        verify(message, atLeastOnce()).getPayload();
    }

    @Test
    public void should_print_message_to_stream() throws Exception {
        MessageBody message = mock(MessageBody.class);
        PrintStream out = mock(PrintStream.class);
        when(message.getPayload()).thenReturn(HELLO_WORLD_MESSAGE);

        MessageStrategy strategy = factory.createStrategy(message, out);
        strategy.sendMessage();

        verify(out, atLeastOnce()).println(HELLO_WORLD_MESSAGE);
    }
}