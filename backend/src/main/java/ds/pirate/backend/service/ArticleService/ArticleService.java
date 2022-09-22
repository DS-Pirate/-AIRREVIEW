package ds.pirate.backend.service.ArticleService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import ds.pirate.backend.vo.EmbedCard;
import ds.pirate.backend.vo.search;
import org.springframework.data.domain.Pageable;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.HashTagDTO;
import ds.pirate.backend.dto.SaveDTO;
import ds.pirate.backend.dto.acommentDTO;
import ds.pirate.backend.dto.acommentRateDTO;
import ds.pirate.backend.dto.likeUnlikeDTO;
import ds.pirate.backend.dto.reportDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.SaveList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.likeUnlikeList;
import ds.pirate.backend.entity.reportList;

public interface ArticleService {
    String addArticle(ArticleDTO dto, List<String> hashlist);
    ArticleDTO getArticleInfoByAid(Long aid);
    Long addNewComment(acommentDTO dto);
    Long addNewCommentReply(acommentDTO dto);
    String rateupComment(acommentRateDTO dto);
    Double getArticleAvgRating(Long aid);
    String addArticleReport(reportDTO dto);
    String addLikeUnlike(likeUnlikeDTO dto);
    String addSave(SaveDTO dto);
    String removeComment(acommentDTO dto);
    HashMap<String, Boolean> getFunctionBtnStatusByUserid(Long userid, Long aid);
    HashMap<String, String> getSubscardInfo(Long aid, Long userid);
    List<ArticleDTO> getSearchCardInfo(Long aid);
    String subsFunction(Long aid, Long userid);
    HashMap<String, Object> getCommentListByAid2(Long aid, Pageable pageable);
    HashMap<String, Object> getCommentListByAidTwo2(Long aid, Pageable pageable, Long userid);
    HashMap<String, Object> getCardInfosByHashTagName(Long aid, Pageable pageable);
    ArticleDTO CheckBeforeModifyArticle(Long aid, Long userid);
    String ArticleModify(ArticleDTO dto, List<String> tags);
//    List<Object[]> getArticleList();
     List<EmbedCard> getArticleList();
    List<acommentDTO>getListByUserIdAndAuthorId(Long userid, Long authorid);
    Optional<Object[]> getSearchList(String search);


    default ArticlesList dtoToEntity(ArticleDTO dto) {
        ArticlesList aentity = ArticlesList.builder()
                .aid(dto.getAid())
                .atitle(dto.getAtitle())
                .context(dto.getContext().getBytes())
                .opend(dto.isOpened())
                .shareable(dto.isShareable())
                .aUser(dto.getUserId())
                .opencount(dto.getOpencount())
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
                .regdate(entity.getRegDate())
                .opencount(entity.getOpencount())
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

    default reportList reportDTOtoEntity(reportDTO dto){
        reportList entity = reportList.builder().reid(dto.getReid())
        .articles(ArticlesList.builder().aid(dto.getArticleid()).build())
        .userid(airUser.builder().userid(dto.getUserid()).build())
        .reportContext(dto.getReportContext()).build();
        return entity;
    }

    default reportDTO reportEntitytoDTO(reportList entity){
        reportDTO dto = reportDTO.builder().reid(entity.getReid())
        .articleid(entity.getArticles().getAid())
        .userid(entity.getUserid().getUserid())
        .reportContext(entity.getReportContext()).build();
        return dto;
    }

    default likeUnlikeList favoDTOtoEntity(likeUnlikeDTO dto){
        likeUnlikeList entity = likeUnlikeList.builder()
        .favid(dto.getFavid())
        .aid(dto.getAid())
        .userid(dto.getUserid())
        .build();
        return entity;
    }

    default likeUnlikeDTO favoEntityToEDTO(likeUnlikeList entity){
        likeUnlikeDTO dto = likeUnlikeDTO.builder()
        .favid(entity.getFavid())
        .aid(entity.getAid())
        .userid(entity.getUserid())
        .build();
        return dto;
    }

    default SaveList saveDTOtoEntity(SaveDTO dto){
        SaveList entity = SaveList.builder()
        .savid(dto.getSavid())
        .aid(dto.getAid())
        .userid(dto.getUserid())
        .build();
        return entity;
    }

    default SaveDTO saveEntityToDTO(SaveList entity){
        SaveDTO dto = SaveDTO.builder()
        .savid(entity.getSavid())
        .aid(entity.getAid())
        .userid(entity.getUserid())
        .build();
        return dto;
    }

}
