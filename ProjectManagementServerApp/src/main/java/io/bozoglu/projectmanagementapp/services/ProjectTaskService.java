package io.bozoglu.projectmanagementapp.services;

import io.bozoglu.projectmanagementapp.domain.Backlog;
import io.bozoglu.projectmanagementapp.domain.ProjectTask;
import io.bozoglu.projectmanagementapp.exceptions.ProjectNotFoundException;
import io.bozoglu.projectmanagementapp.repositories.BacklogRepository;
import io.bozoglu.projectmanagementapp.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer BackLogSequence = backlog.getPTSequence();
            BackLogSequence++;

            backlog.setPTSequence(BackLogSequence);
            projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + BackLogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if(projectTask.getPriority() == null || projectTask.getPriority() == 0){
                projectTask.setPriority(3);
            }

            if(projectTask.getStatus() == null || projectTask.getStatus() == ""){
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        } catch (Exception e){
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id) {

        Iterable<ProjectTask> projectTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
        if(projectTasks.spliterator().getExactSizeIfKnown() == 0)
            throw new ProjectNotFoundException(
                    "Backlog with ID: " + backlog_id + " is empty"
            );
        return projectTasks;
    }

    public ProjectTask findByProjectSequence(String backlog_id, String pt_id){
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if(backlog == null){
            throw new ProjectNotFoundException("Project with ID: " + backlog_id + " does not exist");
        }

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '" + pt_id+"' not found");
        }

        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exist in project: '" + backlog_id + "'");
        }
        return projectTask;
    }
}
