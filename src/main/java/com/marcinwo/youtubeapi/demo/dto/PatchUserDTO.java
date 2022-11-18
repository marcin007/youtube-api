package com.marcinwo.youtubeapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDTO {

    @Size(min = 2, max = 10, message = "Edit first name.")
    private String firstName;

    @Size(min = 2, max = 10, message = "Edit last name.")
    private String lastName;

    @Size(min = 2, max = 10, message = "Edit password.")
    private String password;

}
