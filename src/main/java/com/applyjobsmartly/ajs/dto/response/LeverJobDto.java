package com.applyjobsmartly.ajs.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeverJobDto {

    private String id;
    private String text;
    private String hostedUrl;
    private Categories categories;

    @Getter
    @Setter
    public static class Categories {
        private String location;
        private String team;
    }
}
