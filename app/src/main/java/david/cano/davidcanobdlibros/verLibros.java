package david.cano.davidcanobdlibros;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class verLibros extends AppCompatActivity {
    CrearBD crearBD;
    ListView listaLibros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);
        crearBD = new CrearBD(this);

        listaLibros = findViewById(R.id.lvListaLibros);
        listarLibros();

    }

    public void listarLibros() {
        List<String> items = new ArrayList<String>();
        SQLiteDatabase bd = crearBD.getReadableDatabase();
        Cursor contenido = bd.rawQuery("select * from libros", null);
        int i = 0;
        String cad = "";
        //Volcamos el contenido del cursor en el arrayList
        while (contenido.moveToNext() && i < contenido.getCount()) {
            //concatena los datos de cada libro en un String cad
            cad = "" + contenido.getString(0) + " " + contenido.getString(1) + " " + contenido.getString(2) + "\n";
            items.add(cad);
            i++;
        }
        //Define un ArrayAdapter y volcamos en el contenido del ArrayList items
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        //llenamos el ListView listaLibros
        listaLibros.setAdapter(adapter);
        contenido.close();
        bd.close();
    }


}