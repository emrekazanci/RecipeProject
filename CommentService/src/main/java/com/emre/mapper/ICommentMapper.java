package com.emre.mapper;

import com.emre.dto.request.AddCommentRequestDto;
import com.emre.dto.request.UpdateCommentRequestDto;
import com.emre.repository.entity.Comment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentMapper {
    ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);

    Comment addCommentDtoToComment(final AddCommentRequestDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment updateCommentDtoToComment(final UpdateCommentRequestDto dto, @MappingTarget Comment comment);
}
