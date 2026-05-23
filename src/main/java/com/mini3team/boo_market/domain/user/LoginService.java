package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.common.util.JwtUtil;
import com.mini3team.boo_market.dto.request.LoginRequest;
import com.mini3team.boo_market.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ApiException(HttpStatus.CONFLICT, "login", "아이디 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ApiException(HttpStatus.CONFLICT, "login", "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponse("로그인 되었습니다.", jwtUtil.createToken(user.getId()));
    }
}
