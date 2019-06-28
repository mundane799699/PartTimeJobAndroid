package com.example.testfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shop extends Activity {
    private ListView list;
    private List<Map<String, Object>> data = null;
    String[] names = { "沙爹鱼串", "奶油雪糕", "健力宝", "糖人" };
    String[] urls = {
            "让你好吃到爆，小时候经常吃的零食，回忆起来也是人间极品！沙爹味与雨的完美结合！", "回忆小时候的味道，让你停不下来，让你好吃到爆，奶油的味道让回味无穷", "在八十年代的人，童年里的记忆深处都会有一罐健力宝。酸甜的味道，冲劲十足的气泡儿，可是风靡大江南北的“中国魔水”啊", "让你好吃到爆"
    };
    int[] icons = new int[] {
            R.drawable.seven, R.drawable.two, R.drawable.three, R.drawable.six
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        ArrayAdapter adapter = new ArrayAdapter(shop.this, android.R.layout.simple_list_item_1, names);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", names[i]);
            map.put("url", urls[i]);
            map.put("icon", icons[i]);
            lists.add(map);
        }
        SimpleAdapter adapter2 = new SimpleAdapter(shop.this, lists, R.layout.bj, new String[] { "icon", "name", "url" }, new int[] { R.id.a1, R.id.t1, R.id.t2 });
        list.setAdapter(adapter2);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Toast.makeText(shop.this, "您长按了" + names[arg2], Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
    
    public void a1(View v) {
        Intent intent = new Intent(shop.this, details2.class);
        startActivity(intent);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}

