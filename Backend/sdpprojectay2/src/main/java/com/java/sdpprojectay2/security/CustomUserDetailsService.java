package com.java.sdpprojectay2.security;

import com.java.sdpprojectay2.model.Users;
import com.java.sdpprojectay2.repository.RepoUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final RepoUsers userRepository;
//
//    @Autowired
//    public CustomUserDetailsService(RepoUsers userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
//        if(user != null) {
//            return new User(
//                    user.getEmail(),
//                    user.getPassword(),
//                    user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
//                            .collect(Collectors.toList())
//
//            );
//        } else {
//            throw new UsernameNotFoundException("Invalid email or password");
//        }
//    }
//}


import com.java.sdpprojectay2.model.RulesGroup;
import com.java.sdpprojectay2.repository.RepoGroups;
import com.java.sdpprojectay2.repository.RepoRules;
import com.java.sdpprojectay2.repository.RepoRulesGroup;


import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    RepoUsers repoUsers;
    @Autowired
    RepoGroups repoGroups;
    @Autowired
    RepoRulesGroup repoRulesGroup;
    @Autowired
    RepoRules repoRules;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = repoUsers.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        List<RulesGroup> groupRules = repoRulesGroup.findAllByIdGroup(user.get().getIdGroup());

        return new MyUserDetails(user.get(), groupRules.stream()
                .map(rulesGroup -> repoRules.findById(rulesGroup.getIdRule()).orElse(null))
                .collect(Collectors.toList())
        );
    }
}
