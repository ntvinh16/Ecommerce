package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.service.interfaces.IPermissionService;
import com.ecommerce.ecommerce.domain.entity.PermissionEntity;
import com.ecommerce.ecommerce.domain.model.request.PermissionRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.PermissionResponse;
import com.ecommerce.ecommerce.exception.AppException;
import com.ecommerce.ecommerce.exception.ErrorCode;
import com.ecommerce.ecommerce.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public boolean save(PermissionRequest request) {
            var checkPermission = permissionRepository.findByName(request.getName());
            if(checkPermission != null){
                throw new AppException(ErrorCode.PERMISSION_EXISTED);
            }

            PermissionEntity permissionEntity = mapper.map(request, PermissionEntity.class);
            permissionRepository.save(permissionEntity);

            return true;
    }

    @Override
    public PagedList<PermissionResponse> findAll(Integer pageNumber, Integer pageSize) {
        var result = permissionRepository.findAll().stream().map(per -> mapper.map(per, PermissionResponse.class)).toList();
        return new PagedList<>(pageNumber, pageSize, result);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
