package net.kimleo.hello.validate;


import net.kimleo.hello.annotation.Component;

@Component
public interface Validator<T> {
    boolean validate(T t);
}
