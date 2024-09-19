package com.ahmed.bookstore.user;

import java.util.List;

public record UserDTO(String firstName, String lastName,
                      List<String> role) {
}
