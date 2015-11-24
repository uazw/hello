package net.kimleo.hello.text;

import net.kimleo.hello.annotation.Component;

@FunctionalInterface
@Component
public interface TokenVisitor {
    void visit(String token);
}
