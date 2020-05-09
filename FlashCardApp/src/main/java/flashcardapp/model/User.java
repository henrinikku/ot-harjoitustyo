package flashcardapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Represents a single user
 */
@Entity
@NoArgsConstructor
@Where(clause = "deleted is null")
@SQLDelete(sql = "update User set deleted = now() where id = ?")
public class User extends BaseEntity {

    /**
     * Creates an user object with the given username and password
     *
     * @param username the users username
     * @param password the users password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private List<Card> cards;

    @OneToMany(mappedBy = "owner")
    @Getter
    @Setter
    private List<Deck> decks;
}
