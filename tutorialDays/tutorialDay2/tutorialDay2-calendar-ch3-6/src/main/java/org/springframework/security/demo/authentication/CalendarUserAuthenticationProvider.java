package org.springframework.security.demo.authentication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.demo.core.authority.CalendarUserAuthorityUtils;
import org.springframework.security.demo.domain.CalendarUser;
import org.springframework.security.demo.service.CalendarService;
import org.springframework.stereotype.Component;
 

/**
 * A Spring Security {@link AuthenticationProvider} that uses our {@link CalendarService} for authentication. Compare
 * this to our {@link CalendarUserDetailsService} which is called by Spring Security's {@link DaoAuthenticationProvider}.
 *
 * @author Rob Winch
 * @see CalendarUserDetailsService
 */
@Component
public class CalendarUserAuthenticationProvider implements AuthenticationProvider {
    private final CalendarService calendarService;

    @Autowired
    public CalendarUserAuthenticationProvider(CalendarService calendarService) {
        if (calendarService == null) {
            throw new IllegalArgumentException("calendarService cannot be null");
        }
        this.calendarService = calendarService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DomainUsernamePasswordAuthenticationToken token = (DomainUsernamePasswordAuthenticationToken) authentication;
        String userName = token.getName();
        String domain = token.getDomain();
        String email = userName + "@" + domain;
        CalendarUser user = email == null ? null : calendarService.findUserByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }
        String password = user.getPassword();
        if(!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils.createAuthorities(user);
        return new DomainUsernamePasswordAuthenticationToken(user, password, domain, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return DomainUsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}