package com.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wind {

    @SerializedName("speed")
    private Double speed;
    
    @SerializedName("deg")
    private Double deg;
}
