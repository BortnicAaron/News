package com.news.services;

import com.news.model.News;
import com.news.model.Writer;
import com.news.utils.EntityURLBuilder;
import com.news.utils.PostResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.news.repository.NewsRepository;
import com.news.utils.PaginationResponse;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class NewService {
    
    private static final String PERSON_PATH = "news";
    private final NewsRepository newsRepository;
    private final WriterService writerService;
        
    @Autowired
    public NewService(NewsRepository newRepository,WriterService writerService) {
        this.newsRepository = newRepository;
        this.writerService = writerService;
    }

    public News getNewsById(Integer id) {
        return newsRepository.findById(id)
                 .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public PostResponse addNew(News news) {
        final News newsSaved = newsRepository.save(news);
                return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(PERSON_PATH, newsSaved.getId().toString()))
                .build();
    }
    
    public List<News> getAll() {
        return newsRepository.findAll();
    }
    
    public void deleteNewsById(Integer id) {
        newsRepository.deleteById(id);
    }
     
    public void NewsAndWriter(Integer idN,Integer idW) {
       News news = getNewsById(idN);
       Writer person =  writerService.getWriterById(idW);
       news.setWriter(person);
       newsRepository.save(news);
    }
    
    public PaginationResponse<News> getAll(Integer page, Integer size) {
        if (!Objects.isNull(page) && !Objects.isNull(size)) {
            Pageable pageable = PageRequest.of(page, size);
            Page<News> pages = newsRepository.findAll(pageable);
            return new PaginationResponse<>(pages.getContent(),
                    pages.getTotalPages(),
                    pages.getTotalElements());
        }
        List<News> newsList = newsRepository.findAll();
        return new PaginationResponse<>(newsList, 1, (long) newsList.size());
    }
}
