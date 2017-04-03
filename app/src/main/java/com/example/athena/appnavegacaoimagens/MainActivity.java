package com.example.athena.appnavegacaoimagens;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends Activity {
    ImageView imageShow;
    List<URL> Imagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageShow = (ImageView) findViewById(R.id.imageShow);
    }

    public void anterior(View v){
        Toast.makeText(MainActivity.this, "Anterior", Toast.LENGTH_SHORT).show();
    }

    public void proximo(View v){
        Toast.makeText(MainActivity.this, "Prooooximaa", Toast.LENGTH_SHORT).show();
    }

    public void loadImage(View v){
        DownloadAsyncImage process = new DownloadAsyncImage();
        try {
            Imagens = null;
            Imagens.add(new URL("http://southparkoculus.toolofnadrive.com/assets/imgs/global/10.jpg"));
            Imagens.add(new URL("http://esq.h-cdn.co/assets/16/45/980x490/landscape-1478792565-election-stan.png"));
            Imagens.add(new URL("http://www.southpark.com.br/wp-content/themes/simplo/images/south-park-header.jpg"));
            Imagens.add(new URL("https://i.ytimg.com/vi/ZrU_tt4R3xY/maxresdefault.jpg"));
            Imagens.add(new URL("http://www.indiewire.com/wp-content/uploads/2016/04/south-park-season-19.jpg"));
            process.execute(Imagens);
        } catch (MalformedURLException e) {
            Toast.makeText(getApplicationContext(),"URL inválida",Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadAsyncImage extends AsyncTask<List<URL>, Integer, List<Bitmap>> {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(MainActivity.this,"Carregando Imagens","Por favor aguarde...");
        }
        @Override
        protected List<Bitmap> doInBackground(List<URL>... params) {
            List<Bitmap> myBitmaps = null;

            List<URL> urls= params[0];
            try{
                myBitmaps = Util.dowloadImageList(urls);
            }catch (IOException e){
                Toast.makeText(MainActivity.this, "Erro ao carregar a imagem", Toast.LENGTH_SHORT).show();
                return null;
            }

            return myBitmaps;
        }
        @Override
        protected void onPostExecute(List<Bitmap> bitmaps){
            for (Bitmap bitmap: bitmaps) {
                if(bitmap != null){
                    imageShow.setImageBitmap(bitmap);
                }
                progressDialog.dismiss();
            }
            }

    }
}
