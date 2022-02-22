package david.cano.davidcanobdlibros;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Presentacion extends AppCompatActivity {
    Button boton;
    MediaPlayer mp=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);
        boton=findViewById(R.id.btSiguientePantalla);
        mp=MediaPlayer.create(this,R.raw.cantina);
        mp.start();
    }
    public void pantallaInicial(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        mp.stop();
    }

}
