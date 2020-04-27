package flashcardapp.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Base class for all entities.
 *
 * Provides a primary key field and timestamps for creation and deletion.
 */
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
