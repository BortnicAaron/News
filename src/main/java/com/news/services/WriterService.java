package com.news.services;

import com.news.model.Writer;
import com.news.utils.EntityURLBuilder;
import com.news.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import com.news.repository.WriterRepository;

@Service
public class WriterService {

    private static final String PERSON_PATH = "person";
    private WriterRepository WriterRepository;

    @Autowired
    public WriterService(WriterRepository writerRepository) {
        this.WriterRepository = writerRepository;
    }

    public Writer getWriterById(Integer id) {
        return WriterRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public PostResponse addWriter(Writer writer) {
        final Writer personSaved = WriterRepository.save(writer);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(PERSON_PATH, personSaved.getId().toString()))
                .build();
    }
    
    public List<Writer> getAll() {
        return WriterRepository.findAll();
    }
    
    public void deleteWriterById(Integer id) {
        WriterRepository.deleteById(id);
    }
    
}
