package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(nullable = false)
    @Getter
    @Setter
    private boolean deleted;
}
