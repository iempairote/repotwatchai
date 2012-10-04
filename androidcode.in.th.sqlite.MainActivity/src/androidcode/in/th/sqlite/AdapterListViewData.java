package androidcode.in.th.sqlite;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

public class AdapterListViewData extends BaseAdapter{
 
    private LayoutInflater mInflater;
    private Context context;
    private MainActivity control;
    
    //list ในการเก็บข้อมูลของ MemberData
    private ArrayList<MemberData> listData = new ArrayList<MemberData>();
 
    public AdapterListViewData(MainActivity control,ArrayList<MemberData> listData) {
    	this.control = control;
        this.context = control.getBaseContext();
        this.mInflater = LayoutInflater.from(context);
        this.listData = listData;
    }
 
    public int getCount() {
    	//ส่งขนาดของ List ที่เก็บข้อมุลอยู่
        return listData.size(); 
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
 
    public View getView(final int position, View convertView, ViewGroup parent) {
        HolderListAdapter holderListAdapter; //เก็บส่วนประกอบของ List แต่ละอัน
 
        if(convertView == null)
        {
            //ใช้ Layout ของ List เราเราสร้างขึ้นเอง (adapter_listview.xml)
            convertView = mInflater.inflate(R.layout.adapter_listview, null);
 
             //สร้างตัวเก็บส่วนประกอบของ List แต่ละอัน
            holderListAdapter = new HolderListAdapter();
 
            //เชื่อมส่วนประกอบต่างๆ ของ List เข้ากับ View
            holderListAdapter.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holderListAdapter.txtSurname = (TextView) convertView.findViewById(R.id.txtSurname);
            holderListAdapter.txtAge = (TextView) convertView.findViewById(R.id.txtAge);
            holderListAdapter.btnEdit = (Button) convertView.findViewById(R.id.btnEdit);
            holderListAdapter.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);
 
            convertView.setTag(holderListAdapter);
        }else{
            holderListAdapter = (HolderListAdapter) convertView.getTag();
        }
 
        //ดึงข้อมูลจาก listData มาแสดงทีละ position
        final int id = listData.get(position).getId();
        final String name = listData.get(position).getName();
        final String surname = listData.get(position).getSurname();
        final int age = listData.get(position).getAge();
        
        holderListAdapter.txtName.setText("name : "+name);
        holderListAdapter.txtSurname.setText("surname : "+surname);
        holderListAdapter.txtAge.setText("age : "+age);
        
      //สร้าง Event ให้ปุ่ม Delete
        holderListAdapter.btnDelete.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				control.deleteMember(id);
			}
		});

        //สร้าง Event ให้ปุ่ม Edit
        holderListAdapter.btnEdit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				control.showEdit(id, name, surname, age);
			}
		});
        
        return convertView;
    }
}