package net.kimleo.hello.strategy;

import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;

@Component
public class DefaultMessageStrategy implements MessageStrategy {

    private final MessageResolver messageResolver;
    private MessageBody messageBody = new MessageBody();

    public DefaultMessageStrategy(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void sendMessage() {
        String message = messageBody.getPayload();
        messageResolver.resolve(message);
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }
}
