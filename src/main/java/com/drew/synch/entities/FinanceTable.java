package com.drew.synch.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "finance_table")
public class FinanceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String tableName;

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

    @Override
    public String toString() {
        return "FinanceTable{" +
                "id=" + id +
                ", tableName='" + tableName + '\'' +
                ", users=" + users +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expenses=" + expenses +
                '}';
    }
}
