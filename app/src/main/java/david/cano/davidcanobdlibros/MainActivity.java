package david.cano.davidcanobdlibros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edcodigo;
    EditText edtitulo;
    EditText edautor;
    Button btLimpiar;
    CrearBD crearBD=new CrearBD(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edcodigo=(EditText) findViewById(R.id.etCodigo);
        EditText edtitulo=(EditText) findViewById(R.id.etTitulo);
        EditText edautor=(EditText) findViewById(R.id.etAutor);
        SQLiteDatabase bd= crearBD.getWritableDatabase();
        /*bd.execSQL("INSERT INTO libros VALUES(1,'La Celestina', 'Fernando de Rojas');");
        bd.execSQL("INSERT INTO libros VALUES(2,'El Quijote', 'Miguel de Cervantes');");
        bd.execSQL("INSERT INTO libros VALUES(3,'El Lazarillo de Tormes','Anonimo');");
        bd.execSQL("INSERT INTO libros VALUES(4,'El Principe de la Niebla', 'Carlos Ruiz Zafón');");
        bd.close();*/

    }



    public void ConsultarLibro(View v)
    {
        String cod=edcodigo.getText().toString();
        SQLiteDatabase bd = crearBD.getReadableDatabase();
        Cursor contenido = bd.rawQuery("select * from libros where codigo='" +cod+ "'" , null);
        if (contenido.moveToNext()){
            edtitulo.setText(contenido.getString(1));
            edautor.setText(contenido.getString(2));  }
        contenido.close();
        bd.close();
    }

    public void insertarLibro(View v){
        SQLiteDatabase bd=crearBD.getWritableDatabase();
        if(edcodigo.getText().toString().equals("")|| edtitulo.getText().toString().equals("")||
        edautor.getText().toString().equals("")){
        verMensajeToast("Cajas vacías, debes introducir los datos");
        }else{
            String cod=edcodigo.getText().toString();
            String tit=edtitulo.getText().toString();
            String aut=edautor.getText().toString();
            try{
                bd.execSQL("INSERT INTO LIBROS VALUES("+cod + ",'" + tit + ",'" + aut +"'); ");
                verMensajeToast("Datos insertados");
            }catch(Exception sqlex){
                verMensajeToast(sqlex.getMessage());
            }
            limpiarCajas();
            crearBD.close();
        }
    }

    public void verMensajeToast(String mensaje){
        Context context=getApplicationContext();
        Toast toast= Toast.makeText(context,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    public void limpiarCajas(){
            edcodigo.setText("");
            edtitulo.setText("");
            edautor.setText("");
        }

        public void borrarLibro(){
            SQLiteDatabase bd=crearBD.getWritableDatabase();
            if(edcodigo.getText().toString().equals("")|| edtitulo.getText().toString().equals("")||
                    edautor.getText().toString().equals("")) {
                verMensajeToast("Cajas vacías, debes introducir los datos");
            }else{
                String cod=edcodigo.getText().toString();
                String tit=edtitulo.getText().toString();
                String aut=edautor.getText().toString();
                try{
                    bd.execSQL("DELETE INTO LIBROS VALUES("+cod + ",'" + tit + ",'" + aut +"'); ");
                    verMensajeToast("Datos borrados");
                }catch(Exception sqlex){
                    verMensajeToast(sqlex.getMessage());
                }
                limpiarCajas();
                crearBD.close();
            }
        }
    }

