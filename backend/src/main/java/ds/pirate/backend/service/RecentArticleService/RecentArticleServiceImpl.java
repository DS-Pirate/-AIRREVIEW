package ds.pirate.backend.service.RecentArticleService;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import ds.pirate.backend.entity.QArticlesList;
import ds.pirate.backend.entity.QairUser;
import ds.pirate.backend.vo.ArticleCard;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecentArticleServiceImpl implements RecentArticleService{

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory factory;


    @Override
    public void addRecentOpenedArticleNumToAirUser(Long userid, Long aid) {
        QairUser qairUser = QairUser.airUser;
        JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, qairUser);
        jpaUpdateClause
            .where(qairUser.userid
            .eq(userid))
            .set(qairUser.recentArticles, qairUser.recentArticles+aid.toString())
            .execute();
    }

    @Override
    public HashMap<String, Object> getArticlesListDtoByUserId(Long userid, int reqNum) {
        Pageable pageable = PageRequest.of(reqNum, 6);
        QArticlesList qal = QArticlesList.articlesList;
        QairUser aruser = QairUser.airUser;
        String[] articleStringList = factory.select(QairUser.airUser.recentArticles).from(QairUser.airUser).where(QairUser.airUser.userid.eq(userid)).fetchOne().trim().split(",");
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        for (int i = 0; i < articleStringList.length; i++) {
            booleanBuilder.or(QArticlesList.articlesList.aid.eq(Long.valueOf(articleStringList[i])));
        }

        // Page<ArticleCard> result = factory.select(new ArticleCard(aruser.userid ,qal.atitle, qal.context, qal.opencount, qal.regDate)).from(qal).leftJoin(aruser).on(qal.aUser.eq(aruser.userid));

        


        return null;
    }
    
}
