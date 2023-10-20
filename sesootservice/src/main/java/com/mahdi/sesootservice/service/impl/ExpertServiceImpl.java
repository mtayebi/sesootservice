package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.entity.Expert;
import com.mahdi.sesootservice.repository.ExpertRepo;
import com.mahdi.sesootservice.service.ExpertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    ExpertRepo expertRepo;

    public ExpertServiceImpl(ExpertRepo expertRepo) {
        this.expertRepo = expertRepo;
    }

    @Override
    public void signUp(Expert expert) {

    }

    @Override
    public Expert profile(String email) {
        return null;
    }

    @Override
    public void updateUser(Expert expert) {

    }

    @Override
    public List<Expert> allUsers() {
        return null;
    }
}
