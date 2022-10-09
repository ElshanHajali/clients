package com.example.transactional.service.impl;

import com.example.transactional.dao.repository.ClientsRepository;
import com.example.transactional.model.request.ClientsRequest;
import com.example.transactional.service.ClientsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientsServiceImpl implements ClientsService {
    private final ClientsRepository clientsRepository;

//    @Transactional
    @Override
    public void save(ClientsRequest request) {
        log.info("ActionLog.save.start");

        if (request == null) {
            log.error("ActionLog.save.error");
            throw new RuntimeException("Request Body is null");
        }

        clientsRepository.saveClient(request.getName(), request.getBirthDate());
        log.info("ActionLog.save.success");
    }

    // Atomicity
    @Transactional
    // By default, checked exception isn't cared
    // rollbackFor = Exception.class (adds atomicity for checked exceptions)
    // noRollbackFor = RuntimeException.class (it breaks atomicity rule)
    public void updateBirthDate(String name , LocalDate birthDate) {
        log.info("ActionLog.updateBirthDate.start");

        if (name == null) {
            log.error("ActionLog.updateBirthDate.error");
            throw new RuntimeException("Name Not Found");
        }

        clientsRepository.updateBirthDate(name,birthDate);
        log.info("ActionLog.updateBirthDate.success");

        //to check atomicity
//        throw new RuntimeException("Anything Happened");
    }

    // Propagation
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveWithPropagationLevel(ClientsRequest request) {
        save(request);
    }
}
