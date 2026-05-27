package in.sp.main.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import in.sp.main.entities.Student;
import in.sp.main.services.StudService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/stud")
public class StudController {

	@Autowired
	private StudService service;
	
	@PostMapping
	public Student addStudent(@Valid @RequestBody Student stud)
	{
		return service.addStudent(stud);
	}
	
	@GetMapping
	public List<Student> getAll()
	{
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id)
	{
		Student getStudent = service.getById(id);
		if(getStudent !=null)
		{
			return ResponseEntity.ok(getStudent);
		}
		else
			return ResponseEntity.status(404).body("Id Not Found");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id)
	{
		boolean isDeleted = service.deleteById(id);
		if(isDeleted)
		{
			return ResponseEntity.ok("successfully Deleted");
		}
		else {
			return ResponseEntity.status(404).body("Id Not Found");
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable int id,@Valid @RequestBody Student stud)
	{
		Student updateStudent = service.updateById(id, stud);
		if(updateStudent!=null)
		{
			return ResponseEntity.ok(updateStudent);
		}
		else {
			return ResponseEntity.status(404).body("Id Not Found");
		}
	}	

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateAge(@PathVariable int id, @RequestBody Student stud)
	{
		Student updateAge = service.updateAge(id, stud);
		
		  // allow only age
	    if(stud.getName() != null || stud.getCity() != null || stud.getPhone() != null)
	    {
	        return ResponseEntity.badRequest().body("PATCH method allows only age field");
	    }
	    
	    
	    
		if(updateAge!=null)
		{
			return ResponseEntity.ok(updateAge);
		}
		else
			return ResponseEntity.status(404).body("Id Not Found");
	}
	
	//For Pagination 	
	@GetMapping("/page")
	public Page<Student> withPagination(@RequestParam int page, @RequestParam int size)
	{
		return service.withPagination(page, size);
	}
}
