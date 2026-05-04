package pro.sorokovsky.schoolmanagerbackend.storage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pro.sorokovsky.schoolmanagerbackend.entity.Token;

import java.util.Optional;

public interface TokenStorage {
    Optional<Token> getToken(HttpServletRequest request);
    void setToken(Token token, HttpServletResponse response);
    void clearToken(HttpServletResponse response);
}
