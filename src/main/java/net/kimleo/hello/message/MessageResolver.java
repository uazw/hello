package net.kimleo.hello.message;

import net.kimleo.hello.annotation.Component;

@Component
public interface MessageResolver {
    void resolve(String message);
}
