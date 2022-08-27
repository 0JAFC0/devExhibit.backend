package io.github.jafc.jafcportfolio.presentation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private Long id;
    
    private String fullname;
    
    private String password;
    
    private String email;

    private Integer age;

    private String work;

    private String liveIn;

    private String imageBase64;

    private String field;
    
    private List<RoleResponse> roles;
}
