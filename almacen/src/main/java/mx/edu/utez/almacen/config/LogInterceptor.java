package mx.edu.utez.almacen.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.almacen.model.user.UserRepository;
import mx.edu.utez.almacen.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private LogService logService;

    @Autowired
    private UserRepository userRepository;

    public String getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername(); // Devuelve el nombre de usuario
        } else {
            return principal.toString(); // Si es un string, devuelve el valor
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Principal userPrincipal = request.getUserPrincipal();
        String username = (userPrincipal != null) ? userPrincipal.getName() : "ANONYMOUS";
        String method = request.getMethod();
        String endpoint = request.getRequestURI();

        logService.saveLog(username, method, endpoint);
        return true;
    }
}
