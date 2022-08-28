package ds.pirate.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.acomments;

public interface CommentRepository extends JpaRepository<acomments, Long>{
    

    @Query("select ct from acomments ct where articles_aid=:aid order by comment_group")
    Optional<List<acomments>> getListByAid(Long aid);
}
