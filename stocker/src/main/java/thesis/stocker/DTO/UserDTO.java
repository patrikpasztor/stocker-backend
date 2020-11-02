package thesis.stocker.DTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

/*
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDTO> transactions = new ArrayList<>();
*/

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<TransactionDTO> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<TransactionDTO> transactions) {
//        this.transactions = transactions;
//    }
}
