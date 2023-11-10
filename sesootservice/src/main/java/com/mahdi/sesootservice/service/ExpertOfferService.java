package com.mahdi.sesootservice.service;

import com.mahdi.sesootservice.entity.ExpertOffer;
import org.springframework.stereotype.Service;

@Service
public interface ExpertOfferService {
    void save(ExpertOffer expertOffer);
}
