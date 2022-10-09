package com.example.transactional.service;

import com.example.transactional.model.request.ClientsRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface ClientsService {

    void save(ClientsRequest request);

    void updateBirthDate(String name, LocalDate birthDate);
}
