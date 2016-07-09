package ec.eac.sitac.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ec.eac.sitac.model.Compra;
import ec.eac.sitac.model.DetallesCompra;
import ec.eac.sitac.model.Empresa;
import ec.eac.sitac.model.Retencion;

public class PDFGenerator {
	
	private static final Logger logger = Logger.getLogger(PDFGenerator.class);

	/**
	 * Genera el PDF de Retenciones
	 * 
	 * @param nombrePDF, sin la extension
	 * @param compra
	 * @param context
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public static void generarPDFRetencion(Compra compra, ServletContext context) throws FileNotFoundException, DocumentException
	   {
		  String directorioEmpresaActual = context.getRealPath("/resources");
	      FileOutputStream archivo = new FileOutputStream(directorioEmpresaActual + File.separatorChar + compra.getIdCompra() + ".pdf");
	      Document documento = new Document(PageSize.A3.rotate());
	      PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	     
	      documento.open();
	      PdfContentByte cb = writer.getDirectContent();
	      
	      SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	      
	      // Fuentes
	      Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	      font.setColor(BaseColor.BLACK);
	      font.setSize(18);
	      
	      Font cfont = FontFactory.getFont(FontFactory.HELVETICA);
	      cfont.setColor(BaseColor.BLACK);
	      cfont.setSize(24);
	      
	      Empresa empresa = compra.getEmpresa();
	      Retencion retencion = compra.getRetencion();
	      String seriePuntoEmision = compra.getPuntoEmision().getSerie();
	      String estabFactura = seriePuntoEmision.substring(0, 3);
	      String puntoEFactura = seriePuntoEmision.substring(3, 6);
	    	
	      Paragraph ruc = new Paragraph("RUC: "+ empresa.getRucContribuyente(), cfont);
	      ruc.setSpacingAfter(Float.valueOf(5));
	      
	      Paragraph comp = new Paragraph("COMPROBANTE DE RETENCION: ", cfont);
	      comp.setSpacingAfter(Float.valueOf(5));
			
	      // Verificar q este es el No.
	      Paragraph No = new Paragraph("No: " + retencion.getSerieRetencion() + "-" + Utility.rellenarCadenaConCeros(String.valueOf(retencion.getSecuenciaRetencion())), font);
	      No.setSpacingAfter(Float.valueOf(5));
			
	      Paragraph noAut = new Paragraph("Número de Autorización: " + retencion.getAutorizacionRetencion(), font);
	      noAut.setSpacingAfter(Float.valueOf(5));
	      
	      Paragraph fechaAut = new Paragraph("Fecha de Atorización: " + retencion.getFechaRetencion(), font);
	      noAut.setSpacingAfter(Float.valueOf(5));
			
	      Paragraph ambiente = null;
	      if(empresa.getTipoAmbiente().getCodigo() == 1){
		      ambiente = new Paragraph("Ambiente: PRUEBAS", font);
		      ambiente.setSpacingAfter(Float.valueOf(5));
	      }else{
	    	  ambiente = new Paragraph("Ambiente: PRODUCCION", font);
	    	  ambiente.setSpacingAfter(Float.valueOf(5));
	      }
	      
	      Paragraph emision = null;
	      if(empresa.getTipoEmision().getCodigo() == 1){
		      emision = new Paragraph("Emisión: NORMAL", font);
		      emision.setSpacingAfter(Float.valueOf(5));
	      }else{
	    	  emision = new Paragraph("Emisión: CONTINGENCIA", font);
	    	  emision.setSpacingAfter(Float.valueOf(5));
	      }

	      Paragraph clave = new Paragraph("Clave de acceso: ", font);
	      clave.setSpacingAfter(Float.valueOf(5));
	      Barcode39 code39ext = new Barcode39();
	      code39ext.setCode(compra.getRetencion().getClaveAcceso());
	      code39ext.setStartStopText(false);
	      code39ext.setExtended(true);
	      
	      String imagePath = directorioEmpresaActual + File.separatorChar + "uce.png";
		  Image imagen = null;
		  try {
			  imagen = Image.getInstance(imagePath);
		  } catch (MalformedURLException e) {
			  logger.error("This is Error message", e);
		  } catch (IOException e) {
			  logger.error("This is Error message", e);
		  }
		  documento.add(imagen);
			
	      ColumnText column = new ColumnText(writer.getDirectContent());
	      Rectangle rectangle = new Rectangle(580, 400, 1150, 800);
	      rectangle.setBorder(Rectangle.BOX);
	      rectangle.setBorderWidth(2);
	      rectangle.setBorderColor(BaseColor.BLACK);
	      rectangle.setBackgroundColor(BaseColor.WHITE);
	      cb.rectangle(rectangle);
	      
	      column.setSimpleColumn(rectangle);
	      column.setAlignment(Element.ALIGN_CENTER);
	     // column.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
	      column.addElement(ruc);
	      column.addElement(comp);
	      column.addElement(No);
	      column.addElement(noAut);
	      column.addElement(fechaAut);
	      column.addElement(ambiente);
	      column.addElement(emision);
	      column.addElement(clave);
	      column.addElement(code39ext.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLACK));
	     // column.addElement(Chunk.NEWLINE);
	      column.go();
	      
	      Paragraph razon = new Paragraph(empresa.getNombre(), font);
    	  razon.setSpacingAfter(Float.valueOf(5));
	      documento.add(razon);
	      
	      Paragraph direcMatriz = new Paragraph("Dirección Matriz: "+empresa.getDireccionMatriz(), font);
	      direcMatriz.setSpacingAfter(Float.valueOf(5));
	      documento.add(direcMatriz);
	      
	      Paragraph llevaContab = new Paragraph("Obligado a llevar contabilidad: SI", font);
	      llevaContab.setSpacingAfter(Float.valueOf(5));
	      documento.add(llevaContab);
	      
	      Paragraph contEspecial = new Paragraph("Costribullente especial Res.No: "+ empresa.getNoResolucionContribEspecial(), font);
	      contEspecial.setSpacingAfter(Float.valueOf(5));
	      documento.add(contEspecial);
	      
	      Paragraph identififcacion = new Paragraph("Identificación: "+ compra.getPersonalEmpresa().getId(), font);
	      identififcacion.setSpacingAfter(Float.valueOf(5));
	      documento.add(identififcacion);
			
	      Paragraph NameProveedor = new Paragraph("Proveedor: "+ compra.getPersonalEmpresa().getNombre(), font);
	      NameProveedor.setSpacingAfter(Float.valueOf(5));
	      documento.add(NameProveedor);
			
	      Paragraph direccion = new Paragraph("Dirección: "+ compra.getPersonalEmpresa().getDireccion(), font);
	      direccion.setSpacingAfter(Float.valueOf(5));
	      documento.add(direccion);
			
	      Paragraph email = new Paragraph("E-mail: "+compra.getPersonalEmpresa().getEmail(), font);
	      email.setSpacingAfter(Float.valueOf(5));
	      documento.add(email);
			
	      Paragraph fecha = new Paragraph("Fecha: " + retencion.getFechaRetencion(), font);
	      fecha.setSpacingAfter(Float.valueOf(5));
	      documento.add(fecha);
			
	      Paragraph telf = new Paragraph("Teléfono: "+ compra.getPersonalEmpresa().getTelefono(), font);
	      telf.setSpacingAfter(Float.valueOf(5));
	      documento.add(telf);
		
	      PdfPTable table = new PdfPTable(9);
	      table.setWidthPercentage(100.0f);
	      table.setWidths(new float[] {1.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
	      table.setSpacingBefore(5);
	      table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		
	      // define table header cell
	      PdfPCell cell = new PdfPCell();
	      cell.setBackgroundColor(BaseColor.WHITE);
	      cell.setPadding(5);
		
	      cell.setPhrase(new Phrase("Comprobante", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Número", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Fecha Emisión", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Ejercicio Fiscal", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Impuesto", font));
	      table.addCell(cell);
	      
	      cell.setPhrase(new Phrase("Código", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Base Imponible", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Porcentaje", font));
	      table.addCell(cell);
		
	      cell.setPhrase(new Phrase("Valor Retenido", font));
	      table.addCell(cell);
	
	      //comprobante
	      table.addCell(compra.getTipoComprobante().getNombre());
	      //Número
	      table.addCell(estabFactura + puntoEFactura + "-" + Utility.rellenarCadenaConCeros(String.valueOf(compra.getPuntoEmision().getSecFactura())));
	      //fecha emision
	      table.addCell(String.valueOf(compra.getFechaEmision()));
	      //ejercicio fiscal
	      String s = dt.format(compra.getFechaEmision());
	      table.addCell(s.substring(3, 10));
	      
	      int cont = 1;
	      for (DetallesCompra detalle : compra.getDetallesCompras()) {
	    	  
	    	  table.addCell("IVA");
	    	  float codigoRI = 0;
	    	  if(detalle.getRIret() == 70){
	    		  codigoRI = 2;
	    	  } else if(detalle.getRIret() == 100){
	    		  codigoRI = 3;
	    	  } 
	    	  
	    	  table.addCell(String.valueOf(codigoRI));
    		  table.addCell(String.valueOf(detalle.getRIvalorIva()));
    		  table.addCell(String.valueOf(detalle.getRIret()));
    		  float vri = detalle.getRIvalorIva() * detalle.getRIret()/100;
    		  table.addCell(String.valueOf(vri));
    		 
    		  table.completeRow();
    		  PdfPCell celda = new PdfPCell();
    		  celda.setBackgroundColor(BaseColor.WHITE);
    		  celda.setPadding(5);
    		  celda.setColspan(4);
    		  celda.setBorder(Rectangle.NO_BORDER);
    		  table.addCell(celda);
    		  
    		  table.addCell("RENTA");
    		  table.addCell(detalle.getTipoTransaccion().getCodigo());
    		  table.addCell(String.valueOf(detalle.getRIRbaseImp12()));
    		  table.addCell(String.valueOf(detalle.getRIRret()));
    		  float vrr = detalle.getRIRbaseImp12() * detalle.getRIRret()/100;
    		  table.addCell(String.valueOf(vrr));
    		  
    		  cont ++;
    		  if(cont <= compra.getDetallesCompras().size()){
    			  table.completeRow();
        		  PdfPCell cel = new PdfPCell();
        		  cel.setBackgroundColor(BaseColor.WHITE);
        		  cel.setPadding(5);
        		  cel.setColspan(4);
        		  cel.setBorder(Rectangle.NO_BORDER);
        		  table.addCell(cel); 
    		  }
		}
	      
	     documento.add(table);
		
	      Paragraph total = new Paragraph("Total a retener: " + retencion.getTotalRetencion(), font);
	      total.setSpacingAfter(Float.valueOf(5));
	      documento.add(total);
		
	/*      Paragraph numLetras = new Paragraph("Son: ", font);
	      numLetras.setSpacingAfter(Float.valueOf(5));
	      documento.add(numLetras);
	       */
	      documento.close();
	   }

}
