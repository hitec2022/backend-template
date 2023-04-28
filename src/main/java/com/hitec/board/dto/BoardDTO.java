package com.hitec.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {
    private long id;

    @NotBlank(message = "title is mandatory")
    @Size(message="title size 2 more", min = 2, max = 14)
    private String title;
    @NotBlank(message = "content is mandatory")
    @Size(message="content size 1 more", min = 1, max = 500)
    private String content;

    private String userName;
}
