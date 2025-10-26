package com.drew.synch.mappers.finance;

import com.drew.synch.dtos.finance.InputExpenseDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.facades.FinanceFacadeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {

    private final FinanceFacadeManagement financeFacade;

    public Expense toExpense(InputExpenseDTO dto) {
        return Expense.builder()
                .name(dto.name())
                .month(Month.valueOf(dto.month().toUpperCase()))
                .amount(dto.amount())
                .financeTable(financeFacade.getFinanceTableById(dto.idTable()))
                .dueDate(dto.dueDate())
                .paymentDate(dto.paymentDate())
                .paymentCategory(dto.paymentCategory())
                .link(dto.link())
                .build();
    }

    public OutputExpenseDTO toOutputExpense(Expense expense) {
        return OutputExpenseDTO.builder()
                .idExpense(expense.getId())
                .name(expense.getName())
                .month(expense.getMonth())
                .amount(expense.getAmount())
                .dueDate(expense.getDueDate())
                .paymentDate(expense.getPaymentDate())
                .paymentCategory(expense.getPaymentCategory())
                .link(expense.getLink())
                .build();
    }

}
