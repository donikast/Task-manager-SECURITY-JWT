package bg.tu_varna.sit.taskmanager16042025.controller;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RoleDto;
import bg.tu_varna.sit.taskmanager16042025.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/roles")
    private ResponseEntity<Message> createRole(@RequestBody RoleDto roleDto) throws TaskApiException {
        Message message = adminService.createRole(roleDto);
        return ResponseEntity.status(201).body(message);
    }
}
