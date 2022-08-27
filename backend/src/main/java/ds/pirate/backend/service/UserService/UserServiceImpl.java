package ds.pirate.backend.service.UserService;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.UserApiDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository urepo;

    @Override
    public airUserDTO getUserInfoByuserid(Long userid) {
        // 
        return null;
    }

    @Override
    public HashMap<String, String> getUserInfoByuseridForarticle(Long userid) {
        airUserDTO dto = entityToDTO(urepo.getReferenceById(userid));
        HashMap<String, String> airUserDTO = new HashMap<>();
        airUserDTO.put("airName", dto.getAirName());
        airUserDTO.put("userIntro", dto.getUserIntro());
        return airUserDTO;
    }

    @Override
    public Long register(UserApiDTO dto) {
        //
        return null;
    }

}
