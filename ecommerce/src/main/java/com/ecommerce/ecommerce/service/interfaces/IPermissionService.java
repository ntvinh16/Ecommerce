package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.domain.model.request.PermissionRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.PermissionResponse;

public interface IPermissionService {
    boolean save(PermissionRequest request);
    PagedList<PermissionResponse> findAll(Integer pageNumber, Integer pageSize);
    boolean delete(String id);
}
