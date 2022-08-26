package ds.pirate.backend.dto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.acomments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private Long aid;
    private String atitle;

    @Lob
    private String context;

    private boolean opened;
    private boolean shareable;
    private List<String> tags;

    private Long userId;

    @Builder.Default
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<ImagesList> images = new ArrayList<>();

    private List<acomments> cgroup;
}
