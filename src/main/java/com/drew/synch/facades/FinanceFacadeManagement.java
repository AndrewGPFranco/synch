package com.drew.synch.facades;

import com.drew.synch.entities.FinanceTable;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.repositories.FinanceTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinanceFacadeManagement {

    private final FinanceTableRepository financeTableRepository;

    public FinanceTable getFinanceTableById(Long idTable) {
        return financeTableRepository.findById(idTable).orElseThrow(() ->
                new NotFoundException(String.format("Nenhuma tabela encontrada com o ID: %s", idTable)));
    }

    public void saveFinanceTable(FinanceTable financeTable) {
        financeTableRepository.save(financeTable);
    }
}
