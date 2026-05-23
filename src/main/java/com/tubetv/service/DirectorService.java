package com.tubetv.service;

import com.tubetv.entity.Director;
import com.tubetv.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<Director> findAll(){
        return directorRepository.findAll();
    }

    public Director save(Director director) {
        return directorRepository.save(director);
    }

    public Optional<Director> findById(Long id) {
        return directorRepository.findById(id);
    }

    public Optional<Director> update(Long id, Director director) {
        Optional<Director> optional = directorRepository.findById(id);
        if (optional.isPresent()) {
            Director updatedDirector = optional.get();
            updatedDirector.setName(director.getName());

            directorRepository.save(updatedDirector);
            return Optional.of(updatedDirector);
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

}
