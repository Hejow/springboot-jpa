package com.kdt.order;

import com.kdt.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Item item;

    protected OrderItem() {
    }

    public OrderItem(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        if (Objects.nonNull(this.order)) {
            this.order.removeOrderItem(this);
        }

        this.order = order;
        order.getOrderItems().add(this);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }
}
