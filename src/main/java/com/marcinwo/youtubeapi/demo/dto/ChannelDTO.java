package com.marcinwo.youtubeapi.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {

    private Long id;
    private String name;
    private String description;
    private String user;

}
