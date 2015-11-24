package net.kimleo.hello.message;

import net.kimleo.hello.strategy.MessageStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        verify(strategy).sendMessage();
    }
}