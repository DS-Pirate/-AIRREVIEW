package ds.pirate.backend.service.RankingService;

import java.util.List;


import ds.pirate.backend.dto.rankingDTO;
import ds.pirate.backend.entity.Ranking;

public interface RankingService {

    List<rankingDTO> gerRanking();
    void addRanking(String keyword);

    
    default Ranking dtoToEntity(rankingDTO dto){
        Ranking entity = Ranking.builder().rkid(dto.getRkid()).keyword(dto.getKeyword()).searchCount(dto.getSearchCount()).build();
        return entity;
    }

    default rankingDTO entityToDTO(Ranking entity){
        rankingDTO dto = rankingDTO.builder().rkid(entity.getRkid()).keyword(entity.getKeyword()).searchCount(entity.getSearchCount()).moddate(entity.getModDate()).regdate(entity.getRegDate()).build();
        return dto;
    }
}
