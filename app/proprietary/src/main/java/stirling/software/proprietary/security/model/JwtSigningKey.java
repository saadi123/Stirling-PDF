package stirling.software.proprietary.security.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtSigningKey implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @JsonAlias("kid")
    @ToString.Include
    private String keyId;

    @JsonAlias("n")
    private String key;

    @JsonAlias("alg")
    private String algorithm;

    @ToString.Include private LocalDateTime createdAt;

    public JwtSigningKey(String keyId, String key) {
        this.keyId = keyId;
        this.key = key;
        this.algorithm = "RS256";
        this.createdAt = LocalDateTime.now();
    }
}
