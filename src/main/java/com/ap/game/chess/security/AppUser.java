package com.ap.game.chess.security;

import com.ap.game.chess.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@EqualsAndHashCode
public class AppUser implements UserDetails {

    private Long id;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static AppUser create(User user){
        List<GrantedAuthority> authorityList =
                user.getRoles()
                        .stream()
                        .map((role) -> new SimpleGrantedAuthority(role.getTitle()))
                        .collect(Collectors.toList());

        return AppUser.builder()
                .id(user.getId())
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
