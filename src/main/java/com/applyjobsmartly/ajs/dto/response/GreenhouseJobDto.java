package com.applyjobsmartly.ajs.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GreenhouseJobDto {

    private Long id;
    private String title;
    private String absoluteUrl;
    private Location location;

    @Getter
    @Setter
    public static class Location {
        private String name;
    }
}
