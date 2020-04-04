package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;

    @CreationTimestamp
    @Column
    @Getter
    @Setter
    private LocalDateTime created;

    @Column
    @Getter
    @Setter
    private LocalDateTime deleted;
}
