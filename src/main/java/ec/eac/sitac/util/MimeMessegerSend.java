package ec.eac.sitac.util;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import ec.eac.sitac.model.Compra;

public class MimeMessegerSend {
	private JavaMailSender mailSender;
	MimeMessagePreparator m;
	MimeMessageHelper message;
	ServletContext context;

	public MimeMessegerSend(JavaMailSender mailSender, ServletContext contex) {
		super();
		this.mailSender = mailSender;
		this.context = contex;
	}

	public void enviarCompRetencion( final Compra compra) {

		final String pathAux = "/" + "ESIGEF" + "/" + "Empresa" + String.valueOf(compra.getEmpresa().getIdEmpresa());
		final String directorioEmpresaActual = context.getRealPath(pathAux);
		final String directorio = context.getRealPath("/resources");
		final String secuencia = Utility.rellenarCadenaConCeros(compra.getRetencion().getSerieRetencion());

		m = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mime) throws Exception {
				// TODO Auto-generated method stub
				message = new MimeMessageHelper(mime, true, "UTF-8");
				message.setTo(compra.getPersonalEmpresa().getEmail());
				message.setSubject("Comprobante de retención");
				message.setText("", "<p>Estimado Proveedor, Su comprobante de retención se generó exitosamente con el siguiente detalle:"
						+ "</p><br/><p>FECHA DE EMISIÓN: "+ String.valueOf(compra.getRetencion().getFechaRetencion()) 
						+"</p><br/><p>COMPROBANTE ELECTRÓNICO No.: "+ compra.getPuntoEmision().getSerie() +"-"+ secuencia
						+ "</p><br/><p>IDENTIFICACIÓN: "+ compra.getPersonalEmpresa().getId()
						+ "</p><br/><p>RAZÓN SOCIAL: "+ compra.getPersonalEmpresa().getNombre()
						+ "</p><br/><p>CLAVE ACCESO: "+ compra.getRetencion().getClaveAcceso()
						+ "</p><br/><p>AUTORIZACIÓN: "+ compra.getRetencion().getAutorizacionRetencion()
						+ "</p><br/><p>Se agregó a este correo su comprobante electrónico con la interpretación en PDF.</p><br/>"
						+ "<p>Saludos cordiales,: </p><br/>"
						+ "<p>"+ compra.getEmpresa().getNombre() + "</p><br/>"
						+ "<p>RUC: "+ compra.getEmpresa().getRucContribuyente() +"</p>");
				message.addAttachment(compra.getIdCompra() + ".pdf", new File(directorio + File.separator + compra.getIdCompra() + ".pdf"));
				message.addAttachment(compra.getArchivoXMLAsociado(),  new File(directorioEmpresaActual + File.separator + compra.getArchivoXMLAsociado()));
			}
		};

		mailSender.send(m);	

	}
}
