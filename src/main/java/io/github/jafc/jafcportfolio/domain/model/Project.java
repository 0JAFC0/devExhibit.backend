package io.github.jafc.jafcportfolio.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.jafc.jafcportfolio.domain.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "T_PROJECTS", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = true)
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private String imageBase64;

    @Column(name = "url_project")
    private String urlProject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false)
    @JsonIgnore
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
