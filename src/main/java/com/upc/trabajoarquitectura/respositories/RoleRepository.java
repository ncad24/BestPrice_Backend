package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleId(long id);
}
