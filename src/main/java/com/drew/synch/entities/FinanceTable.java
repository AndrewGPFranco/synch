package com.drew.synch.entities;

import com.drew.synch.enums.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "finance_table", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class FinanceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String tableName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "finance_table_user",
            joinColumns = @JoinColumn(name = "finance_table_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "financeTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenses;

    @Column(length = 15)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    public FinanceTable(String tableName, List<User> users, List<Expense> expenses, User user, StatusType status) {
        this.tableName = tableName;
        this.users = users;
        this.expenses = expenses;
        this.user = user;
        this.status = status;
    }

    @Override
    public String toString() {
        return "FinanceTable{" +
                "idExpense=" + id +
                ", tableName='" + tableName + '\'' +
                ", users=" + users +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expenses=" + expenses +
                '}';
    }
}
