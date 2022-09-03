package ds.pirate.backend.service.ArticleService;

import java.util.List;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.HashTagDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.dto.acommentRateDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;

public interface ArticleService {
    String addArticle(ArticleDTO dto, List<String> hashlist);

    ArticleDTO getArticleInfoByAid(Long aid);
    List<acommentDTO> getCommentListByAid(Long aid);
    Long addNewComment(acommentDTO dto);
    Long addNewCommentReply(acommentDTO dto);
    String rateupComment(acommentRateDTO dto);

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


    default acommentDTO commentEntityToDTO(acomments entity){
        acommentDTO dto = acommentDTO.builder()
        .cid(entity.getCid())
        .aid(entity.getArticles().getAid())
        .userid(entity.getAiruser().getUserid())
        .userName(entity.getAiruser().getAirName())
        .commentGroup(entity.getCommentGroup())
        .commnetDepth(entity.getCommnetDepth())
        .commentSorts(entity.getCommentSorts())
        .commentContext(entity.getCommentContext())
        .rate(entity.getRate())
        .articleRate(entity.getArticleRate())
        .regDate(entity.getRegDate())
        .build();
        return dto;
    }


    default acomments commentDTOtoEntity(acommentDTO dto){
        ArticlesList aid = ArticlesList.builder().aid(dto.getAid()).build();
        airUser userid = airUser.builder().userid(dto.getUserid()).build();
        acomments entity = acomments.builder()
        .cid(dto.getCid())
        .articles(aid)
        .airuser(userid)
        .commentGroup(dto.getCommentGroup())
        .commnetDepth(dto.getCommnetDepth())
        .commentSorts(dto.getCommentSorts())
        .commentContext(dto.getCommentContext())
        .rate(dto.getRate())
        .articleRate(dto.getArticleRate())
        .build();
        return entity;
    }

}
