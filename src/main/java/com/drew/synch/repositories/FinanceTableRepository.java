package com.drew.synch.repositories;

import com.drew.synch.entities.FinanceTable;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceTableRepository extends JpaRepository<FinanceTable, Long> {

    @Query("select ft from FinanceTable ft where ft.user.id = :idUser")
    List<FinanceTable> findTablesByUser(@Param("idUser") Long idUser);

    @Modifying
    @Transactional
    @Query("update FinanceTable ft set ft.tableName = :newName where ft.id = :idTable and ft.user.id = :idUser")
    void editTableNameByUser(@Param("idUser") @NotNull Long idUser, @Param("idTable") @NotNull Long idTable, @Param("newName") @NotBlank String newName);
}
