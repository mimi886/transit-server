package com.transit.rest.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.transit.rest.config.JwtService;
import com.transit.rest.model.User;
import com.transit.rest.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final String AUTH_TOKEN_HEADER_NAME="API_KEY";
    private static final String AUTH_TOKEN="zCoT77eFcvZu7uNnNJ99ihIBs3X81WB6";


    private final PasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user=userRepository.findByEmail(request.getEmail())
                    .orElseThrow();

        var jwtToken=jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .success(true)
                .role(user.getRole())
                .fullname(user.getFullname())
                .build();
    }

    public RegisterResponse register(RegisterRequest request) {
      long millis=System.currentTimeMillis();  
      java.sql.Date date = new java.sql.Date(millis);  
        var user=User.builder()
                  .fullname(request.getFullname())
                  .email(request.getEmail())
                  .password(passwordEncoder.encode(request.getPassword()))
                  .phonenumber(request.getPhonenumber())
                  .role(request.getRole())
                  .verified(false)
                  .passwordexpired(true)
                  .createdat(date)
                  .build();
                  userRepository.save(user);
                 
                  
                  
        return RegisterResponse.builder()
                    .success(true)
                    .build();
    }

      public static boolean getApikeyAuthentication(HttpServletRequest request){
      String apikey=request.getHeader(AUTH_TOKEN_HEADER_NAME);
      if(apikey==null || !apikey.equals(AUTH_TOKEN)){
        return false;
      }
      return true;
    }

    public Integer getUserId(String email){
      Optional<User> user=userRepository.findByEmail(email);
      if(user.isPresent()){
        return user.get().getId();
      }
      return null;
    }

    public List<User> allUser() {
        List<User> users= new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // public ResetResponse resetPassord(ResetRequest request) {
    //     ResetResponse response=new ResetResponse();
    //     long millis=System.currentTimeMillis();  
    //     java.sql.Date date = new java.sql.Date(millis); 

    //     Optional<User> optionalUser=userRepository.findByEmail(request.getEmail());
    //     if(optionalUser.isPresent()){
    //       User user=optionalUser.get();
    //       user.setPassword(passwordEncoder.encode(request.getPassword()));
    //       user.setPasswordreset(date);
    //       user.setPasswordexpired(false);
    //       userRepository.save(user);
    //       response.setSuccess(true);
    //       return response;
    //     }else{
    //       response.setSuccess(false);
    //       return response;
    //     }
        
    // }

}
