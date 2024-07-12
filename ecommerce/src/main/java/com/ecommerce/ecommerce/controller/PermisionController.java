package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.interfaces.IPermissionService;
import com.ecommerce.ecommerce.common.enums.PermissionMessageEnum;
import com.ecommerce.ecommerce.domain.model.request.PermissionRequest;
import com.ecommerce.ecommerce.domain.model.response.ApiResponse;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.PermissionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermisionController {

    @Autowired
    IPermissionService permissionService;

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody PermissionRequest request) {
        boolean result = permissionService.save(request);

        return ApiResponse.<Boolean>builder()
                .message(PermissionMessageEnum.CREATE_SUCCESS.name())
                .result(result)
                .build();
    }

    @GetMapping
    ApiResponse<PagedList<PermissionResponse>> findAll(Integer pageNumber, Integer pageSize) {
        var result = permissionService.findAll(pageNumber, pageSize);
        return ApiResponse.<PagedList<PermissionResponse>>builder()
                .message(PermissionMessageEnum.FIND_ALL_SUCCESS.name())
                .result(result)
                .build();
    }
}
