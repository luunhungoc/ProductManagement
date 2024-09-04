package demo.ProductManagement.entity;

import jakarta.persistence.*;


@Entity
@Table(name="orderDetails")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="orderId")
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    public OrderDetailEntity(){}

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails: {" +
                "id=" + id +
                ", productId='" + product.getId() + '\'' +
                ", productName='" + product.getName() + '\'' +
                ", quantity=" + quantity +
                ", unit price=" + product.getUnitPrice() +
                '}';
    }
}