package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.Ranking;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long>, QuerydslPredicateExecutor<Ranking>{
    
}
