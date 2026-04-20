package pro.sorokovsky.schoolmanagerbackend.factory;

import pro.sorokovsky.schoolmanagerbackend.model.Token;
import pro.sorokovsky.schoolmanagerbackend.model.User;

import java.util.function.Function;

public interface RefreshTokenFactory extends Function<User, Token> {
}
