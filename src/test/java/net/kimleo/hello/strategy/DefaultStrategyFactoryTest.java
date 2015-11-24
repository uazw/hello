package net.kimleo.hello.strategy;

import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultStrategyFactoryTest {

    public static final String HELLO_WORLD_MESSAGE = "hello world!";
    private StrategyFactory factory;
    private MessageBody messageBody;
    private MessageResolver messageResolver;

    @Before
    public void setUp() throws Exception {
        factory = DefaultStrategyFactory.getInstance();
        messageBody = mock(MessageBody.class);
        messageResolver = mock(MessageResolver.class);
        when(messageBody.getPayload()).thenReturn(HELLO_WORLD_MESSAGE);
    }

    @Test
    public void should_get_same_instance() throws Exception {
        assertThat(factory, is(DefaultStrategyFactory.getInstance()));
    }

    @Test
    public void should_create_message_strategy() throws Exception {
        MessageStrategy strategy = factory.createStrategy(messageBody, messageResolver);

        assertNotNull(strategy);
    }

}