package com.upc.trabajoarquitectura.servicies;

import com.upc.trabajoarquitectura.entities.UserApp;
import com.upc.trabajoarquitectura.entities.UserList;
import com.upc.trabajoarquitectura.exceptions.RequestException;
import com.upc.trabajoarquitectura.interfaces.IUserListService;
import com.upc.trabajoarquitectura.respositories.UserAppRepository;
import com.upc.trabajoarquitectura.respositories.UserListRepository;
import com.upc.trabajoarquitectura.util.ValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserListService implements IUserListService {
    @Autowired
    private UserListRepository userListRepository;
    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public List<UserList> getUserLists(){
        return userListRepository.findAll();
    }

    @Transactional
    @Override
    public UserList registerUserList(UserList userList, Long userAppId){
        UserApp user = userAppRepository.findById(userAppId).orElseThrow(() -> new RequestException("E-006",HttpStatus.NOT_FOUND,"El usuario no existe"));
        userList.setUserApp(user);
        return userListRepository.save(userList);
    }

    @Transactional
    @Override
    public UserList updateUserList(UserList userList){
        if (userListRepository.findById(userList.getListId()).isPresent()){
            return userListRepository.save(userList);
        }
        else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"La lista a actualizar no existe");
        }
    }

    @Transactional
    @Override
    public void deleteUserList(Long userListId){
        if (userListRepository.existsById(userListId)){
            userListRepository.deleteById(userListId);
        }
        else {
            throw new RequestException("E-006", HttpStatus.NOT_FOUND,"La lista a eliminar no existe");
        }
    }

    @Override
    public UserList findByUserListId(Long userListId){
        if (!userListRepository.existsById(userListId)){
            throw new RequestException("E00", HttpStatus.NOT_FOUND, "La lista a buscar no existe");
        }
        return userListRepository.findByListId(userListId);
    }

    @Override
    public List<UserList> findListsByUser(Long userListId){
        if (!userListRepository.existsById(userListId)){
            throw new RequestException("E00", HttpStatus.NOT_FOUND, "La lista a buscar no existe");
        }
        return userListRepository.findByUserApp_UserId(userListId);
    }
}