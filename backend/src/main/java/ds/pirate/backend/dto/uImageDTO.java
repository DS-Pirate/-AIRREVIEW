package ds.pirate.backend.dto;

import java.time.LocalDateTime;

import ds.pirate.backend.entity.airUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class uImageDTO {
    private Long iid; 
    private String fileName;
    private int idx;
    private airUser airuser;
    private LocalDateTime regDate;
}
