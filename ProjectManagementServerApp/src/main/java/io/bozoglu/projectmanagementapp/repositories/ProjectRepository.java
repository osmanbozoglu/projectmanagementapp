package io.bozoglu.projectmanagementapp.repositories;

import io.bozoglu.projectmanagementapp.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
}
