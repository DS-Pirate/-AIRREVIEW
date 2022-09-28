package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.ImagesList;

public interface ImageRepository extends JpaRepository<ImagesList, Long>{
}
