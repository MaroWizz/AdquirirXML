import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

public class MainClass {
	static MainClass mc = new MainClass();
	public static void main(String[] args) throws FOPException, IOException, TransformerException {

		//Ejecución de hilos en la generación de PDFs
//		File dirOrigen = new File ("C:\\Users\\mcasteletti\\Desktop\\groupon\\prueba\\proceso\\NC\\1");
//		File dirOrigen2 = new File ("C:\\Users\\mcasteletti\\Desktop\\groupon\\prueba\\proceso\\NC\\2");
//		String xsl = "C:\\Users\\mcasteletti\\Desktop\\groupon\\prueba\\xslNC.xsl";
//		GeneraPDF gp1 = new GeneraPDF(dirOrigen1, xsl);
//		GeneraPDF gp2 = new GeneraPDF(dirOrigen2, xsl);
//		gp1.start();
//		gp2.start();
		File dirOrigen = new File ("C:\\Users\\mcasteletti\\Desktop\\1");
		String xsl = "C:\\Users\\mcasteletti\\Desktop\\1xslNC.xsl";
		mc.buscarXML(dirOrigen);
		
		
		
		//mc.buscarXSL();
		//mc.convertToPDF(dirOrigen, xsl);
		//mc.buscarXML(dirOrigen, xsl);
		//String xml = "C:\\Users\\mcasteletti\\Desktop\\groupon\\xml.xml";

		
		
		
		

	}
	
	

	
	
	
	

	public static void buscarXML (File dirOrigen) {
		String s = null;
		String z = null;
		int contador = 1;
		try {

			Connection conn = null;
			String urlDatabase =  "jdbc:postgresql://192.168.2.39:5432/recepdteprd";
		    Class.forName("org.postgresql.Driver"); //cargar el driver manualmente
		    conn = DriverManager.getConnection(urlDatabase,  "azrecepprd", "azrecepprd");
			PreparedStatement pst = null;
			ResultSet rs = null;
			String query = "select xml_doc from dterecepprodos.dte_recep_doc_xml where id_recep_doc =613069";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			FileOutputStream fos = new FileOutputStream(dirOrigen+"\\Factura_"+1+".xml");
			while (rs.next()) {
				byte[] fileBytes = rs.getBytes(1);
			    fos.write(fileBytes);
				
				
				
//				byte[] datos = rs.getBytes("xml_doc");
//				System.out.println("bute"+datos);
//				FileWriter xml = null;
//				PrintWriter pw = null;
//				xml = new FileWriter(dirOrigen+"\\Factura_"+2+".xml");
//				z = new String(datos, "UTF-8");
//				System.out.println("string"+z);
//				pw = new PrintWriter(xml);
//				pw.println(z);
//				if (null != xml) {
//					xml.close();
//					System.out.println(contador+" documentos generados ["+1+".xml]");
//					contador ++;
//				}
				
				
//					Blob blob = rs.getBlob("DATOS");
//					int numero = rs.getInt("NUMERO");
//					byte[] bdata = blob.getBytes(1, (int) blob.length());
//					s = new String(bdata);
//					FileWriter xml = null;
//					PrintWriter pw = null;
//					xml = new FileWriter(dirOrigen+"\\Factura_"+numero+".xml");
//					//xml = new FileWriter("C:\\\\Users\\\\mcasteletti\\\\Desktop\\\\groupon\\\\prueba\\\\proceso\\\\Facturas\\\\Factura_"+numero+".xml");
//					pw = new PrintWriter(xml);
//					pw.println(s);
//					if (null != xml) {
//						xml.close();
//						System.out.println(contador+" documentos generados ["+1+".xml]");
//						contador ++;
//					}

				}
				
			


		}	catch(Exception e){
			e.printStackTrace();
		}	

	}

	public static String buscarXSL () {
		String s = null;
		try {
			String id = "1435391388E_69043";
			Connection conn = Conexion.getConnection("DTE_CO");
			PreparedStatement pst = null;
			ResultSet rs = null;
			String query = "select data from AZTPS.PLANTILLAS where id = '" + id +"'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Blob blob = rs.getBlob("DATA");
				byte[] bdata = blob.getBytes(1, (int) blob.length());
				s = new String(bdata);
				System.out.println(s);
				return s;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}




	


}