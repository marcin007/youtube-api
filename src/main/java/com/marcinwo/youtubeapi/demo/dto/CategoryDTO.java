package com.marcinwo.youtubeapi.demo.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @NonNull
    private String name;

}
