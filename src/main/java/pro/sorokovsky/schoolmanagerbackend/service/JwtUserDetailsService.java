package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.model.Token;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private static final String MESSAGE_CODE = "errors.user.not-found";

    private final UsersService usersService;

    @Override
    public UserDetails loadUserDetails(@NonNull PreAuthenticatedAuthenticationToken preAuthenticatedAuthenticationToken)
            throws UsernameNotFoundException {
        if (preAuthenticatedAuthenticationToken.getPrincipal() instanceof Token token) {
            final var user = usersService.getByLogin(token.login())
                    .orElseThrow(() -> new UsernameNotFoundException(token.login()));
            user.getAuthorities().addAll(token.authorities().stream().map(SimpleGrantedAuthority::new).toList());
            return user;
        }
        throw new UsernameNotFoundException(MESSAGE_CODE);
    }
}
