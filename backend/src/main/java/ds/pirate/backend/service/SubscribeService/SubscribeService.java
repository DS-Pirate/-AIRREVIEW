package ds.pirate.backend.service.SubscribeService;

import java.util.List;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.dto.subScribeDTO;
import ds.pirate.backend.entity.subscribList;

public interface SubscribeService {
  List<airUserDTO> getListUserId(Long userid);

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
