package ds.pirate.backend.service.SubscribeService;

import java.util.List;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.subScribeDTO;
import ds.pirate.backend.entity.subscribList;

public interface SubscribeService {
  List<ArticleDTO> getListAid(Long aid);

  default subscribList dtoToEntity(subScribeDTO dto) {
    subscribList entity = subscribList.builder()
        .sbid(dto.getSbid())
        .targetId(dto.getTargetId())
        .userid(dto.getUserid())
        .build();
    return entity;
  }

  default subScribeDTO EntityToDto(subscribList entity) {
    subScribeDTO dto = subScribeDTO.builder()
        .sbid(entity.getSbid())
        .targetId(entity.getTargetId())
        .userid(entity.getUserid())
        .build();
    return dto;
  }
}
