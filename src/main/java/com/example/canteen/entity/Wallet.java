package com.example.canteen.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @OneToMany(
            mappedBy = "wallet",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TransactionHistory> transactionHistories;

    // Constructor
    public Wallet() {
    }

    // Getter Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionHistory> getTransactionHistories() {
        return transactionHistories;
    }

    public void setTransactionHistories(List<TransactionHistory> transactionHistories) {
        this.transactionHistories = transactionHistories;
    }
}
