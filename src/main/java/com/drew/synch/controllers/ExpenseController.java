package com.drew.synch.controllers;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
class ExpenseController {

    private final ExpenseService service;

    @PostMapping
    void createExpense(@RequestBody InputExpenseDTO dto) {
        service.createExpense(dto);
    }

}
