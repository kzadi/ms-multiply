package dev.jzadi.springboot.multiply.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Tentative, créée le 04/11/2022 à 17:29
 *
 * @author Joachim Zadi
 * @version 1.0 du 04/11/2022
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@RequiredArgsConstructor
@Entity(name = "T_TENTATIVE")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    final User user;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    final Multiplication multiplication;

    final int reponse;
    final boolean correct;

    public Tentative() {
        this(null, null, 0, false);
    }
}
