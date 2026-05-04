package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.entity.Token;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private static final String MESSAGE_CODE = "errors.user.not-found";

    private final UsersService usersService;

    @Override
    public UserDetails loadUserDetails(@NonNull PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken)
            throws UsernameNotFoundException {
        if (preAuthenticatedAuthenticationToken.getPrincipal() instanceof Token token) {
            return usersService.getByLogin(token.subject())
                    .orElseThrow(() -> new UsernameNotFoundException(token.subject()));
        }
        throw new UsernameNotFoundException(MESSAGE_CODE);
    }
}
