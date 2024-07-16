package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.domain.entity.UserEntity;
import com.ecommerce.ecommerce.domain.model.request.AuthenticationRequest;
import com.ecommerce.ecommerce.domain.model.request.IntrospectRequest;
import com.ecommerce.ecommerce.domain.model.request.LogoutRequest;
import com.ecommerce.ecommerce.domain.model.request.RefreshRequest;
import com.ecommerce.ecommerce.domain.model.response.AuthenticationResponse;
import com.ecommerce.ecommerce.domain.model.response.IntrospectResponse;
import com.ecommerce.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.exception.ErrorCode;
import com.ecommerce.ecommerce.repository.UserRepositoy;
import com.ecommerce.ecommerce.service.interfaces.IAuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthenticationService {

    UserRepositoy userRepositoy;

    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) {
        var token = request.getToken();

        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            try {

                SignedJWT signedJWT = SignedJWT.parse(token);

                Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

               var verified = signedJWT.verify(verifier);

                return IntrospectResponse.builder()
                        .valid(verified && expirationTime.after(new Date()))
                        .build();

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepositoy.findByUsername(request.getUsername());
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_EXCEPTION);
        }

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    public void logout(LogoutRequest request) {

    }


    public AuthenticationResponse refreshToken(RefreshRequest request) {
        return null;
    }


    public SignedJWT verifyToken(String token, boolean isRefresh) {
        return null;
    }

    String generateToken(UserEntity user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("ecommerce.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return jwsObject.serialize();
    }

    String buildScope(UserEntity user) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            });

        }
        return stringJoiner.toString();
    }
}
