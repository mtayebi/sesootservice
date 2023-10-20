package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.entity.Expert;
;

import java.util.List;

public interface ExpertService {
    void signUp(Expert expert);
    Expert profile(String email);
    void updateUser(Expert expert);
    List<Expert> allUsers();

}
