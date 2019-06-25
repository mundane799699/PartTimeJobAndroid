package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.adapter.SceneAdapter;
import com.example.bean.Scene;
import com.example.dao.SceneDAO;
import com.example.lvyou.R;
import java.util.ArrayList;
import java.util.List;

public class ScenicActivity extends Activity implements OnItemClickListener {
    
    private ListView lv_strategy;
    private List<Scene> mSceneList = new ArrayList<Scene>();
    private SceneDAO mSceneDAO;
    private SceneAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);
        
        setTitle("����");
        lv_strategy = (ListView) findViewById(R.id.lv_strategy);
        mAdapter = new SceneAdapter(this, mSceneList);
        lv_strategy.setAdapter(mAdapter);
        lv_strategy.setOnItemClickListener(this);
        initData();
    }
    
    private void initData() {
        mSceneDAO = new SceneDAO(this);
        List<Scene> sceneList = mSceneDAO.queryAllScene();
        if (sceneList == null || sceneList.isEmpty()) {
            initDaoData();
        }
        sceneList = mSceneDAO.queryAllScene();
        mSceneList.clear();
        mSceneList.addAll(sceneList);
        mAdapter.notifyDataSetChanged();
    }
    
    private void initDaoData() {
        Scene scene1 = new Scene();
        scene1.drawableName = "jingdian1";
        scene1.name = "������";
        scene1.describe =
                "��������һ���ģ���ĹŽ���Ⱥ��������ɣ�������˷ϣ��ǹ���ͼ�����ӵĵط����й��Ĵ�����֮һ������Ϊ�ػ���ʤ����Ϊ�Ŷ��Ͼ�����ɫ��������Ҳ���������������ʤ�أ����й����Ĵ�ͳ�Ž��С���������������ʱ���Ͼ����Ľ����ģ�ͬʱҲ�ǾӶ��ϸ�ʡ֮�ڵ��Ľ̽���Ⱥ��������ʼ�����Σ�λ���ػ��ӱ����Ĺ�Ժ���ԣ���ǰ���ػ���Ϊ���أ��ϰ���ʯשǽΪ�ձڣ�ȫ��110�ף���10�ף���ȫ���ձ�֮�������ǰ�о���ͤ��˼��ͤ;�������Ͻ��������š�����š���ɵ�����á��𾭸�Ƚ���;���������п��Ǹ󡣷�󻡢��褡�������л������ס��������⾴��������λ�����ľ��¼ҡ����μҡ���ѧ�������ﴴ���˲����ҵ����д����ǧ�Ŵ��е�ƪ�¡���ʷ�ϵķ��������Ļ��彨�����һ�λ���1937���վ����Ե��ڻ���1984�긴���������������ѽӴ��ο�һ�ڶ��ˣ�ƽʱ����������10���˴����ϣ��ڼ�����30���˴����ϡ�";
        mSceneDAO.add(scene1);
        
        Scene scene2 = new Scene();
        scene2.drawableName = "jingdian2";
        scene2.name = "��Т��";
        scene2.describe =
                "��Т��λ�ڽ���ʡ�Ͼ����������Ͻ�ɽ��´������������£�������ɽ�꣬����÷��ɽ��λ����ɽ�羰��ʤ���ڣ�����̫����Ԫ�����ʺ�ĺ�����Ĺ����ʺ������ֺš�Т�ȸ߻ʺ󡰣��������Т�����£�������Т�ꡰ����ռ�������170����ƽ���ף����й���ģ���ĵ�������֮һ����Т��ʼ����������ʮ����(1381��)��������������(1405��)���ɣ��Ⱥ���þ���10����ʱ��25�ꡣ�����ε��ꡰ��ɽΪ�ꡱ���ƣ��ִ�����Ϊ�������ơ�����������Ȼ��гͳһ���ﵽ���˺�һ�������߶ȣ���Ϊ�й���ͳ���������Ļ��뻷����ѧ���ϵ�����䷶����Т����Ϊ�й�������֮�ף�����������������ʯ����������߳ɾͣ�ֱ��Ӱ�����������������20�����������޵����ƣ�����ʷ���̷ֲ��ڱ������������������ӱ��ȵص�����ʼ����ޣ������Ͼ���Т��Ĺ��ƺ�ģʽӪ�������й����귢չʷ����������ĵ�λ���ʶ��С�����ʼҵ�һ�ꡰ��������1961��3�£���Т����Ϊ����ȫ���ص����ﱣ����λ;2003��7�£����������Ļ��Ų���ѡ��׼����Т�꼰��������Ĺ����Ϊ�����Ļ��Ų�;2006��12�£��ֱ���Ϊ���������ص�羰��ʤ������������AAAAA�����ξ�����";
        mSceneDAO.add(scene2);
        
        Scene scene3 = new Scene();
        scene3.drawableName = "jingdian3";
        scene3.name = "�����";
        scene3.describe =
                "�������԰λ�ڽ���ʡ�Ͼ����������������Ͻ�ɽ����������ǽ�����й����Ļʼ�԰�ֺ�����Ҳ���й�����Ľ��ϻʼ�԰�ֺͽ��ϵ������ĳ��ڹ�԰������Ϊ���������顰����Ϊ�����ص㹫԰������AAAA�����ξ��������������ɣ���������������ǧ�������������ʷ����������ʷ�����׷��������ʱ�ڣ�����ʱ�ڱ�Ϊ�ʼ�԰�֣�����ʱΪ�Ʋ�⣬��ϵ�ʼҽ��أ�ֱ����ĩ�ٰ�����Ȱҵ��ʱ�����ٷ�����(��������)��Ϊ�������԰֮�������������Բ�������������(���ޡ�ӣ�ޡ����ޡ����ޡ�����)�����޵�����ͨ����Ȼһ�壬������ɽ��ˮ������ŷ����Ҳ��д��������Ī���ں��;Ǯ��Ī�����������������Ϊ�羰԰�֣���Ϊ�Ļ�ʤ�أ���������ɧ�ͣ���Ҫ���������ڴ�������Ӱ����Ϊ���˴�Ϊ��̸��";
        mSceneDAO.add(scene3);
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, ContentActivity.class);
        Scene scene = mSceneList.get(position);
        intent.putExtra("image", scene.drawableName);
        intent.putExtra("title", scene.name);
        intent.putExtra("content", scene.describe);
        intent.putExtra("befor", "����");
        startActivity(intent);
    }
}
