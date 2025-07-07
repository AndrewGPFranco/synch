package com.drew.synch.repositories;

import com.drew.synch.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("select e from Expense e where e.financeTable.user.id = :idUser")
    List<Expense> getExpensesByUser(@Param("idUser") Long idUser);
}
