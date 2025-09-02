package com.Soham.X.Controllers;

import com.Soham.X.Config.JwtProvider;
import com.Soham.X.Entities.User;
import com.Soham.X.Entities.Verification;
import com.Soham.X.Exception.UserException;
import com.Soham.X.Repository.UserRepository;
import com.Soham.X.Response.AuthResponse;
import com.Soham.X.Service.CustomUserDetailsServiceImple;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private CustomUserDetailsServiceImple customUserDetailsServiceImple;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        String email=user.getEmail();
        String password= user.getPassword();
        String fullName= user.getFullName();
        String birthDate=user.getBirthDate();
        User doesEmailExsists=userRepository.findByEmail(email);
        if(doesEmailExsists!=null){
            throw  new UserException("Email Already Used");
        }
        User createdUser=new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVerfication(new Verification());
        User savedUser=userRepository.save(createdUser);

        Authentication authentication= new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=provider.generateToken(authentication);
        AuthResponse res= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(res, HttpStatus.CREATED) ;

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signin(@RequestBody User user){
        String username= user.getEmail();
        String password=user.getPassword();
        Authentication authentication= authenticate(username,password);
        String token=provider.generateToken(authentication);
        AuthResponse res= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED) ;

    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails=customUserDetailsServiceImple.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid User-name");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Username or Password");

        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
