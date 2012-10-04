package androidcode.in.th.sqlite;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//ตัวแปรของ View
	private EditText txtName,txtSurname,txtAge;
	private Button btnAdd;
	private ListView listMember;
	
    //list ในการเก็บข้อมูลของ MemberData
    private ArrayList<MemberData> listData = new ArrayList<MemberData>();
	
	//ตัวจัดการฐานข้อมูล
	private DatabaseHelper dbHelper;
	private SQLiteDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //เชื่อม View
        txtName = (EditText)findViewById(R.id.txtName);
        txtSurname = (EditText)findViewById(R.id.txtSurname);
        txtAge = (EditText)findViewById(R.id.txtAge);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        listMember = (ListView)findViewById(R.id.listMember);
        
        //สร้าง Event ให้ปุ่มเพิ่มข้อมูล
        btnAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addMember();
			}
		});

        //สร้างตัวจัดการฐานข้อมูล
        dbHelper = new DatabaseHelper(this);
        
        //นำตัวจัดการฐานข้อมูลมาใช้งาน
        database = dbHelper.getWritableDatabase();
        
        //แสดงรายการสมาชิก
        showList();
    }
    
    //Method แก้ไขข้อมูลใน SQLite
    public void editMember(int id,String name,String surname,int age){
    	//เตรียมค่าต่างๆ เพื่อทำการแก้ไข
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", name);
		values.put("surname", surname);
		values.put("age", age);
		
		//ให้ Database ทำการแก้ไขข้อมูลที่ id ที่กำหนด
		database.update("member", values, "id = ?", new String[] { ""+id });
		
		//แสดงข้อมูลล่าสุด
		showList();
    }

    //Method ลบข้อมูลใน SQLite
    public void deleteMember(int id){
    	database.delete("member", "id = " + id, null);
		Toast.makeText(this, "Delete Data Id " + id + " Complete", Toast.LENGTH_SHORT).show();
		
		showList();
    }

    //Method ดึงข้อมูลจาก SQLite
	private void getMember() {
		//ทำการ Query ข้อมูลจากตาราง member ใส่ใน Cursor
		Cursor mCursor = database.query(true, "member", new String[] {
				"id", "name", "surname", "age" }, null,
				null, null, null, null, null);
        
		if (mCursor != null) {
			mCursor.moveToFirst();

			listData.clear();
			//ถ้ามีข้อมูลจะทำการเก็บข้อมูลใส่ List เพื่อนำไปแสดง
			if(mCursor.getCount() > 0){
				do {
					int id = mCursor.getInt(mCursor.getColumnIndex("id"));
					String name = mCursor.getString(mCursor.getColumnIndex("name"));
					String surname = mCursor.getString(mCursor.getColumnIndex("surname"));
					int age = mCursor.getInt(mCursor.getColumnIndex("age"));
		            
					listData.add(new MemberData(id, name, surname, age));
		        }while (mCursor.moveToNext());
			}
		}
	}

    //Method เพิ่มข้อมูลใน SQLite
	private void addMember() {
		// TODO Auto-generated method stub
		if(txtName.length() > 0 && txtSurname.length() > 0 && txtAge.length() > 0){
			//เตรียมข้อมูลสำหรับใส่ลงไปในตาราง
			ContentValues values = new ContentValues();
			values.put("name", txtName.getText().toString());
			values.put("surname", txtSurname.getText().toString());
			values.put("age", txtAge.getText().toString());
			
			//ทำการเพิ่มข้อมูลลงไปในตาราง member
			database.insert("member", null, values);
			
			Toast.makeText(this, "Add Data Complete", Toast.LENGTH_SHORT).show();
			
			//ล้างข้อมูล From
			txtName.setText("");
			txtSurname.setText("");
			txtAge.setText("");
			
			showList();
		}else{
			Toast.makeText(this, "Please Input Data", Toast.LENGTH_SHORT).show();
		}
	}

	private void showList() {
        //ดึงข้อมูลสมาชิกจาก SQLite Database
        getMember();
        
        //แสดงสมาชิกใน ListView
        listMember.setAdapter(new AdapterListViewData(this,listData));
	}
	
	public void showEdit(int id,String name,String surname,int age){
		Intent i = new Intent(this,EditActivity.class);
		
		//ทำการส่งค่าต่างๆ ให้ EditActivity ไปทำการแก้ไข
		i.putExtra("keyId", id);
		i.putExtra("keyName", name);
		i.putExtra("keySurname", surname);
		i.putExtra("keyAge", age);
		//***** ในการส่งค่าและรับค่า ส่งเป็นตัวแปรชนิดไหน ต้องรับเป็นตัวแปรชนิดนั้น *****//
		
		//ทำการเรียก EditActivity โดยให้ Request Code เป็น 1
		startActivityForResult(i, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//ถ้ากลับมาหน้า MainActivity แล้วผลลัพธ์การทำงานสมบูรณ์
		if(requestCode == 1 && resultCode == RESULT_OK){
			
			//เก็บค่าที่ส่งกลับมาใส่ตัวแปร
			int id = intent.getExtras().getInt("keyId");
			String name = intent.getExtras().getString("keyName");
			String surname = intent.getExtras().getString("keySurname");
			int age = intent.getExtras().getInt("keyAge");
			//***** ในการส่งค่าและรับค่า ส่งเป็นตัวแปรชนิดไหน ต้องรับเป็นตัวแปรชนิดนั้น *****//
			
			//ให้แก้ไขข้อมูลสมาชิก
			editMember(id, name, surname, age);
		}
	}
}
