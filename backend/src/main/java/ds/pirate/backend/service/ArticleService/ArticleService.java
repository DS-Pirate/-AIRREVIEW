package ds.pirate.backend.service.ArticleService;

import java.util.List;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.HashTagDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;

public interface ArticleService {
    String addArticle(ArticleDTO dto, List<String> hashlist);

    default ArticlesList dtoToEntity(ArticleDTO dto){
        ArticlesList entity = ArticlesList.builder()
        .atitle(dto.getAtitle())
        .context(dto.getContext())
        .opend(dto.isOpened())
        .shareable(dto.isShareable())
        .build();
        return entity;
    }

    default HashTags listToHSEntity(List<String> hashlist){
        
        HashTags tags = HashTags.builder().build();
        return tags;
    }

}
