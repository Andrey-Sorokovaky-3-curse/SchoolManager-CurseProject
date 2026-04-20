package pro.sorokovsky.schoolmanagerbackend.factory;

import pro.sorokovsky.schoolmanagerbackend.model.Token;

import java.util.function.Function;

public interface AccessTokenFactory extends Function<Token, Token> {
}
