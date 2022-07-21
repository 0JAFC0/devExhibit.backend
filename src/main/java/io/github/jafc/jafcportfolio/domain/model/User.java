package io.github.jafc.jafcportfolio.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@DynamicUpdate
@Table(name = "users")
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private Date year;

    @Getter
    @Setter
    @Column(nullable = false)
    private String work;

    @Getter
    @Setter
    @Column(name = "live_in",nullable = false)
    private String liveIn;

    @Getter
    @Setter
    @Column(nullable = false)
    private String imageBase64;

    @Getter
    @Setter
    @Column(nullable = false)
    private String field;
    
}
