package ec.eac.sitac.util;

import org.springframework.web.multipart.MultipartFile;

public class CustomFile {
		 
		MultipartFile file;
		
		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}		
}
