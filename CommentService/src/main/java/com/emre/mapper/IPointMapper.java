package com.emre.mapper;

import com.emre.dto.request.AddPointRequestDto;
import com.emre.dto.request.UpdatePointRequestDto;
import com.emre.repository.entity.Point;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPointMapper {
    IPointMapper INSTANCE = Mappers.getMapper(IPointMapper.class);

    Point addPoint(final AddPointRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Point updatePoint(final UpdatePointRequestDto dto, @MappingTarget Point point);
}
