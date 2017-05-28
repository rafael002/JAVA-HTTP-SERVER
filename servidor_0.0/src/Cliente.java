import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cliente {

	public static void main(String[] args) {
		
		try {
			Socket client = new Socket("localhost", 12345 );
			
			ObjectInputStream ios = new ObjectInputStream( client.getInputStream() );
			
			ObjectOutputStream oos = new ObjectOutputStream( client.getOutputStream() );
			
			
			oos.writeObject("Enviado pelo Cliente");
			
			System.out.println((String)ios.readObject());
			
			oos.close();
			ios.close();
			client.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
