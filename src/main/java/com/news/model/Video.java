
package com.news.model;

import com.news.modelEnum.NewsEnum;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "Video")
public class Video extends News{

    @ElementCollection
    private List<String> urlV;
    
    @Override
    public NewsEnum newsEnum() {
        return NewsEnum.VIDEO;
    }
}
