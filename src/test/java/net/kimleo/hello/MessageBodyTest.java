package net.kimleo.hello;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MessageBodyTest {

    private MessageBody messageBody;

    @Before
    public void setUp() throws Exception {
        messageBody = new MessageBody();
    }

    @Test
    public void should_send_message_use_strategy() throws Exception {
        MessageStrategy strategy = mock(MessageStrategy.class);

        messageBody.send(strategy);

        verify(strategy, atLeastOnce()).sendMessage();
    }
}