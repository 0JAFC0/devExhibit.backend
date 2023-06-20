package io.github.jafc.jafcportfolio.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@DynamicUpdate
@Entity
@Table(name = "T_PROFESSIONAL_EXPERIENCE", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "name"}))
public class ProfessionalExperience extends Experience{

    private String post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}