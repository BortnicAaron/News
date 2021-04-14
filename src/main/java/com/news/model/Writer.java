package com.news.model;

import com.news.modelEnum.NewsEnum;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Writer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El campo 'dni' no puede estar vacio.")
    private String dni;

    @NotBlank(message = "El campo 'name' no puede estar vacio.")
    @Size(min = 1, max = 30, message = "Name must be between 1 and 30 characters long") 
    private String name;

    @NotBlank(message = "El campo 'lastName' no puede estar vacio.")
    @Size(min = 1, max = 30, message = "lastName must be between 1 and 30 characters long") 
    private String lastName;
    
    @NotBlank(message = "El campo 'pass' no puede estar vacio.")
    private String pass;
}
