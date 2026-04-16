package com.tubetv.mapper;

import com.tubetv.controller.request.StreamingRequest;
import com.tubetv.controller.response.StreamingResponse;
import com.tubetv.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    /*
    * -To Category
    * transformar o Record category request em Category
    *
    * -Padrão Builder
     * Uma forma simplificada de fazer o mapeamento para a entidade, atraves da annotation @Builder na classe principal
    * */

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}
