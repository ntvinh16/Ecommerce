package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.common.enums.UserMessageEnum;
import com.ecommerce.ecommerce.domain.model.request.UserRequest;
import com.ecommerce.ecommerce.domain.model.response.ApiResponse;
import com.ecommerce.ecommerce.domain.model.response.PagedList;
import com.ecommerce.ecommerce.domain.model.response.UserResponse;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    ApiResponse<Boolean> create(@RequestBody @Valid UserRequest request){
        var result = userService.create(request);
        return ApiResponse.<Boolean>builder()
                .code(UserMessageEnum.CREATE_SUCCESS.getCode())
                .message(UserMessageEnum.CREATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @PutMapping
    ApiResponse<Boolean> update(@RequestParam String id,  @RequestBody @Valid UserRequest request){
        var result = userService.update(id, request);
        return ApiResponse.<Boolean>builder()
                .code(UserMessageEnum.UPDATE_SUCCESS.getCode())
                .message(UserMessageEnum.UPDATE_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping
    ApiResponse<PagedList<UserResponse>> findAll(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size){
        var result = userService.findAll(page, size);
        return ApiResponse.<PagedList<UserResponse>>builder()
                .code(UserMessageEnum.FIND_ALL_SUCCESS.getCode())
                .message(UserMessageEnum.FIND_ALL_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @GetMapping("{id}")
    ApiResponse<UserResponse> findById(@PathVariable(value = "id") String id){
        var result = userService.findUserById(id);
        return ApiResponse.<UserResponse>builder()
                .code(UserMessageEnum.FIND_USER_SUCCESS.getCode())
                .message(UserMessageEnum.FIND_USER_SUCCESS.getMessage())
                .result(result)
                .build();
    }

    @DeleteMapping("{id}")
    ApiResponse<Boolean> delete(@PathVariable(value = "id") String id){
        var result = userService.delete(id);
        return ApiResponse.<Boolean>builder()
                .code(UserMessageEnum.DELETE_SUCCESS.getCode())
                .message(UserMessageEnum.DELETE_SUCCESS.getMessage())
                .result(result)
                .build();
    }
}