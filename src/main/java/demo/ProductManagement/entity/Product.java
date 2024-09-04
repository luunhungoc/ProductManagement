package demo.ProductManagement.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="unitPrice")
    private double unitPrice;

    @Column(nullable = true, length = 64)
    private String photo;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orderDetailEntityList;
    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPhoto() {
        return photo;
    }

//    @Transient
//    public String getPhotosImagePath() {
//        if (photos == null || id == 0) return null;
//        return "/product-photos/" + id + "/" + photos;
//    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public List<OrderDetailEntity> getOrderDetailEntityList() {
        return orderDetailEntityList;
    }

    public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
        this.orderDetailEntityList = orderDetailEntityList;
    }

    @Override
    public String toString() {
        return "{" +
                "Id='" + id + '\'' +
               "Name='" + name + '\'' +
                ", description='" + description + '\'' +
                "unit price='" + unitPrice + '\'' +
                '}';
    }
}