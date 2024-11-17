package com.upc.trabajoarquitectura.interfaces;

import com.upc.trabajoarquitectura.dtos.manageinfo.UserInfoDTO;
import com.upc.trabajoarquitectura.entities.UserApp;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUserAppService {
    public List<UserApp> getUsers();
    public UserApp registerUser(UserApp userApp, MultipartFile image) throws IOException;
    public UserApp updateUser(UserApp userApp, MultipartFile image) throws Exception;
    public void deleteUser(Long userId) throws Exception;
    public Long countTotalUsers();
    public UserApp findByUserId(Long userId);
    public UserInfoDTO findByUsername(String username);
}