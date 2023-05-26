package com.emre.service;

import com.emre.dto.request.AddPointRequestDto;
import com.emre.dto.request.UpdatePointRequestDto;
import com.emre.dto.response.GetUserIdAndUsernameDto;
import com.emre.exception.CommentManagerException;
import com.emre.exception.ErrorType;
import com.emre.manager.IRecipeManager;
import com.emre.manager.IUserProfileManager;
import com.emre.mapper.IPointMapper;
import com.emre.repository.IPointRepository;
import com.emre.repository.entity.Point;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointService extends ServiceManager<Point, String> {
    private final IPointRepository pointRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IRecipeManager recipeManager;
    private final IUserProfileManager userProfileManager;

    public PointService(IPointRepository pointRepository,
                        JwtTokenProvider jwtTokenProvider,
                        IRecipeManager recipeManager,
                        IUserProfileManager userProfileManager) {
        super(pointRepository);
        this.pointRepository = pointRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.recipeManager = recipeManager;
        this.userProfileManager = userProfileManager;
    }

    public Point addPoint(String token, AddPointRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto getUserInfo = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody(); //Sadece userId çekmek için
        Point point = IPointMapper.INSTANCE.addPoint(dto);
        String recipeId = recipeManager.getRecipeIdForIsExistRecipe(dto.getRecipeId()).getBody(); //RecipeId' çekmek için kullanıldı.
        if (!dto.getRecipeId().equals(recipeId)) throw new CommentManagerException(ErrorType.RECIPE_NOT_FOUND);
        point.setUserId(getUserInfo.getUserId());
        Optional<Point> optionalPoint = pointRepository.findByRecipeIdAndUserId(dto.getRecipeId(), getUserInfo.getUserId());
        if (optionalPoint.isEmpty())
            save(point);
        else
            throw new RuntimeException("Zaten puanladın");
        recipeManager.sendPointId(point.getPointId(), recipeId);
        return point;
    }

    public Boolean updatePoint(String token, UpdatePointRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto getUserInfo = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody();
        Optional<Point> optionalPoint = pointRepository.findById(dto.getPointId());
        if (optionalPoint.isEmpty()) throw new CommentManagerException(ErrorType.POINT_NOT_FOUND);
        if (getUserInfo.getUserId().equals(optionalPoint.get().getUserId())) {
            optionalPoint.get().setPoint(dto.getPoint());
            update(IPointMapper.INSTANCE.updatePoint(dto, optionalPoint.get()));
        }
        return true;
    }

    public Boolean deletePoint(String token, String pointId) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new CommentManagerException(ErrorType.INVALID_TOKEN);
        GetUserIdAndUsernameDto getUserInfo = userProfileManager.getUserIdAndUserNameWithAuthId(authId.get()).getBody();
        Optional<Point> point = pointRepository.findById(pointId);
        if (!point.get().getUserId().equals(getUserInfo.getUserId()))
            throw new CommentManagerException(ErrorType.POINT_IS_NOT_THIS_USER);
        deleteById(pointId);
        recipeManager.deletePointInRecipeForDeletedPoint(pointId, point.get().getRecipeId());
        return true;
    }

    public Boolean deletePointForDeletedRecipe(String recipeId) {
        List<Point> points = pointRepository.findByRecipeId(recipeId);
        points.forEach(x -> deleteById(x.getPointId()));
        return true;
    }
}
