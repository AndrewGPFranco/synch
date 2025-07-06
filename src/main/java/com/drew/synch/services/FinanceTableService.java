package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.mappers.finance.FinanceTableMapper;
import com.drew.synch.repositories.FinanceTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceTableService {

    private final FinanceTableMapper financeTableMapper;
    private final FinanceTableRepository financeTableRepository;

    public void createTable(InputFinanceTableDTO dto) {
        try {
            log.info("Iniciando a criação da tabela de finanças. {}", dto);
            FinanceTable entity = financeTableMapper.toFinanceTable(dto);
            financeTableRepository.save(entity);
            log.info("Tabela criada com sucesso!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<FinanceTable> getTables() {
        return financeTableRepository.findAll();
    }
}
