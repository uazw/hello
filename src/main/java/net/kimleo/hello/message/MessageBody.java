package net.kimleo.hello.message;

import net.kimleo.hello.strategy.MessageStrategy;

public class MessageBody {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public void configure(String message) {
        payload = message;
    }

    public void send(MessageStrategy ms) {
        ms.sendMessage();
    }
}
