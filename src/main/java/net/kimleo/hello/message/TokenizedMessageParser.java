package net.kimleo.hello.message;

import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.text.TokenList;
import net.kimleo.hello.text.Tokenizer;

@Component
public class TokenizedMessageParser implements MessageParser<TokenList> {

    private final Tokenizer tokenizer;

    public TokenizedMessageParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    @Override
    public TokenList parse(String message) {
        return tokenizer.tokenize(message);
    }
}
