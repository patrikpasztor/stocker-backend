package thesis.stocker.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String email;

    private String password;

    @Builder.Default
    private Boolean locked = false;

    @Builder.Default
    private Boolean enabled = false;

    @Builder.Default
    private UserRole userRole = UserRole.USER;

    @Column()
    private double balance;

    @ElementCollection
    @CollectionTable(name = "user_stock_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "stock_name")
    @Column(name = "amount")
    private Map<String, Double> stockAmountMap;

    @ElementCollection
    @CollectionTable(name = "user_watchlist_mapping",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @Column(name = "stock_name")
    private List<String> watchlist;

    public User(String name) {
        this.name = name;
    }

    public void setStockAmount(String stock, Double newAmount) {
        this.stockAmountMap.put(stock, newAmount);
    }

    public void deleteStock(String stock) {
        this.stockAmountMap.remove(stock);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}