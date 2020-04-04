package flashcardapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class User extends BaseEntity {

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
