package com.sewah.usersservice.security;

import com.sewah.usersservice.configProperties.JwtConfig;
import com.sewah.usersservice.dtos.LoginDto;
import com.sewah.usersservice.services.userServices.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService userDetailsService;
    private  JwtConfig jwtConfig;

    public AuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtConfig jwtConfig){
        this.authenticationManager = authenticationManager;
        this.userDetailsService=userDetailsService;
        this.jwtConfig=jwtConfig;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            var creds = new ObjectMapper().readValue(request.getInputStream(),LoginDto.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>())
            );
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var username = ((User) authResult.getPrincipal()).getUsername();
        var principal = userDetailsService.loadUserByEmail(username);

        String token = Jwts.builder()
                .setSubject(principal.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() +Long.parseLong(jwtConfig.getTtl())))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
        response.addHeader("token",token);
    }
}
