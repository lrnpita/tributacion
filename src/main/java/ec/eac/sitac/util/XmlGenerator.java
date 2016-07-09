package ec.eac.sitac.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import ec.eac.sitac.dao.VentaHome;
import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.DocumentosAnulados;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.PersonalEmpresa;
import ec.eac.sitac.model.PuntoEmision;
import ec.eac.sitac.model.TipoPagoVsCompra;
import ec.eac.sitac.model.Venta;
import ec.eac.sitac.model.VentaVsProducto;


public class XmlGenerator{
	@Autowired
	VentaHome ventaDao;


	/*
	 * Se crea el xml para el formulario 104
	 */

	public static void generateXmlFormulario104(String rucEmpresa, String nombreEmpresa, String rucRepLegal, int idBanco, formatoAnexo104 formato104, ServletContext context) throws Exception{

		String name = "formulario";
		
		DecimalFormat decFormat = createDecimalFormat();
		Document document = createDocumentBuilder(null, name, null);
		document.setXmlStandalone(true);

		//Main Node
		Element formulario = document.getDocumentElement();
		formulario.setAttribute("version","0.2");

		Element cabecera = document.createElement("cabecera"); 
		Element codigo_version_formulario = document.createElement("codigo_version_formulario"); codigo_version_formulario.appendChild(document.createTextNode(""));

		Element ruc = document.createElement("ruc"); ruc.appendChild(document.createTextNode(rucEmpresa));
		Element codigo_moneda = document.createElement("codigo_moneda"); codigo_moneda.appendChild(document.createTextNode("1"));

		cabecera.appendChild(codigo_version_formulario);
		cabecera.appendChild(ruc);
		cabecera.appendChild(codigo_moneda);

		Element detalle = document.createElement("detalle");
		String numero = "numero";
		Element campo102 = document.createElement("campo"); campo102.setAttribute(numero, "102"); campo102.appendChild(document.createTextNode(formato104.getC102()));
		Element campo101 = document.createElement("campo"); campo101.setAttribute(numero, "101"); campo101.appendChild(document.createTextNode(formato104.getC101()));
		Element campo31 = document.createElement("campo"); campo31.setAttribute(numero, "31"); campo31.appendChild(document.createTextNode("O"));
		Element campo104 = document.createElement("campo"); campo104.setAttribute(numero, "104"); campo104.appendChild(document.createTextNode(formato104.getC104()));
		Element campo201 = document.createElement("campo"); campo201.setAttribute(numero, "201"); campo201.appendChild(document.createTextNode(rucEmpresa));
		Element campo202 = document.createElement("campo"); campo202.setAttribute(numero, "202"); campo202.appendChild(document.createTextNode(nombreEmpresa));
		Element campo411 = document.createElement("campo"); campo411.setAttribute(numero, "411"); campo411.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC411()))));
		Element campo421 = document.createElement("campo"); campo421.setAttribute(numero, "421"); campo421.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC421()))));
		Element campo401 = document.createElement("campo"); campo401.setAttribute(numero, "401"); campo401.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC401()))));
		Element campo412 = document.createElement("campo"); campo412.setAttribute(numero, "412"); campo412.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC412()))));
		Element campo422 = document.createElement("campo"); campo422.setAttribute(numero, "422"); campo422.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC422()))));
		Element campo402 = document.createElement("campo"); campo402.setAttribute(numero, "402"); campo402.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC402()))));
		Element campo413 = document.createElement("campo"); campo413.setAttribute(numero, "413"); campo413.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC413()))));
		Element campo403 = document.createElement("campo"); campo403.setAttribute(numero, "403"); campo403.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC403()))));
		Element campo414 = document.createElement("campo"); campo414.setAttribute(numero, "414"); campo414.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC414()))));
		Element campo404 = document.createElement("campo"); campo404.setAttribute(numero, "404"); campo404.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC404()))));
		Element campo415 = document.createElement("campo"); campo415.setAttribute(numero, "415"); campo415.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC415()))));
		Element campo405 = document.createElement("campo"); campo405.setAttribute(numero, "405"); campo405.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC405()))));
		Element campo416 = document.createElement("campo"); campo416.setAttribute(numero, "416"); campo416.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC416()))));
		Element campo406 = document.createElement("campo"); campo406.setAttribute(numero, "406"); campo406.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC406()))));
		Element campo409 = document.createElement("campo"); campo409.setAttribute(numero, "409"); campo409.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC409()))));
		Element campo419 = document.createElement("campo"); campo419.setAttribute(numero, "419"); campo419.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC419()))));
		Element campo429 = document.createElement("campo"); campo429.setAttribute(numero, "429"); campo429.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC429()))));
		Element campo431 = document.createElement("campo"); campo431.setAttribute(numero, "431"); campo431.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC431()))));
		Element campo441 = document.createElement("campo"); campo441.setAttribute(numero, "441"); campo441.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC441()))));
		Element campo442 = document.createElement("campo"); campo442.setAttribute(numero, "442"); campo442.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC442()))));
		Element campo453 = document.createElement("campo"); campo453.setAttribute(numero, "453"); campo453.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC453()))));
		Element campo443 = document.createElement("campo"); campo443.setAttribute(numero, "443"); campo443.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC443()))));
		Element campo434 = document.createElement("campo"); campo434.setAttribute(numero, "434"); campo434.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC434()))));
		Element campo454 = document.createElement("campo"); campo454.setAttribute(numero, "454"); campo454.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC454()))));
		Element campo444 = document.createElement("campo"); campo444.setAttribute(numero, "444"); campo444.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC444()))));
		Element campo499 = document.createElement("campo"); campo499.setAttribute(numero, "499"); campo499.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC499()))));
		Element campo485 = document.createElement("campo"); campo485.setAttribute(numero, "485"); campo485.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC485()))));
		Element campo484 = document.createElement("campo"); campo484.setAttribute(numero, "484"); campo484.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC484()))));
		Element campo483 = document.createElement("campo"); campo483.setAttribute(numero, "483"); campo483.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC483()))));
		Element campo482 = document.createElement("campo"); campo482.setAttribute(numero, "482"); campo482.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC482()))));
		Element campo481 = document.createElement("campo"); campo481.setAttribute(numero, "481"); campo481.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC481()))));
		Element campo480 = document.createElement("campo"); campo480.setAttribute(numero, "480"); campo480.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC480()))));
		Element campo111 = document.createElement("campo"); campo111.setAttribute(numero, "111"); campo111.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC111()))));
		Element campo113 = document.createElement("campo"); campo113.setAttribute(numero, "113"); campo113.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC113()))));
		Element campo500 = document.createElement("campo"); campo500.setAttribute(numero, "500"); campo500.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC500()))));
		Element campo520 = document.createElement("campo"); campo520.setAttribute(numero, "520"); campo520.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC520()))));
		Element campo510 = document.createElement("campo"); campo510.setAttribute(numero, "510"); campo510.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC510()))));
		Element campo521 = document.createElement("campo"); campo521.setAttribute(numero, "521"); campo521.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC521()))));
		Element campo511 = document.createElement("campo"); campo511.setAttribute(numero, "511"); campo511.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC511()))));
		Element campo501 = document.createElement("campo"); campo501.setAttribute(numero, "501"); campo501.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC501()))));
		Element campo522 = document.createElement("campo"); campo522.setAttribute(numero, "522"); campo522.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC522()))));
		Element campo502 = document.createElement("campo"); campo502.setAttribute(numero, "502"); campo502.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC502()))));
		Element campo512 = document.createElement("campo"); campo512.setAttribute(numero, "512"); campo512.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC512()))));
		Element campo517 = document.createElement("campo"); campo517.setAttribute(numero, "517"); campo517.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC517()))));
		Element campo507 = document.createElement("campo"); campo507.setAttribute(numero, "507"); campo507.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC507()))));
		Element campo508 = document.createElement("campo"); campo508.setAttribute(numero, "508"); campo508.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC508()))));
		Element campo518 = document.createElement("campo"); campo518.setAttribute(numero, "518"); campo518.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC518()))));
		Element campo509 = document.createElement("campo"); campo509.setAttribute(numero, "509"); campo509.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC509()))));
		Element campo519 = document.createElement("campo"); campo519.setAttribute(numero, "519"); campo519.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC519()))));
		Element campo529 = document.createElement("campo"); campo529.setAttribute(numero, "529"); campo529.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC529()))));
		Element campo531 = document.createElement("campo"); campo531.setAttribute(numero, "531"); campo531.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC531()))));
		Element campo541 = document.createElement("campo"); campo541.setAttribute(numero, "541"); campo541.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC541()))));
		Element campo532 = document.createElement("campo"); campo532.setAttribute(numero, "532"); campo532.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC532()))));
		Element campo542 = document.createElement("campo"); campo542.setAttribute(numero, "542"); campo542.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC542()))));
		Element campo543 = document.createElement("campo"); campo543.setAttribute(numero, "543"); campo543.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC543()))));
		Element campo544 = document.createElement("campo"); campo544.setAttribute(numero, "544"); campo544.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC544()))));
		Element campo554 = document.createElement("campo"); campo554.setAttribute(numero, "554"); campo554.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC554()))));
		Element campo555 = document.createElement("campo"); campo555.setAttribute(numero, "555"); campo555.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC555()))));
		Element campo545 = document.createElement("campo"); campo545.setAttribute(numero, "545"); campo545.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC545()))));
		Element campo535 = document.createElement("campo"); campo535.setAttribute(numero, "535"); campo535.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC535()))));
		Element campo563 = document.createElement("campo"); campo563.setAttribute(numero, "563"); campo563.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC563()))));
		Element campo564 = document.createElement("campo"); campo564.setAttribute(numero, "564"); campo564.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC564()))));
		Element campo115 = document.createElement("campo"); campo115.setAttribute(numero, "115"); campo115.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC115()))));
		Element campo117 = document.createElement("campo"); campo117.setAttribute(numero, "117"); campo117.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC117()))));
		Element campo119 = document.createElement("campo"); campo119.setAttribute(numero, "119"); campo119.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC119()))));
		Element campo601 = document.createElement("campo"); campo601.setAttribute(numero, "601"); campo601.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC601()))));
		Element campo602 = document.createElement("campo"); campo602.setAttribute(numero, "602"); campo602.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC602()))));
		Element campo605 = document.createElement("campo"); campo605.setAttribute(numero, "605"); campo605.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC605()))));
		Element campo607 = document.createElement("campo"); campo607.setAttribute(numero, "607"); campo607.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC607()))));
		Element campo609 = document.createElement("campo"); campo609.setAttribute(numero, "609"); campo609.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC609()))));
		Element campo611 = document.createElement("campo"); campo611.setAttribute(numero, "611"); campo611.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC611()))));
		Element campo612 = document.createElement("campo"); campo612.setAttribute(numero, "612"); campo612.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC612()))));
		Element campo613 = document.createElement("campo"); campo613.setAttribute(numero, "613"); campo613.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC613()))));
		Element campo615 = document.createElement("campo"); campo615.setAttribute(numero, "615"); campo615.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC615()))));
		Element campo617 = document.createElement("campo"); campo617.setAttribute(numero, "617"); campo617.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC617()))));
		Element campo619 = document.createElement("campo"); campo619.setAttribute(numero, "619"); campo619.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC619()))));
		Element campo621 = document.createElement("campo"); campo621.setAttribute(numero, "621"); campo621.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC621()))));
		Element campo699 = document.createElement("campo"); campo699.setAttribute(numero, "699"); campo699.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC699()))));
		Element campo890 = document.createElement("campo"); campo890.setAttribute(numero, "890"); campo890.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC890()))));
		Element campo899 = document.createElement("campo"); campo899.setAttribute(numero, "899"); campo899.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC899()))));
		Element campo897 = document.createElement("campo"); campo897.setAttribute(numero, "897"); campo897.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC897()))));
		Element campo898 = document.createElement("campo"); campo898.setAttribute(numero, "898"); campo898.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC898()))));
		Element campo902 = document.createElement("campo"); campo902.setAttribute(numero, "902"); campo902.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC902()))));
		Element campo903 = document.createElement("campo"); campo903.setAttribute(numero, "903"); campo903.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC903()))));
		Element campo904 = document.createElement("campo"); campo904.setAttribute(numero, "904"); campo904.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC904()))));
		Element campo999 = document.createElement("campo"); campo999.setAttribute(numero, "999"); campo999.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC999()))));
		Element campo905 = document.createElement("campo"); campo905.setAttribute(numero, "905"); campo905.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC905()))));
		Element campo906 = document.createElement("campo"); campo906.setAttribute(numero, "906"); campo906.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC906()))));
		Element campo907 = document.createElement("campo"); campo907.setAttribute(numero, "907"); campo907.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC907()))));
		Element campo908 = document.createElement("campo"); campo908.setAttribute(numero, "908"); campo908.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC908()))));
		Element campo910 = document.createElement("campo"); campo910.setAttribute(numero, "910"); campo910.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC910()))));
		Element campo912 = document.createElement("campo"); campo912.setAttribute(numero, "912"); campo912.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC912()))));
		Element campo909 = document.createElement("campo"); campo909.setAttribute(numero, "909"); campo909.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC909()))));
		Element campo911 = document.createElement("campo"); campo911.setAttribute(numero, "911"); campo911.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC911()))));
		Element campo913 = document.createElement("campo"); campo913.setAttribute(numero, "913"); campo913.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC913()))));
		Element campo915 = document.createElement("campo"); campo915.setAttribute(numero, "915"); campo915.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC915()))));
		Element campo918 = document.createElement("campo"); campo918.setAttribute(numero, "918"); campo918.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC918()))));
		Element campo916 = document.createElement("campo"); campo916.setAttribute(numero, "916"); campo916.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC916()))));
		Element campo919 = document.createElement("campo"); campo919.setAttribute(numero, "919"); campo919.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC919()))));
		Element campo917 = document.createElement("campo"); campo917.setAttribute(numero, "917"); campo917.appendChild(document.createTextNode(String.valueOf(decFormat.format(formato104.getC917()))));
		Element campo198 = document.createElement("campo"); campo198.setAttribute(numero, "198"); campo198.appendChild(document.createTextNode(rucRepLegal));
		Element campo922 = document.createElement("campo"); campo922.setAttribute(numero, "922"); campo922.appendChild(document.createTextNode(String.valueOf(idBanco)));


		detalle.appendChild(campo101);detalle.appendChild(campo102);
		detalle.appendChild(campo104);detalle.appendChild(campo111);
		detalle.appendChild(campo113);detalle.appendChild(campo115);
		detalle.appendChild(campo117);detalle.appendChild(campo119);
		detalle.appendChild(campo198);detalle.appendChild(campo201);
		detalle.appendChild(campo202);detalle.appendChild(campo31);
		detalle.appendChild(campo401);detalle.appendChild(campo402);
		detalle.appendChild(campo403);detalle.appendChild(campo404);
		detalle.appendChild(campo405);detalle.appendChild(campo406);
		detalle.appendChild(campo409);detalle.appendChild(campo411);
		detalle.appendChild(campo412);detalle.appendChild(campo412);
		detalle.appendChild(campo413);detalle.appendChild(campo414);
		detalle.appendChild(campo415);detalle.appendChild(campo416);
		detalle.appendChild(campo419);detalle.appendChild(campo421);
		detalle.appendChild(campo422);detalle.appendChild(campo429);
		detalle.appendChild(campo431);detalle.appendChild(campo434);
		detalle.appendChild(campo441);detalle.appendChild(campo442);
		detalle.appendChild(campo443);detalle.appendChild(campo444);
		detalle.appendChild(campo453);detalle.appendChild(campo454);
		detalle.appendChild(campo480);detalle.appendChild(campo481);
		detalle.appendChild(campo482);detalle.appendChild(campo483);
		detalle.appendChild(campo484);detalle.appendChild(campo485);
		detalle.appendChild(campo499);detalle.appendChild(campo500);
		detalle.appendChild(campo501);detalle.appendChild(campo502);
		detalle.appendChild(campo507);detalle.appendChild(campo508);
		detalle.appendChild(campo509);detalle.appendChild(campo510);
		detalle.appendChild(campo511);detalle.appendChild(campo512);
		detalle.appendChild(campo517);detalle.appendChild(campo518);
		detalle.appendChild(campo519);detalle.appendChild(campo520);
		detalle.appendChild(campo521);detalle.appendChild(campo522);
		detalle.appendChild(campo531);detalle.appendChild(campo532);
		detalle.appendChild(campo535);detalle.appendChild(campo529);
		detalle.appendChild(campo541);detalle.appendChild(campo542);
		detalle.appendChild(campo543);detalle.appendChild(campo544);
		detalle.appendChild(campo545);detalle.appendChild(campo554);
		detalle.appendChild(campo555);detalle.appendChild(campo563);
		detalle.appendChild(campo564);detalle.appendChild(campo601);
		detalle.appendChild(campo602);detalle.appendChild(campo605);
		detalle.appendChild(campo607);detalle.appendChild(campo609);
		detalle.appendChild(campo611);detalle.appendChild(campo612);
		detalle.appendChild(campo613);detalle.appendChild(campo615);
		detalle.appendChild(campo617);detalle.appendChild(campo619);
		detalle.appendChild(campo621);detalle.appendChild(campo699);
		detalle.appendChild(campo890);detalle.appendChild(campo897);
		detalle.appendChild(campo898);detalle.appendChild(campo899);
		detalle.appendChild(campo902);detalle.appendChild(campo903);
		detalle.appendChild(campo904);detalle.appendChild(campo905);
		detalle.appendChild(campo906);detalle.appendChild(campo907);
		detalle.appendChild(campo908);detalle.appendChild(campo909);
		detalle.appendChild(campo910);detalle.appendChild(campo911);      
		detalle.appendChild(campo912);detalle.appendChild(campo913);
		detalle.appendChild(campo912);detalle.appendChild(campo913);
		detalle.appendChild(campo915);detalle.appendChild(campo916);
		detalle.appendChild(campo917);detalle.appendChild(campo918);
		detalle.appendChild(campo919);detalle.appendChild(campo922);
		detalle.appendChild(campo999);
		formulario.appendChild(cabecera);
		formulario.appendChild(detalle);

		TransformerFactory fábricaTransformador = TransformerFactory.newInstance();
		Transformer transformador = fábricaTransformador.newTransformer();
		Source origen = new DOMSource(document);
		String directorioEmpresaActual = context.getRealPath("/resources");
		Result destino = new StreamResult(directorioEmpresaActual + "\\Formulario104.xml");
		transformador.transform(origen, destino);

	}

	/*
	 * Se crea el xml para el anexo ATS
	 */

	public static void generateAnexoATS( List<Compra> listCompras,List<Venta> listVentas, List<DocumentosAnulados> listAnulados, PuntoEmision ptoEmision, Empresa empresa, int anno, int mes, ServletContext context, VentaHome ventaDao) throws Exception{

		String string = ptoEmision.getSerie();
		String estab = string.substring(0, 3);
		String puntoE = string.substring(3, 6);
		String name = "iva";
		String codTransaccion = null;
		float totalVentasEmpresa = 0;

		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

		DecimalFormat decFormat = createDecimalFormat();
		Document document = createDocumentBuilder(null, name, null);
		document.setXmlStandalone(false);

		//Main Node
		Element iva = document.getDocumentElement();

		Element TipoIDInformante = document.createElement("TipoIDInformante"); TipoIDInformante.appendChild(document.createTextNode("R"));
		Element IdInformante = document.createElement("IdInformante"); IdInformante.appendChild(document.createTextNode(empresa.getRucContribuyente()));
		Element razonSocial = document.createElement("razonSocial"); razonSocial.appendChild(document.createTextNode(empresa.getNombre()));
		Element Anio = document.createElement("Anio"); Anio.appendChild(document.createTextNode(String.valueOf(anno)));
		String Nomes = String.valueOf(mes).length() == 1 ? "0"+String.valueOf(mes) : String.valueOf(mes);
		Element Mes = document.createElement("Mes"); Mes.appendChild(document.createTextNode(String.valueOf(Nomes)));
		Element numEstabRuc = document.createElement("numEstabRuc"); numEstabRuc.appendChild(document.createTextNode(estab));
		Element totalVentas = document.createElement("totalVentas"); 
		Element codigoOperativo = document.createElement("codigoOperativo"); codigoOperativo.appendChild(document.createTextNode("IVA"));

		Element compras = document.createElement("compras"); 
		Element air = document.createElement("air");
		

		if(!listCompras.isEmpty())
			for (Compra compra : listCompras) {
				float baseNoObjetoIva = 0;
				float baseImp = 0;
				float baseImpGravada = 0;
				// valor iva
				float valorIva = 0;
				float valorRet10 = 0;
				float valorRet20 = 0;
				float valorRet30 = 0;
				float valorRet70 = 0;
				float valorRet100 = 0;
				float baseImponibleAir = 0;
				float porcAir = 0;
				float valorretenidoAir = 0;

				for(DetallesCompra detalle : compra.getDetallesCompras()) {
					baseNoObjetoIva += detalle.getBaseNoObjetoIva();
					baseImp += detalle.getBaseImpobible0();
					baseImpGravada += detalle.getBaseImponible12();
					valorIva += detalle.getRIvalorIva();
					// estos valores son de la rentencion IR
					baseImponibleAir = detalle.getRIRbaseNoObjIva() + detalle.getRIRbaseImp0() + detalle.getRIRbaseImp12();
					porcAir = detalle.getRIRret();
					// total retIR
					valorretenidoAir = ((detalle.getRIRbaseNoObjIva() + detalle.getRIRbaseImp0() + detalle.getRIRbaseImp12())*detalle.getRIRret())/100;

					if(detalle.getRIret() == 10){
						valorRet10 += detalle.getRIvalorIva()*10/100;
					} 
					else
						if(detalle.getRIret() == 20){
							valorRet20 += detalle.getRIvalorIva()*20/100;
						}
						else
							if(detalle.getRIret() == 30){
								valorRet30 += detalle.getRIvalorIva()*30/100;
							}
							else
								if(detalle.getRIret() == 70){
									valorRet70 += detalle.getRIvalorIva()*70/100;
								}
								else
									if(detalle.getRIret() == 100){
										valorRet100 += detalle.getRIvalorIva();
									}
					
					Element detalleAir = document.createElement("detalleAir");
					Element codRetAir = document.createElement("codRetAir"); codRetAir.appendChild(document.createTextNode(detalle.getTipoTransaccion().getCodigo()));
					Element baseImpAir = document.createElement("baseImpAir"); baseImpAir.appendChild(document.createTextNode(String.valueOf(decFormat.format(baseImponibleAir))));
					Element porcentajeAir = document.createElement("porcentajeAir"); porcentajeAir.appendChild(document.createTextNode(String.valueOf(decFormat.format(porcAir))));
					Element valRetAir = document.createElement("valRetAir"); valRetAir.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorretenidoAir))));

					detalleAir.appendChild(codRetAir);
					detalleAir.appendChild(baseImpAir);
					detalleAir.appendChild(porcentajeAir);
					detalleAir.appendChild(valRetAir);
					air.appendChild(detalleAir);
				}

				// estos elementos se crean en dependencia de la cantidad de detalles q tenga la compra
				Element detalleCompras = document.createElement("detalleCompras"); 
				Element codSustento = document.createElement("codSustento"); codSustento.appendChild(document.createTextNode(compra.getTipoCompra().getCodigo()));
				Element tpIdProv = document.createElement("tpIdProv"); tpIdProv.appendChild(document.createTextNode(compra.getPersonalEmpresa().getTipoIdentificacion().getCodigo()));
				Element idProv = document.createElement("idProv"); idProv.appendChild(document.createTextNode(compra.getPersonalEmpresa().getId()));
				String codTipoComprobante = compra.getTipoComprobante().getCodigo() == "01" || compra.getTipoComprobante().getCodigo() ==  "02" ? "18" : compra.getTipoComprobante().getCodigo();
				Element tipoComprobante = document.createElement("tipoComprobante"); tipoComprobante.appendChild(document.createTextNode(codTipoComprobante));
				String parteRe = compra.getPersonalEmpresa().getParteRelacionada() == true ? "SI" : "NO";
				Element parteRel = document.createElement("parteRel"); parteRel.appendChild(document.createTextNode(parteRe));
				Element fechaRegistro = document.createElement("fechaRegistro"); fechaRegistro.appendChild(document.createTextNode(dt.format(compra.getRegistroContable())));
				Element establecimiento = document.createElement("establecimiento"); establecimiento.appendChild(document.createTextNode(estab));
				Element puntoEmision = document.createElement("puntoEmision"); puntoEmision.appendChild(document.createTextNode(puntoE));
				
				String stringSecFactura = Utility.rellenarCadenaConCeros(String.valueOf(compra.getPuntoEmision().getSecFactura()));
				Element secuencial = document.createElement("secuencial"); secuencial.appendChild(document.createTextNode(stringSecFactura));
				Element fechaEmision = document.createElement("fechaEmision"); fechaEmision.appendChild(document.createTextNode(dt.format(compra.getFechaEmision())));
				Element autorizacion = document.createElement("autorizacion"); autorizacion.appendChild(document.createTextNode(compra.getCodigoAutorizacion()));
				Element baseNoGraIva = document.createElement("baseNoGraIva"); baseNoGraIva.appendChild(document.createTextNode(String.valueOf(decFormat.format(baseNoObjetoIva))));
				Element baseImponible = document.createElement("baseImponible"); baseImponible.appendChild(document.createTextNode(String.valueOf(decFormat.format(baseImp))));
				Element baseImpGrav = document.createElement("baseImpGrav"); baseImpGrav.appendChild(document.createTextNode(String.valueOf(decFormat.format(baseImpGravada))));
				Element baseImpExe = document.createElement("baseImpExe"); baseImpExe.appendChild(document.createTextNode("0.00"));
				float iceImp = compra.getIceImpuesto() == null? 0 : compra.getIceImpuesto();
				Element montoIce = document.createElement("montoIce"); montoIce.appendChild(document.createTextNode(String.valueOf(decFormat.format(iceImp))));
				Element montoIva = document.createElement("montoIva"); montoIva.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorIva))));

				Element valRetBien10 = document.createElement("valRetBien10"); valRetBien10.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorRet10))));
				Element valRetServ20 = document.createElement("valRetServ20"); valRetServ20.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorRet20))));
				Element valorRetBienes = document.createElement("valorRetBienes"); valorRetBienes.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorRet30))));
				Element valorRetServicios = document.createElement("valorRetServicios"); valorRetServicios.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorRet70))));
				Element valRetServ100 = document.createElement("valRetServ100"); valRetServ100.appendChild(document.createTextNode(String.valueOf(decFormat.format(valorRet100))));

				Element pagoExterior = document.createElement("pagoExterior");

				Element pagoLocExt = null;
				Element paisEfecPago = null;
				Element aplicConvDobTrib = null;
				Element pagExtSujRetNorLeg = null;

				// si el tipo pago es 01 es local sino 02 es exterior
				if(compra.getTipoPagoSegunLugar().getCodigo().equals("01")){
					pagoLocExt = document.createElement("pagoLocExt"); pagoLocExt.appendChild(document.createTextNode("01"));
					paisEfecPago = document.createElement("paisEfecPago"); paisEfecPago.appendChild(document.createTextNode("NA"));
					aplicConvDobTrib = document.createElement("aplicConvDobTrib"); aplicConvDobTrib.appendChild(document.createTextNode("NA"));
					pagExtSujRetNorLeg = document.createElement("pagExtSujRetNorLeg"); pagExtSujRetNorLeg.appendChild(document.createTextNode("NA"));
				} 
				else{
					pagoLocExt = document.createElement("pagoLocExt"); pagoLocExt.appendChild(document.createTextNode("02"));
					paisEfecPago = document.createElement("paisEfecPago"); paisEfecPago.appendChild(document.createTextNode(compra.getPais().getCodigo()));

					if(compra.getConvenio()){
						aplicConvDobTrib = document.createElement("aplicConvDobTrib"); aplicConvDobTrib.appendChild(document.createTextNode("SI"));
					} else {
						aplicConvDobTrib = document.createElement("aplicConvDobTrib"); aplicConvDobTrib.appendChild(document.createTextNode("NO"));
					}

					if(compra.getNormaLegal()){
						pagExtSujRetNorLeg = document.createElement("pagExtSujRetNorLeg"); pagExtSujRetNorLeg.appendChild(document.createTextNode("SI"));
					} else {
						pagExtSujRetNorLeg = document.createElement("pagExtSujRetNorLeg"); pagExtSujRetNorLeg.appendChild(document.createTextNode("NO"));
					}

				}

				pagoExterior.appendChild(pagoLocExt);
				pagoExterior.appendChild(paisEfecPago);
				pagoExterior.appendChild(aplicConvDobTrib);
				pagoExterior.appendChild(pagExtSujRetNorLeg);

				Element formasDePago = null;
				if(compra.getTiposPago() != null){
					formasDePago = document.createElement("formasDePago");
					for (TipoPagoVsCompra tipoPago : compra.getTiposPago()) {
						Element formaPago = document.createElement("formaPago"); formaPago.appendChild(document.createTextNode(tipoPago.getTipoPago().getCodigo()));
						formasDePago.appendChild(formaPago);
					}
				}

				Element estabRetencion1 = document.createElement("estabRetencion1"); estabRetencion1.appendChild(document.createTextNode(compra.getRetencion().getSerieRetencion().substring(0, 2)));
				Element ptoEmiRetencion1 = document.createElement("ptoEmiRetencion1"); ptoEmiRetencion1.appendChild(document.createTextNode(compra.getRetencion().getSerieRetencion().substring(3, 5)));
				Element secRetencion1 = document.createElement("secRetencion1"); secRetencion1.appendChild(document.createTextNode(String.valueOf(compra.getRetencion().getSecuenciaRetencion())));
				Element autRetencion1 = document.createElement("autRetencion1"); autRetencion1.appendChild(document.createTextNode(compra.getRetencion().getAutorizacionRetencion()));
				Element fechaEmiRet1 = document.createElement("fechaEmiRet1"); fechaEmiRet1.appendChild(document.createTextNode(String.valueOf(dt.format(compra.getRetencion().getFechaRetencion()))));

				detalleCompras.appendChild(codSustento);
				detalleCompras.appendChild(tpIdProv);
				detalleCompras.appendChild(idProv);
				detalleCompras.appendChild(tipoComprobante);
				detalleCompras.appendChild(parteRel);
				detalleCompras.appendChild(fechaRegistro);
				detalleCompras.appendChild(establecimiento);
				detalleCompras.appendChild(puntoEmision);
				detalleCompras.appendChild(secuencial);
				detalleCompras.appendChild(fechaEmision);
				detalleCompras.appendChild(autorizacion);
				detalleCompras.appendChild(baseNoGraIva);
				detalleCompras.appendChild(baseImponible);
				detalleCompras.appendChild(baseImpGrav);
				detalleCompras.appendChild(baseImpExe);
				detalleCompras.appendChild(montoIce);
				detalleCompras.appendChild(montoIva);
				detalleCompras.appendChild(valRetBien10);
				detalleCompras.appendChild(valRetServ20);
				detalleCompras.appendChild(valorRetBienes);
				detalleCompras.appendChild(valorRetServicios);
				detalleCompras.appendChild(valRetServ100);
				detalleCompras.appendChild(pagoExterior);
				if(formasDePago != null){
					detalleCompras.appendChild(formasDePago);
				}
				detalleCompras.appendChild(air);
				detalleCompras.appendChild(estabRetencion1);
				detalleCompras.appendChild(ptoEmiRetencion1);
				detalleCompras.appendChild(secRetencion1);
				detalleCompras.appendChild(autRetencion1);
				detalleCompras.appendChild(fechaEmiRet1);

				compras.appendChild(detalleCompras);

				baseNoObjetoIva = 0;
				baseImp = 0;
				baseImpGravada = 0;
				valorIva = 0;
			}

		Element ventas = document.createElement("ventas");

		String cliente = "0000000000000";
		float bNoGraIva = 0;
		float bImponible = 0;
		float bImpGrav = 0;
		float mIva = 0;
		float mIce = 0;
		float vRetIva = 0;
		float vRetRenta = 0;
		int cont = 1;

		Element detalleVentas = null;
		Element tpIdCliente = null;
		Element idCliente = null;
		Element parteRelVtas = null;
		Element tipoComprobante = null;
		Element numeroComprobantes = null;
		Element baseNoGraIva = null;
		Element baseImponible = null;
		Element baseImpGrav = null;
		Element montoIva = null;
		Element montoIce = null;
		Element valorRetIva = null;
		Element valorRetRenta = null;

		if(!listVentas.isEmpty()){

			Venta v = new Venta();
			PersonalEmpresa p = new PersonalEmpresa();
			p.setId("0000000000000");
			v.setPersonalEmpresa(p);
			listVentas.add(v);

			for (int i = 0; i < listVentas.size()-1; i++) {

				if(!listVentas.get(i).getPersonalEmpresa().getId().equals(cliente)){

					detalleVentas = document.createElement("detalleVentas");
					tpIdCliente = document.createElement("tpIdCliente"); 
					idCliente = document.createElement("idCliente"); 
					parteRelVtas = document.createElement("parteRelVtas");
					tipoComprobante = document.createElement("tipoComprobante"); 
					numeroComprobantes = document.createElement("numeroComprobantes"); 
					baseNoGraIva = document.createElement("baseNoGraIva"); 
					baseImponible = document.createElement("baseImponible"); 
					baseImpGrav = document.createElement("baseImpGrav"); 
					montoIva = document.createElement("montoIva"); 
					montoIce = document.createElement("montoIce");
					valorRetIva = document.createElement("valorRetIva"); 
					valorRetRenta = document.createElement("valorRetRenta"); 
				}     
				cliente = listVentas.get(i).getPersonalEmpresa().getId();

				bNoGraIva += listVentas.get(i).getBaseImpNoGravada()!= null ? listVentas.get(i).getBaseImpNoGravada() : 0;
				bImponible += listVentas.get(i).getBaseImp() != null ? listVentas.get(i).getBaseImp() : 0;
				bImpGrav +=  listVentas.get(i).getBaseImpGravada() != null ? listVentas.get(i).getBaseImpGravada() : 0;
				mIva += listVentas.get(i).getMontoIva() != null ? listVentas.get(i).getMontoIva() : 0 ;
				mIce += listVentas.get(i).getIce() != null ? listVentas.get(i).getIce() : 0 ;
				vRetIva += listVentas.get(i).getRetencion().getRetencionIVA();
				vRetRenta += listVentas.get(i).getRetencion().getRetencionIR();

				if(!listVentas.get(i+1).getPersonalEmpresa().getId().equals(cliente)){

					tpIdCliente.appendChild(document.createTextNode(listVentas.get(i).getPersonalEmpresa().getTipoIdentificacion().getCodigo()));
					idCliente.appendChild(document.createTextNode(listVentas.get(i).getPersonalEmpresa().getId()));
					String parteR = listVentas.get(i).getPersonalEmpresa().getParteRelacionada() == true ? "SI" : "NO";
					parteRelVtas.appendChild(document.createTextNode(parteR));
					tipoComprobante.appendChild(document.createTextNode(listVentas.get(i).getTipoComprobante().getCodigo()));
					numeroComprobantes.appendChild(document.createTextNode(String.valueOf(cont)));
					baseNoGraIva.appendChild(document.createTextNode(String.valueOf(decFormat.format(bNoGraIva))));
					baseImponible.appendChild(document.createTextNode(String.valueOf(decFormat.format(bImponible))));
					baseImpGrav.appendChild(document.createTextNode(String.valueOf(decFormat.format(bImpGrav))));
					montoIva.appendChild(document.createTextNode(String.valueOf(decFormat.format(mIva))));
					montoIce.appendChild(document.createTextNode(String.valueOf(decFormat.format(mIce))));
					valorRetIva.appendChild(document.createTextNode(String.valueOf(decFormat.format(vRetIva))));
					valorRetRenta.appendChild(document.createTextNode(String.valueOf(decFormat.format(vRetRenta))));

					detalleVentas.appendChild(tpIdCliente);
					detalleVentas.appendChild(idCliente);
					detalleVentas.appendChild(parteRelVtas);
					detalleVentas.appendChild(tipoComprobante);
					detalleVentas.appendChild(numeroComprobantes);
					detalleVentas.appendChild(baseNoGraIva);
					detalleVentas.appendChild(baseImponible);
					detalleVentas.appendChild(baseImpGrav);
					detalleVentas.appendChild(montoIva);
					detalleVentas.appendChild(montoIce);
					detalleVentas.appendChild(valorRetIva);
					detalleVentas.appendChild(valorRetRenta);
					ventas.appendChild(detalleVentas);

					bNoGraIva = 0;
					bImponible = 0;
					bImpGrav = 0;
					mIva = 0;
					mIce = 0;
					vRetIva = 0;
					vRetRenta = 0;
					cont = 1;
				}

				cont++;

				// sumar valor de total de ventas
				totalVentasEmpresa += listVentas.get(i).getBaseImp() + listVentas.get(i).getBaseImpGravada() + listVentas.get(i).getBaseImpNoGravada();
				
			}

			totalVentas.appendChild(document.createTextNode(String.valueOf(decFormat.format(totalVentasEmpresa))));
		}
		
		Element ventasEstablecimiento = document.createElement("ventasEstablecimiento");
		Element codEstab = null;
		Element ventasEstab = null;
		float totalVentasEstab = 0;

		// no deben ser todas las empresas.
		for (PuntoEmision puntosEmisionEmpresa : empresa.getPuntoEmisions()) {
			totalVentasEstab = 0;
			List<Venta> ventasVsEstab  = ventaDao.listByPuntoEmision(empresa.getIdEmpresa(), puntosEmisionEmpresa.getId(), anno, mes);

			for (Venta venta : ventasVsEstab) {
				totalVentasEstab += venta.getBaseImp() + venta.getBaseImpGravada() + venta.getBaseImpNoGravada();
			}
			// aqui van separados por todos los establecimientos de la empresa
			Element ventaEst = document.createElement("ventaEst");
			codEstab = document.createElement("codEstab"); codEstab.appendChild(document.createTextNode(puntosEmisionEmpresa.getSerie().substring(0, 3)));
			ventasEstab = document.createElement("ventasEstab"); ventasEstab.appendChild(document.createTextNode(String.valueOf(decFormat.format(totalVentasEstab))));
			ventaEst.appendChild(codEstab);
			ventaEst.appendChild(ventasEstab); 
			ventasEstablecimiento.appendChild(ventaEst);
		}

		/*	        Element exportaciones = document.createElement("exportaciones");
	        for (Exportacion exportacion : listExportaciones) {

	        	Element detalleExportaciones = document.createElement("detalleExportaciones");
		        Element exportacionDe = document.createElement("exportacionDe"); exportacionDe.appendChild(document.createTextNode("esto mismo"));
		        Element tipoComprobante = document.createElement("tipoComprobante"); tipoComprobante.appendChild(document.createTextNode("esto mismo"));
		        Element distAduanero = document.createElement("distAduanero"); distAduanero.appendChild(document.createTextNode("esto mismo"));
		        Element anio = document.createElement("anio"); anio.appendChild(document.createTextNode("esto mismo"));
		        Element regimen = document.createElement("regimen"); regimen.appendChild(document.createTextNode("esto mismo"));
		        Element correlativo = document.createElement("correlativo"); correlativo.appendChild(document.createTextNode("esto mismo"));
		        Element docTransp = document.createElement("docTransp"); docTransp.appendChild(document.createTextNode("esto mismo"));
		        Element fechaEmbarque = document.createElement("fechaEmbarque"); fechaEmbarque.appendChild(document.createTextNode("esto mismo"));
		        Element valorFOBComprobante = document.createElement("valorFOBComprobante"); valorFOBComprobante.appendChild(document.createTextNode("esto mismo"));
		        Element establecimiento = document.createElement("establecimiento"); establecimiento.appendChild(document.createTextNode("esto mismo"));
		        Element puntoEmision = document.createElement("puntoEmision"); puntoEmision.appendChild(document.createTextNode("esto mismo"));
		        Element secuencial = document.createElement("secuencial"); secuencial.appendChild(document.createTextNode("esto mismo"));
		        Element autorizacion = document.createElement("autorizacion"); autorizacion.appendChild(document.createTextNode("esto mismo"));
		        Element fechaEmision = document.createElement("fechaEmision"); fechaEmision.appendChild(document.createTextNode("esto mismo"));

		        detalleExportaciones.appendChild(exportacionDe);
		        detalleExportaciones.appendChild(tipoComprobante);
		        detalleExportaciones.appendChild(distAduanero);
		        detalleExportaciones.appendChild(anio);
		        detalleExportaciones.appendChild(regimen);
		        detalleExportaciones.appendChild(correlativo);
		        detalleExportaciones.appendChild(docTransp);
		        detalleExportaciones.appendChild(fechaEmbarque);
		        detalleExportaciones.appendChild(valorFOBComprobante);
		        detalleExportaciones.appendChild(establecimiento);
		        detalleExportaciones.appendChild(puntoEmision);
		        detalleExportaciones.appendChild(secuencial);
		        detalleExportaciones.appendChild(autorizacion);
		        detalleExportaciones.appendChild(fechaEmision);
		        ventas.appendChild(detalleExportaciones);

			}
		 */
		Element anulados = document.createElement("anulados");

		if(!listAnulados.isEmpty()){

			DocumentosAnulados d = new DocumentosAnulados();
			d.setSecuencia(0);
			listAnulados.add(d);
			int secI = listAnulados.get(0).getSecuencia();
			int valorSecI = listAnulados.get(0).getSecuencia();

			for (int i = 0; i < listAnulados.size(); i++) {

				if(secI != listAnulados.get(i).getSecuencia()){
					Element detalleAnulados = document.createElement("detalleAnulados");
					Element tipoComp = document.createElement("tipoComprobante"); 
					Element establecimiento = document.createElement("establecimiento"); 
					Element puntoEmision = document.createElement("puntoEmision"); 
					Element secuencialInicio = document.createElement("secuencialInicio"); 
					Element secuencialFin = document.createElement("secuencialFin"); 
					Element autorizacion = document.createElement("autorizacion"); 

					tipoComp.appendChild(document.createTextNode(listAnulados.get(i-1).getTipoComprobante().getCodigo()));
					establecimiento.appendChild(document.createTextNode(estab));
					puntoEmision.appendChild(document.createTextNode(puntoE));
					secuencialInicio.appendChild(document.createTextNode(String.valueOf(valorSecI)));
					secuencialFin.appendChild(document.createTextNode(String.valueOf(listAnulados.get(i-1).getSecuencia())));
					autorizacion.appendChild(document.createTextNode(listAnulados.get(i-1).getAutorizacion()));

					detalleAnulados.appendChild(tipoComp);
					detalleAnulados.appendChild(establecimiento);
					detalleAnulados.appendChild(puntoEmision);
					detalleAnulados.appendChild(secuencialInicio);
					detalleAnulados.appendChild(secuencialFin);
					detalleAnulados.appendChild(autorizacion);
					anulados.appendChild(detalleAnulados);

					valorSecI = listAnulados.get(i).getSecuencia();
					secI = listAnulados.get(i).getSecuencia()+1;
				}else{
					secI++;  
				} 
			}
		}        
		iva.appendChild(TipoIDInformante);
		iva.appendChild(IdInformante);
		iva.appendChild(razonSocial);
		iva.appendChild(Anio);
		iva.appendChild(Mes);
		iva.appendChild(numEstabRuc);
		iva.appendChild(totalVentas);
		iva.appendChild(codigoOperativo);
		iva.appendChild(compras);
		iva.appendChild(ventas);
		iva.appendChild(ventasEstablecimiento);
		iva.appendChild(anulados);

		TransformerFactory fábricaTransformador = TransformerFactory.newInstance();
		Transformer transformador = fábricaTransformador.newTransformer();
		Source origen = new DOMSource(document);
		String directorioEmpresaActual = context.getRealPath("/resources");
		Result destino = new StreamResult(directorioEmpresaActual + "\\ATS_"+ mes + "_" + anno + ".xml");
		transformador.transform(origen, destino);
	} 

	/*
	 * Se crea el xml para la factura electrónica
	 */

	public static void generateFacturaElectronica(Venta venta, PuntoEmision ptoEmision, Empresa empresa) throws ParserConfigurationException {
		String string = ptoEmision.getSerie();
		String establecimiento = string.substring(0, 3);
		String puntoE = string.substring(3, 6);

		DecimalFormat decFormat = createDecimalFormat();
		Document document = createDocumentBuilder(null, null, null);

		//Main Node
		Element factura = document.getDocumentElement();
		factura.setAttribute("id","comprobante");
		factura.setAttribute("version", "1.0.0");

		Element infoTributaria = document.createElement("infoTributaria"); 
		Element ambiente = document.createElement("ambiente"); ambiente.appendChild(document.createTextNode(String.valueOf(empresa.getTipoAmbiente().getCodigo())));
		Element tipoEmision = document.createElement("tipoEmision"); tipoEmision.appendChild(document.createTextNode(String.valueOf(empresa.getTipoEmision().getCodigo())));
		Element razonSocial = document.createElement("razonSocial"); razonSocial.appendChild(document.createTextNode(empresa.getNombre()));
		Element nombreComercial = document.createElement("nombreComercial"); nombreComercial.appendChild(document.createTextNode(empresa.getNombreComercial()));
		Element ruc = document.createElement("ruc"); ruc.appendChild(document.createTextNode(empresa.getRucContribuyente()));
		Element claveAcceso = document.createElement("claveAcceso"); claveAcceso.appendChild(document.createTextNode(venta.getClaveAcceso()));
		Element codDoc = document.createElement("codDoc"); codDoc.appendChild(document.createTextNode(venta.getTipoComprobante().getCodigo()));
		Element estab = document.createElement("estab"); estab.appendChild(document.createTextNode(establecimiento));
		Element ptoEmi = document.createElement("ptoEmi"); ptoEmi.appendChild(document.createTextNode(puntoE));
		Element secuencial = document.createElement("secuencial"); secuencial.appendChild(document.createTextNode(String.valueOf(venta.getRetencion().getSecuenciaRetencion())));
		Element dirMatriz = document.createElement("dirMatriz"); dirMatriz.appendChild(document.createTextNode(empresa.getDireccionMatriz()));

		infoTributaria.appendChild(ambiente);
		infoTributaria.appendChild(tipoEmision);
		infoTributaria.appendChild(razonSocial);
		infoTributaria.appendChild(nombreComercial);
		infoTributaria.appendChild(ruc);
		infoTributaria.appendChild(claveAcceso);
		infoTributaria.appendChild(codDoc);
		infoTributaria.appendChild(estab);
		infoTributaria.appendChild(ptoEmi);
		infoTributaria.appendChild(secuencial);
		infoTributaria.appendChild(dirMatriz);

		Element infoFactura = document.createElement("infoFactura"); 
		Element fechaEmision = document.createElement("fechaEmision"); fechaEmision.appendChild(document.createTextNode(String.valueOf(venta.getFechaEmision())));
		Element dirEstablecimiento = document.createElement("dirEstablecimiento"); dirEstablecimiento.appendChild(document.createTextNode(empresa.getDescripcion()));
		Element contibuyenteEspecial = document.createElement("contribuyenteEspecial"); contibuyenteEspecial.appendChild(document.createTextNode(empresa.getNoResolucionContribEspecial()));
		String llevaContabilidad = empresa.getLlevaContabilidad() == true ? "SI" : "NO";
		Element obligadoContabilidad = document.createElement("obligadoContabilidad"); obligadoContabilidad.appendChild(document.createTextNode(llevaContabilidad)); 
		Element tipoIdentificacionComprador = document.createElement("tipoIdentificacionComprador"); tipoIdentificacionComprador.appendChild(document.createTextNode(venta.getPersonalEmpresa().getTipoIdentificacion().getCodigo()));
		Element razonSocialComprador = document.createElement("razonSocialComprador"); razonSocialComprador.appendChild(document.createTextNode(venta.getPersonalEmpresa().getNombre()));
		Element identificadorComprador = document.createElement("identificacionComprador"); identificadorComprador.appendChild(document.createTextNode(venta.getPersonalEmpresa().getId()));

		Element totalConImpuestos = document.createElement("totalConImpuestos");
		Element totalImpuesto = document.createElement("totalImpuesto");
		// Impuesto de IVA
		String codigoPorcentIva = venta.getPorcentajeIva() == 12 ? "2" : "0";
		float bimponible = venta.getBaseImp() + venta.getBaseImpGravada() + venta.getBaseImpNoGravada();
		float valorImpuesto = venta.getPorcentajeIva() == 12 ? bimponible*12/100 : 0; // porcentaje aplicado a la base imponible
		Element codigo = document.createElement("codigo");  codigo.appendChild(document.createTextNode("2"));
		Element codigoPorcentaje = document.createElement("codigoPorcentaje"); codigoPorcentaje.appendChild(document.createTextNode(codigoPorcentIva));
		Element baseImponible = document.createElement("baseImponible"); baseImponible.appendChild(document.createTextNode(String.valueOf(bimponible)));
		Element valor = document.createElement("valor"); valor.appendChild(document.createTextNode(String.valueOf(valorImpuesto)));

		Element totalSinImpuestos = document.createElement("totalSinImpuestos"); totalSinImpuestos.appendChild(document.createTextNode(String.valueOf(bimponible)));
		Element totalDescuento = document.createElement("totalDescuento"); totalDescuento.appendChild(document.createTextNode(String.valueOf(venta.getDescuento())));
		Element propina = document.createElement("propina"); propina.appendChild(document.createTextNode("0.00"));
		Element importeTotal = document.createElement("importeTotal"); importeTotal.appendChild(document.createTextNode(String.valueOf(((bimponible + valorImpuesto)-venta.getDescuento()))));
		Element moneda = document.createElement("moneda"); moneda.appendChild(document.createTextNode("DOLAR")); moneda.appendChild(document.createTextNode("DOLAR"));

		totalImpuesto.appendChild(codigo);
		totalImpuesto.appendChild(codigoPorcentaje);
		totalImpuesto.appendChild(baseImponible);
		totalImpuesto.appendChild(valor);
		totalConImpuestos.appendChild(totalImpuesto);
		infoFactura.appendChild(fechaEmision);
		infoFactura.appendChild(dirEstablecimiento);
		infoFactura.appendChild(contibuyenteEspecial);
		infoFactura.appendChild(obligadoContabilidad);
		infoFactura.appendChild(tipoIdentificacionComprador);
		infoFactura.appendChild(razonSocialComprador);
		infoFactura.appendChild(identificadorComprador);
		infoFactura.appendChild(totalSinImpuestos);
		infoFactura.appendChild(totalDescuento);
		infoFactura.appendChild(totalConImpuestos);
		infoFactura.appendChild(propina);
		infoFactura.appendChild(importeTotal);
		infoFactura.appendChild(moneda);

		Element detalles = document.createElement("detalles");
		Element detalle = null;
		Element codigoPrincipal = null;
		Element codigoAuxiliar = null;
		Element descripcion = null;
		Element cantidad = null;
		Element precioUnitario = null;
		Element descuento = null;
		Element precioTotalSinimpuesto = null;
		Element impuestos = null;
		Element impuesto = null;
		Element cod = null;
		Element codPorcentaje = null;
		Element tarifa = null;
		Element bImponible = null;
		Element val = null;

		for (VentaVsProducto ventaVsProducto : venta.getVentaVsProductos()) {

			double ptSinImp = (ventaVsProducto.getProducto().getValorUnitario() - ventaVsProducto.getDescuento()) * ventaVsProducto.getCantidad();
			detalle = document.createElement("detalle");
			codigoPrincipal = document.createElement("codigoPrincipal"); codigoPrincipal.appendChild(document.createTextNode(ventaVsProducto.getProducto().getCodigoPrincipal()));
			codigoAuxiliar = document.createElement("codigoAuxiliar"); codigoAuxiliar.appendChild(document.createTextNode(ventaVsProducto.getProducto().getCodigoAuxiliar()));
			descripcion = document.createElement("descripcion"); descripcion.appendChild(document.createTextNode(ventaVsProducto.getProducto().getDescripcion1()));
			cantidad = document.createElement("cantidad"); cantidad.appendChild(document.createTextNode(String.valueOf(ventaVsProducto.getCantidad())));
			precioUnitario = document.createElement("precioUnitario"); precioUnitario.appendChild(document.createTextNode(decFormat.format(ventaVsProducto.getProducto().getValorUnitario())));
			descuento = document.createElement("descuento"); descuento.appendChild(document.createTextNode(decFormat.format(ventaVsProducto.getDescuento())));
			precioUnitario = document.createElement("precioUnitario"); precioUnitario.appendChild(document.createTextNode(decFormat.format(ventaVsProducto.getProducto().getValorUnitario())));
			precioTotalSinimpuesto = document.createElement("precioTotalSinImpuesto"); precioTotalSinimpuesto.appendChild(document.createTextNode(decFormat.format(ptSinImp)));

			impuestos = document.createElement("impuestos");
			impuesto = document.createElement("impuesto");
			// Impuesto de IVA
			String codPorcentIva = String.valueOf(ventaVsProducto.getProducto().getTarifaIva().getCodigo());
			String tarifaIva = codPorcentIva == "2" ? "12" : "0";
			float base = ventaVsProducto.getProducto().getValorUnitario() * ventaVsProducto.getCantidad();
			float valImpuesto = tarifaIva == "12" ? base*12/100 : 0; // porcentaje aplicado a la base imponible
			cod = document.createElement("codigo"); cod.appendChild(document.createTextNode("2"));
			codPorcentaje = document.createElement("codigoPorcentaje"); codigoPorcentaje.appendChild(document.createTextNode(codPorcentIva));
			tarifa = document.createElement("tarifa"); tarifa.appendChild(document.createTextNode(tarifaIva));
			bImponible = document.createElement("baseImponible"); baseImponible.appendChild(document.createTextNode(String.valueOf(base)));
			val = document.createElement("valor"); val.appendChild(document.createTextNode(String.valueOf(valImpuesto)));

			impuesto.appendChild(cod);
			impuesto.appendChild(codPorcentaje);
			impuesto.appendChild(tarifa);
			impuesto.appendChild(bImponible);
			impuesto.appendChild(val);
			impuestos.appendChild(impuesto);
			detalle.appendChild(codigoPrincipal);
			detalle.appendChild(codigoAuxiliar);
			detalle.appendChild(descripcion);
			detalle.appendChild(cantidad);
			detalle.appendChild(precioUnitario);
			detalle.appendChild(descuento);
			detalle.appendChild(precioTotalSinimpuesto);
			detalle.appendChild(impuestos);
			detalles.appendChild(detalle);
		}

		// verificar si hay q poner las retenciones

		Element infoAdicional = document.createElement("infoAdicional");
		Element campoAdicional = document.createElement("campoAdicional"); campoAdicional.setAttribute("nombre", "Dirección"); campoAdicional.appendChild(document.createTextNode("------------"));
		infoAdicional.appendChild(campoAdicional);
		campoAdicional.cloneNode(true); campoAdicional.setAttribute("nombre", "Teléfono");
		infoAdicional.appendChild(campoAdicional);
		campoAdicional.cloneNode(true); campoAdicional.setAttribute("nombre", "Email");
		infoAdicional.appendChild(campoAdicional);

		factura.appendChild(infoTributaria);
		factura.appendChild(infoFactura);
		factura.appendChild(detalles);
		factura.appendChild(infoAdicional);
	}

	/**
	 * Crea un DOcumentBuilder
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static Document createDocumentBuilder(String arg0, String arg1, DocumentType arg2 ) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		DOMImplementation implementation = builder.getDOMImplementation();
		Document document = implementation.createDocument(arg0, arg1, arg2);
		document.setXmlVersion("1.0");
		document.getXmlEncoding();
		

		return document;
	}

	/**
	 * Crea un DecimalFormat
	 * @return
	 */
	private static DecimalFormat createDecimalFormat() {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator('.'); 
		DecimalFormat df = new DecimalFormat("########0.00");
		df.setDecimalFormatSymbols(otherSymbols);

		return df;
	}
}

