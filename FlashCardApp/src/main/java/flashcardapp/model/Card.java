package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Card extends BaseEntity {

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private User owner;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Deck deck;
}
