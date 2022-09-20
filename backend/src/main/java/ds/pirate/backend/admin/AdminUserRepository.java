package ds.pirate.backend.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ds.pirate.backend.entity.airUser;

public interface AdminUserRepository extends JpaRepository<airUser, Long>, QuerydslPredicateExecutor<airUser> {
    
}
