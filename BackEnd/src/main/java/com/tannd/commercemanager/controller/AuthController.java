package com.tannd.commercemanager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.message.request.LoginRequest;
import com.tannd.commercemanager.message.request.SignupRequest;
import com.tannd.commercemanager.message.response.JwtResponse;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.*;
import com.tannd.commercemanager.repository.AccountRepository;
import com.tannd.commercemanager.repository.EmployeeRepository;
import com.tannd.commercemanager.repository.RoleRepository;
import com.tannd.commercemanager.repository.UserRepository;
import com.tannd.commercemanager.security.jwt.JwtUtils;
import com.tannd.commercemanager.security.services.UserDetailsImpl;
import com.tannd.commercemanager.services.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/signin2")
    public ResponseEntity<?> signInWithoutCapcha(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String email = userDetails.getEmail();
        String role = roles.get(0);
        System.out.println(role);

        if(role.equals("ROLE_ADMIN") || role.equals("ROLE_EMPLOYEE"))
        {
            Employee employee = employeeRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());

            jwtResponse.setMessage("Success");
            jwtResponse.setStatus(200);
            jwtResponse.setToken(jwt);
            jwtResponse.setId(employee.getId());
            jwtResponse.setEmail(employee.getEmail());
            jwtResponse.setPhone(employee.getPhone());
            jwtResponse.setAddress(employee.getAddress());
            jwtResponse.setFirstName(employee.getFirstname());
            jwtResponse.setLastName(employee.getLastname());
            jwtResponse.setGender(employee.getGender());
            jwtResponse.setBirthday(employee.getBirthday());
            jwtResponse.setRole(role);

            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
                    jwtResponse));
        } else {
            User customer = userRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());

            jwtResponse.setMessage("Success");
            jwtResponse.setStatus(200);
            jwtResponse.setToken(jwt);
            jwtResponse.setId(customer.getId());
            jwtResponse.setEmail(customer.getEmail());
            jwtResponse.setPhone(customer.getPhone());
            jwtResponse.setAddress(customer.getAddress());
            jwtResponse.setFirstName(customer.getFirstname());
            jwtResponse.setLastName(customer.getLastname());
            jwtResponse.setGender(customer.getGender());
            jwtResponse.setBirthday(customer.getBirthday());
            jwtResponse.setRole(role);

            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
                    jwtResponse));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String email = userDetails.getEmail();
        String role = roles.get(0);
        System.out.println(role);
        if(role.equals("ROLE_ADMIN") || role.equals("ROLE_EMPLOYEE"))
        {
            Employee employee = employeeRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
            if(!captchaVerified) {
                return ResponseEntity.ok().body(new CustomResponse(400, "Invalid Capcha",
                        null));
            }else {
                jwtResponse.setMessage("Success");
                jwtResponse.setStatus(200);
                jwtResponse.setToken(jwt);
                jwtResponse.setId(employee.getId());
                jwtResponse.setEmail(employee.getEmail());
                jwtResponse.setPhone(employee.getPhone());
                jwtResponse.setAddress(employee.getAddress());
                jwtResponse.setFirstName(employee.getFirstname());
                jwtResponse.setLastName(employee.getLastname());
                jwtResponse.setGender(employee.getGender());
                jwtResponse.setBirthday(employee.getBirthday());
                jwtResponse.setRole(role);
            }
            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
                    jwtResponse));
        } else {
            User customer = userRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
            if(!captchaVerified) {
                return ResponseEntity.ok().body(new CustomResponse(400, "Invalid Capcha",
                        null));
            }else {
                jwtResponse.setMessage("Success");
                jwtResponse.setStatus(200);
                jwtResponse.setToken(jwt);
                jwtResponse.setId(customer.getId());
                jwtResponse.setEmail(customer.getEmail());
                jwtResponse.setPhone(customer.getPhone());
                jwtResponse.setAddress(customer.getAddress());
                jwtResponse.setFirstName(customer.getFirstname());
                jwtResponse.setLastName(customer.getLastname());
                jwtResponse.setGender(customer.getGender());
                jwtResponse.setBirthday(customer.getBirthday());
                jwtResponse.setRole(role);
            }
            return ResponseEntity.ok().body(new CustomResponse(200, "Login successfully",
                    jwtResponse));
        }
    }


    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        System.out.println("Sign Up worked");
        System.out.println(signUpRequest.toString());
        System.out.println("--------------\n");
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new CustomResponse(400, "Email has been already existed!!!",
                    null));
        }
        String role = signUpRequest.getRole();

        // Create new user's account

        if (role == null) {
            throw new RuntimeException("Error: Role is not found");
        } else {
            if (role == "admin" || role == "employee") {
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Account account = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), adminRole);
                accountRepository.save(account);

                Employee employee = new Employee();
                employee.setEmail(signUpRequest.getEmail()).setFirstname(signUpRequest.getFirstName())
                        .setLastname(signUpRequest.getLastName()).setGender(signUpRequest.getGender())
                        .setBirthday(signUpRequest.getBirthday()).setAddress(signUpRequest.getAddress())
                        .setPhone(signUpRequest.getPhone());
                employeeRepository.save(employee);
                return ResponseEntity.ok(new CustomResponse(200, "User registered successfully!",
                        employee));
            } else {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Account account = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), userRole);
                accountRepository.save(account);

                User user = new User();
                user.setEmail(signUpRequest.getEmail()).setFirstname(signUpRequest.getFirstName())
                        .setLastname(signUpRequest.getLastName()).setGender(signUpRequest.getGender())
                        .setBirthday(signUpRequest.getBirthday()).setAddress(signUpRequest.getAddress())
                        .setPhone(signUpRequest.getPhone());
                userRepository.save(user);
                return ResponseEntity.ok(new CustomResponse(200, "User registered successfully!",
                        user));
            }


        }


    }
}
