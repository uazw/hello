package net.kimleo.hello;

import java.io.PrintStream;

public interface StrategyFactory {
    MessageStrategy createStrategy(MessageBody mb, final PrintStream out);
}
