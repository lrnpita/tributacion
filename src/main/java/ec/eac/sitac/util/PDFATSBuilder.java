package ec.eac.sitac.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.Exportacion;
import ec.eac.sitac.model.Venta;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFATSBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<Compra> ListCompras = (List<Compra>) model.get("listCompras");
		List<Venta> ListVentas = (List<Venta>) model.get("listVentas");
		List<Exportacion> ListExportaciones = (List<Exportacion>) model.get("listExportaciones");
		
		doc.addTitle("ReporteATS");
		int cantidadAnulados = 0;
		float retencionIvaVenta = 0;
		float retencionIRVenta = 0;
		
		String imageName = "/workspace/java/sitacejava/src/main/resources/sri.jpg";
		Image imagen = Image.getInstance(imageName);
		doc.add(imagen);
		
		Paragraph paragraph = new Paragraph("TALÓN RESUMEN DE ANEXO TRANSACCIONAL");
		paragraph.setSpacingAfter(Float.valueOf(10));
		doc.add(paragraph);
		
		Paragraph paragraph2 = new Paragraph("SERVICIO DE RENTAS INTERNAS - RIG");
		paragraph2.setSpacingAfter(Float.valueOf(10));
		doc.add(paragraph2);
		
		Paragraph razonSocial = new Paragraph("RAZÓN SOCIAL: " + model.get("razonSocial"));
		razonSocial.setSpacingAfter(Float.valueOf(10));
		doc.add(razonSocial);
		
		Paragraph ruc = new Paragraph("Ruc: " + model.get("ruc"));
		ruc.setSpacingAfter(Float.valueOf(10));
		doc.add(ruc);
		
		Paragraph certifico = new Paragraph("Certifico que la información obtenida en el medio magnético adjunto al presente de Anexo Transaccional para el período "+ model.get("mes") +"-" + model.get("anno") + ", es fiel refrejo del siguiente reporte.");
		certifico.setSpacingAfter(Float.valueOf(10));
		doc.add(certifico);
		
		PdfPTable tableCompras = new PdfPTable(7);
		tableCompras.setWidthPercentage(100.0f);
		tableCompras.setWidths(new float[] {1.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f, 2.0f});
		tableCompras.setSpacingBefore(5);
		tableCompras.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		PdfPTable tableVentas = new PdfPTable(7);
		tableVentas.setWidthPercentage(100.0f);
		tableVentas.setWidths(new float[] {1.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f, 2.0f});
		tableVentas.setSpacingBefore(8);
		tableVentas.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		PdfPTable tableExportaciones = new PdfPTable(4);
		tableExportaciones.setWidthPercentage(100.0f);
		tableExportaciones.setWidths(new float[] {1.0f, 2.0f, 2.0f, 3.0f});
		tableExportaciones.setSpacingBefore(8);
		tableExportaciones.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		
		PdfPTable tableAnulados = new PdfPTable(2);
		tableAnulados.setWidthPercentage(100.0f);
		tableAnulados.setWidths(new float[] {1.0f, 1.0f});
		tableAnulados.setSpacingBefore(8);
		tableAnulados.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		
		PdfPTable tableRetenciones = new PdfPTable(7);
		tableRetenciones.setWidthPercentage(100.0f);
		tableRetenciones.setWidths(new float[] {1.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f, 2.0f});
		tableRetenciones.setSpacingBefore(8);
		tableRetenciones.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		PdfPTable tableRetencionesVentas = new PdfPTable(3);
		tableRetencionesVentas.setWidthPercentage(100.0f);
		tableRetencionesVentas.setWidths(new float[] {2.0f, 3.0f, 3.0f});
		tableRetencionesVentas.setSpacingBefore(8);
		tableRetencionesVentas.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setColor(BaseColor.BLACK);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setPadding(5);
		
		// define font for table header row
		Font fontTitulo = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		fontTitulo.setColor(BaseColor.WHITE);
		
		// define table title
		PdfPCell cellTitulo = new PdfPCell();
		cellTitulo.setBackgroundColor(BaseColor.BLUE);
		cellTitulo.setPadding(5);
		
		// write table header 
		cellTitulo.setPhrase(new Phrase("COMPRAS", fontTitulo));
		cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellTitulo.setColspan(7);
		tableCompras.addCell(cellTitulo);
		cellTitulo.setPhrase(new Phrase("VENTAS", fontTitulo));
		tableVentas.addCell(cellTitulo);
		cellTitulo.setPhrase(new Phrase("EXPORTACIONES", fontTitulo));
		tableExportaciones.addCell(cellTitulo);
		cellTitulo.setPhrase(new Phrase("COMPROBANTES ANULADOS", fontTitulo));
		tableAnulados.addCell(cellTitulo);
		cellTitulo.setPhrase(new Phrase("RESUMEN DE RETENCIONES - AGENTE DE RETENCION", fontTitulo));
		tableRetenciones.addCell(cellTitulo);
		cellTitulo.setPhrase(new Phrase("RESUMEN DE RETENCIONES QUE LE HAN EFECTUADO EN EL PERIODO", fontTitulo));
		tableRetencionesVentas.addCell(cellTitulo);
		
		cell.setPhrase(new Phrase("Cód.", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		tableExportaciones.addCell(cell);
		
		cell.setPhrase(new Phrase("Transacción", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		tableExportaciones.addCell(cell);

		cell.setPhrase(new Phrase("No. Reg.", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		tableExportaciones.addCell(cell);
		
		cell.setPhrase(new Phrase("BI Tarifa 0%", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("BI Tarifa 12%", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Base No Obj. de Iva", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Valor IVA", font));
		tableCompras.addCell(cell);
		tableVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Operación", font));
		tableRetencionesVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Tipo de Retención", font));
		tableRetencionesVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Valor Retenido", font));
		tableRetencionesVentas.addCell(cell);
		
		cell.setPhrase(new Phrase("Valor FOB Comprobante", font));
		tableExportaciones.addCell(cell);
		
		cell.setPhrase(new Phrase("Total de comprobantes anulados (no incluye los datos de baja)", font));
		tableAnulados.addCell(cell);
		cell.setPhrase(new Phrase(String.valueOf(cantidadAnulados), font));
		tableAnulados.addCell(cell);
		
		doc.add(tableCompras);
		doc.add(tableVentas);
		doc.add(tableExportaciones);
		doc.add(tableAnulados);
		doc.add(tableRetenciones);
		
//------------------------------------ tabla de resumen de retenciones de la ventas-------------------------------------------------		
		tableRetencionesVentas.addCell("Venta");
		tableRetencionesVentas.addCell("Valor de IVA que le han retenido");
		tableRetencionesVentas.addCell(String.valueOf(retencionIvaVenta));
		
		tableRetencionesVentas.addCell("Venta");
		tableRetencionesVentas.addCell("Valor de RENTA que le han retenido");
		tableRetencionesVentas.addCell(String.valueOf(retencionIRVenta));
		
		doc.add(tableRetencionesVentas);
		
 
		
	}

}
