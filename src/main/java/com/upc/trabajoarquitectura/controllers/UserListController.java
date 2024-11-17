package com.upc.trabajoarquitectura.controllers;

import com.upc.trabajoarquitectura.dtos.entities.UserListDTO;
import com.upc.trabajoarquitectura.entities.UserList;
import com.upc.trabajoarquitectura.interfaces.IUserListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200","http://18.223.169.236/"})
@RestController
@RequestMapping("/api")
public class UserListController {
    @Autowired
    private IUserListService userListService;

    @GetMapping("/userlists")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<UserListDTO> getAllUserLists() {
        ModelMapper mapper = new ModelMapper();
        List<UserList> userLists = userListService.getUserLists();
        List<UserListDTO> userListsDTO = Arrays.asList(mapper.map(userLists, UserListDTO[].class));
        return userListsDTO;
    }

    @PostMapping("/userlist")
    public ResponseEntity<UserListDTO> registerUserList(@RequestBody UserListDTO userListDTO) {
        ModelMapper mapper = new ModelMapper();
        UserList userList = mapper.map(userListDTO, UserList.class);
        userList = userListService.registerUserList(userList, userList.getUserApp().getUserId());
        userListDTO = mapper.map(userList, UserListDTO.class);
        return ResponseEntity.ok(userListDTO);
    }

    @PutMapping("/userlist")
    public ResponseEntity<UserListDTO> updateUserList(@RequestBody UserListDTO userListDTO) {
        ModelMapper mapper = new ModelMapper();
        UserList userList = mapper.map(userListDTO, UserList.class);
        userList = userListService.updateUserList(userList);
        userListDTO = mapper.map(userList, UserListDTO.class);
        return ResponseEntity.ok(userListDTO);
    }

    @DeleteMapping("/userlist/delete/{id}")
    public void deleteUserList(@PathVariable Long id) {
        userListService.deleteUserList(id);
    }

    @GetMapping("userlist/find/{id}")
    public List<UserList> getUserListById(@PathVariable Long id) {
        return userListService.findListsByUser(id);
    }
}