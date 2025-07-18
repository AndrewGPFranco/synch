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
import java.util.UUID;

@Repository
public interface FinanceTableRepository extends JpaRepository<FinanceTable, UUID> {

    @Query("select ft from FinanceTable ft where ft.user.id = :idUser")
    List<FinanceTable> findTablesByUser(@Param("idUser") UUID idUser);

    @Query("""
                select distinct f from FinanceTable f
                left join f.users u
                where f.user.id = :userId or u.id = :userId
            """)
    List<FinanceTable> findExternalTablesByUser(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("update FinanceTable ft set ft.tableName = :newName where ft.id = :idTable and ft.user.id = :idUser")
    void editTableNameByUser(@Param("idUser") @NotNull UUID idUser, @Param("idTable") @NotNull UUID idTable, @Param("newName") @NotBlank String newName);
}
