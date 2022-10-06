package ds.pirate.backend.service.RecentArticleService;


import java.util.HashMap;



public interface RecentArticleService {

    void addRecentOpenedArticleNumToAirUser(Long userid, Long aid);
    HashMap<String, Object> getArticlesListDtoByUserId(Long userid, int reqNum);
    
}
