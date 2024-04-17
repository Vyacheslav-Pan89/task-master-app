package com.taskmaster.taskmasterapp.repository;

import com.taskmaster.taskmasterapp.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
