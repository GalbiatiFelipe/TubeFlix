package com.tubetv.controller.response;

import lombok.Builder;

@Builder
public record DirectorResponse(Long id, String name) {
}
