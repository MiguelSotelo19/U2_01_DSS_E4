package mx.edu.utez.almacen.controller.log;

import mx.edu.utez.almacen.model.logbean.LogBean;
import mx.edu.utez.almacen.model.logbean.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @GetMapping
    public List<LogBean> getAllLogs() {
        return logRepository.findAll();
    }
}
