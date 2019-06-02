package com.example.quanlysinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter {
    ArrayList<SinhVien> arr;
    Context ct;
        public SinhVienAdapter(Context context, int resource){
             super(context, resource);
             this.ct = context;
             this.arr = new ArrayList<>(data.getDt().arrSV);
        }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
       View row = convertView;
       viewHodler v;
       if (row==null){
           LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           row = inflater.inflate(R.layout.item_sinh_vien,null);

       }
       v = new viewHodler(row);
       if (arr.size()>0){
           v.setView(arr.get(position));
       }
       return row;
    }
    class viewHodler{
            TextView txvTen,txvSDT,txvEMAIL,txvlop;
            ImageView imgGT;
            public viewHodler(View v){
                txvTen = (TextView)v.findViewById(R.id.txvTen);
                txvEMAIL = (TextView)v.findViewById(R.id.txvEMAIL);
                txvlop = (TextView)v.findViewById(R.id.txvlop);
                txvSDT = (TextView)v.findViewById(R.id.txvSDT);
                imgGT = (ImageView)v.findViewById(R.id.imgGT);
            }
            public void setView(SinhVien sv){
                txvTen.setText(sv.getTen());
                txvSDT.setText(sv.getSodt());
                txvlop.setText(sv.getLophoc());
                txvEMAIL.setText(sv.getEmail());

                if (sv.getGioitinh().equals("nu")){
                    imgGT.setImageResource(R.drawable.female);
                }
                else {
                    imgGT.setImageResource(R.drawable.male);
                }
            }
    }
}
