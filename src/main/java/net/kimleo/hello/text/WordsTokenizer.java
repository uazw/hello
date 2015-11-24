package net.kimleo.hello.text;

import net.kimleo.hello.annotation.Component;

import static java.util.Arrays.asList;

@Component
public class WordsTokenizer implements Tokenizer {

    private TokenListFactory tokenListFactory;

    public WordsTokenizer(TokenListFactory tokenListFactory) {
        this.tokenListFactory = tokenListFactory;
    }

    @Override
    public TokenList tokenize(String text) {
        String[] strings = text.split("\\s");
        return tokenListFactory.create(asList(strings));
    }

}
