package net.kimleo.hello.text;

import net.kimleo.hello.annotation.Component;

@Component
public interface TokenList extends Iterable<String> {
    void accept(TokenVisitor visitor);
}
