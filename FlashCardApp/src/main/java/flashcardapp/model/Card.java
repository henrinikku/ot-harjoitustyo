package flashcardapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents a single card.
 */
@Entity
@NoArgsConstructor
@Where(clause = "deleted is null")
@SQLDelete(sql = "update Card set deleted = now() where id = ?")
public class Card extends BaseEntity {

    /**
     * Self explanatory.
     *
     * @param name name of the card
     * @param question question associated with the card
     * @param answer answer to the given question
     */
    public Card(String name, String question, String answer) {
        this.name = name;
        this.question = question;
        this.answer = answer;
    }

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private String question;

    @Column(nullable = false)
    @Getter
    @Setter
    private String answer;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private User owner;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Deck deck;

    /**
     * Returns a string representation of the card.
     *
     * @return a string representation of the card i.e. its name
     */
    @Override
    public String toString() {
        return name;
    }
}
