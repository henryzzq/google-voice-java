import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Demo {
	public static void main(String[] args) throws Exception
	{
		Path path = Paths.get("C:\\eclipse-cismon\\ws-cismon\\google-voice-java\\src\\out.flac");
	       byte[] data = Files.readAllBytes(path);
	       
	       String request = "https://www.google.com/"+
	                "speech-api/v1/recognize?"+
	                "xjerr=1&lang=en-US&maxresults=10";
	       URL url = new URL(request);
	       HttpURLConnection connection = (HttpURLConnection) url.openConnection();          
	       connection.setDoOutput(true);
	       connection.setDoInput(true);
	       connection.setInstanceFollowRedirects(false);
	       connection.setRequestMethod("POST");
	       connection.setRequestProperty("Content-Type", "audio/x-flac; rate=16000");
	       connection.setRequestProperty("User-Agent", "speech2text");
	       connection.setConnectTimeout(60000);
	       connection.setUseCaches (false);
	       
	       DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
	       wr.write(data);
	       wr.flush();
	       wr.close();
	       connection.disconnect();
	       
	       System.out.println("Done");
	       
	       BufferedReader in = new BufferedReader(
	               new InputStreamReader(
	               connection.getInputStream()));
	                String decodedString;
	                while ((decodedString = in.readLine()) != null) {
	                System.out.println(decodedString);
	                }
	}
}
