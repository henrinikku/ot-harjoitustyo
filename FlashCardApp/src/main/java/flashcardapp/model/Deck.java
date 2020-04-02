package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Deck extends BaseEntity {

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @ManyToOne(optional = false)
    @Getter
    @Setter
    private User owner;

    @OneToMany(mappedBy = "deck")
    @Getter
    @Setter
    private List<Card> cards;
}

