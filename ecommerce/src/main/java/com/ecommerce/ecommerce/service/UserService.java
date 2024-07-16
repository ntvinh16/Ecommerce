package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.domain.entity.UserEntity;
import com.ecommerce.ecommerce.domain.model.request.UserRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.UserResponse;
import com.ecommerce.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.exception.ErrorCode;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.repository.UserRepositoy;
import com.ecommerce.ecommerce.service.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.ecommerce.ecommerce.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {

    UserRepositoy userRepositoy;
    RoleRepository roleRepository;
    ModelMapper mapper;
    PasswordEncoder passwordEncoder;

    public boolean create(UserRequest request) {
        var checkUser = userRepositoy.findByUsername(request.getUsername());
        if (checkUser != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        var checkMail = userRepositoy.findByEmail(request.getEmail());
        if (checkMail != null) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        var user = mapper.map(request, UserEntity.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        userRepositoy.save(user);

        return true;
    }

    public boolean update(String id, UserRequest request) {
        var user = userRepositoy.findById(convertStringToUUID(id));
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var checkUsername = userRepositoy.findByUsername(request.getUsername());
        if (checkUsername != null) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        var checkMail = userRepositoy.findByEmail(request.getEmail());
        if (checkMail != null) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        var userUpdate = mapper.map(request, UserEntity.class);
        userUpdate.setId(convertStringToUUID(id));
        userUpdate.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        userUpdate.setRoles(new HashSet<>(roles));

        userRepositoy.save(userUpdate);

        return true;
    }

    public boolean delete(String id) {
        var user = userRepositoy.findById(convertStringToUUID(id));
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        userRepositoy.deleteById(convertStringToUUID(id));
        return true;
    }

    public UserResponse findUserById(String id) {
        var user = userRepositoy.findById(convertStringToUUID(id));
        if (user.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        return mapper.map(user, UserResponse.class);
    }

    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public PagedList<UserResponse> findAll(int page, int size) {
        var result = userRepositoy.findAll().stream().map(user ->mapper.map(user, UserResponse.class)).toList();
        return new PagedList<>(page, size, result);
    }
}
