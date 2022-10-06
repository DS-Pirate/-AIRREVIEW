package ds.pirate.backend.service.RecentArticleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import ds.pirate.backend.admin.Repository.AdminArticleRepository;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.QArticlesList;
import ds.pirate.backend.entity.QairUser;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.vo.ArticleCard;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class RecentArticleServiceImpl implements RecentArticleService{

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory factory;
    private final AdminArticleRepository adrepo;

    @Override
    @Transactional
    public void addRecentOpenedArticleNumToAirUser(Long userid, Long aid) {
        QairUser qairUser = QairUser.airUser;
        airUser list = factory.select(qairUser).from(qairUser).where(qairUser.userid.eq(userid)).fetchOne();
        String[] artilist = list.getRecentArticles().split(",");
        if(!Arrays.asList(artilist).contains(aid.toString())){
            JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, qairUser);
            jpaUpdateClause
                .where(qairUser.userid
                    .eq(userid))
                .set(qairUser.recentArticles, list.getRecentArticles()+","+aid.toString())
                .execute();
        }else{
            String completedString = "";
            List<String> filteredList = Arrays.stream(artilist).filter(item -> !item.equals(aid.toString())).toList();
            for (String string : filteredList) {
                completedString += string+",";
            }
            JPAUpdateClause jpaUpdateClause = new JPAUpdateClause(em, qairUser);
            jpaUpdateClause
                .where(qairUser.userid
                    .eq(userid))
                .set(qairUser.recentArticles, completedString+aid.toString())
                .execute();
        }
    }

    @Override
    public HashMap<String, Object> getArticlesListDtoByUserId(Long userid, int reqNum) {
        QairUser airuser = QairUser.airUser;
        QArticlesList qarticlelList = QArticlesList.articlesList;
        Pageable pageable = PageRequest.of(reqNum, 10, Sort.unsorted());
        airUser list = factory.select(airuser).from(airuser).where(airuser.userid.eq(userid)).fetchOne();
        BooleanBuilder condition = new BooleanBuilder();
        List<String> arylList = new ArrayList<>();
        for (String ele:list.getRecentArticles().split(",")) {
            arylList.add(ele);
        };
        Collections.reverse(arylList);
        for (String string : arylList) {
            condition.or(qarticlelList.aid.eq(Long.parseLong(string.trim())));

        }
        Page<ArticlesList> articlePageable =  adrepo.findAll(condition, pageable);
        List<ArticleCard> articlecard = adrepo.findAll(condition, pageable).stream().map((Function<ArticlesList, ArticleCard>) v->{
            ArticleCard tmp = new ArticleCard(list.getAirName(), v.getAtitle(), v.getContext(), v.getOpencount(), null, v.getRegDate());
            if(!v.getImages().isEmpty()){
                tmp.setFilename(v.getImages().get(0).getFileName());
            }else{
                tmp.setFilename("basic.png");
            }
            return tmp;
        }).toList();

        HashMap<String, Object> hash = new HashMap<>();
        hash.put("cardInfo", articlecard);
        hash.put("totalPage", articlePageable.getTotalPages());
        return hash;
    }
    
}
