package com.emre.controller;

import com.emre.dto.request.AddPointRequestDto;
import com.emre.dto.request.UpdatePointRequestDto;
import com.emre.repository.entity.Point;
import com.emre.service.PointService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(POINT)
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @PostMapping(POINT_ADD + "/{token}")
    public ResponseEntity<Point> addPoint(@PathVariable String token, @RequestBody @Valid AddPointRequestDto dto) {
        return ResponseEntity.ok(pointService.addPoint(token, dto));
    }

    @PutMapping(UPDATE + "/{token}")
    public ResponseEntity<Boolean> updatePoint(@PathVariable String token, @RequestBody UpdatePointRequestDto dto) {
        return ResponseEntity.ok(pointService.updatePoint(token, dto));
    }

    @DeleteMapping(DELETE + "/{token}/{pointId}")
    public ResponseEntity<Boolean> deletePoint(@PathVariable String token, @PathVariable String pointId) {
        return ResponseEntity.ok(pointService.deletePoint(token, pointId));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Point>> findAll() {
        return ResponseEntity.ok(pointService.findAll());
    }

    //========================
    @PostMapping("/delete-point-for-deleted-recipe/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> deletePointForDeletedRecipe(@PathVariable String recipeId) {
        return ResponseEntity.ok(pointService.deletePointForDeletedRecipe(recipeId));
    }

}
