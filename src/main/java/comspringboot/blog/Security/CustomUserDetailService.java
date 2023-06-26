package comspringboot.blog.Security;

import comspringboot.blog.entity.User;
import comspringboot.blog.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {

      User user =  userRepository.findByUsernameOrEmail(usernameorEmail,usernameorEmail)
                .orElseThrow(()->new UsernameNotFoundException("User not found with user name or email : "+ usernameorEmail));

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());


        return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

    }
}
