package com.drew.synch.controllers;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.services.FinanceTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/finance")
@RequiredArgsConstructor
class FinanceTableController {

    private final FinanceTableService service;

    @PostMapping
    void createTable(@RequestBody InputFinanceTableDTO dto) {
        service.createTable(dto);
    }

    @GetMapping
    List<FinanceTable> getTables() {
        return service.getTables();
    }

}
