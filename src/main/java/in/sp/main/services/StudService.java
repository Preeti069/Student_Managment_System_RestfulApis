package in.sp.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Student;
import in.sp.main.repositories.StudRepo;

@Service
public class StudService {

	@Autowired
	private StudRepo repo;
	
	
	public Student addStudent(Student stud)
	{
		return repo.save(stud);
	}
	
	public List<Student> getAll()
	{
		return repo.findAll();
	}
	
	public Student getById(int id)
	{
		return repo.findById(id).orElse(null);
	}
	
	public boolean deleteById(int id)
	{
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	public Student updateById(int id, Student stud)
	{
		Student updateStudent = repo.findById(id).orElse(null);
		
		if(updateStudent!=null)
		{
			stud.setId(id);
			return repo.save(stud);
		}
		else
			return null;
	}	

	
	public Student updateAge(int id, Student stud)
	{
		Optional<Student> optional = repo.findById(id);
		if(optional.isPresent())
		{
			Student oldAge = optional.get();
			oldAge.setAge(stud.getAge());
			return repo.save(oldAge);
		}
		else {
			return null;
		}
	}
	
	//For Pagination
	public Page<Student> withPagination(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return repo.findAll(pageable);
	}
	
	
	
	
	
	
//	@Autowired
//	private StudRepo repo;
//	
//	public Student addStudent(Student stud)
//	{
//		return repo.save(stud);
//	}
//	public List<Student> getAll()
//	{
//		return repo.findAll();
//	}
//	public Student getID(int id)
//	{
//		return repo.findById(id).orElse(null);
//	}
//	public Student updateStudent(int id, Student stud)
//	{
//		Student updating = repo.findById(id).orElse(null);
//		
//		if(updating!=null)
//		{
//			stud.setId(id);
//			return repo.save(stud);
//		}
//		else {
//			return null;
//		}
//	}
//	public boolean deleteId(int id)
//	{
//		if(repo.existsById(id))
//		{
//			repo.deleteById(id);
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	
//	public Student upadateAge(int id, Student stud)
//	{
//		Optional<Student> optional = repo.findById(id);
//		if(optional.isPresent())
//		{
//			Student oldAge = optional.get();
//			oldAge.setAge(stud.getAge());
//			return repo.save(oldAge);
//		}
//		else {
//			return null;
//		}	
//	}
//	public Page<Student> withPagination(int page, int size)
//	{
//		Pageable pageable = PageRequest.of(page, size);
//		return repo.findAll(pageable);
//	}
}
