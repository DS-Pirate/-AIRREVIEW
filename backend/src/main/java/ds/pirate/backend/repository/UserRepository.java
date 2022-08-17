package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.airUser;

public interface UserRepository extends JpaRepository<airUser, Long>{
    
}
