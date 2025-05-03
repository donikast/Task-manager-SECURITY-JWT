package bg.tu_varna.sit.taskmanager16042025.service.impl;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.LoginDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RegisterDto;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Role;
import bg.tu_varna.sit.taskmanager16042025.model.entity.User;
import bg.tu_varna.sit.taskmanager16042025.repository.RoleRepository;
import bg.tu_varna.sit.taskmanager16042025.repository.UserRepository;
import bg.tu_varna.sit.taskmanager16042025.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message register(RegisterDto registerDto) throws TaskApiException {
        if(userRepository.existsByUserName(registerDto.getUserName())) {
            throw new TaskApiException("Username already exists");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        Role role = roleRepository.findByName(registerDto.getRole())
                .orElseThrow(() -> new TaskApiException("Role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return new Message("User registered");
    }

    @Override
    public Message login(HttpServletRequest req, LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(), loginDto.getPassword()));

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);

         HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return new Message("Logged in");
    }

    @Override
    public Message logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return new Message("Logged out");
    }

}
