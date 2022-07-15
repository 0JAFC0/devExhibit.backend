package io.github.jafc.jafcportfolio.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Date year;

    @Column
    private String work;

    @Column(name = "live_in")
    private String liveIn;

    @Column
    private String imageBase64;

    @Column
    private String field;

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Review> publications = new ArrayList<>();

    public boolean addExperience(Experience experience){
        if(!(experiences.stream().allMatch(experienceM -> (experienceM.equals(experience))))) {
            return this.experiences.add(experience);
         }
        return false;
    }

    public boolean removeExperience(Experience experience) {
        if(!(experiences.stream().allMatch(experienceM -> (experienceM.equals(experience))))) {
            return this.experiences.remove(experience);
        }
        return false;
    }

    public boolean addSkills(Skill skill){
        if(!(skills.stream().allMatch(skillM -> (skillM.equals(skill))))) {
            return this.skills.add(skill);
         }
        return false;
    }

    public boolean removeExperience(Skill skill) {
        if(!(skills.stream().allMatch(skillM -> (skillM.equals(skill))))) {
            return this.skills.remove(skill);
         }
        return false;
    }

    public boolean addProject(Project project){
        if(!(projects.stream().allMatch(projectM -> (projectM.equals(project))))) {
            return this.projects.add(project);
         }
        return false;
    }

    public boolean removeProject(Project project){
        if(!(projects.stream().allMatch(projectM -> (projectM.equals(project))))) {
            return this.projects.remove(project);
         }
        return false;
    }

    public boolean addPublication(Review review){
        if(!(publications.stream().allMatch(publicationM -> (publicationM.equals(review))))) {
            return this.publications.add(review);
         }
        return false;
    }

    public boolean removePublication(Review review){
        if(!(publications.stream().allMatch(publicationM -> (publicationM.equals(review))))) {
            return this.publications.remove(review);
         }
        return false;
    }
}
