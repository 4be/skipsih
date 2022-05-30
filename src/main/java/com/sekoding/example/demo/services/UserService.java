package com.sekoding.example.demo.services;

import com.sekoding.example.demo.model.request.UserRequest;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Object getAllUser();

    Object createUser(UserRequest userRequest);

    Object createUserByUpload(MultipartFile fileRequest);

    Object getUserById(Long id);

    Object getUserByNikManager(String nikManager);

    Object updateUserByNik(UserRequest userRequest, String nik);

    Object deleteUser(Long id);

    Object deleteUserByNik(String nik);


}
