package ec.eac.sitac.util;

public class CheckDigit {

	public static boolean isCedula(String cedula) {
		if (cedula.length() != 10)
			return false;
		
		String checkDigit = cedula.substring(cedula.length() - 1, cedula.length());
		
		int total = 0;
		int factor = 2;
		int tempValue = 0;
		
		int cantidadCaracteres = cedula.length();
		for (int i = 0; i < cantidadCaracteres - 1; i++ ) {
			tempValue = Integer.parseInt(cedula.substring(i, i + 1)) * factor;
			
			if (tempValue > 9)
				tempValue = digitsSum(tempValue);
			
			total += tempValue;
			
			factor = (factor == 2) ? 1 : 2;
		}
		
		if (nextMultiple(total) - total != Integer.parseInt(checkDigit))
			return false;
		
		return true;
	}
	
	public static boolean isRUC(String ruc, int checkDigitPosition) {
		if (ruc.length() != 13)
			return false;
		
		String subRuc = ruc.substring(0, 9);
		String checkDigit = ruc.substring(checkDigitPosition - 1, checkDigitPosition);
		
		int factor = 2;
		int total = 0;
		int tempValue = 0;
		
		int cantidadCaracteres = subRuc.length();
		for (int i = cantidadCaracteres - 1; i >= 0; i--) {
			tempValue = Integer.parseInt(subRuc.substring(i, i + 1)) * factor;
			
			total += tempValue;
			
			factor++;
			if (factor > 7)
				factor = 2;
		}
		
		int rest = total % 11;
		
		// reglas del módulo 11
		if (rest == 0)
			rest = 11;
		else if (rest == 1)
			rest = 10;
		 
		if (11 - rest != Integer.valueOf(checkDigit))
			return false;
		
		return true;
	}
	
	public static boolean isRUCPublicSector(String ruc) {
		if (!isRUC(ruc, 9))
			return false;
		
		return true;
	}
	
	public static boolean isRUCPrivateSector(String ruc) {
		if (!isRUC(ruc, 10))
			return false;
		
		return true;
	}
	
	private static int digitsSum(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		
		return sum;
	}
	
	
	
	private static int nextMultiple(int num) {
		while (num % 10 != 0) {
			num++;
		}
		
		return num;
	}
}
