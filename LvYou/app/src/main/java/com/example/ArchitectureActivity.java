package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.ArchitectureAdapter;
import com.example.bean.Architecture;
import com.example.dao.ArchitectureDAO;
import com.example.lvyou.R;
import java.util.ArrayList;
import java.util.List;

public class ArchitectureActivity extends Activity implements OnItemClickListener{
    
    private ListView lv_architecture;
    private ArchitectureAdapter mAdapter;
    private List<Architecture> mList = new ArrayList<Architecture>();
    private ArchitectureDAO mArchitectureDAO;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architecture);
        
        setTitle("����");
        lv_architecture = (ListView) findViewById(R.id.lv_architecture);
        mAdapter = new ArchitectureAdapter(this, mList);
        lv_architecture.setAdapter(mAdapter);
        lv_architecture.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mArchitectureDAO = new ArchitectureDAO(this);
        List<Architecture> list = mArchitectureDAO.queryAllArchitecture();
        if (list == null || list.isEmpty()) {
            initDaoData();
        }
        list = mArchitectureDAO.queryAllArchitecture();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        Architecture arc1 = new Architecture();
        arc1.drawableName = "jianzhu1";
        arc1.name = "�Ϸ�";
        arc1.describe =
                "�Ͼ��Ϸ���á� ������ߡ��й����ġ���½�ڶ���¥�Ϸ����18�������Ͼ�������ɵ�����ʽ�����Ǽ��Ϻ�����������ĺ��й���½�����ĵڶ��߶ȡ����ˣ�����������������������������ʮ���¥���С� �Ϸ�������Ϻ��̵ؼ���Ͷ�ʿ�����������������ƹ�˾SOM������������չ���һ����׼��ơ�λ���Ͼ����ĳ�������Ϊ������ضΡ��Ĺ�¥�㳡���ܽ������30����ƽ���ף��߶�450�ף��Ǽ������Ǽ��޼ʾƵꡢ�׼��칫¥���ߵ��̳���ҵ̬Ϊһ��ĳ��߲���ͳ����ۺ��塣�̵ؼ��Ŷ��³����ܲ���������ʾ���Ϸ���õ����Ͷ�����ƶ����н���ˮƽ��������ҵ�ṹ�Լ����������Ѻú�����������Ҫ�ٴ룬Ҳ�ǵ�ǰ������ҵ�᳹��������ڼӿ췢չ�ִ�����ҵҪ�󡢴�����и߶��ִ�����ҵ������ȡ�õĽ׶��Գɹ���";
        mArchitectureDAO.add(arc1);
    
        Architecture arc2 = new Architecture();
        arc2.drawableName = "jianzhu2";
        arc2.name = "�Ͼ���";
        arc2.describe =
                "�Ͼ���λ�ڽ���ʡ�Ͼ��н����������³�����������ᣬ�ǳ����������۹ⲽ���ţ���Խ�Ͼ������н������λ���Ͼ�����Ļ�������԰�ڣ��յ��ڽ���������ɭ�ֹ�԰�ڡ������б���ĸ���������Ͼ������ٵ����ң����˴������������������Ծ�����������Ͼ����µر�Ҳ���Ͼ����¾��㡣2014��3��24�գ��Ͼ�2014��»Ἢ�������ԡ������˶����ഺ������֮�š�Ϊ���⣬���С�Ӣ�������������������ⷢ�����������Ϊ������ȫ�������������˺��������ѵĹ㷺��ע�ͻ������룬������������5�������󣬡��Ͼ��ۡ���õ�����֧������ߣ���28.85%��������ӱ��������ʽ��Ϊ�Ͼ�����Ļ�������԰�����ŵ���������";
        mArchitectureDAO.add(arc2);
    
        Architecture arc3 = new Architecture();
        arc3.drawableName = "jianzhu3";
        arc3.name = "����ǽ";
        arc3.describe =
                "�Ͼ�����ǽ�������������ʱ�������Ĺ��ǡ��ʳǡ����Ǻ�������س�ǽ���ֶ�ָ������õľ���(�ڳ�)��ǽ���Ͼ�����ǽʼ����1366��(Ԫ����إ����)��ȫ���깤��1393��(������إ����)������ȫ����ʡ��ʮ�˸���һ����ʮ�����ع�28���񹤣�Լ3.5�ڿ��ש����ʱ��27�꣬������������������س�ԫ�ĸ�֡��Ͼ�����ǽӪ��ʱ��һ����������ǽȡ���λ���εľ��ƣ������Ͼ�ɽ����ˮϵ���������ǣ���ɽ��֮�����ս���֮�ƣ��γ��������⡰�϶��������Ļ��׸�֡����о��ǳ�ǽ�����̻�35.3����������й���һ���ԫ�����������һ���ԫ�����ɹ���ѡ�����¼Э�������һ���ǽ��������֮��������ǽ���ǳ���60����Ͼ�����ǽ�߼���ں��ڣ��ݸ�¢֮������ɽ��ˮ���������й�����ƶ�����Ȼ���ϵĵ䷶��Ҳ�ǹŴ����ǽ���Ľܳ������Ͼ�����ǽ��Ϊ�й��Ŵ����·�����ʩ����ԫ���켼�������֮����������ʷ��ֵ�����ͼ�ֵ�����ż�ֵ�Լ�������ơ���ģ�����ܵ���棬�������ǽ���޷���֮���⣬��ν�Ǽ��й�����֮�����һ�깹��1956��10�£��Ͼ�����ǽ�ֶ�����뽭��ʡ���ﱣ����λ;1988��1�£��Ͼ�����ǽȫ�α�ȷ��Ϊȫ���ص����ﱣ����λ��2012��11�£����ǳ�ǽ��Ϊ���й������ǽ����Ŀǣͷ�ĳ��б������й������Ļ��Ų�Ԥ��������";
        mArchitectureDAO.add(arc3);
    }
    
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        Architecture arc = mList.get(arg2);
        Intent intent = new Intent(this,ContentActivity.class);
        intent.putExtra("image", arc.drawableName);
        intent.putExtra("title", arc.name);
        intent.putExtra("content", arc.describe);
        intent.putExtra("befor","����");
        startActivity(intent);
    }
}
