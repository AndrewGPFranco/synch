package com.drew.synch.mappers.finance;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.entities.User;
import com.drew.synch.facades.UserFacadeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FinanceTableMapper {

    private final UserFacadeManagement userFacade;

    public FinanceTable toFinanceTable(InputFinanceTableDTO dto) {
        return new FinanceTable(dto.tableName(), userFacade.returningListUsers(dto.users()),
                new ArrayList<>(), userFacade.returningListUsers(Collections.singletonList(dto.idOwner())).getFirst());
    }

    public OutputFinanceTableDTO toOutputFinanceTable(FinanceTable financeTable) {
        return OutputFinanceTableDTO.builder()
                .idTable(financeTable.getId())
                .tableName(financeTable.getTableName())
                .createdAt(financeTable.getCreatedAt())
                .updatedAt(financeTable.getUpdatedAt())
                .expenses(financeTable.getExpenses())
                .users(userFacade.returningListUserDTOs(financeTable.getUsers()))
                .build();
    }

}
