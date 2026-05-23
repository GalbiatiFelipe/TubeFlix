package com.tubetv.controller;

import com.tubetv.controller.request.DirectorRequest;
import com.tubetv.controller.response.DirectorResponse;
import com.tubetv.entity.Director;
import com.tubetv.mapper.DirectorMapper;
import com.tubetv.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tubetv/director")
@RequiredArgsConstructor
public class DirectorControllerImpl {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorResponse>> findAll() {
        List<DirectorResponse> directors = directorService.findAll()
                .stream()
                .map(DirectorMapper::toDirectorResponse)
                .toList();
        return ResponseEntity.ok(directors);
    }

    @PostMapping
    public ResponseEntity<DirectorResponse> save(@RequestBody DirectorRequest directorRequest) {
        Director newDirector = DirectorMapper.toDirector(directorRequest);
        Director savedDirector = directorService.save(newDirector);
        return ResponseEntity.status(HttpStatus.CREATED).body(DirectorMapper.toDirectorResponse(savedDirector));
    }


}
