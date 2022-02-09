package com.zee.zee5app.exception.apierror;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

public class ApiValidationError extends ApiSubError 
{
	
	private String object;
	private String field;
	private Object rejectedvalue;
	private String message;
	
	public ApiValidationError(String object, String message) 
	{
		this.object=object;
		this.message=message;
		
	}

	
	
}