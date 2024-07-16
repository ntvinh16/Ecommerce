package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.domain.model.request.AuthenticationRequest;
import com.ecommerce.ecommerce.domain.model.request.IntrospectRequest;
import com.ecommerce.ecommerce.domain.model.request.LogoutRequest;
import com.ecommerce.ecommerce.domain.model.request.RefreshRequest;
import com.ecommerce.ecommerce.domain.model.response.AuthenticationResponse;
import com.ecommerce.ecommerce.domain.model.response.IntrospectResponse;
import com.nimbusds.jwt.SignedJWT;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void logout(LogoutRequest request);
    AuthenticationResponse refreshToken(RefreshRequest request);
    SignedJWT verifyToken(String token, boolean isRefresh);
}
