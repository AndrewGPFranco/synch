package com.drew.synch.repositories;

import com.drew.synch.entities.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    @Query("select e from Expense e where e.financeTable.user.id = :idUser")
    List<Expense> getExpensesByUser(@Param("idUser") UUID idUser);

    @Modifying
    @Transactional
    @Query("delete from Expense e where e.id = :idExpense")
    void deleteExpenseByID(@Param("idExpense") UUID idExpense);
}
