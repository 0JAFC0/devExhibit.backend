package io.github.jafc.jafcportfolio.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    
    @ApiModelProperty(notes = "Id do review salvo no banco")
    private Long id;

    @ApiModelProperty(notes = "Titulo do review")
    private String title;

    @ApiModelProperty(notes = "Conteudo do review")
    private String content;

    @ApiModelProperty(notes = "Imagem do review em base64")
    private String imageBase64;
    @ApiModelProperty(notes = "Objeto usuario nele vai conter informações como id")
    private UserRequest user;

}
