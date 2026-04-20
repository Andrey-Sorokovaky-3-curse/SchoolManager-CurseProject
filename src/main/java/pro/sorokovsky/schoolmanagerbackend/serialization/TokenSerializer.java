package pro.sorokovsky.schoolmanagerbackend.serialization;

import pro.sorokovsky.schoolmanagerbackend.model.Token;

import java.util.Optional;
import java.util.function.Function;

public interface TokenSerializer extends Function<Token, Optional<String>> {

}
