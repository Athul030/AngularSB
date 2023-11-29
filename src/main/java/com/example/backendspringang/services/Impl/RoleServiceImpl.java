package com.example.backendspringang.services.Impl;

import com.example.backendspringang.entity.Role;
import com.example.backendspringang.repository.RoleRepository;
import com.example.backendspringang.services.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole() {

        return roleRepository.findByRoleName("USER");
    }
}
