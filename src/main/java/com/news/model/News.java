package com.news.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.news.modelEnum.NewsEnum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.AccessType;

@Data
@NoArgsConstructor
@Entity(name="News")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true, property = "newsEnum")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Image.class, name = "IMAGE"),
    @JsonSubTypes.Type(value = Video.class, name = "VIDEO"),
})
public abstract class News implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "El campo 'title' no puede estar vacio.")
    private String title;
    @NotBlank(message = "El campo 'description' no puede estar vacio.")
    private String description;
    @NotBlank(message = "El campo 'text' no puede estar vacio.")
    private String text;
    
    
    @AccessType(AccessType.Type.PROPERTY)
    public abstract NewsEnum newsEnum();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private Writer writer;
}
