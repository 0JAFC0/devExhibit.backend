package io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ModelMapperService {

    private ModelMapper modelMapper;

    public <O,T> T convert(O origin, Class<T>  target) {
        modelMapper = init();
        return modelMapper.map(origin, target);
    }

    public ModelMapper init() {
        if(Objects.isNull(modelMapper)) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

    public <O, T> List<T> convertList(List<O> origin, Class<T> target) {
        return origin.stream()
        .map(element -> convert(element, target))
        .toList();
    }
}
