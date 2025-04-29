package apapedia.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    private UUID id = UUID.randomUUID();

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "status", nullable = false)
    private Integer status = 0;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice = 0;

    @Column(name = "customer", nullable = false)
    private UUID customer;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_customer", referencedColumnName = "id")
    // private Customer customer;

    @Column(name = "seller", nullable = false)
    private UUID seller;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_seller", referencedColumnName = "id")
    // private Seller seller;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem = new ArrayList<>();
}
