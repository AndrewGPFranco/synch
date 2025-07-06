package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.facades.FinanceFacadeManagement;
import com.drew.synch.mappers.finance.ExpenseMapper;
import com.drew.synch.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final FinanceFacadeManagement financeFacade;

    public OutputExpenseDTO createExpense(InputExpenseDTO dto) {
        FinanceTable financeTable = financeFacade.getFinanceTableById(dto.idTable());

        if (financeTable == null)
            throw new RuntimeException(String.format("Tabela de finanças com ID: %s não encontrado.", dto.idTable()));

        Expense entity = expenseMapper.toExpense(dto);
        Expense expense = expenseRepository.save(entity);

        if (financeTable.getExpenses() == null) financeTable.setExpenses(new ArrayList<>());

        financeTable.getExpenses().add(expense);

        financeFacade.saveFinanceTable(financeTable);

        return expenseMapper.toOutputExpense(expense);
    }

}
