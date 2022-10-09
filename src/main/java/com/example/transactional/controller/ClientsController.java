package com.example.transactional.controller;

import com.example.transactional.model.request.ClientsRequest;
import com.example.transactional.service.ClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clients")
public class ClientsController {
    private final ClientsService clientsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ClientsRequest request) {
        clientsService.save(request);
    }

    @PatchMapping("{name}/birthDate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBirthDate(@PathVariable String name,
                                @RequestParam("value") String birthDate){
        clientsService.updateBirthDate(name, LocalDate.parse(birthDate));
    }

}
