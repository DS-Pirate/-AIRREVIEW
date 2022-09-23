package ds.pirate.backend.service.EmbedService;

import ds.pirate.backend.vo.Embed;
import ds.pirate.backend.vo.EmbedCard;

public interface EmbedService {
    Embed getEmbedInfo(Long article);

}
