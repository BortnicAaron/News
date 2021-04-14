package com.news.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiClimaResponse {

    @SerializedName("coord")
    private Coord coord;
    
    /*@SerializedName("weather")
    private String Mapweather;*/
    
    @SerializedName("base")
    private String base;
    
    @SerializedName("main")
    private Main main;
    
    @SerializedName("visibility")
    private Double visibility;
    
    @SerializedName("wind")
    private Wind wind;
    
    /*@SerializedName("clouds")
    private Integer clouds;
    */
    @SerializedName("dt")
    private Integer dt;
    /*
    @SerializedName("sys")
    private Integer sys;
    */
    @SerializedName("timezone")
    private Double timezone;
    
    @SerializedName("id")
    private Integer id;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("cod")
    private Integer cod;
    
    
}
