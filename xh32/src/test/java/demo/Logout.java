package demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Logout {
	
	public static void main(String[] args){
		boolean rtn=sendMessageToEndPoint("http://localhost:8080/bpm/logout.jsp","hello");
		System.out.println("logout success");
	}

	public static boolean sendMessageToEndPoint(final String url, final String message) {      
        HttpURLConnection connection = null;
        BufferedReader in = null;
        try {
        
            final URL logoutUrl = new URL(url);
            final String output = "logoutRequest=" + URLEncoder.encode(message, "UTF-8");

            connection = (HttpURLConnection) logoutUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            connection.setRequestProperty("Content-Length", ""
                + Integer.toString(output.getBytes().length));
            connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
            final DataOutputStream printout = new DataOutputStream(connection
                .getOutputStream());
            printout.writeBytes(output);
            printout.flush();
            printout.close();

            in = new BufferedReader(new InputStreamReader(connection
                .getInputStream()));

            while (in.readLine() != null) {
                // nothing to do
            }
           
          
            return true;
        } catch (final Exception e) {
           
            return false;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (final IOException e) {
                    // can't do anything
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
