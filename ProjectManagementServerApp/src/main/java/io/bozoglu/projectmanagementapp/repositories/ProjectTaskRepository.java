package io.bozoglu.projectmanagementapp.repositories;

import io.bozoglu.projectmanagementapp.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
