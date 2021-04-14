
package com.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Coord {

    @SerializedName("lon")
    private Double lon;
    
    @SerializedName("lat")
    private Double lat;
        
}
