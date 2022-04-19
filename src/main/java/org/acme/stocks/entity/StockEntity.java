package org.acme.stocks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "STOCKS")
@Data
@NoArgsConstructor
public class StockEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column
    private String code;

    @Column
    private String stock;

    @Column
    private Long theoricQuantity;

    @Column
    private BigDecimal share;

}
