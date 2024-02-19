package com.Geko.pruebas.Mapper;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase que extiende ModelMapper y proporciona métodos adicionales para mapear objetos entre diferentes clases.
 */
@Component
public class GenericModelMapper extends ModelMapper {
    private static GenericModelMapper instance;

    /**
     * Constructor privado para garantizar que la clase sea un singleton.
     */
    private GenericModelMapper() {
    }

    /**
     * Método para obtener una única instancia de GenericModelMapper.
     * @return La instancia única de GenericModelMapper.
     */
    public static GenericModelMapper singleModelMapper() {
        if (instance == null) {
            instance = new GenericModelMapper();
        }
        return instance;
    }

    /**
     * Método para realizar el mapeo de un objeto fuente a un objeto de destino de una clase específica.
     * @param source El objeto fuente que se desea mapear.
     * @param targetClass La clase del objeto de destino.
     * @param <S> El tipo del objeto fuente.
     * @param <T> El tipo del objeto de destino.
     * @return El objeto mapeado de la clase de destino.
     */
    public <S, T> T Mapper(S source, Class<T> targetClass) {
        return this.mapper().map(source, targetClass);
    }

    /**
     * Método para realizar el mapeo de una lista de objetos fuente a una lista de objetos de destino de una clase específica.
     * @param Source La lista de objetos fuente que se desea mapear.
     * @param targetClass La clase de los objetos de destino.
     * @param <S> El tipo de los objetos fuente en la lista.
     * @param <T> El tipo de los objetos de destino en la lista.
     * @return La lista de objetos mapeados de la clase de destino.
     */
    public <S, T> List<T> ListMapper(List<S> Source, Class<T> targetClass) {
        return Source.stream().map(element -> this.mapper().map(element, targetClass)).collect(Collectors.toList());
    }

    /**
     * Método para realizar el mapeo de un conjunto de objetos fuente a un conjunto de objetos de destino de una clase específica.
     * @param Source El conjunto de objetos fuente que se desea mapear.
     * @param targetClass La clase de los objetos de destino.
     * @param <S> El tipo de los objetos fuente en el conjunto.
     * @param <T> El tipo de los objetos de destino en el conjunto.
     * @return El conjunto de objetos mapeados de la clase de destino.
     */
    public <S, T> Set<T> ListMapperDto(Set<S> Source, Class<T> targetClass) {
        return Source.stream().map(element -> this.mapper().map(element, targetClass)).collect(Collectors.toSet());
    }

    /**
     * Método privado para crear una nueva instancia de ModelMapper con una estrategia de coincidencia estricta.
     * @return Una nueva instancia de ModelMapper con estrategia de coincidencia estricta.
     */
    private ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}