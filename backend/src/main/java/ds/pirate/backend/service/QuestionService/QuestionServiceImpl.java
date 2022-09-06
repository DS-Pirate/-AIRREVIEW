package ds.pirate.backend.service.QuestionService;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.QuestionDTO;
import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class QuestionServiceImpl implements QuestionService {
  private final QuestionRepository qrepo;

  @Override
  public String sendQuestion(QuestionDTO dto) {
    QuestionsList entity = DTOtoEntity(dto);
    qrepo.save(entity);
    return "성공하였습니다";
  }
}
