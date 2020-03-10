import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class GeneraPDF extends Thread {

	private File dirOrigen;
	private String xsl;

	public GeneraPDF(File dirOrigen, String xsl) {
		this.dirOrigen=dirOrigen;
		this.xsl=xsl;

	}

	public void run() {
		try {
			convertToPDF(dirOrigen,xsl);
		} catch (FOPException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void convertToPDF(File dirOrigen, String xsl)  throws IOException, FOPException, TransformerException {
		File[] files = dirOrigen.listFiles();
		for (File file : files) {
			String nombreXML = file.getName();
			String archivoXML = (dirOrigen.toString() + '\\'+ nombreXML);
			if (!nombreXML.equals("PDF")) {
				convertToPDF(archivoXML, xsl, dirOrigen, nombreXML);	
			}
		}
	}

	public void convertToPDF(String xml, String xsl, File dirOrigen, String nombreXML)  throws IOException, FOPException, TransformerException {

		File xsltFile = new File(xsl);
		StreamSource xmlSource = new StreamSource(new File(xml));
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		OutputStream out;
		String nombrePDF = nombreXML.substring(0,17);
		String nombreFinal = ("C:\\\\Users\\\\mcasteletti\\\\Desktop\\\\groupon\\\\prueba\\\\proceso\\"+"\\PDF-NC\\"+nombrePDF+".pdf");
		System.out.println(nombreFinal);
		out = new java.io.FileOutputStream(nombreFinal);
		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
			SAXResult res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(xmlSource, (javax.xml.transform.Result) res);
			out.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
