package edu.njupt.zhb.xlistviewtest;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
@EActivity(R.layout.activity_list_2)
public class ActivityList2 extends Activity implements IXListViewListener{
	
	XListView listView;
	
	private ChatBaseAdapter chatBaseAdapter;
	@AfterViews
	void afterViewInitList(){
		listView = (XListView)findViewById(R.id.listView);
		chatBaseAdapter = new ChatBaseAdapter(this);
		listView.setAdapter(chatBaseAdapter);
		listView.setXListViewListener(this);
		listView.setPullLoadEnable(false);//禁用加载更多
		for(int i=0;i<10;i++){
			Msg msg = new Msg();
			if(i%2==0){
				msg.setSelf(false);
			}else {
				msg.setSelf(true);			
			}
			msg.setContent("abc"+i);
			chatBaseAdapter.addMsg(msg);
		}
		chatBaseAdapter.notifyDataSetChanged();
		
	}
	@ItemClick
	public void listView(int position) {
		// TODO Auto-generated method stub
		Log.d("ItemClick", "pos="+position);
		position = position-1;
		String string = "clicked item"+position+"content="+chatBaseAdapter.getMsg(position).getContent();
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onRefresh() {
		refresh();
	}
	@UiThread
	void refresh(){
		/**
		 * 模拟从数据库中读取更多聊天记录
		 */
		List<Msg> list = new ArrayList<Msg>();
		for(int i=0;i<10;i++){
			Msg msg = new Msg();
			if(i%2==0){
				msg.setSelf(false);
			}else {
				msg.setSelf(true);			
			}
			msg.setContent("def"+i);
			list.add(msg);
		}
		chatBaseAdapter.addAll(list, true);
		chatBaseAdapter.notifyDataSetChanged();
		listView.setSelection(list.size());//将光标移动到加载的交界处
		onLoad();
	}
	
	@Override
	public void onLoadMore() {
		
	}
	
	
	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
}
