package ds.pirate.backend.service.UserService;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.repository.UserRepository.UserIntroPage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository urepo;

    @Override
    public UserIntroPage getUserInfoByUserId(Long userid) {
        Optional<UserIntroPage> result = urepo.getUserInfoByUserId(userid);
        if(result.isPresent()){
            return result.get();
        }else{
            return null;
        }
        
    }

    @Override
    public HashMap<String, String> getUserInfoByuseridForarticle(Long userid) {
        airUserDTO dto = entityToDTO(urepo.getReferenceById(userid));
        HashMap<String, String> airUserDTO = new HashMap<>();
        airUserDTO.put("airName", dto.getAirName());
        airUserDTO.put("userIntro", dto.getUserIntro());
        return airUserDTO;
    }



}
