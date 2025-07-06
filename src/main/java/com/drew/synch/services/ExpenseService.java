package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.repositories.ExpenseRepository;
import com.drew.synch.repositories.FinanceTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final FinanceTableRepository financeTableRepository;

    public void createExpense(InputExpenseDTO dto) {
        FinanceTable financeTable = financeTableRepository.findById(dto.idTable()).orElse(null);

        if (financeTable == null) throw new RuntimeException("Finance table not found");

        Expense expense = expenseRepository.save(Expense.builder()
                .name(dto.name())
                .month(Month.valueOf(dto.month().toUpperCase()))
                .amount(dto.amount())
                .financeTable(financeTable)
                .build());

        if (financeTable.getExpenses() == null) financeTable.setExpenses(new ArrayList<>());

        financeTable.getExpenses().add(expense);

        financeTableRepository.save(financeTable);
    }

}
