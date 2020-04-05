package com.marcinwo.youtubeapi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDTO {

    @Size(min = 2, max = 10, message = "Edit password.")
    @NotEmpty(message = "Cant be empty.")
    private String password;

}
