package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Student;

public interface StudRepo extends JpaRepository<Student, Integer>{

}
