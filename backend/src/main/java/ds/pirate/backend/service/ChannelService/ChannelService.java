package ds.pirate.backend.service.ChannelService;

import org.springframework.data.domain.Page;

import ds.pirate.backend.repository.ArticleRepository.getMyChannelArticleList;

public interface ChannelService {
    Page<getMyChannelArticleList> articleListByUserid(Long userid, Integer pageNum, String search);
    void uploadCHImage(Long userid, String fileName);
    String getCHImage(Long userid);
}
