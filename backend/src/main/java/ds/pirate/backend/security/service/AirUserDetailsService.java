package ds.pirate.backend.security.service;


import java.util.Optional;
import java.util.stream.Collectors;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.security.dto.AuthMemberDTO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class AirUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);
        Optional<airUser> result = repository.findByEmail(username);
        if (!result.isPresent()) {
            throw new UsernameNotFoundException("Check email or Social");
        }
        airUser entity = result.get();
        log.info("airUser 유저정보: " + entity);

        AuthMemberDTO dto = new AuthMemberDTO(
                entity.getEMail(), entity.getPasswd(), entity.getUserid(),
                entity.isAuth(),
                entity.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()));
        dto.setName(entity.getAirName());
        dto.setAuth(entity.isAuth());
        return dto;
    }
}
