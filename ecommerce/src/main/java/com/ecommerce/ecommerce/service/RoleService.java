package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.domain.entity.RoleEntity;
import com.ecommerce.ecommerce.domain.model.request.RoleRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.RoleResponse;
import com.ecommerce.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.exception.ErrorCode;
import com.ecommerce.ecommerce.repository.PermissionRepository;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.service.interfaces.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.ecommerce.ecommerce.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    ModelMapper mapper;

    public boolean save(RoleRequest request) {
        var checkRole = roleRepository.findByName(request.getName());

        if (checkRole != null) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        }

        var role = mapper.map(request, RoleEntity.class);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);

        return true;
    }

    public boolean update(String id, RoleRequest request) {
        var checkRole = roleRepository.findById(convertStringToUUID(id));

        if (checkRole.isEmpty()) {
            throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        }

        request.setId(convertStringToUUID(id));
        var role = mapper.map(request, RoleEntity.class);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return true;
    }

    public boolean delete(String id) {
        var checkRole = roleRepository.findById(convertStringToUUID(id));
        if(checkRole.isEmpty()){
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        }

        roleRepository.delete(checkRole.get());
        return true;

    }

    public PagedList<RoleResponse> findAll(Integer page, Integer size) {
        var result = roleRepository.findAll().stream().map(role -> mapper.map(role, RoleResponse.class)).toList();
        return new PagedList<>(page, size, result);
    }
}
