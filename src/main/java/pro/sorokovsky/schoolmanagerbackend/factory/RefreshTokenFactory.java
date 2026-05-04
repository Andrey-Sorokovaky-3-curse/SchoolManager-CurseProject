package pro.sorokovsky.schoolmanagerbackend.factory;

import pro.sorokovsky.schoolmanagerbackend.entity.Token;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;

import java.util.function.Function;

public interface RefreshTokenFactory extends Function<UserEntity, Token> {
}
