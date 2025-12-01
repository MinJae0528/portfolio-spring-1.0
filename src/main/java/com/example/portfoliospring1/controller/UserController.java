package com.example.portfoliospring1.controller;

import com.example.portfoliospring1.config.auth.JwtUserPrincipal;
import com.example.portfoliospring1.controller.response.BaseResponse;
import com.example.portfoliospring1.domain.dto.UserDto;
import com.example.portfoliospring1.domain.dto.infra.KauthTokenDto;
import com.example.portfoliospring1.domain.dto.request.AddUserDto;
import com.example.portfoliospring1.domain.dto.request.LoginByEmailDto;
import com.example.portfoliospring1.domain.dto.request.LoginByKakaoDto;
import com.example.portfoliospring1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-user")
    public BaseResponse<UserDto> getUser(@RequestParam String nickname){
        return new BaseResponse<>(userService.getUser(nickname));
    }
    @GetMapping("/get-users")
    public BaseResponse<List<UserDto>> getUsers(){
        return new BaseResponse<>(userService.getUsers());
    }

    // public add-us    er 추가
    @PostMapping("/public/add-user")
    public BaseResponse<String> addUserPublic(@RequestBody AddUserDto addUserDto){
        return new BaseResponse<>(userService.addUser(addUserDto, null));
    }

    @PostMapping("/add-user")
    public BaseResponse<String> addUser(
            @AuthenticationPrincipal JwtUserPrincipal principal,
            @RequestBody AddUserDto addUserDto){
        System.out.println("principal.getProviderId()"+principal.getProviderId());

        return new BaseResponse<>(userService.addUser(addUserDto, principal.getProviderId()));
    }

    @GetMapping("/public/is-valid-nickname")
    public BaseResponse<Boolean> isValidNickname(@RequestParam String nickname){
        return new BaseResponse<>(userService.isValidNickname(nickname));
    }

    @PostMapping("/public/login-by-email")
    public BaseResponse<String> loginByEmail(@RequestBody LoginByEmailDto loginByEmailDto){
        return new BaseResponse<>(userService.login(loginByEmailDto));
    }

    @PostMapping("/public/login-by-kakao")
    public BaseResponse<String> loginByKakao(@RequestBody LoginByKakaoDto loginByKakaoDto){
        return new BaseResponse<>(userService.loginByKakao(loginByKakaoDto));
    }

    @PostMapping("/me")
    public BaseResponse<UserDto> me(@AuthenticationPrincipal JwtUserPrincipal principal){
        if(principal.getUserId() == null){
            return new BaseResponse<>(new UserDto());
        }

        return new BaseResponse<>(userService.me(principal.getUserId()));
    }

    // 스프링시큐리티

}
