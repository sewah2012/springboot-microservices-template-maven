package com.sewah.usersservice.configProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "security.jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtConfig {
    @NotBlank
    private String ttl;
    @NotBlank
    private String secret;
}
