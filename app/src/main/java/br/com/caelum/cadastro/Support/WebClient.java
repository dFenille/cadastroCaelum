package br.com.caelum.cadastro.Support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/**
 * Created by android7087 on 18/11/17.
 */

public class WebClient {
    public URL url;
    public HttpURLConnection conn;
    public String jsonDeResposta;
    public OutputStream outputStream;
    public InputStream inputStream;


    public String post(String json){

        try {
            url = new URL("https://www.caelum.com.br/mobile");
            conn = (HttpURLConnection) this.url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-type","application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            outputStream = conn.getOutputStream();


            PrintStream saida = new PrintStream(outputStream);
            saida.print(json);
            conn.connect();

            inputStream = conn.getInputStream();
            jsonDeResposta= new Scanner(inputStream).next();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {

                if(inputStream != null)
                    inputStream.close();

                if(outputStream != null)
                    outputStream.close();

                if(conn != null)
                    conn.disconnect();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonDeResposta;
    }
}
