package com.tubetv.service;

import com.tubetv.entity.Streaming;
import com.tubetv.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    /*
     * injeção de dependencia
     * -Com anotação @Autowired
     * -Usando Lombok, com anotação @RequiredArgsConstructor
     * -Criando um construtor para o atributo
     * */
    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll(){
        return streamingRepository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(id);
    }

    public void delete(Long id) {
        streamingRepository.deleteById(id);
    }

}
