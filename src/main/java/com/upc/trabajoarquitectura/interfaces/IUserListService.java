package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.entities.UserApp;
import com.upc.trabajoarquitectura.entities.UserList;

import java.util.List;

public interface IUserListService {
    public List<UserList> getUserLists();
    public UserList registerUserList(UserList userList, Long userAppId);
    public UserList updateUserList(UserList userList);
    public void deleteUserList(Long userListId);
    public UserList findByUserListId(Long userListId);
    public List<UserList> findListsByUser(Long userListId);
}