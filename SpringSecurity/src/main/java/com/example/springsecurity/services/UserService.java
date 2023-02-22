package com.example.springsecurity.services;

import com.example.springsecurity.models.dtos.UserRegisterDTO;
import com.example.springsecurity.models.entities.User;
import com.example.springsecurity.models.entities.UserRole;
import com.example.springsecurity.models.enums.Role;
import com.example.springsecurity.repositories.UserRepository;
import com.example.springsecurity.repositories.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService appUserDetailsService;
    private String adminPass;

    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService appUserDetailsService)
                      // @Value("${app.default.admin.password}") String adminPass)
    {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
    //    this.adminPass = adminPass;
    }

    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole().setRole(Role.ADMIN);
            UserRole moderatorRole = new UserRole().setRole(Role.MODERATOR);

            adminRole = userRoleRepository.save(adminRole);
            moderatorRole = userRoleRepository.save(moderatorRole);

            initAdmin(List.of(adminRole, moderatorRole));
            initModerator(List.of(moderatorRole));
            initUser(List.of());
        }
    }

    private void initAdmin(List<UserRole> roles) {
        User admin = new User().
                setRoles(roles).
                setFirstName("Admin").
                setLastName("Adminov").
                setUsername("admin@example.com").
                setPassword(passwordEncoder.encode("admin"));

        userRepository.save(admin);
    }

    private void initModerator(List<UserRole> roles) {
        User moderator = new User().
                setRoles(roles).
                setFirstName("Moderator").
                setLastName("Moderatorov").
                setUsername("moderator@example.com").
                setPassword(passwordEncoder.encode("moderator"));

        userRepository.save(moderator);
    }

    private void initUser(List<UserRole> roles) {
        User user = new User().
                setRoles(roles).
                setFirstName("User").
                setLastName("Userov").
                setUsername("user@example.com").
                setPassword(passwordEncoder.encode("user"));

        userRepository.save(user);
    }



    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        User newUser =
                new User().
                        setUsername(userRegisterDTO.getUsername()).
                        setFirstName(userRegisterDTO.getFirstName()).
                        setLastName(userRegisterDTO.getLastName()).
                        setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(newUser);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(newUser.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
