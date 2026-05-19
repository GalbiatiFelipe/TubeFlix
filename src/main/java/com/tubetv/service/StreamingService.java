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

    public Optional<Streaming> update(Long id, Streaming streaming) {
        Optional<Streaming> optional = streamingRepository.findById(id);
        if (optional.isPresent()) {
            Streaming updatedStreaming = optional.get();
            updatedStreaming.setName(streaming.getName());

            streamingRepository.save(updatedStreaming);
            return Optional.of(updatedStreaming);
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        streamingRepository.deleteById(id);
    }

}
