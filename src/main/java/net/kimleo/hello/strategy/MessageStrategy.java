package net.kimleo.hello.strategy;

import net.kimleo.hello.annotation.Component;

@Component
public interface MessageStrategy {
    void sendMessage();
}
