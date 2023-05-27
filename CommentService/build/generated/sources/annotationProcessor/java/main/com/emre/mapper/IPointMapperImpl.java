package com.emre.mapper;

import com.emre.dto.request.AddPointRequestDto;
import com.emre.dto.request.UpdatePointRequestDto;
import com.emre.repository.entity.Point;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-26T23:46:37+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IPointMapperImpl implements IPointMapper {

    @Override
    public Point addPoint(AddPointRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Point.PointBuilder<?, ?> point = Point.builder();

        point.point( dto.getPoint() );
        point.recipeId( dto.getRecipeId() );

        return point.build();
    }

    @Override
    public Point updatePoint(UpdatePointRequestDto dto, Point point) {
        if ( dto == null ) {
            return point;
        }

        if ( dto.getPointId() != null ) {
            point.setPointId( dto.getPointId() );
        }
        if ( dto.getPoint() != null ) {
            point.setPoint( dto.getPoint() );
        }

        return point;
    }
}
