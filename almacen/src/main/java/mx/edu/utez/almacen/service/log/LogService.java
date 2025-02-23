package mx.edu.utez.almacen.service.log;

import mx.edu.utez.almacen.model.logbean.LogBean;
import mx.edu.utez.almacen.model.logbean.LogRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void saveLog(String username, String action, String endpoint) {
        LogBean log = new LogBean();
        log.setUsername(username);
        log.setAction(action);
        log.setEndpoint(endpoint);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }
}
