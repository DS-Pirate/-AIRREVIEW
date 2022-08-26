package ds.pirate.backend.service.ArticleService;

import java.util.List;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.HashTagDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;

public interface ArticleService {
    String addArticle(ArticleDTO dto, List<String> hashlist);

    ArticleDTO getArticleInfoByAid(Long aid);

    default ArticlesList dtoToEntity(ArticleDTO dto) {
        ArticlesList aentity = ArticlesList.builder()
                .atitle(dto.getAtitle())
                .context(dto.getContext().getBytes())
                .opend(dto.isOpened())
                .shareable(dto.isShareable())
                .aUser(dto.getUserId())
                .build();
        return aentity;
    }

    default HashTags listToHSEntity(String hashlist) {
        HashTags tags = HashTags
                .builder()
                .hashTagName(hashlist)
                .build();
        return tags;
    }

    default HashTagDTO hEntityToDTO(HashTags list) {
        HashTagDTO dto = HashTagDTO
                .builder()
                .hid(list.getHid())
                .hashTagName(list.getHashTagName())
                .articles(list.getArticles()).build();
        return dto;

    }

    default ArticleDTO EntityToDTO(ArticlesList entity) {
        ArticleDTO dto = ArticleDTO.builder()
                .aid(entity.getAid())
                .atitle(entity.getAtitle())
                .context(entity.updateContextToString(entity.getContext()))
                .opened(entity.isOpend())
                .shareable(entity.isShareable())
                .userId(entity.getAUser())
                .build();
        return dto;
    }

}
