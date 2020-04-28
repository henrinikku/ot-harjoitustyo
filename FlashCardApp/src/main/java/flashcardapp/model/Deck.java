package flashcardapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a single deck.
 */
@Entity
@NoArgsConstructor
@Where(clause = "deleted is null")
@SQLDelete(sql = "update Deck set deleted = now() where id = ?")
public class Deck extends BaseEntity {

    public Deck(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private User owner;

    @OneToMany(mappedBy = "deck")
    @Getter
    @Setter
    private List<Card> cards;

    /**
     * Returns a string representation of the deck.
     *
     * @return a string representation of the deck i.e. its name
     */
    @Override
    public String toString() {
        return getName();
    }
}

