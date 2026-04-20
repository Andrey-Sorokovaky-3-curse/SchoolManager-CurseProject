package pro.sorokovsky.schoolmanagerbackend.configuration;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.sorokovsky.schoolmanagerbackend.deserializer.JweTokenDeserializer;
import pro.sorokovsky.schoolmanagerbackend.deserializer.JwsTokenDeserializer;
import pro.sorokovsky.schoolmanagerbackend.factory.DefaultAccessTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.factory.DefaultRefreshTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.serialization.JweTokenSerializer;
import pro.sorokovsky.schoolmanagerbackend.serialization.JwsTokenSerializer;
import pro.sorokovsky.schoolmanagerbackend.storage.BearerTokenStorage;
import pro.sorokovsky.schoolmanagerbackend.storage.CookieTokenStorage;

import java.text.ParseException;
import java.time.Duration;

@Configuration
public class TokensConfiguration {
    @Bean
    public JweTokenSerializer jweTokenSerializer(@Value("${tokens.refresh.secret-key}") String refreshSecretKey)
            throws ParseException, KeyLengthException {
        return JweTokenSerializer
                .builder()
                .algorithm(JWEAlgorithm.DIR)
                .encrypter(new DirectEncrypter(OctetSequenceKey.parse(refreshSecretKey)))
                .method(EncryptionMethod.A192GCM)
                .build();
    }

    @Bean
    public JwsTokenSerializer jwsTokenSerializer(@Value("${tokens.access.secret-key}") String accessSecretKey)
            throws ParseException, KeyLengthException {
        return JwsTokenSerializer
                .builder()
                .algorithm(JWSAlgorithm.HS256)
                .signer(new MACSigner(OctetSequenceKey.parse(accessSecretKey)))
                .build();
    }

    @Bean
    public JweTokenDeserializer jweTokenDeserializer(@Value("${tokens.refresh.secret-key}") String refreshSecretKey)
            throws ParseException, KeyLengthException {
        return JweTokenDeserializer
                .builder()
                .decrypter(new DirectDecrypter(OctetSequenceKey.parse(refreshSecretKey)))
                .build();
    }

    @Bean
    public JwsTokenDeserializer jwsTokenDeserializer(@Value("${tokens.access.secret-key}") String accessSecretKey)
            throws ParseException, JOSEException {
        return JwsTokenDeserializer
                .builder()
                .verifier(new MACVerifier(OctetSequenceKey.parse(accessSecretKey)))
                .build();
    }

    @Bean
    public DefaultAccessTokenFactory defaultAccessTokenFactory(@Value("${tokens.access.lifetime}") Duration lifetime) {
        return DefaultAccessTokenFactory
                .builder()
                .lifetime(lifetime)
                .build();
    }

    @Bean
    public DefaultRefreshTokenFactory defaultRefreshTokenFactory(@Value("${tokens.refresh.lifetime}") Duration lifetime) {
        return DefaultRefreshTokenFactory
                .builder()
                .lifetime(lifetime)
                .build();
    }

    @Bean
    public BearerTokenStorage bearerTokenStorage(JwsTokenDeserializer deserializer, JwsTokenSerializer serializer) {
        return BearerTokenStorage
                .builder()
                .deserializer(deserializer)
                .serializer(serializer)
                .build();
    }

    @Bean
    public CookieTokenStorage cookieTokenStorage(JweTokenDeserializer deserializer, JweTokenSerializer serializer) {
        return CookieTokenStorage
                .builder()
                .cookieName("__Host-refresh-token")
                .deserializer(deserializer)
                .serializer(serializer)
                .build();
    }
}
