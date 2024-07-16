package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.domain.model.request.RoleRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.RoleResponse;

public interface IRoleService {
    boolean save(RoleRequest roleRequest);
    boolean update(String id, RoleRequest roleRequest);
    boolean delete(String id);
    PagedList<RoleResponse> findAll(Integer page, Integer size);

}
