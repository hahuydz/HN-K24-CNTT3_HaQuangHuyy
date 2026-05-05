package com.example.canteen.repository;

import com.example.canteen.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionHistoryRepository
        extends JpaRepository<TransactionHistory, Long> {

    // Method Query + Paging
    Page<TransactionHistory>
    findByWalletId(Long walletId, Pageable pageable);

    // HQL Query
    @Query("""
            SELECT t
            FROM TransactionHistory t
            WHERE t.amount > :amount
            """)
    List<TransactionHistory>
    findLargeTransactions(BigDecimal amount);
}