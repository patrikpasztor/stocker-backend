package thesis.stocker.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column()
    private double balance;

    @ElementCollection
    @CollectionTable(name = "user_stock_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "stock_name")
    @Column(name = "amount")
    private Map<String, Double> stockAmountMap;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Map<String, Double> getStockAmountMap() {
        return stockAmountMap;
    }

    public void setStockAmountMap(Map<String, Double> stockAmountMap) {
        this.stockAmountMap = stockAmountMap;
    }

    public void setStockAmount(String stock, Double newAmount) {
        this.stockAmountMap.put(stock, newAmount);
    }

    public void deleteStock(String stock) {
        this.stockAmountMap.remove(stock);
    }

}