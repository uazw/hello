package net.kimleo.hello.message;

import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.text.TokenList;
import net.kimleo.hello.text.TokenVisitor;

@Component
public class PrintMessageResolver implements MessageResolver {

    private final MessageParser<TokenList> parser;
    private TokenVisitor visitor;

    public PrintMessageResolver(MessageParser<TokenList> parser, TokenVisitor visitor) {
        this.parser = parser;
        this.visitor = visitor;
    }

    @Override
    public void resolve(String message) {
        parser.parse(message).accept(visitor);
    }
}
