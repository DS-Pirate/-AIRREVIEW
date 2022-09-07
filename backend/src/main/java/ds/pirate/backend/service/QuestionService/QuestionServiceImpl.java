package ds.pirate.backend.service.QuestionService;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
  private final QuestionRepository qrepo;

  @Override
  public String sendQuestion(QuestionDTO dto) {
    QuestionsList entity = DTOtoEntity(dto);
    qrepo.save(entity);
    return "성공하였습니다";
  }
}
