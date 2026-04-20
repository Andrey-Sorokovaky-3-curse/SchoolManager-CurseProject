package pro.sorokovsky.schoolmanagerbackend.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pro.sorokovsky.schoolmanagerbackend.contract.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.contract.LoginUser;
import pro.sorokovsky.schoolmanagerbackend.model.User;
import pro.sorokovsky.schoolmanagerbackend.service.AuthorizationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("authorization")
public class AuthorizationController {
    private final AuthorizationService service;

    @PostMapping("register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody CreateUser newUser,
            HttpServletResponse response,
            @NonNull UriComponentsBuilder uriBuilder
    ) {
        service.register(newUser, response);
        return  ResponseEntity.created(uriBuilder.replacePath("profile").build().toUri()).build();
    }

    @PostMapping("login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginUser loginUser, HttpServletResponse response) {
        service.login(loginUser, response);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("profile")
    public ResponseEntity<User> getProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        service.logout(response);
        return ResponseEntity.noContent().build();
    }
}
