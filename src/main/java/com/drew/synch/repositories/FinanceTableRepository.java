package com.drew.synch.repositories;

import com.drew.synch.entities.FinanceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceTableRepository extends JpaRepository<FinanceTable, Long> {
}
