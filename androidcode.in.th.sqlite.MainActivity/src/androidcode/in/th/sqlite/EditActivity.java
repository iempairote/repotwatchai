package androidcode.in.th.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity  extends Activity {
	
	//ตัวแปรของ View
	private EditText txtName,txtSurname,txtAge;
	private Button btnEdit;
	
	//ตัวแปรไว้เก็บว่าข้อมูลที่จะแก้ไข id เป็นอะไร
	private int id;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        
        //เชื่อม View
        txtName = (EditText)findViewById(R.id.txtName);
        txtSurname = (EditText)findViewById(R.id.txtSurname);
        txtAge = (EditText)findViewById(R.id.txtAge);
        btnEdit = (Button)findViewById(R.id.btnEdit);

        //รับค่าจาก MainActivity มาแสดงข้อมูลเพื่อทำการแก้ไข
        this.id = getIntent().getExtras().getInt("keyId");
        txtName.setText(getIntent().getExtras().getString("keyName"));
        txtSurname.setText(getIntent().getExtras().getString("keySurname"));
        txtAge.setText(""+getIntent().getExtras().getInt("keyAge"));
		//***** ในการส่งค่าและรับค่า ส่งเป็นตัวแปรชนิดไหน ต้องรับเป็นตัวแปรชนิดนั้น *****//
		
        //สร้าง Event ให้ปุ่มแก้ไขข้อมูล
        btnEdit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		    	Intent i = new Intent();
		    	
		    	//ตั้งค่าผลลัพธ์การทำงานว่า RESULT_OK
		    	setResult(RESULT_OK,i);
		    	
		    	//ส่งข้อมูลกลับไปให้ MainActivity ทำการแก้ไขข้อมูลให้
		    	i.putExtra("keyId", id);
		    	i.putExtra("keyName", txtName.getText().toString());
		    	i.putExtra("keySurname", txtSurname.getText().toString());
		    	i.putExtra("keyAge", Integer.parseInt(txtAge.getText().toString()));
				//***** ในการส่งค่าและรับค่า ส่งเป็นตัวแปรชนิดไหน ต้องรับเป็นตัวแปรชนิดนั้น *****//
		    	
		    	finish();
			}
		});
    }
}
