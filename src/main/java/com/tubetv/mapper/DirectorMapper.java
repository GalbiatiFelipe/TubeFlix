package com.tubetv.mapper;

import com.tubetv.controller.request.DirectorRequest;
import com.tubetv.controller.response.DirectorResponse;
import com.tubetv.entity.Director;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DirectorMapper {

    public static Director toDirector(DirectorRequest directorRequest) {
        return Director
                .builder()
                .name(directorRequest.name())
                .build();
    }

    public static DirectorResponse toDirectorResponse(Director director) {
        return DirectorResponse
                .builder()
                .id(director.getId())
                .name(director.getName())
                .build();
    }

}
