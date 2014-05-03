package edu.njupt.zhb.xlistviewtest;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
@EActivity(R.layout.activity_list_1)
public class ActivityList1 extends Activity implements IXListViewListener{
	
	XListView listView;
	//ListView的设配器
	private BaseAdapter1 baseAdapter1;
	@AfterViews
	void afterViewInitList(){
		listView = (XListView)findViewById(R.id.listView);
		baseAdapter1 = new BaseAdapter1(this,R.layout.listview1);
		listView.setAdapter(baseAdapter1);
		listView.setXListViewListener(this);//添加XListView的上拉和下拉刷新监听器
		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(true);
		for(int i=0;i<10;i++){
			Model model = new Model();
			model.setImgHead(R.drawable.ic_launcher);
			model.setName("Name"+i);
			model.setContent("五一过的太无聊了，，，，没意思。。。");
			model.setType(1);
			model.setPhonemodel("Nexus 5");
			model.setDate(new SimpleDateFormat().format(new Date()).toString());
			baseAdapter1.addModel(model);
		}
		baseAdapter1.notifyDataSetChanged();
		
	}
	
	@ItemClick(R.id.listView)
	public void onItemClick(int position) {
		// TODO Auto-generated method stub
		Log.d("ItemClick", "pos="+position);
		position = position - 1;
		if(null != baseAdapter1.getModel(position)){
			//String string = "clicked item"+position+"content="+baseAdapter1.getModel(position).getTelephone();
			//Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onRefresh() {
		Log.d("xlistview", "onrefresh");
		refreshListViewInBackground();
	}
	@UiThread
	void refreshListViewInBackground(){//模拟刷新数据
		baseAdapter1.clear();
		int startIndex = 10;
		for(int i=startIndex;i<10+startIndex;i++){
			Model model = new Model();
//			model.setImgHead(R.drawable.ic_launcher);
//			model.setName("Name"+i);
//			model.setTelephone("手机  181111111"+i);
//			model.setDate(new SimpleDateFormat().format(new Date()).toString());
			baseAdapter1.addModel(model);
		}
		baseAdapter1.notifyDataSetChanged();
		
		onLoad();
	}
	
	
	@Override
	public void onLoadMore() {
		Log.d("ItemClick", "onloadmore");
		loadMoreInBackground();
	}
	@UiThread
	void loadMoreInBackground(){
		int startIndex = 10;
		for(int i=startIndex;i<10+startIndex;i++){
			Model model = new Model();
//			model.setImgHead(R.drawable.ic_launcher);
//			model.setName("Name"+i);
//			model.setTelephone("手机  151111111"+i);
//			model.setDate(new SimpleDateFormat().format(new Date()).toString());
			baseAdapter1.addModel(model);
		}
		baseAdapter1.notifyDataSetChanged();
		listView.setSelection(baseAdapter1.getCount()-1);//将光标移动到加载的交界处
		onLoad();
	}
	
	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
}
