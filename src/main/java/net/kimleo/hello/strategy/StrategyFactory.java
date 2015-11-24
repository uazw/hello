package net.kimleo.hello.strategy;

import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;

@Component
public interface StrategyFactory {
    MessageStrategy createStrategy(MessageBody mb, MessageResolver messageResolver);
}
