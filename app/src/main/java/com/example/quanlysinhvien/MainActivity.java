package com.example.quanlysinhvien;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

                ListView lsvSinhVien;
                Dialog dialog;
                viewDialog viewDialog;
                SinhVienAdapter adapter;
                TuongTacDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsvSinhVien = (ListView)findViewById(R.id.lsvSinhVien);
        database = new TuongTacDatabase(this);
        getDATA();
        adapter = new SinhVienAdapter(this  ,0);
        lsvSinhVien.setAdapter(adapter);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activitytsx);
        viewDialog = new viewDialog(dialog);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.idTrangChu:
                break;
            case R.id.idQuanLy:
                break;
            case R.id.idTimKiem:
            break;
            case R.id.idDK:
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void getDATA(){
        database.open();
        data.getDt().arrSV = new ArrayList<>(database.getAlldata());
        database.close();
    }
    class viewDialog{
        EditText edtTen,edtSDT,edtEmail;
        Button btnNgaySinh,btnThem;
        Spinner splLop;
        String lop,gt="nam";

        RadioGroup GT;

        public viewDialog(Dialog v ){
            edtTen = (EditText)v.findViewById(R.id.edtTen);
            edtSDT = (EditText)v.findViewById(R.id.edtSDT);
            edtEmail = (EditText)v.findViewById(R.id.edtEmail);

            btnNgaySinh = (Button)v.findViewById(R.id.btnChonNgay);
            btnThem = (Button)v.findViewById(R.id.btnThem);

            splLop = (Spinner)v.findViewById(R.id.splLop);
            GT=(RadioGroup)v.findViewById(R.id.RGGT);
            setRS();
            setBtnThem();
        }
        private void setBtnThem(){
                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.getDt().arrSV.add(getSV());
                        database.open();
                        database.themSV(getSV());
                        database.close();
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
        }

        private void setRS(){
            splLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long i1) {
                        lop = getResources().getStringArray(R.array.lop)[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    lop = getResources().getStringArray(R.array.lop)[0];
                }
            });
            if (GT.getCheckedRadioButtonId()==R.id.nam){
                gt ="nam";
            }
            else {
                gt ="nu";
            }
        }
        private SinhVien getSV(){
            SinhVien sv = new SinhVien();
            sv.setTen(edtTen.getText().toString());
            sv.setEmail(edtEmail.getText().toString());
            sv.setSodt(edtSDT.getText().toString());
            sv.setLophoc(lop);
            sv.setGioitinh(gt);
            return sv;
        }
    }
}
