package io.bozoglu.projectmanagementapp.services;

import io.bozoglu.projectmanagementapp.domain.Project;
import io.bozoglu.projectmanagementapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){

        return projectRepository.save(project);
    }
}
