package mx.edu.utez.almacen.security.service;

import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.security.model.UserDetailsImpl;
import mx.edu.utez.almacen.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService service;

    public UserDetailsServiceImpl(UserService service) {
        this.service = service;
    }
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBean> foundUser = service.findUserByUsername(username);
        if (foundUser.isPresent())
            return UserDetailsImpl.build(foundUser.get());
        throw new UsernameNotFoundException("UserNotFound");
    }
}
