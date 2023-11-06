package com.example.library.service.ImplService;

import com.example.library.dto.ResultDto;
import com.example.library.security.JwtTokenUtil;
import com.example.library.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class TokenServiceImpl implements TokenService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${access.token.expiration.time}")
    private Long expiryDate;

    public TokenServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public ResponseEntity<ResultDto> jwtToken(String username, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String jwt = jwtTokenUtil.jwtGenerator((UserDetails) authenticate.getPrincipal());
            return ResponseEntity.ok(getResponseModel(jwt));
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    private ResultDto getResponseModel(String jwt) {
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("expiry_time",expiryDate);
        map.put("issued_at",new Date());
        return ResultDto.getSuccess(map);
    }

}
