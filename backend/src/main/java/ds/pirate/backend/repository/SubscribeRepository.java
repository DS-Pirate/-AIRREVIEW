package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.subscribList;

public interface SubscribeRepository extends JpaRepository<subscribList, Long>{
    
}
