package com.ahmed.bookstore.user;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {

        userService.changePassword(request, connectedUser);

        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetailsr) {
        return ResponseEntity.ok(userService.getUser(userDetailsr ));
    }


}
