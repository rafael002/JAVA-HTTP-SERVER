import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public static void main(String[] args)
	{
		try {
			
			ServerSocket server = new ServerSocket( 12345 );
			
			while( true ){
					
					Socket client = server.accept();
					
					ObjectInputStream ios = new ObjectInputStream( client.getInputStream() );
					
					ObjectOutputStream oos = new ObjectOutputStream( client.getOutputStream() );
					
					System.out.println( (String) ios.readObject());
					
					oos.writeObject("Resposta do Servidor");
					
					oos.close();
					ios.close();
					client.close();
					server.close();
				
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
