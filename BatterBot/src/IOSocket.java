import java.io.*;
import java.net.*;

public class IOSocket implements IOInterface
{
	private BufferedReader myInput;
	private PrintStream myOutput;
	private Socket skt;
	
	public void setup()
	{
		String host = "206.87.3.136";
		int port = 9999;
		
		try
		{
			skt = new Socket(host, port);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			myInput = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			myOutput = new PrintStream(skt.getOutputStream());
		} 
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
}

	@Override
	public void print(String response) 
	{
		myOutput.flush();
		System.out.println(response);
		myOutput.print(response + "\n");
	}

	@Override
	public String read() 
	{
		String string = fromServer();
		System.out.println(string);
		return string;
	}
	
	public String fromServer()
	{
		String buf = null;

		//		try 
//		{
//			buf = myInput.readLine();
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
		
		try 
		{
			while(!myInput.ready());

				//System.out.println("waiting");
				try 
				{
					buf = myInput.readLine();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					System.exit(0);
				}

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buf;
	}
	
	public void close()
	{
		try 
		{
			skt.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

