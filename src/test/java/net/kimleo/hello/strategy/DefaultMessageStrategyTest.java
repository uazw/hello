package net.kimleo.hello.strategy;

import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class DefaultMessageStrategyTest {

    private static final String HELLO_WORLD_MESSAGE = "hello world";
    private MessageBody messageBody;
    private MessageResolver messageResolver;
    private DefaultMessageStrategy strategy;

    @Before
    public void setUp() throws Exception {
        messageBody = mock(MessageBody.class);
        messageResolver = mock(MessageResolver.class);
        when(messageBody.getPayload()).thenReturn(HELLO_WORLD_MESSAGE);
        strategy = new DefaultMessageStrategy(messageResolver);
        strategy.setMessageBody(messageBody);
    }

    @Test
    public void should_use_message_payload() throws Exception {
        strategy.sendMessage();

        verify(messageBody).getPayload();
    }

    @Test
    public void should_print_message_to_stream() throws Exception {
        strategy.sendMessage();

        verify(messageResolver).resolve(HELLO_WORLD_MESSAGE);
    }


}