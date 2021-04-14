package com.news.controller;

import com.news.model.News;
import com.news.services.NewService;
import com.news.utils.PaginationResponse;
import com.news.utils.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewService newService;
    
    @Operation(summary = "Dar de alta una noticia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Noticia creada exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, verificar el Json",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    @PostMapping()
    public ResponseEntity<?> PostNew(@RequestBody News Newf) {
        final PostResponse postResponse = newService.addNew(Newf);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }
    
    
    @Operation(summary = "Traer una noticia")
    @GetMapping("/{id}")
    public News GetNews(@PathVariable Integer id) {
        return newService.getNewsById(id);
    }

    /*@Operation(summary = "Traer todas las noticias")
    @GetMapping
    public List<News> getNewsAll() {
        return newService.getAll();
    }*/
    
    @Operation(summary = "Eliminar noticia")
    @DeleteMapping("/{id}")
    public void DeleteNews(@PathVariable Integer id) {
        newService.deleteNewsById(id);
    }
    
    @Operation(summary = "Relacionar Noticia con Escritor")
    @PutMapping("/{idN}/writer/{idW}")
    public void PutNewsAndWriter(@PathVariable Integer idN,@PathVariable Integer idW) {
        System.out.println("holaaa " +idN+"-"+idW);
        newService.NewsAndWriter(idN,idW);
    }

    @Operation(summary = "Traer todas las noticias")
    @GetMapping    
    public PaginationResponse<News> getAll(@RequestParam(value = "size", defaultValue = "20") Integer size,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return newService.getAll(page, size);
    }
}
