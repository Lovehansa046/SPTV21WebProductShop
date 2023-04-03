

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class History implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    @OneToOne
    private Customer customer;
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleProduct;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date returnBook;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProduct(Date saleProduct) {
        this.saleProduct = saleProduct;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id 
                + ", product=" + product 
                + ", customer=" + customer 
                + ", saleProduct=" + saleProduct 
                + '}';
    }

   
    
}
