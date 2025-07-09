package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
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

    public OutputFinanceTableDTO createTable(InputFinanceTableDTO dto, Long idOwner) {
        try {
            log.info("Iniciando a criação da tabela de finanças. {}", dto);
            FinanceTable entity = financeTableMapper.toFinanceTable(dto, idOwner);
            FinanceTable savedFinance = financeTableRepository.save(entity);
            log.info("Tabela criada com sucesso!");

            return financeTableMapper.toOutputFinanceTable(savedFinance, idOwner);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao criar a tabela, verifique os dados informados.");
        }
    }

    public List<OutputFinanceTableDTO> getTablesByUser(Long id) {
        List<FinanceTable> userTables = financeTableRepository.findTablesByUser(id);

        return userTables.stream().map(f -> financeTableMapper.toOutputFinanceTable(f, id)).toList();
    }

    public void deleteTable(Long idUser, Long idTable) {
        try {
            List<FinanceTable> userTables = financeTableRepository.findTablesByUser(idUser);
            userTables.stream().filter(u -> u.getId().equals(idTable)).forEach(financeTableRepository::delete);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Ocorreu um erro ao deletar a tabela com ID %s.", idTable));
        }
    }
}
