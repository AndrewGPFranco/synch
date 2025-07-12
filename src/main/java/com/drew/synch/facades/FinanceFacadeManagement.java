package com.drew.synch.facades;

import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.repositories.ExpenseRepository;
import com.drew.synch.repositories.FinanceTableRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FinanceFacadeManagement {

    private final ExpenseRepository expenseRepository;
    private final FinanceTableRepository financeTableRepository;

    public FinanceTable getFinanceTableById(UUID idTable) {
        return financeTableRepository.findById(idTable).orElseThrow(() ->
                new NotFoundException(String.format("Nenhuma tabela encontrada com o ID: %s", idTable)));
    }

    public void saveFinanceTable(FinanceTable financeTable) {
        financeTableRepository.save(financeTable);
    }

    public @NotNull List<Expense> getExpensesByUser(UUID idUser) {
        return expenseRepository.getExpensesByUser(idUser);
    }
}
