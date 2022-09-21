package ds.pirate.backend.admin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.airUser;

@Repository
public interface AdminUserRepository extends JpaRepository<airUser, Long>, QuerydslPredicateExecutor<airUser> {
    
    @Query("SELECT au.auth FROM airUser au WHERE au.userid =:userid")
    Boolean isAdminCheck(Long userid);
}
