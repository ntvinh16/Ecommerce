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
                .code(PermissionMessageEnum.CREATE_SUCCESS.getCode())
                .message(PermissionMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping
    ApiResponse<PagedList<PermissionResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size) {
        var result = permissionService.findAll(page, size);
        return ApiResponse.<PagedList<PermissionResponse>>builder()
                .code(PermissionMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(PermissionMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping
    ApiResponse<Boolean> delete(@RequestParam String id) {
        var result = permissionService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(PermissionMessageEnum.DELETE_SUCCESS.getCode())
                .message(PermissionMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

}
