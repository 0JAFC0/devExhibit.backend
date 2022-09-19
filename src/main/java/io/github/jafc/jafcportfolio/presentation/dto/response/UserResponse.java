package io.github.jafc.jafcportfolio.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
    private String fullname;
    
    private String email;

    private Integer age;

    private String work;

    private String liveIn;

    private String imageBase64;

    private String field;

    private String about;
    
}
