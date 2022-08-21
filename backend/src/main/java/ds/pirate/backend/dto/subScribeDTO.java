package ds.pirate.backend.dto;

import ds.pirate.backend.entity.airUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class subScribeDTO {
    private Long sbid;
    private Long targetId;
    private airUser userid;
}
