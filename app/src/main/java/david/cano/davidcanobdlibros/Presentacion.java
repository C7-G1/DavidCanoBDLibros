package david.cano.davidcanobdlibros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Presentacion extends AppCompatActivity {
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);
        boton=findViewById(R.id.btSiguientePantalla);
    }
    public void pantallaInicial(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
