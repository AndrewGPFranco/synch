package com.drew.synch.mappers.finance;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.facades.FinanceFacadeManagement;
import com.drew.synch.facades.UserFacadeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FinanceTableMapper {

    private final ExpenseMapper expenseMapper;
    private final UserFacadeManagement userFacade;
    private final FinanceFacadeManagement financeFacade;

    public FinanceTable toFinanceTable(InputFinanceTableDTO dto) {
        return new FinanceTable(dto.tableName(), userFacade.returningListUsers(dto.users()),
                new ArrayList<>(), userFacade.returningListUsers(Collections.singletonList(dto.idOwner())).getFirst());
    }

    public OutputFinanceTableDTO toOutputFinanceTable(FinanceTable financeTable, Long idUser) {
        return OutputFinanceTableDTO.builder()
                .idTable(financeTable.getId())
                .tableName(financeTable.getTableName())
                .createdAt(financeTable.getCreatedAt())
                .updatedAt(financeTable.getUpdatedAt())
                .expenses(getExpensesByUser(idUser))
                .users(userFacade.returningListUserDTOs(financeTable.getUsers()))
                .build();
    }

    private List<OutputExpenseDTO> getExpensesByUser(Long idUser) {
        List<Expense> expensesByUser = financeFacade.getExpensesByUser(idUser);
        return expensesByUser.stream().map(expenseMapper::toOutputExpense).collect(Collectors.toList());
    }

}
