package dev.jzadi.springboot.multiply.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe User, créée le 04/11/2022 à 16:04
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
@Getter
@Entity(name = "T_USER")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id", "alias"})
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    Integer id;

    @NotNull
    final String alias;

    public User() {
        this(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        User user = (User) o;
        return alias != null && Objects.equals(alias, user.alias);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
