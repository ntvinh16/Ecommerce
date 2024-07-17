package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.enums.AuthenticationEnum;
import com.ecommerce.ecommerce.domain.model.request.AuthenticationRequest;
import com.ecommerce.ecommerce.domain.model.request.IntrospectRequest;
import com.ecommerce.ecommerce.domain.model.request.LogoutRequest;
import com.ecommerce.ecommerce.domain.model.request.RefreshRequest;
import com.ecommerce.ecommerce.domain.model.response.ApiResponse;
import com.ecommerce.ecommerce.domain.model.response.AuthenticationResponse;
import com.ecommerce.ecommerce.domain.model.response.IntrospectResponse;
import com.ecommerce.ecommerce.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(AuthenticationEnum.LOGIN_SUCCESS.getCode())
                .message(AuthenticationEnum.LOGIN_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(AuthenticationEnum.INTROSPECT_SUCCESS.getCode())
                .message(AuthenticationEnum.INTROSPECT_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .code(AuthenticationEnum.LOGOUT_SUCCESS.getCode())
                .message(AuthenticationEnum.LOGOUT_SUCCESS.getMessage())
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(AuthenticationEnum.REFRESH_SUCCESS.getCode())
                .message(AuthenticationEnum.REFRESH_SUCCESS.getMessage())
                .result(result)
                .build();
    }

}
