package pro.sorokovsky.schoolmanagerbackend.deserializer;

import pro.sorokovsky.schoolmanagerbackend.model.Token;

import java.util.Optional;
import java.util.function.Function;

public interface TokenDeserializer extends Function<String, Optional<Token>> {
}
