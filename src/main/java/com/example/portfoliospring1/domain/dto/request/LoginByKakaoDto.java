package com.example.portfoliospring1.domain.dto.request;

import lombok.Data;

@Data
public class LoginByKakaoDto {
    String code;
    String origin;
}
