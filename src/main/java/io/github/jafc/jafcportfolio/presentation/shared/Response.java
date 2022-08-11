package io.github.jafc.jafcportfolio.presentation.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class Response<T> implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private T data;
    private List<String> erros = new ArrayList<>();
    private List<String> links = new ArrayList<>();

    public Response(T data) {
        super();
        this.data = data;
    }
}
