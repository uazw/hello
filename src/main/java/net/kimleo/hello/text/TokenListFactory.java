package net.kimleo.hello.text;

import net.kimleo.hello.annotation.Component;

import java.util.List;

@Component
public interface TokenListFactory {
    TokenList create(List<String> tokens);
}
