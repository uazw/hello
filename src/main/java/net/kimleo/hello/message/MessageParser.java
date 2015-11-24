package net.kimleo.hello.message;

import net.kimleo.hello.annotation.Component;

@Component
public interface MessageParser<T> {
    T parse(String messageBody);
}
