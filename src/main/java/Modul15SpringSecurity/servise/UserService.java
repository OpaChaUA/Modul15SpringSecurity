package Modul15SpringSecurity.servise;

import Modul15SpringSecurity.entity.User;
import Modul15SpringSecurity.enums.Role;
import Modul15SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public User addNewUser (User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.ROLE_USER.name());
        user.setEnabled(1);
        return userRepository.save(user);
    }
    public int getIdByUsername(String userName){
        return userRepository.findByUsername(userName).getId();
    }
    public boolean isUserExit(String userName){
        return Objects.nonNull(userRepository.findByUsername(userName));
    }
}
