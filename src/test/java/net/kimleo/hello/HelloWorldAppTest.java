package net.kimleo.hello;

import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;
import net.kimleo.hello.message.PrintMessageResolver;
import net.kimleo.hello.strategy.DefaultMessageStrategy;
import net.kimleo.hello.strategy.StrategyFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldAppTest {

    public static final String HELLO_WORLD_MESSAGE = "hello world!";
    public static final MessageBody MESSAGE_BODY = new MessageBody();
    private StrategyFactory factory;
    private HelloWorldApp app;
    private MessageResolver printMessageResolver;

    @Before
    public void setUp() throws Exception {
        factory = mock(StrategyFactory.class);
        printMessageResolver = mock(PrintMessageResolver.class);
        app = new HelloWorldApp(factory, printMessageResolver);
        app.setMessageBody(MESSAGE_BODY);
        DefaultMessageStrategy strategy = new DefaultMessageStrategy(printMessageResolver);
        strategy.setMessageBody(MESSAGE_BODY);
        when(factory.createStrategy(MESSAGE_BODY, printMessageResolver))
                .thenReturn(strategy);
    }

    @Test
    public void should_integrated_successfully() throws Exception {
        app.say(HELLO_WORLD_MESSAGE);

        verify(factory).createStrategy(MESSAGE_BODY, printMessageResolver);
        verify(printMessageResolver).resolve(HELLO_WORLD_MESSAGE);
        assertEquals(MESSAGE_BODY.getPayload(), HELLO_WORLD_MESSAGE);
    }

    @Test
    public void should_run_main() throws Exception {
        HelloWorldApp.main(null);
    }
}