package com.drew.synch.controllers;

import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.dtos.finance.OutputReporteCalculoDespesa;
import com.drew.synch.entities.User;
import com.drew.synch.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{idTable}")
    ResponseEntity<ResponseAPI> getTablesByUser(@PathVariable UUID idTable, @AuthenticationPrincipal User user) {
        List<OutputExpenseDTO> expenses = service.getExpenseByID(idTable, user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAPI(expenses));
    }

    @GetMapping("/calcula-despesas/{idTable}")
    ResponseEntity<OutputReporteCalculoDespesa> calculaDespesas(@PathVariable UUID idTable) {
        Double valorDespesas = service.calculaDespesas(idTable);
        return ResponseEntity.ok().body(new OutputReporteCalculoDespesa(valorDespesas));
    }

    @PostMapping("/duplicate/{idExpense}")
    void duplicaDespesa(@PathVariable UUID idExpense) {
        service.duplicaDespesa(idExpense);
    }

    @PutMapping("/marcar-como-paga/{idExpense}")
    void marcarDespesaComoPaga(@PathVariable UUID idExpense) {
        service.marcarDespesaComoPaga(idExpense);
    }

}
