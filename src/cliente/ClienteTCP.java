package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TODO: Complementa esta clase para que genere la conexi�n TCP con el servidor
 * para enviar un boleto, recibir la respuesta y finalizar la sesion
 */
public class ClienteTCP {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Constructor
	 */
	public ClienteTCP(String ip, int puerto) {
		try {
			socket = new Socket(ip, puerto);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param combinacion que se desea enviar
	 * @return respuesta del servidor con la respuesta del boleto
	 */
	public String comprobarBoleto(int[] combinacion) {
		try {
			StringBuilder combinacionStr = new StringBuilder();
			for (int num : combinacion) {
				combinacionStr.append(num).append(" ");
			}
			out.println(combinacionStr.toString().trim());
			return in.readLine();
		} catch (IOException e) {
			return "Error al enviar o recibir datos";
		}
	}

	/**
	 * Sirve para finalizar la la conexi�n de Cliente y Servidor
	 */
	public void finSesion() {
		try {
			out.print("FIN");
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
