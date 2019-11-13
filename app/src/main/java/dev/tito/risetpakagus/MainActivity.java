package dev.tito.risetpakagus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static dev.tito.risetpakagus.DbHelper.COLUMN_DISKON;
import static dev.tito.risetpakagus.DbHelper.COLUMN_HARGA_BELI;
import static dev.tito.risetpakagus.DbHelper.COLUMN_HARGA_JUAL;
import static dev.tito.risetpakagus.DbHelper.COLUMN_KODE;
import static dev.tito.risetpakagus.DbHelper.COLUMN_NAMA;
import static dev.tito.risetpakagus.DbHelper.COLUMN_UNIT;

public class MainActivity extends AppCompatActivity {

    EditText edKode, edNama, edUnit, edhargaJual, edHargaBeli, edDiskon;
    Button save, update, delete, clear;
    ListView listBarang;

    List<Data> itemList = new ArrayList<>();
    iniAdapternya iniAdapternya;
    DbHelper SQLite = new DbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite = new DbHelper(getApplicationContext());

        edKode = findViewById(R.id.edKode);
        edNama = findViewById(R.id.ednama);
        edUnit = findViewById(R.id.edUnit);
        edhargaJual = findViewById(R.id.edhargaJual);
        edHargaBeli = findViewById(R.id.edHargaBeli);
        edDiskon = findViewById(R.id.edDiskon);

        save = findViewById(R.id.save);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.clear);

        listBarang = findViewById(R.id.listBarang);


        iniAdapternya = new iniAdapternya(MainActivity.this, itemList);
        listBarang.setAdapter(iniAdapternya);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edKode.setText(null);
                edNama.setText(null);
                edUnit.setText(null);
                edHargaBeli.setText(null);
                edhargaJual.setText(null);
                edDiskon.setText(null);

            }
        });



        listBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edKode.setText(itemList.get(position).getKode());
                edNama.setText(itemList.get(position).getNama());
                edUnit.setText(itemList.get(position).getUnit());
                edHargaBeli.setText(itemList.get(position).getHargaBeli());
                edhargaJual.setText(itemList.get(position).getHargaJual());
                edDiskon.setText(itemList.get(position).getDiskon());
            }
        });

        getAllData();
    }

    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++) {
            String kode = row.get(i).get(COLUMN_KODE);
            String nama = row.get(i).get(COLUMN_NAMA);
            String unit = row.get(i).get(COLUMN_UNIT);
            String hargaJual = row.get(i).get(COLUMN_HARGA_JUAL);
            String hargaBeli = row.get(i).get(COLUMN_HARGA_BELI);
            String diskon = row.get(i).get(COLUMN_DISKON);

            Data data = new Data();

            data.setKode(kode);
            data.setNama(nama);
            data.setUnit(unit);
            data.setHargaJual(hargaJual);
            data.setHargaBeli(hargaBeli);
            data.setDiskon(diskon);

            itemList.add(data);
        }

        iniAdapternya.notifyDataSetChanged();
    }

    private void save() {
        if (String.valueOf(edKode.getText()).equals("") || String.valueOf(edNama.getText()).equals("") || String.valueOf(edhargaJual.getText()).equals("") || String.valueOf(edHargaBeli.getText()).equals("") || String.valueOf(edDiskon.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "ISIEN KABEH COK ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(edKode.getText().toString().trim(), edNama.getText().toString().trim(), edUnit.getText().toString().trim(), edhargaJual.getText().toString().trim(), edHargaBeli.getText().toString().trim(), edDiskon.getText().toString().trim());

            itemList.clear();
            getAllData();
        }
    }

    private void delete() {
        if (String.valueOf(edKode.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "ISIEN DATANE", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.delete(edKode.getText().toString().trim());
            itemList.clear();
            getAllData();
        }
    }

    private void update() {
        if (String.valueOf(edKode.getText()).equals("") || String.valueOf(edNama.getText()).equals("") || String.valueOf(edhargaJual.getText()).equals("") || String.valueOf(edHargaBeli.getText()).equals("") || String.valueOf(edDiskon.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "ISIEN DATANE", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(edKode.getText().toString().trim(), edNama.getText().toString().trim(), edUnit.getText().toString().trim(), edhargaJual.getText().toString().trim(), edHargaBeli.getText().toString().trim(), edDiskon.getText().toString().trim());

            itemList.clear();
            getAllData();
        }
    }

}
