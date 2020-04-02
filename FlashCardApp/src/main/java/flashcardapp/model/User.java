package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends BaseEntity {

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
