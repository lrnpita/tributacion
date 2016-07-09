package ec.eac.sitac.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.PuntoEmision;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDF103Builder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<Compra> ListCompras = (List<Compra>) model.get("ListCompra");
		PuntoEmision puntoEmision = (PuntoEmision) model.get("puntoEmision");
		
		Paragraph paragraph = new Paragraph("Reporte de Compras");
		paragraph.setSpacingAfter(Float.valueOf(20));
		doc.add(paragraph);
		
		// define current date
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = (Date) Calendar.getInstance().getTime();
		String myDate = format.format(date);
		
		Paragraph fecha = new Paragraph(myDate);
		fecha.setSpacingAfter(Float.valueOf(20));
		doc.add(fecha); 
		
		PdfPTable table = new PdfPTable(17);
		table.setSpacingAfter(20);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {2.0f, 1.6f, 1.3f, 1.3f, 1.0f , 1.5f , 1.0f , 1.0f , 1.0f , 1.0f , 1.0f , 1.0f , 1.0f , 1.0f, 1.5f , 1.0f , 1.5f});
		table.setSpacingBefore(2);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setColor(BaseColor.WHITE);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(5);
		// write table header 
		cell.setPhrase(new Phrase("Proveedor", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Registro", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Comprob.", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Secuencia", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Tipo Trans.", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Base no Obj. Iva", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Base 0%", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Base 12%", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Iva", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("% Ret", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Ret. Iva", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Base Ret.IR", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("% Ret", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Ret I.R", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Crédito", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Dev", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Comp.Retenc.", font));
		table.addCell(cell);
		
		Float BaseNoObjIva = null;
		Float baseImponible0 = null;
		Float baseImponible12 = null;
		float totalBaseNoObjIva = 0;
		float totalBaseImonible0 = 0;
		float totalBaseImponible12 = 0;
		float totalIva = 0;
		float totalRetIva = 0;
		float totalBaseRetIR = 0;
		float totalRetIR = 0;
		float iva = 0;
		float retIva = 0;
		float baseRetIR = 0;
		
		for (Compra compra : ListCompras) {
			// proveedor
			table.addCell(compra.getPersonalEmpresa().getId());
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			Date fechaRegistro = (Date) compra.getRegistroContable();
			String registro = df.format(fechaRegistro);
			// registro
			table.addCell(registro);
			// Comprobante
			table.addCell(compra.getTipoComprobante().getCodigo());
			// secuencia
			table.addCell(String.valueOf(compra.getRetencion().getSecuenciaRetencion()));
			// crédito
			table.addCell(compra.getTipoCompra().getCodigo());
			// dev
			if(compra.isDevolucionIva()){
				table.addCell("S");
			}
			else {
				table.addCell("N");
			}
			// comprobante de retención
			String comprobanteRetencion = compra.getRetencion().getSerieRetencion() + compra.getRetencion().getSecuenciaRetencion();
			table.addCell(comprobanteRetencion);
			
			for (DetallesCompra detalle : compra.getDetallesCompras()) {

				BaseNoObjIva = detalle.getBaseNoObjetoIva();
				baseImponible0 = detalle.getBaseImpobible0();
				baseImponible12 = detalle.getBaseImponible12();

				// tipo de transaccion
				table.addCell(detalle.getTipoTransaccion().getCodigo());	
				// base no obj de iva
				table.addCell(String.valueOf(BaseNoObjIva));
				// base 0%
				table.addCell(String.valueOf(baseImponible0));
				// base 12%
				table.addCell(String.valueOf(baseImponible12));
				// IVA --> (base12% * porcentajeIva)/100 
				iva = (baseImponible12 * detalle.getPorcentajeIva())/100;
				table.addCell(String.valueOf(iva));
				// % Ret de iva
				table.addCell(String.valueOf(detalle.getRIret()));
				// Ret. Iva
				retIva = (iva * detalle.getRIret())/100;
				table.addCell(String.valueOf(retIva));
				// Base Ret.IR
				baseRetIR = BaseNoObjIva + baseImponible0 + baseImponible12;	
				table.addCell(String.valueOf(baseRetIR));
				// % Rer de impuesto a la renta
				table.addCell(String.valueOf(detalle.getRIRret()));
				// Ret.IR
				Float retRIR = (baseRetIR * detalle.getRIRret())/100;
				table.addCell(String.valueOf(retRIR));

				totalBaseNoObjIva += BaseNoObjIva;
				totalBaseImonible0 += baseImponible0;
				totalBaseImponible12 += baseImponible12;
				totalIva += iva;
				totalRetIva += retIva;
				totalRetIR += retRIR;
				totalBaseRetIR += baseRetIR;


				table.completeRow();
				PdfPCell celda = new PdfPCell();
				celda.setBackgroundColor(BaseColor.WHITE);
				celda.setPadding(5);
				celda.setColspan(4);
				celda.setBorder(Rectangle.NO_BORDER);
				table.addCell(celda);

			}
		}

		doc.add(table);
		
		doc.add(new Paragraph("TOTAL"));
		doc.add(new Paragraph("Base No Obj de Iva = " + String.valueOf(totalBaseNoObjIva)));
		doc.add(new Paragraph("Base 0% = " + String.valueOf(totalBaseImonible0)));
		doc.add(new Paragraph("Base Gravada = " + String.valueOf(totalBaseImponible12)));
		doc.add(new Paragraph("Iva = " + String.valueOf(totalIva)));
		doc.add(new Paragraph("Retención Iva = " + String.valueOf(totalRetIva)));
		doc.add(new Paragraph("Base Retención I.R= " + String.valueOf(totalBaseRetIR)));
		doc.add(new Paragraph("Retención I.R = " + String.valueOf(totalRetIR)));
		
	}

}
