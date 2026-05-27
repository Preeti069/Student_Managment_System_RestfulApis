package in.sp.main.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.Escape;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.sp.main.entities.Student;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex)
	{
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error ->
		{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errors);
		
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex)
	{
		return ResponseEntity.badRequest().body("Invalid Request Body");
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptions(Exception ex)
	{
		return ResponseEntity.status(500).body(ex.getMessage());
	}
}
