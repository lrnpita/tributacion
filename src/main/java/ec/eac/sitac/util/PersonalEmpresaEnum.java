package ec.eac.sitac.util;

public enum PersonalEmpresaEnum implements IPersonalEmpresaEnum {

		 TRABAJADOR(3), CONTADOR(4), REP_LEGAL(5);
		 private int personalEmpresaType;

		 private PersonalEmpresaEnum(int type) {
			 personalEmpresaType = type;
		 }; 
		 
		@Override
		public int getPesonalEmpresaType() {
			// TODO Auto-generated method stub
			return personalEmpresaType;
		} 
}

