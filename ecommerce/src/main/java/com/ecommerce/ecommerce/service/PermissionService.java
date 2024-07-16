package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.service.interfaces.IPermissionService;
import com.ecommerce.ecommerce.domain.entity.PermissionEntity;
import com.ecommerce.ecommerce.domain.model.request.PermissionRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.PermissionResponse;
import com.ecommerce.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.exception.ErrorCode;
import com.ecommerce.ecommerce.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ecommerce.ecommerce.helper.Convert.convertStringToUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    ModelMapper mapper;

    public boolean save(PermissionRequest request) {
            var checkPermission = permissionRepository.findByName(request.getName());
            if(checkPermission != null){
                throw new AppException(ErrorCode.PERMISSION_EXISTED);
            }

            PermissionEntity permissionEntity = mapper.map(request, PermissionEntity.class);
            permissionRepository.save(permissionEntity);

            return true;
    }

    public PagedList<PermissionResponse> findAll(Integer page, Integer size) {
        var result = permissionRepository.findAll().stream().map(per -> mapper.map(per, PermissionResponse.class)).toList();
        return new PagedList<>(page, size, result);
    }

    public boolean delete(String id) {
        var checkPermission = permissionRepository.findById(convertStringToUUID(id));
        if(checkPermission.isEmpty()){
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        }

        permissionRepository.delete(checkPermission.get());
        return true;

    }

}
