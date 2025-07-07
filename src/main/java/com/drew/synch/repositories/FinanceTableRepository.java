package com.drew.synch.repositories;

import com.drew.synch.entities.FinanceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceTableRepository extends JpaRepository<FinanceTable, Long> {

    @Query("select ft from FinanceTable ft where ft.user.id = :idUser")
    List<FinanceTable> findTablesByUser(@Param("idUser") Long idUser);

}
