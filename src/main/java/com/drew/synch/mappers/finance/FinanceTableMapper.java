package com.drew.synch.mappers.finance;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.entities.User;
import com.drew.synch.facades.UserFacadeManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FinanceTableMapper {

    private final UserFacadeManagement userFacade;

    public FinanceTable toFinanceTable(InputFinanceTableDTO dto) {
        return new FinanceTable(dto.tableName(), userFacade.returningListUsers(dto.users()), new ArrayList<>());
    }

    public InputFinanceTableDTO toInputDtoFinanceTable(FinanceTable financeTable) {
        return InputFinanceTableDTO.builder()
                .tableName(financeTable.getTableName())
                .users(returnListIdByUsers(financeTable.getUsers()))
                .build();
    }

    private List<Long> returnListIdByUsers(List<User> users) {
        return users.stream().map(User::getId).toList();
    }

}
