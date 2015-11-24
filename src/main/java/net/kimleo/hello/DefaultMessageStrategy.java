package net.kimleo.hello;

import java.io.PrintStream;

public class DefaultMessageStrategy implements MessageStrategy {

    private final PrintStream stream;
    private final MessageBody messageBody;

    public DefaultMessageStrategy(MessageBody messageBody, PrintStream stream) {
        this.stream = stream;
        this.messageBody = messageBody;
    }

    public void sendMessage() {
        Object obj = messageBody.getPayload();
        stream.println((String) obj);
    }
}
