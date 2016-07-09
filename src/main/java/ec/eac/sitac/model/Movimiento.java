// Generated 07/05/2015 16:52:32 by Hibernate Tools 4.3.1
package ec.eac.sitac.model;

import java.util.Date;

/**
 * Modelo correspondiente a la tabla Movimiento de la base de datos
 *
 * @author Luis M. Teijón gdsram@gmail.com
 * @version 1.0
 *
 * @since
 */
public class Movimiento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identificador
	 *
	 * @since 2015
	 */
	private int idMovimiento;
	
	/**
	 * Empresa que realiza el movimiento
	 *
	 **@see Empresa
	 * @since 2015
	 */
	private Empresa empresa;
	
	/**
	 * Trabajador
	 *
	 * @see PersonalEmpresa
	 * @since 2015
	 */
	private PersonalEmpresa personalEmpresa;
	
	/**
	 * Fecha
	 *
	 * @since 1.0
	 */
	private Date date;
	
	/**
	 * exoneracion por discapacidad
	 *
	 * @since 1.0
	 */
	private float exoneracionDiscapacidad;
	
	/**
	 * exoneracion por tercera edad
	 *
	 * @since 1.0
	 */
	private float exoneracionTerceraE;
	
	/**
	 * Base imponible
	 *
	 * @since 2015
	 */
	private float baseImponible;
	
	/**
	 * Valor retenido
	 *
	 * @since 2015
	 */
	private float valorRetenido;
	
	/**
	 * Sueldos y salarios
	 *
	 * @since 2015
	 */
	private float sueldosYSalarios;
	
	/**
	 * Acumulado de los 13 y 14 sueldos del trabajador.
	 *
	 * @since 2015
	 */
	private float sobresueldos;
	
	/**
	 * Participación de utilidades
	 *
	 * @since 2015
	 */
	private float participacionUtilidades;
	
	/**
	 * Ingresos con otros empleadores
	 *
	 * @since 2015
	 */
	private float ingresosOtrosEmpleadores;
	
	/**
	 * IR asumido por empleadores
	 *
	 * @since 2015
	 */
	private float irAsumidoOtrosEmpleadores;
	
	/**
	 * Décimo tercer sueldo
	 *
	 * @since 2015
	 */
	private float decimotercerSueldo;
	
	/**
	 * Décimo cuarto sueldo
	 *
	 * @since 2015
	 */
	private String decimocuartoSueldo;
	
	/**
	 * Fondos de reserva
	 *
	 * @since 2015
	 */
	private float fondosReserva;
	
	/**
	 * Salario Digno
	 *
	 * @since 2015
	 */
	private float salarioDigno;
	
	/**
	 * Otros ingresos no gravados
	 *
	 * @since 2015
	 */
	private float otrosIngresosNoGravados;
	
	/**
	 * Aporte personal del empleador
	 *
	 * @since 2015
	 */
	private float aportePersonalEmpleadorActual;
	
	/**
	 * IESS con otros empleadores
	 *
	 * @since 2015
	 */
	private float iessOtrosEmpleadores;
	
	/**
	 * Impuesto a la renta causado
	 *
	 * @since 2015
	 */
	private float impuestoRentaCausado;
	
	/**
	 * Retención por otros empleadores
	 *
	 * @since 2015
	 */
	private float retencionOtrosEmpleadores;
	
	/**
	 * Gasto personal en vivienda
	 *
	 * @since 2015
	 */
	private float vivienda;
	
	/**
	 * Gasto personal en salud
	 *
	 * @since 2015
	 */
	private float salud;
	
	/**
	 * Gasto personal en alimentacion
	 *
	 * @since 2015
	 */
	private float alimentacion;
	
	/**
	 * Gasto personal en educacion
	 *
	 * @since 2015
	 */
	private float educacion;
	
	/**
	 * Gasto personal en vestimenta
	 *
	 * @since 2015
	 */
	private float vestimenta;
	
	/**
	 * IR retenido por el empleador
	 *
	 * @since 2015
	 */
	private float irAsumidoEmpleadorActual;
	
	/**
	 * Base imponible gravada
	 *
	 * @since 2015
	 */
	private float baseImponibleGravada;
	
	/**
	 * Retenciones
	 *
	 * @since 1.0
	 */
	private int retenciones;
	
	/**
	 * Meses trabajados
	 *
	 * @since 2015
	 */
	private int mesesTrabajados;

	public Movimiento() {
	}

	public Movimiento(int id, PersonalEmpresa personalEmpresa, Date date, Empresa empresa) {
		this.idMovimiento = id;
		this.personalEmpresa = personalEmpresa;
		this.date = date;
		this.empresa = empresa;
	}

	public Movimiento(int id, Empresa empresa, PersonalEmpresa personalEmpresa, Date date,
			float baseImponible, float valorRetenido,
			float sueldosYSalarios, float sobresueldos,
			float participacionUtilidades, float ingresosOtrosEmpleadores,
			float irAsumidoOtrosEmpleadores, float decimotercerSueldo,
			String decimocuartoSueldo, float fondosReserva, Float salarioDigno,
			float otrosIngresosNoGravados, float aportePersonalEmpleadorActual, float baseImponibleGravada,
			float iessOtrosEmpleadores, float impuestoRentaCausado, float exoneracionDiscapacidad,
			float retencionOtrosEmpleadores, float irRetenidoEmpleadorActual, float exoneracionTerceraE,
			float vivienda, float salud, float alimentacion, float eduacion, float vestimenta,
			int retenciones, int mesesTrabajados) {
		this.empresa = empresa;
		this.idMovimiento = id;
		this.personalEmpresa = personalEmpresa;
		this.date = date;
		this.baseImponible = baseImponible;
		this.valorRetenido = valorRetenido;
		this.sueldosYSalarios = sueldosYSalarios;
		this.sobresueldos = sobresueldos;
		this.participacionUtilidades = participacionUtilidades;
		this.ingresosOtrosEmpleadores = ingresosOtrosEmpleadores;
		this.irAsumidoOtrosEmpleadores = irAsumidoOtrosEmpleadores;
		this.decimotercerSueldo = decimotercerSueldo;
		this.decimocuartoSueldo = decimocuartoSueldo;
		this.fondosReserva = fondosReserva;
		this.salarioDigno = salarioDigno;
		this.otrosIngresosNoGravados = otrosIngresosNoGravados;
		this.aportePersonalEmpleadorActual = aportePersonalEmpleadorActual;
		this.iessOtrosEmpleadores = iessOtrosEmpleadores;
		this.impuestoRentaCausado = impuestoRentaCausado;
		this.retencionOtrosEmpleadores = retencionOtrosEmpleadores;
		this.irAsumidoEmpleadorActual = irRetenidoEmpleadorActual;
		this.vivienda = vivienda;
		this.salud = salud;
		this.alimentacion = alimentacion;
		this.educacion = eduacion;
		this.vestimenta = vestimenta;
		this.retenciones = retenciones;
		this.mesesTrabajados = mesesTrabajados;
		this.exoneracionDiscapacidad = exoneracionDiscapacidad;
		this.exoneracionTerceraE = exoneracionTerceraE;
		this.baseImponibleGravada = baseImponibleGravada;
	}

	public float getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(float baseImponible) {
		this.baseImponible = baseImponible;
	}

	public float getValorRetenido() {
		return valorRetenido;
	}

	public void setValorRetenido(float valorRetenido) {
		this.valorRetenido = valorRetenido;
	}

	public int getIdMovimiento() {
		return this.idMovimiento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setIdMovimiento(int id) {
		this.idMovimiento = id;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return this.personalEmpresa;
	}

	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getSueldosYSalarios() {
		return this.sueldosYSalarios;
	}

	public void setSueldosYSalarios(float sueldosYSalarios) {
		this.sueldosYSalarios = sueldosYSalarios;
	}

	public float getSobresueldos() {
		return this.sobresueldos;
	}

	public void setSobresueldos(float sobresueldos) {
		this.sobresueldos = sobresueldos;
	}

	public float getParticipacionUtilidades() {
		return this.participacionUtilidades;
	}

	public void setParticipacionUtilidades(float participacionUtilidades) {
		this.participacionUtilidades = participacionUtilidades;
	}

	public float getIngresosOtrosEmpleadores() {
		return this.ingresosOtrosEmpleadores;
	}

	public void setIngresosOtrosEmpleadores(float ingresosOtrosEmpleadores) {
		this.ingresosOtrosEmpleadores = ingresosOtrosEmpleadores;
	}

	public float getIrAsumidoOtrosEmpleadores() {
		return this.irAsumidoOtrosEmpleadores;
	}

	public void setIrAsumidoOtrosEmpleadores(float irAsumidoOtrosEmpleadores) {
		this.irAsumidoOtrosEmpleadores = irAsumidoOtrosEmpleadores;
	}

	public float getDecimotercerSueldo() {
		return this.decimotercerSueldo;
	}

	public void setDecimotercerSueldo(float decimotercerSueldo) {
		this.decimotercerSueldo = decimotercerSueldo;
	}

	public String getDecimocuartoSueldo() {
		return this.decimocuartoSueldo;
	}

	public void setDecimocuartoSueldo(String decimocuartoSueldo) {
		this.decimocuartoSueldo = decimocuartoSueldo;
	}

	public float getFondosReserva() {
		return this.fondosReserva;
	}

	public void setFondosReserva(float fondosReserva) {
		this.fondosReserva = fondosReserva;
	}

	public float getSalarioDigno() {
		return this.salarioDigno;
	}

	public void setSalarioDigno(Float salarioDigno) {
		this.salarioDigno = salarioDigno;
	}

	public float getOtrosIngresosNoGravados() {
		return this.otrosIngresosNoGravados;
	}

	public void setOtrosIngresosNoGravados(float otrosIngresosNoGravados) {
		this.otrosIngresosNoGravados = otrosIngresosNoGravados;
	}

	public float getAportePersonalEmpleadorActual() {
		return this.aportePersonalEmpleadorActual;
	}

	public void setAportePersonalEmpleadorActual(
			float aportePersonalEmpleadorActual) {
		this.aportePersonalEmpleadorActual = aportePersonalEmpleadorActual;
	}

	public float getIessOtrosEmpleadores() {
		return this.iessOtrosEmpleadores;
	}

	public void setIessOtrosEmpleadores(float iessOtrosEmpleadores) {
		this.iessOtrosEmpleadores = iessOtrosEmpleadores;
	}

	public float getImpuestoRentaCausado() {
		return this.impuestoRentaCausado;
	}

	public void setImpuestoRentaCausado(float impuestoRentaCausado) {
		this.impuestoRentaCausado = impuestoRentaCausado;
	}

	public float getRetencionOtrosEmpleadores() {
		return this.retencionOtrosEmpleadores;
	}

	public void setRetencionOtrosEmpleadores(float retencionOtrosEmpleadores) {
		this.retencionOtrosEmpleadores = retencionOtrosEmpleadores;
	}

	public float getIrAsumidoEmpleadorActual() {
		return this.irAsumidoEmpleadorActual;
	}

	public void setIrAsumidoEmpleadorActual(float irAsumidoEmpleadorActual) {
		this.irAsumidoEmpleadorActual = irAsumidoEmpleadorActual;
	}

	public int getRetenciones() {
		return this.retenciones;
	}

	public void setRetenciones(int retenciones) {
		this.retenciones = retenciones;
	}

	public int getMesesTrabajados() {
		return this.mesesTrabajados;
	}

	public void setMesesTrabajados(int mesesTrabajados) {
		this.mesesTrabajados = mesesTrabajados;
	}

	public float getVivienda() {
		return vivienda;
	}

	public void setVivienda(float vivienda) {
		this.vivienda = vivienda;
	}

	public float getSalud() {
		return salud;
	}

	public void setSalud(float salud) {
		this.salud = salud;
	}

	public float getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(float alimentacion) {
		this.alimentacion = alimentacion;
	}

	public float getEducacion() {
		return educacion;
	}

	public void setEducacion(float educacion) {
		this.educacion = educacion;
	}

	public float getVestimenta() {
		return vestimenta;
	}

	public void setVestimenta(float vestimenta) {
		this.vestimenta = vestimenta;
	}

	public float getExoneracionDiscapacidad() {
		return exoneracionDiscapacidad;
	}

	public void setExoneracionDiscapacidad(float exoneracionDiscapacidad) {
		this.exoneracionDiscapacidad = exoneracionDiscapacidad;
	}

	public float getExoneracionTerceraE() {
		return exoneracionTerceraE;
	}

	public void setExoneracionTerceraE(float exoneracionTerceraE) {
		this.exoneracionTerceraE = exoneracionTerceraE;
	}

	public float getBaseImponibleGravada() {
		return baseImponibleGravada;
	}

	public void setBaseImponibleGravada(float baseImponibleGravada) {
		this.baseImponibleGravada = baseImponibleGravada;
	}

}
