package ds.pirate.backend.service.LikeUnlikeService;

import java.util.HashMap;

import ds.pirate.backend.dto.likeUnlikeDTO;
import ds.pirate.backend.entity.likeUnlikeList;

public interface LikeUnlikeService {
  HashMap<String, Object> getListAid(Long aid);

  default likeUnlikeList dtoToEntity(likeUnlikeDTO dto) {
    likeUnlikeList entity = likeUnlikeList.builder()
        .aid(dto.getAid())
        .favid(dto.getFavid())
        .userid(dto.getUserid())
        .build();
    return entity;
  }

  default likeUnlikeDTO EntityToDto(likeUnlikeList entity) {
    likeUnlikeDTO dto = likeUnlikeDTO.builder()
        .aid(entity.getAid())
        .favid(entity.getFavid())
        .userid(entity.getUserid())
        .build();
    return dto;
  }
}
