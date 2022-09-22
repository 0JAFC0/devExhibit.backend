package io.github.jafc.jafcportfolio.infrastructure.utils.modelmapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

    private ModelMapper modelMapper;

    public <T> T convert(Object origin, Class<T> target) {
        modelMapper = init();
        return modelMapper.map(origin, target);
    }

    private ModelMapper init() {
        if(Objects.isNull(modelMapper)) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

    public <S, T> List<T> convertList(List<S> origin, Class<T> target) {
        return origin.stream()
        .map(element -> convert(element, target))
        .collect(Collectors.toList());
    }
}
