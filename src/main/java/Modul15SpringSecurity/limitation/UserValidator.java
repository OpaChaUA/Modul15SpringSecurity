package Modul15SpringSecurity.limitation;

import Modul15SpringSecurity.entity.User;
import Modul15SpringSecurity.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserService userService;
    private final Pattern usernamePattern = Pattern.compile(".{5,50}");
    private final Pattern passwordPattern = Pattern.compile(".{8,100}");

    public List<String> validateUser(User user) {
        List<String> errorsList = new ArrayList<>();

        if (user.getUsername().isBlank()) {
            errorsList.add("Username is required.");
        } else if (!usernamePattern.matcher(user.getUsername()).matches()) {
            errorsList.add("Username length must be between 5 and 50 characters.");
        } else if (userService.isUserExit(user.getUsername())) {
            errorsList.add("Username already exists.");
        }

        if (user.getPassword().isBlank()) {
            errorsList.add("Password is required.");
        } else if (!passwordPattern.matcher(user.getPassword()).matches()) {
            errorsList.add("Password length must be between 8 and 100 characters.");
        }

        return errorsList;
    }
}
