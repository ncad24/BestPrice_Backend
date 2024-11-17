package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.entities.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface UserListRepository extends JpaRepository<UserList, Long> {
    public UserList findByListId(Long userListId);
    public List<UserList> findByUserApp_UserId(Long userId);
}
