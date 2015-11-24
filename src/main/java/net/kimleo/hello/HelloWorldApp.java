package net.kimleo.hello;


import java.io.PrintStream;

public class HelloWorldApp {

    private final MessageBody messageBody;
    private final StrategyFactory factory;
    private final PrintStream stream;

    public HelloWorldApp(MessageBody messageBody, StrategyFactory factory, PrintStream stream) {
        this.messageBody = messageBody;
        this.factory = factory;
        this.stream = stream;
    }

    public static void main(String[] args) {
        HelloWorldApp helloWorldApp = new HelloWorldApp(new MessageBody(), DefaultFactory.getInstance(), System.out);
        helloWorldApp.say("Hello World!");
    }

    public void say(String message) {
        messageBody.configure(message);
        MessageStrategy strategy = factory.createStrategy(messageBody, stream);
        messageBody.send(strategy);
    }
}
