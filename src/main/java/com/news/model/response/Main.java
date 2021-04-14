package com.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Main {

    @SerializedName("temp")
    private Double temp;
    
    @SerializedName("feels_like")
    private Double feels_like;
    
    @SerializedName("temp_min")
    private Double temp_min;
    
    @SerializedName("temp_max")
    private Double temp_max;
    
    @SerializedName("pressure")
    private Double pressure;
    
    @SerializedName("humidity")
    private Double humidity;
}
