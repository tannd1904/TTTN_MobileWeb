package com.tannd.commercemanager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tannd.commercemanager.maper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.maper.UserMapper;
import com.tannd.commercemanager.message.request.LoginRequest;
import com.tannd.commercemanager.message.request.SignupRequest;
import com.tannd.commercemanager.message.response.JwtResponse;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.*;
import com.tannd.commercemanager.repository.RoleRepository;
import com.tannd.commercemanager.repository.UserRepository;
import com.tannd.commercemanager.security.jwt.JwtUtils;
import com.tannd.commercemanager.security.services.UserDetailsImpl;
import com.tannd.commercemanager.services.CaptchaService;
import io.swagger.annotations.ApiOperation;
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
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private CaptchaService captchaService;

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

        User user = userRepository.findByEmail(email).get();
        JwtResponse jwtResponse = new JwtResponse();
        boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
        if(!captchaVerified) {
            jwtResponse.setMessage("Invalid captcha");
            jwtResponse.setStatus(400);
        }else {
            jwtResponse.setMessage("Success");
            jwtResponse.setStatus(200);
            jwtResponse.setToken(jwt);
            jwtResponse.setId(user.getId());
            jwtResponse.setEmail(user.getEmail());
            jwtResponse.setPhone(user.getPhone());
            jwtResponse.setAddress(user.getAddress());
            jwtResponse.setName(user.getName());
            jwtResponse.setRole(role);
        }
        return ResponseEntity.ok(jwtResponse);
    }

//    @PostMapping("/signin-2")
//    public ResponseEntity<?> signInWithoutCapcha(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        String email = userDetails.getEmail();
//        String role = roles.get(0);
//        System.out.println(role);
//
//        User user = userRepository.findByEmail(email).get();
//        JwtResponse jwtResponse = new JwtResponse();
//        boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
//        if(!captchaVerified) {
//            jwtResponse.setMessage("Invalid captcha");
//            jwtResponse.setStatus(400);
//        }else {
//            jwtResponse.setMessage("Success");
//            jwtResponse.setStatus(200);
//            jwtResponse.setToken(jwt);
//            jwtResponse.setId(user.getId());
//            jwtResponse.setEmail(user.getEmail());
//            jwtResponse.setPhone(user.getPhone());
//            jwtResponse.setAddress(user.getAddress());
//            jwtResponse.setName(user.getName());
//            jwtResponse.setRole(role);
//        }
//        return ResponseEntity.ok(jwtResponse);
//    }
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser2(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//        User user = userRepository.findByEmail(loginRequest.getEmail()).get();
//        JwtResponse jwtResponse = new JwtResponse();
//        jwtResponse.setMessage("Success");
//        jwtResponse.setStatus(200);
//        jwtResponse.setToken(jwt);
//        jwtResponse.setId(user.getId());
//        jwtResponse.setEmail(user.getEmail());
//        jwtResponse.setPhone(user.getPhone());
//        jwtResponse.setAddress(user.getAddress());
//        jwtResponse.setName(user.getName());
//        jwtResponse.setRole(role);
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                userDetails.getPhone(),
//                userDetails.getAddress(),
//                userDetails.getFirstname(),
//                userDetails.getLastname(),
//                roles));
//    }

//    @ApiOperation(value = "Add an student")
//    @Transactional
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        System.out.println(signUpRequest.toString());
        System.out.println("--------------\n");
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new CustomResponse(400, "Email has been already existed!!!",
                    null));
        }
        // Create new user's account
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setAddress(signUpRequest.getAddress());
        user.setPhone(signUpRequest.getPhone());
        user.setRoles(roles);
        System.out.println(user.toString());
        userRepository.save(user);

        var userDTO = UserMapper.INSTANCE.toDto(user, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(new CustomResponse(200, "User registered successfully!",
                userDTO));

//        switch (role) {
//            case "admin":
//                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                User account = new User(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), adminRole);
//                userRepository.save(account);
//
//                List<Employee> employeeList = employeeRepository.findAll();
//                if(employeeList.size() > 0) {
//                    Integer end = employeeList.size() - 1;
//                    String id = employeeList.get(end).getId();
//                    String IdInt = id.substring(2);
//                    String IdBegin = id.substring(0,2);
//                    Integer newIdInt = Integer.parseInt(IdInt);
//                    newIdInt += 1;
//                    String newIdString = newIdInt.toString();
//                    if(newIdString.length() == 1)
//                    {
//                        newIdString = "0000" + newIdString;
//                    }else if(newIdString.length() == 2)
//                    {
//                        newIdString = "000" + newIdString;
//                    }else if(newIdString.length() == 3)
//                    {
//                        newIdString = "00" + newIdString;
//                    }
//                    else if(newIdString.length() == 4)
//                    {
//                        newIdString = "0" + newIdString;
//                    }
//                    String idNew = IdBegin + newIdString;
//                    Employee employee = new Employee(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    System.out.println(employee.getId());
//                    employeeRepository.save(employee);
//                    return ResponseEntity.ok(new MessageResponse("Account admin registered successfully!"));
//                }else {
//                    Employee employee = new Employee("NV00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    System.out.println(employee.getId());
//                    employeeRepository.save(employee);
//                    return ResponseEntity.ok(new MessageResponse("Account admin registered successfully!"));
//                }
//            case "employee":
//                Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                Account employeeAccount = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), employeeRole);
//                accountRepository.save(employeeAccount);
//                List<Employee> employeeLists = employeeRepository.findAll();
//                if(employeeLists.size() > 0) {
//                    Integer end = employeeLists.size() - 1;
//                    String id = employeeLists.get(end).getId();
//                    String IdInt = id.substring(2);
//                    String IdBegin = id.substring(0,2);
//                    Integer newIdInt = Integer.parseInt(IdInt);
//                    newIdInt += 1;
//                    String newIdString = newIdInt.toString();
//                    if(newIdString.length() == 1)
//                    {
//                        newIdString = "0000" + newIdString;
//                    }else if(newIdString.length() == 2)
//                    {
//                        newIdString = "000" + newIdString;
//                    }else if(newIdString.length() == 3)
//                    {
//                        newIdString = "00" + newIdString;
//                    }
//                    else if(newIdString.length() == 4)
//                    {
//                        newIdString = "0" + newIdString;
//                    }
//                    String idNew = IdBegin + newIdString;
//                    Employee employee = new Employee(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    employeeRepository.save(employee);
//                    return ResponseEntity.ok(new MessageResponse("Account employee registered successfully!"));
//                }else {
//                    Employee employee = new Employee("NV00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    employeeRepository.save(employee);
//                    return ResponseEntity.ok(new MessageResponse("Account employee registered successfully!"));
//                }
//            default:
//                Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                Account customerAccount = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()), customerRole);
//                accountRepository.save(customerAccount);
//                List<Customer> customerList = customerRepository.findAll();
//                if(customerList.size() > 0) {
//                    Integer end = customerList.size() - 1;
//                    String id = customerList.get(end).getId();
//                    String IdInt = id.substring(2);
//                    String IdBegin = id.substring(0,2);
//                    Integer newIdInt = Integer.parseInt(IdInt);
//                    newIdInt += 1;
//                    String newIdString = newIdInt.toString();
//                    if(newIdString.length() == 1)
//                    {
//                        newIdString = "0000" + newIdString;
//                    }else if(newIdString.length() == 2)
//                    {
//                        newIdString = "000" + newIdString;
//                    }else if(newIdString.length() == 3)
//                    {
//                        newIdString = "00" + newIdString;
//                    }
//                    else if(newIdString.length() == 4)
//                    {
//                        newIdString = "0" + newIdString;
//                    }
//                    String idNew = IdBegin + newIdString;
//                    Customer customer = new Customer(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    customerRepository.save(customer);
//                    return ResponseEntity.ok(new MessageResponse("Account customer registered successfully!"));
//                }else {
//                    Customer customer = new Customer("KH00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail());
//                    customerRepository.save(customer);
//                    return ResponseEntity.ok(new MessageResponse("Account customer registered successfully!"));
//                }
//        }
    }
}
