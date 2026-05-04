package pro.sorokovsky.schoolmanagerbackend.serialization;

import com.nimbusds.jwt.JWTClaimsSet;
import org.jspecify.annotations.NonNull;
import pro.sorokovsky.schoolmanagerbackend.entity.Token;

import java.util.Date;

public abstract class JwtTokenSerializer implements TokenSerializer{
    protected JWTClaimsSet getClaimsFromToken(@NonNull Token token) {
        return new JWTClaimsSet.Builder()
                .jwtID(token.id().toString())
                .issueTime(Date.from(token.createdAt()))
                .subject(token.subject())
                .expirationTime(Date.from(token.expiresAt()))
                .claim("authorities", token.authorities())
                .build();
    }
}
