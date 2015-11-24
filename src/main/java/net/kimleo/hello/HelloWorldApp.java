package net.kimleo.hello;

import net.kimleo.hello.annotation.Application;
import net.kimleo.hello.context.ApplicationRunner;
import net.kimleo.hello.message.MessageBody;
import net.kimleo.hello.message.MessageResolver;
import net.kimleo.hello.strategy.MessageStrategy;
import net.kimleo.hello.strategy.StrategyFactory;

@Application
public class HelloWorldApp {

    private final StrategyFactory factory;
    private final MessageResolver messageResolver;
    private MessageBody messageBody = new MessageBody();

    public HelloWorldApp(StrategyFactory factory, MessageResolver messageResolver) {
        this.factory = factory;
        this.messageResolver = messageResolver;
    }

    public static void main(String[] args) {
        new ApplicationRunner().run(HelloWorldApp.class);
    }

    public void run() {
        this.say("hello world!");
    }

    public void say(String message) {
        messageBody.configure(message);
        MessageStrategy strategy = factory.createStrategy(messageBody, messageResolver);
        messageBody.send(strategy);
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }
}
