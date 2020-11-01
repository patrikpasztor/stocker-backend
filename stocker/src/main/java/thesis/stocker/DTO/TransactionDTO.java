package thesis.stocker.DTO;

import thesis.stocker.util.TransactionType;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
