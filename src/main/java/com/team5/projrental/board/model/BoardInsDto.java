package com.team5.projrental.board.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BoardInsDto {
    private Integer iusers;
    private String title;
    private String contents;
    private List<MultipartFile> storedPic;
}
