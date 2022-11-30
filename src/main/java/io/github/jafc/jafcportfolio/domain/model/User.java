package io.github.jafc.jafcportfolio.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false, length = 120)
    private String password;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String work;

    @Column(name = "live_in", nullable = false)
    private String liveIn;

    @Column(name = "image_base64", length = 85000)
    private String imageBase64;

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    private String about;

    private boolean isMaintence;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_role",
    joinColumns = {@JoinColumn(name="id_user")},
    inverseJoinColumns = {@JoinColumn(name="id_role")})
    private Set<Role> roles = new HashSet<>();
    
}
