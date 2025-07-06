package com.drew.synch.controllers;

import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.services.FinanceTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance")
class FinanceTableController {

    private final FinanceTableService service;

    @PostMapping
    ResponseEntity<ResponseAPI> createTable(@RequestBody InputFinanceTableDTO dto) {
        OutputFinanceTableDTO table = service.createTable(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI(table));
    }

    @GetMapping
    List<FinanceTable> getTables() {
        return service.getTables();
    }

}
