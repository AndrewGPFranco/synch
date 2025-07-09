package com.drew.synch.controllers;

import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.User;
import com.drew.synch.services.FinanceTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance")
class FinanceTableController {

    private final FinanceTableService service;

    @PostMapping
    ResponseEntity<ResponseAPI> createTable(@RequestBody InputFinanceTableDTO dto, @AuthenticationPrincipal User user) {
        OutputFinanceTableDTO table = service.createTable(dto, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(table));
    }

    @GetMapping(value = "/tables")
    ResponseEntity<ResponseAPI> getTablesByUser(@AuthenticationPrincipal User user) {
        List<OutputFinanceTableDTO> tables = service.getTablesByUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(tables));
    }

}
