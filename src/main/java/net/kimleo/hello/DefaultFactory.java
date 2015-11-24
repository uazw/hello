package net.kimleo.hello;

import java.io.PrintStream;

public class DefaultFactory implements StrategyFactory {

    static DefaultFactory instance;

    private DefaultFactory() {
    }

    public static StrategyFactory getInstance() {
        if (instance == null)
            instance = new DefaultFactory();
        return instance;
    }

    public MessageStrategy createStrategy(final MessageBody mb, final PrintStream out) {
        return new DefaultMessageStrategy(mb, out);
    }

}
