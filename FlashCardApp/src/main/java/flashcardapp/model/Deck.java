package flashcardapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
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
}

