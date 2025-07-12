package com.drew.synch.controllers;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
class ExpenseController {

    private final ExpenseService service;

    @PostMapping
    ResponseEntity<OutputExpenseDTO> createExpense(@RequestBody InputExpenseDTO dto) {
        OutputExpenseDTO expense = service.createExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @DeleteMapping("/{idExpense}")
    void deleteExpense(@PathVariable UUID idExpense) {
        service.deleteExpenseByIDAndUser(idExpense);
    }

}
