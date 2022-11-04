package dev.jzadi.springboot.multiply.domains;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Classe Multiplication, créée le 04/11/2022 à 17:10
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
@Getter
@RequiredArgsConstructor
@Entity(name = "T_MULTIPLICATION")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"id", "facteurA", "facteurB"})
public class Multiplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MULTIPLICATION_ID")
    Integer id;

    @NotNull
    final int facteurA;

    @NotNull
    final int facteurB;

    public Multiplication() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Multiplication that = (Multiplication) o;
        return facteurA != 0 && facteurB != 0 && (facteurA == that.facteurA) && (facteurB == that.facteurB);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
