package pro.sorokovsky.schoolmanagerbackend.factory;

import pro.sorokovsky.schoolmanagerbackend.model.Token;
import pro.sorokovsky.schoolmanagerbackend.model.UserModel;

import java.util.function.Function;

public interface RefreshTokenFactory extends Function<UserModel, Token> {
}
