package com.sekoding.example.demo.services.impl;

import com.sekoding.example.demo.model.entity.ERole;
import com.sekoding.example.demo.model.entity.Role;
import com.sekoding.example.demo.model.request.RoleRequest;
import com.sekoding.example.demo.model.response.FailedResponse;
import com.sekoding.example.demo.model.response.SuccessResponse;
import com.sekoding.example.demo.model.response.payload.RoleResponse;
import com.sekoding.example.demo.repository.RoleRepository;
import com.sekoding.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Object getAllRole() {
        try {
            List<Role> roleList = roleRepository.findAll();
            List<RoleResponse> roleResponseList = new ArrayList<>();
            for (Role role : roleList) {
                RoleResponse roleResponse = new RoleResponse(
                    role.getId(),
                    role.getRolename()
                );
                roleResponseList.add(roleResponse);
            }
            return new SuccessResponse(HttpStatus.OK, "Success", roleResponseList);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object createRole(RoleRequest roleRequest) {
        try {
            Role role = new Role(ERole.valueOf(roleRequest.getRolename()));
            Role save = roleRepository.save(role);
            RoleResponse roleResponse = new RoleResponse(
                save.getId(),
                save.getRolename()
            );
            return new SuccessResponse(HttpStatus.OK, "Created", roleResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object getRole(Integer id) {
        Role role = roleRepository.findById(id).get();
        RoleResponse roleResponse = new RoleResponse(
            role.getId(),
            role.getRolename()
        );
        return new SuccessResponse(HttpStatus.OK, "Success", roleResponse);
    }

    @Override
    public Object updateRole(Integer id, RoleRequest roleRequest) {
        try {
            Role role = roleRepository.findById(id).get();
            role.setRolename(ERole.valueOf(roleRequest.getRolename()));
            Role save = roleRepository.save(role);
            RoleResponse roleResponse = new RoleResponse(
                save.getId(),
                save.getRolename()
            );
            return new SuccessResponse(HttpStatus.OK, "Updated", roleResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Object deleteRole(Integer id) {
        try {
            Role role = roleRepository.findById(id).get();
            RoleResponse roleResponse = new RoleResponse(
                role.getId(),
                role.getRolename()
            );
            roleRepository.deleteById(id);
            return new SuccessResponse(HttpStatus.OK, "Deleted", roleResponse);
        } catch (Exception e) {
            return new FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
