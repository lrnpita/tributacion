package ec.eac.sitac.util;

public enum ClienteProveedorEnum implements IPersonalEmpresaEnum {
	CLIENTE(1), PROVEEDOR(2);
	private int personalEmpresaType;

	private ClienteProveedorEnum(int type) {
		personalEmpresaType = type;
	}; 

	@Override
	public int getPesonalEmpresaType() {
		// TODO Auto-generated method stub
		return personalEmpresaType;
	}

}