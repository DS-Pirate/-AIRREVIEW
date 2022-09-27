package ds.pirate.backend.repository;

import ds.pirate.backend.entity.HashTags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.ImagesList;

public interface ImageRepository extends JpaRepository<ImagesList, Long>{
}
