package io.github.jafc.jafcportfolio.presentation.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String password;

    private Date year;

    private String work;

    private String liveIn;

    private String imageBase64;

    private String field;

    private List<ExperienceResponse> experiences = new ArrayList<>();

    private List<SkillResponse> skills = new ArrayList<>();

    private List<ProjectResponse> projects = new ArrayList<>();

    private List<ReviewResponse> publications = new ArrayList<>();
}
