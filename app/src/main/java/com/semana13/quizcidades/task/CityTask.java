package com.semana13.quizcidades.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CityTask extends AsyncTask<String,Void,Bitmap> {

    ImageView imagem;

    public CityTask(ImageView img){
        this.imagem = img;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String stringURL = strings[0];
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = null;
        Bitmap bmp = null;

        try{
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            bmp = BitmapFactory.decodeStream(inputStream);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
        imagem.setImageBitmap(bm);
    }
}
