package ds.pirate.backend.service.RankingService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import ds.pirate.backend.dto.rankingDTO;
import ds.pirate.backend.entity.QRanking;
import ds.pirate.backend.entity.Ranking;
import ds.pirate.backend.repository.RankingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{

    private final JPAQueryFactory queryFactory;
    private final RankingRepository rkrepo;
    
    @PersistenceContext
    EntityManager em;


    @Override
    public List<rankingDTO> gerRanking() {
        QRanking qRanking = QRanking.ranking;
        List<Ranking> result = queryFactory.select(qRanking).from(qRanking).orderBy(qRanking.searchCount.desc()).limit(10).fetch();

        return result.stream().map(new Function<Ranking,rankingDTO>() {
            @Override
            public rankingDTO apply(Ranking t) {
                return entityToDTO(t);
            }
        }).toList(); //jdk17부터 Collectors 안붙여도됨
    }

    @Override
    @Transactional
    public void addRanking(String keyword) {
        QRanking qRanking = QRanking.ranking;
        Optional<List<Ranking>> result = Optional.of(queryFactory.select(qRanking).from(qRanking).where(qRanking.keyword.contains(keyword)).fetch());
        if(result.get().isEmpty()){
            rkrepo.save(Ranking.builder().keyword(keyword).searchCount(1L).build());
        }else{
            result.get().forEach(v->{
                JPAUpdateClause updateClause = new JPAUpdateClause(em, qRanking);
                updateClause.set(qRanking.searchCount, v.getSearchCount()+1).where(qRanking.rkid.eq(v.getRkid())).execute();
            });
        }


    }

}
