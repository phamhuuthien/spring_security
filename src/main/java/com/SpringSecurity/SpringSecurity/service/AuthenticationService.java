package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.auth.AuthenticationRequest;
import com.SpringSecurity.SpringSecurity.auth.AuthenticationResponse;
import com.SpringSecurity.SpringSecurity.entity.Role;
import com.SpringSecurity.SpringSecurity.entity.User;
import com.SpringSecurity.SpringSecurity.repository.RoleCustomRepo;
import com.SpringSecurity.SpringSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;
//    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    RoleCustomRepo roleCustomRepo;

    @Autowired
    private JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
//        check data truyen vao co ton tai khong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
//       1 nguoi co nhieu role
        List<String[]> roles = null;
        if(user!=null){
            roles = roleCustomRepo.getRole(user);
        }
        System.out.println(roles);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> set = new HashSet<>();

//       error
//        roles.stream().forEach(c->set.add(new Role(c.get)));
        if (roles != null) {
            for (String[] roleArray : roles) {
                for (String roleName : roleArray) {
                    set.add(new Role(roleName));
                }
            }
        }


        user.setRoles(set);

        set.stream().forEach(i->authorities.add(new SimpleGrantedAuthority((i.getName()))));

        var jwtToken = jwtService.generateToken(user,authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);

        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}
