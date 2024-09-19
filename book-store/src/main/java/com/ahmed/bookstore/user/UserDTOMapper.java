package com.ahmed.bookstore.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserDTOMapper implements Function<User,UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getRole().getAuthorities().stream().map(
                        SimpleGrantedAuthority::getAuthority
                ).filter(r->!r.startsWith("ROLE_"))
                        .collect(Collectors.toList())
        );
    }
}
