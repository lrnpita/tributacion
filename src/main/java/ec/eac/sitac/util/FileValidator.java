package ec.eac.sitac.util;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return ec.eac.sitac.util.CustomFile.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		ec.eac.sitac.util.CustomFile file = (ec.eac.sitac.util.CustomFile) obj;
		  if (file.getFile().getSize() == 0) {
		   errors.rejectValue("file", "valid.file");
		  }
	}
}