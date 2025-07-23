package com.drew.synch.services;

import com.drew.synch.dtos.finance.InputEditTableNameDTO;
import com.drew.synch.dtos.finance.InputFinanceTableDTO;
import com.drew.synch.dtos.finance.OutputFinanceTableDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.mappers.finance.FinanceTableMapper;
import com.drew.synch.repositories.FinanceTableRepository;
import com.drew.synch.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceTableService {

    private final UserRepository userRepository;
    private final FinanceTableMapper financeTableMapper;
    private final FinanceTableRepository financeTableRepository;

    public OutputFinanceTableDTO createTable(InputFinanceTableDTO dto, UUID idOwner) {
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

    public List<OutputFinanceTableDTO> getTablesByUser(UUID id) {
        Set<FinanceTable> userTables = new HashSet<>(financeTableRepository.findTablesByUser(id));
        Set<FinanceTable> externalTablesByUser = new HashSet<>(financeTableRepository.findExternalTablesByUser(id));

        userTables.addAll(externalTablesByUser);

        return userTables.stream().map(f -> financeTableMapper.toOutputFinanceTable(f, id)).toList();
    }

    public void deleteTable(UUID idUser, UUID idTable) {
        try {
            List<FinanceTable> userTables = financeTableRepository.findTablesByUser(idUser);
            userTables.stream().filter(u -> u.getId().equals(idTable)).forEach(financeTableRepository::delete);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Ocorreu um erro ao deletar a tabela com ID %s.", idTable));
        }
    }

    public void editTableName(UUID idUser, InputEditTableNameDTO dto) {
        try {
            financeTableRepository.editTableNameByUser(idUser, dto.idTable(), dto.newName());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Ocorreu um erro ao editar o nome da tabela com ID %s.", dto.idTable()));
        }
    }

    public void addUserInList(UUID idUser, UUID idTable) {
        try {
            financeTableRepository.findById(idTable).ifPresent(financeTable -> {
                financeTable.getUsers().add(
                        userRepository.findById(idUser).orElseThrow(
                                () -> new NotFoundException(String.format("Nenhum usuário encontrado com id: %s", idUser))
                        )
                );
                financeTableRepository.save(financeTable);
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(String.format("Ocorreu um erro ao adicionar usuário na lista com ID %s.", idTable));
        }
    }
}
