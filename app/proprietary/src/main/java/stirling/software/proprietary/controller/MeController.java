package stirling.software.proprietary.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt, Authentication authentication) {
        List<String> authorities =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

        return Map.of(
                "subject", jwt.getSubject(),
                "issuer", jwt.getIssuer() != null ? jwt.getIssuer().toString() : null,
                "audience", jwt.getAudience(),
                "issuedAt", toEpoch(jwt.getIssuedAt()),
                "expiresAt", toEpoch(jwt.getExpiresAt()),
                "authorities", authorities,
                "claims", jwt.getClaims() // full map of claims from the token
                );
    }

    private Long toEpoch(Instant i) {
        return i == null ? null : i.getEpochSecond();
    }
}
