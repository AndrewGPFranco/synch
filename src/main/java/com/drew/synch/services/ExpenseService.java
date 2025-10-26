package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.facades.FinanceFacadeManagement;
import com.drew.synch.mappers.finance.ExpenseMapper;
import com.drew.synch.mappers.finance.FinanceTableMapper;
import com.drew.synch.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final FinanceTableMapper financeTableMapper;
    private final FinanceFacadeManagement financeFacade;

    public OutputExpenseDTO createExpense(InputExpenseDTO dto) {
        FinanceTable financeTable = financeFacade.getFinanceTableById(dto.idTable());

        if (financeTable == null)
            throw new RuntimeException(String.format("Tabela de finanças com ID: %s não encontrado.", dto.idTable()));

        Expense entity = expenseMapper.toExpense(dto);
        Expense expense = expenseRepository.save(entity);

        if (financeTable.getExpenses() == null) financeTable.setExpenses(new ArrayList<>());

        financeTable.getExpenses().add(expense);
        financeTable.setUpdatedAt(LocalDateTime.now());

        financeFacade.saveFinanceTable(financeTable);

        return expenseMapper.toOutputExpense(expense);
    }

    public void deleteExpenseByIDAndUser(UUID idExpense) {
        try {
            expenseRepository.deleteExpenseByID(idExpense);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<OutputExpenseDTO> getExpenseByID(UUID idTable, UUID idUser) {
        try {
            return financeTableMapper.getExpensesByUser(idUser, idTable);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Ocorreu um erro ao encontrar as despesas com o ID da tabela: %s.", idTable));
        }
    }

    public Double calculaDespesas(UUID idTable) {
        FinanceTable financeTable = financeFacade.getFinanceTableById(idTable);

        List<Expense> expenses = financeTable.getExpenses();

        double valor = 0.0;

        if (expenses.isEmpty())
            return valor;

        for (Expense expense : expenses) {
            valor += expense.getAmount();
        }

        return valor;
    }

    public void duplicaDespesa(UUID idExpense) {
        Expense expense = expenseRepository.findById(idExpense).orElseThrow(() ->
                new NotFoundException(String.format("Despesa com o ID: %s não foi encontrada!", idExpense)));

        Expense novaDespesa = Expense.builder()
                .name(expense.getName())
                .month(expense.getMonth())
                .amount(expense.getAmount())
                .financeTable(expense.getFinanceTable())
                .dueDate(expense.getDueDate())
                .paymentDate(expense.getPaymentDate())
                .paymentCategory(expense.getPaymentCategory())
                .link(expense.getLink())
                .build();

        expenseRepository.save(novaDespesa);
    }
}
