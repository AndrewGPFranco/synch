package com.drew.synch.entities;

import com.drew.synch.enums.PaymentCategoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "expense")
public class Expense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_category", nullable = false, length = 10)
    private PaymentCategoryType paymentCategory;

    @NotNull
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "month", nullable = false)
    private Month month;

    @NotNull
    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn(name = "finance_table_id")
    private FinanceTable financeTable;

    @Column(name = "link")
    private String link;

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentCategory=" + paymentCategory +
                ", dueDate=" + dueDate +
                ", name='" + name + '\'' +
                ", month=" + month +
                ", amount=" + amount +
                ", link='" + link + '\'' +
                '}';
    }
}
