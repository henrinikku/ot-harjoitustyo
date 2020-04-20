package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Where(clause = "deleted is null")
@SQLDelete(sql = "update Card set deleted = now() where id = ?")
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
