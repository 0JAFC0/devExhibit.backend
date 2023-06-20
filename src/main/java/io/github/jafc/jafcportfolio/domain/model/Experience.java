package io.github.jafc.jafcportfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type")
@Entity
public abstract class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 950)
    private String name;

    @Column(length = 400)
    private String description;

    @Column(length = 9)
    private String duration;
}
