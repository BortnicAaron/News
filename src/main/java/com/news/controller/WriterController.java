package com.news.controller;

import com.news.model.Writer;
import com.news.model.dto.WriterDTO;
import com.news.services.WriterService;
import com.news.utils.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/writer")
public class WriterController {
    
    @Autowired
    private WriterService writerService;
    
    @Autowired
    private ConversionService conversionService;

    
    @Operation(summary = "Dar de alta una Writer")
    @PostMapping()
    public ResponseEntity PostPerson(@RequestBody Writer writer) {
        final PostResponse postResponse = writerService.addWriter(writer);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }
    
    @Operation(summary = "Buscar a una Writer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Writer creada exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request, verificar el Json",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    @GetMapping("/{id}")
        public Writer getWriterByID(@PathVariable Integer id) {
        return writerService.getWriterById(id);
    }
        
    @Operation(summary = "Buscar a un Writer DTO: (din,name,lastName)")
    @GetMapping("/dto/{id}")
     public WriterDTO getWriterDtoByID(@PathVariable Integer id) {
        return conversionService.convert(writerService.getWriterById(id), WriterDTO.class);
    }    

    @Operation(summary = "Traer todas las Writer")
    @GetMapping
    public List<Writer> getWriterAll() {
        return writerService.getAll();
    }
    
    @Operation(summary = "Eliminar Writer")
    @DeleteMapping("/{id}")
    public void DeleteWriter(@PathVariable Integer id) {
        writerService.deleteWriterById(id);
    }
    
}
