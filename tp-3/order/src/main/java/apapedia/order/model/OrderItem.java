package apapedia.order.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
@JsonIgnoreProperties(value = {"orderId"},  allowSetters = true)
public class OrderItem {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "product_id", nullable = false)
    private UUID productId;
    // @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Catalog productId;

     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "order_id", referencedColumnName = "id")
     private Order orderId;

    @Column(name = "quatity", nullable = false)
    private Integer quantity;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;
}
