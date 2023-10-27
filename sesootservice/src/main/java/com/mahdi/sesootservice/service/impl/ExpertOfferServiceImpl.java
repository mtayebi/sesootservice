package com.mahdi.sesootservice.service.impl;

import com.mahdi.sesootservice.entity.ExpertOffer;
import com.mahdi.sesootservice.repository.ExpertOfferRepo;
import com.mahdi.sesootservice.service.ExpertOfferService;
import org.springframework.stereotype.Service;


@Service
public class ExpertOfferServiceImpl implements ExpertOfferService {
    ExpertOfferRepo expertOfferRepo;
    public ExpertOfferServiceImpl(ExpertOfferRepo expertOfferRepo){
        this.expertOfferRepo = expertOfferRepo;
    }

    @Override
    public void save(ExpertOffer expertOffer) {

        expertOfferRepo.save(expertOffer);
    }
}
