package net.kimleo.hello.message;

import net.kimleo.hello.annotation.Component;
import net.kimleo.hello.text.TokenVisitor;
import net.kimleo.hello.validate.Validator;

import java.io.PrintStream;

@Component
public class PrintTokenVisitor implements TokenVisitor {

    private final Validator<String> validator;
    private PrintStream stream = System.out;

    public PrintTokenVisitor(Validator<String> validator) {
        this.validator = validator;
    }

    @Override
    public void visit(String token) {
        if (validator.validate(token))
            stream.println(token);
    }

    public void setStream(PrintStream stream) {
        this.stream = stream;
    }
}
