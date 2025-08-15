/**
 * @author : tadiewa
 * date: 8/15/2025
 */


package com.zw.travelling.api;

import com.zw.travelling.dto.CreateApplicationRequest;
import com.zw.travelling.dto.DecisionRequest;
import com.zw.travelling.model.Applicationz;
import com.zw.travelling.service.ServiceIntImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class TsController {


    private final ServiceIntImpl serviceIntImpl;

    public TsController( ServiceIntImpl serviceIntImpl) {

        this.serviceIntImpl = serviceIntImpl;
    }

    @PostMapping("/applications")
    public ResponseEntity<Applicationz> create(@RequestBody @Valid CreateApplicationRequest req) {
        log.info("Creating application: {}", req);
        return ResponseEntity.ok(serviceIntImpl.create(req));
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Applicationz>> listByEmployee(@RequestParam Long employeeId) {
        return ResponseEntity.ok(serviceIntImpl.listForEmployee(employeeId));
    }

    @GetMapping("/applications/pending")
    public ResponseEntity<List<Applicationz>> listPending() {
        return ResponseEntity.ok(serviceIntImpl.listPending());
    }

    @PostMapping("/applications/{id}/decision")
    public ResponseEntity<Applicationz> decide(@PathVariable Long id, @RequestBody @Valid DecisionRequest req) {
        return ResponseEntity.ok(serviceIntImpl.decide(id, req.decision(), req.comments()));
    }
}
