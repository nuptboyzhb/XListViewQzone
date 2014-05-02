package edu.njupt.zhb.xlistviewtest;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	@ViewById
	Button btnList1;
	
	@ViewById
	Button btnList2;
	
	@ViewById
	Button btnList3;
	/**
	 * 绑定点击事件
	 */
	@Click
	void btnList1(){
		Intent intent=new Intent(this,ActivityList1_.class);
		startActivity(intent);
	}
	
	/**
	 * 绑定点击事件
	 */
	@Click
	void btnList2(){
		Intent intent=new Intent(this,ActivityList2_.class);
		startActivity(intent);
	}
	
	/**
	 * 绑定点击事件
	 */
	@Click
	void btnList3(){
		/*Intent intent=new Intent(this,SubActivity_.class);
		intent.putExtra("input_value", myEditText.getEditableText().toString());
		startActivity(intent);*/
	}
}
