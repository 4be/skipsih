package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.request.RoleRequest;

public interface RoleService {

    Object getAllRole();

    Object createRole(RoleRequest roleRequest);

    Object getRole(Integer id);

    Object updateRole(Integer id, RoleRequest roleRequest);

    Object deleteRole(Integer id);

}
