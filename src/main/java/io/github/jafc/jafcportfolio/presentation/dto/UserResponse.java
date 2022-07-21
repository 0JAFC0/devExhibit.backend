package io.github.jafc.jafcportfolio.presentation.dto;

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

    private Integer age;

    private String work;

    private String liveIn;

    private String imageBase64;

    private String field;
}
