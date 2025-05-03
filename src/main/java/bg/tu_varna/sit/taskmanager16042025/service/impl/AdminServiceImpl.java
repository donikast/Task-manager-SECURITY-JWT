package bg.tu_varna.sit.taskmanager16042025.service.impl;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RoleDto;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Role;
import bg.tu_varna.sit.taskmanager16042025.repository.RoleRepository;
import bg.tu_varna.sit.taskmanager16042025.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private RoleRepository roleRepository;
    private ModelMapper mapper;

    public AdminServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.mapper = modelMapper;
    }

    @Override
    public Message createRole(RoleDto roleDto) throws TaskApiException {
        if (roleRepository.existsByName(roleDto.getName())) {
            throw new TaskApiException("Role already exists");
        }
        Role role = mapper.map(roleDto, Role.class);
        roleRepository.save(role);
        return new Message("Role added");
    }
}
