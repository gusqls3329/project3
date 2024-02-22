package com.team5.projrental.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.projrental.common.exception.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsDto {
    @JsonIgnore
    private Integer iboard;
    @JsonIgnore
    private Integer iuser;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 2, max = 50)
    private String title;
    @NotBlank(message = ErrorMessage.CAN_NOT_BLANK_EX_MESSAGE)
    @Length(min = 2, max = 2000)
    private String contents;

    @JsonIgnore
    private List<MultipartFile> storedPic;
}
