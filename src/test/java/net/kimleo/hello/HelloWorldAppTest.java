package net.kimleo.hello;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldAppTest {

    public static final String HELLO_WORLD_MESSAGE = "hello world!";
    public static final MessageBody MESSAGE_BODY = new MessageBody();
    private StrategyFactory factory;
    private PrintStream stream;
    private HelloWorldApp app;

    @Before
    public void setUp() throws Exception {
        factory = mock(StrategyFactory.class);
        stream = mock(PrintStream.class);
        app = new HelloWorldApp(MESSAGE_BODY, factory, stream);

        when(factory.createStrategy(MESSAGE_BODY, stream))
                .thenReturn(new DefaultMessageStrategy(MESSAGE_BODY, stream));
    }

    @Test
    public void should_integrated_successfully() throws Exception {
        app.say(HELLO_WORLD_MESSAGE);

        verify(factory).createStrategy(MESSAGE_BODY, stream);
        verify(stream).println(HELLO_WORLD_MESSAGE);
        assertEquals(MESSAGE_BODY.getPayload(), HELLO_WORLD_MESSAGE);
    }

    @Test
    public void should_run_main() throws Exception {
        HelloWorldApp.main(null);
    }
}