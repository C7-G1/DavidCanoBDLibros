package david.cano.davidcanobdlibros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Presentacion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentacion);
    }
    public void pantallaInicial(View v){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
