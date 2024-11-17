package com.upc.trabajoarquitectura.respositories;

import com.upc.trabajoarquitectura.dtos.manageinfo.UserInfoDTO;
import com.upc.trabajoarquitectura.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    //Querys
    public boolean existsByUserId(long userId);
    //Find user by id
    public UserApp findByUserId(Long userID);
    //Existe username
    public boolean existsByUsername(String username);
    //Existe correo
    public boolean existsByEmail(String email);
    //Existe telefono
    public boolean existsByPhoneNumber(String phone);
    //Se utiliza para el Spring Security
    public Optional<UserApp> findByUsername(String username);
    //InfoUser
    @Query("SELECT new com.upc.trabajoarquitectura.dtos.manageinfo.UserInfoDTO(u.userId, u.names, u.surnames, u.phoneNumber, u.email, u.gender, u.username, u.imagePath) " +
            "FROM UserApp u WHERE u.username = :username")
    public UserInfoDTO findByUsernameDTO(@Param("username") String username);

    @Query("SELECT COUNT(u) FROM UserApp u")
    Long countTotalUsers();
}
