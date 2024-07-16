package com.ecommerce.ecommerce.service.interfaces;

import com.ecommerce.ecommerce.domain.entity.UserEntity;
import com.ecommerce.ecommerce.domain.model.request.UserRequest;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.UserResponse;

public interface IUserService {
    boolean create(UserRequest user);
    boolean update(String id, UserRequest user);
    boolean delete(String id);
    UserResponse findUserById(String id);
    PagedList<UserResponse> findAll(int page, int size);
}
