package ds.pirate.backend.dto;

import java.time.LocalDateTime;

import ds.pirate.backend.entity.ArticlesList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageListDTO {
    private Long iid; // pk
    private String fileName;
    private int idx;
    private ArticlesList articles;
    private LocalDateTime regDate;
}
