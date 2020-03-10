import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DescargaXMLEmision {

	
	
	
	
	
	
	
	
	public static void buscarXML (File dirOrigen, String xsl) {
		String s = null;
		int contador = 1;
		try {
			//	for(int id = 1344103; id <= 1530542 ; id++) {
			for(int id = 1344103; id <= 1530542; id++) {
				
				//Thread.sleep(500);
				//String id = "141C7A7B178_21170";
				Connection conn = Conexion.getConnection("DTE_CO");
				PreparedStatement pst = null;
				ResultSet rs = null;
				String query = "select dd.DATOS,d.NUMERO from DTE.DOCUMENTOS d , DTE.DOCUMENTOS_DATOS dd where d.id = dd.ID and d.ID ="+ id +" and d.TIPO_DOCUMENTO = 'FACTURA' ";
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				while (rs.next()) {
					Blob blob = rs.getBlob("DATOS");
					int numero = rs.getInt("NUMERO");
					byte[] bdata = blob.getBytes(1, (int) blob.length());
					s = new String(bdata);
					FileWriter xml = null;
					PrintWriter pw = null;
					xml = new FileWriter(dirOrigen+"\\Factura_"+numero+".xml");
					//xml = new FileWriter("C:\\\\Users\\\\mcasteletti\\\\Desktop\\\\groupon\\\\prueba\\\\proceso\\\\Facturas\\\\Factura_"+numero+".xml");
					pw = new PrintWriter(xml);
					pw.println(s);
					if (null != xml) {
						xml.close();
						System.out.println(contador+" documentos generados ["+id+".xml]");
						contador ++;
					}

				}
				
			}


		}	catch(Exception e){
			e.printStackTrace();
		}	

	}

	
	
	
	
	
	
	
}
