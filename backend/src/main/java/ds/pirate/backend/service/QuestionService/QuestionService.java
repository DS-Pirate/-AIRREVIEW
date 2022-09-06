package ds.pirate.backend.service.QuestionService;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;

public interface QuestionService {
  
  String sendQuestion(QuestionDTO dto);

  default QuestionsList DTOtoEntity(QuestionDTO dto) {
    airUser userid = airUser.builder().userid(dto.getUserid()).build();
    QuestionsList entity = QuestionsList.builder()
    .qid(dto.getQid())
    .userid(userid)
    .answerContext(dto.getAnswerContext())
    .answered(dto.isAnswered())
    .context(dto.getContext())
    .title(dto.getTitle())
    .build();
    return entity;
}

default QuestionDTO EntitytoDTO(QuestionsList entity) {
    QuestionDTO dto = QuestionDTO.builder()
    .qid(entity.getQid())
    .userid(entity.getUserid().getUserid())
    .answerContext(entity.getAnswerContext())
    .answered(entity.isAnswered())
    .context(entity.getContext())
    .title(entity.getTitle())
    .build();
    return dto;
}
}
