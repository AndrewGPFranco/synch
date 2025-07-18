package com.drew.synch.mappers.finance;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputExpenseDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.Expense;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.enums.StatusType;
import com.drew.synch.facades.FinanceFacadeManagement;
import com.drew.synch.facades.UserFacadeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FinanceTableMapper {

    private final ExpenseMapper expenseMapper;
    private final UserFacadeManagement userFacade;
    private final FinanceFacadeManagement financeFacade;

    public FinanceTable toFinanceTable(InputFinanceTableDTO dto, UUID idOwner) {
        return new FinanceTable(dto.tableName(), userFacade.returningListUsers(List.of(idOwner)), new ArrayList<>(),
                userFacade.returningListUsers(Collections.singletonList(idOwner)).getFirst(), StatusType.TODO);
    }

    public OutputFinanceTableDTO toOutputFinanceTable(FinanceTable financeTable, UUID idUser) {
        return OutputFinanceTableDTO.builder()
                .idTable(financeTable.getId())
                .tableName(financeTable.getTableName())
                .createdAt(financeTable.getCreatedAt())
                .updatedAt(financeTable.getUpdatedAt())
                .expenses(getExpensesByUser(idUser, financeTable.getId()))
                .users(userFacade.returningListUserDTOs(financeTable.getUsers(), idUser))
                .status(returnStatusTypeString(financeTable.getStatus()))
                .build();
    }

    private List<OutputExpenseDTO> getExpensesByUser(UUID idUser, UUID idFinanceTable) {
        Set<Expense> expensesByUser = new HashSet<>(financeFacade.getExpensesByUser(idUser));
        Set<Expense> externalExpenses = new HashSet<>(financeFacade.getExternalExpensesByUser(idFinanceTable));

        expensesByUser.addAll(externalExpenses);

        return expensesByUser.stream().map(expenseMapper::toOutputExpense).collect(Collectors.toList());
    }

    private String returnStatusTypeString(StatusType status) {
        return switch (status) {
            case COMPLETED -> StatusType.COMPLETED.getDescription();
            case ONGOING -> StatusType.ONGOING.getDescription();
            case TODO -> StatusType.TODO.getDescription();
        };
    }

}
