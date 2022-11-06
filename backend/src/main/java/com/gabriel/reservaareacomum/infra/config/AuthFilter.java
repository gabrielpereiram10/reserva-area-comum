package com.gabriel.reservaareacomum.infra.config;

import com.gabriel.reservaareacomum.infra.persistence.models.RoleModel;
import com.gabriel.reservaareacomum.infra.providers.JwtEncoder;
import com.gabriel.reservaareacomum.shared.valueObjects.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final JwtEncoder jwtEncoder;

    public AuthFilter(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String auth = request.getHeader(HEADER);
        if (auth != null) {
            String token = auth.replaceAll("Bearer ", "");
            Map<String, Boolean> permissions = jwtEncoder.getClaims(token);
            Authentication credentials = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    getRoles(permissions)
            );
            SecurityContextHolder.getContext().setAuthentication(credentials);
        }

        filterChain.doFilter(request, response);
    }

    private List<RoleModel> getRoles(Map<String, Boolean> permissions) {
        List<RoleModel> roles = new ArrayList<>();
        if (permissions.get("isAdm").equals(Boolean.TRUE)) roles.add(new RoleModel(Role.ADMIN));
        if (permissions.get("isMorador").equals(Boolean.TRUE)) roles.add(new RoleModel(Role.MORADOR));
        return  roles;
    }
}
