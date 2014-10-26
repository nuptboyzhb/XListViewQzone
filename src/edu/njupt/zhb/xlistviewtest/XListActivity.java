package edu.njupt.zhb.xlistviewtest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
@Fullscreen
@EActivity(R.layout.activity_xlist)
public class XListActivity extends Activity implements IXListViewListener {

	XListView listView;
	// ListView的设配器
	private XBaseAdapter xBaseAdapter;

	@ViewById
	Button btnSendComment;
	
	@ViewById
	EditText etComment;
	
	@AfterViews
	void afterViewInitList() {
		listView = (XListView) findViewById(R.id.listView);
		xBaseAdapter = new XBaseAdapter(this, R.layout.listview_item,this);
		listView.setAdapter(xBaseAdapter);
		listView.setXListViewListener(this);// 添加XListView的上拉和下拉刷新监听器
		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(true);
		initXListData();
	}

	void initXListData() {
		Model model = new Model();
		model.setImgHead(R.drawable.head);
		model.setName("张三");
		model.setContent("五一过的太无聊了。还是好好写代码吧。给大家推荐一个群,Android开发联盟QQ群：272209595");
		model.setType(FinalVar.MSG_TEXT);
		model.setAgree(false);
		model.setPhonemodel("Nexus 5");
		model.setAddress("南京市 玄武湖公园");
		model.setDate(new SimpleDateFormat().format(new Date()).toString());
		xBaseAdapter.addModel(model);
		Model model2 = new Model();
		model2.setImgHead(R.drawable.qq_contact_list_friend_entry_icon);
		model2.setName("李四");
		model2.setContent("哈哈，五一很欢乐。。。有图有真相，不信你看，嘿嘿");
		model2.setType(FinalVar.MSG_IMAGE);
		model2.setPhonemodel("iPhone 5s");
		model2.setAddress("北京市海淀区");
		model2.setDate(new SimpleDateFormat().format(new Date()).toString());
		List<String> imageUrls = new ArrayList<String>();
		imageUrls
				.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
		imageUrls
				.add("http://www.ineiyi.com/uploads/allimg/1312/77-131213100200.jpg");
		model2.setImageUrls(imageUrls);
		model.setDate(new SimpleDateFormat().format(new Date()).toString());
		xBaseAdapter.addModel(model2);
		xBaseAdapter.notifyDataSetChanged();
	}

	@ItemClick(R.id.listView)
	public void onItemClick(int position) {
		// TODO Auto-generated method stub
		Log.d("ItemClick", "pos=" + position);
		position = position - 1;
		
		if (null != xBaseAdapter.getModel(position)) {
			Toast.makeText(this, "click Item...", Toast.LENGTH_SHORT).show();
		}
	}

	@Click(R.id.btnSendComment)
	void btnSendComment(){
		String comment = etComment.getEditableText().toString();
		Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onRefresh() {
		Log.d("xlistview", "onrefresh");
		refreshListViewInBackground();
	}

	@UiThread
	void refreshListViewInBackground() {// 模拟刷新数据
		Model model = new Model();
		model.setImgHead(R.drawable.head);
		model.setName("老男孩");
		model.setContent("为了梦想而努力。。。");
		model.setType(FinalVar.MSG_TEXT);
		model.setAgree(false);
		model.setPhonemodel("Nexus 5");
		model.setAddress("南京市 新模范马路");
		model.setDate(new SimpleDateFormat().format(new Date()).toString());
		xBaseAdapter.addModel(model,true);
		xBaseAdapter.notifyDataSetChanged();
		onLoad();
	}

	@Override
	public void onLoadMore() {
		Log.d("ItemClick", "onloadmore");
		loadMoreInBackground();
	}

	@UiThread
	void loadMoreInBackground() {
		Model model = new Model();
		model.setImgHead(R.drawable.head);
		model.setName("高富帅");
		model.setContent("无聊中...且行且珍惜");
		model.setType(FinalVar.MSG_TEXT);
		model.setAgree(false);
		model.setPhonemodel("Nexus 5");
		model.setAddress("南京市 高铁南站");
		model.setDate(new SimpleDateFormat().format(new Date()).toString());
		xBaseAdapter.addModel(model);
		xBaseAdapter.notifyDataSetChanged();
		listView.setSelection(xBaseAdapter.getCount() - 1);// 将光标移动到加载的交界处
		onLoad();
	}

	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}
}
