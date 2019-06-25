package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.lvyou.adapter.SceneAdapter;
import com.example.lvyou.bean.Scene;
import com.example.lvyou.dao.SceneDAO;

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

        setTitle("景点");
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
        scene1.name = "夫子庙";
        scene1.describe =
                "夫子庙是一组规模宏大的古建筑群，历经沧桑，几番兴废，是供奉和祭祀孔子的地方，中国四大文庙之一，被誉为秦淮名胜而成为古都南京的特色景观区，也是蜚声中外的旅游胜地，是中国最大的传统古街市。夫子庙不仅是明清时期南京的文教中心，同时也是居东南各省之冠的文教建筑群。夫子庙始建于宋，位于秦淮河北岸的贡院街旁，庙前的秦淮河为泮池，南岸的石砖墙为照壁，全长110米，高10米，是全国照壁之最。北岸庙前有聚星亭、思乐亭;中轴线上建有棂星门、大成门、大成殿、明德堂、尊经阁等建筑;另外庙东还有魁星阁。范蠡、周瑜、王导、谢安、李白、杜牧、吴敬梓等数百位著名的军事家、政治家、文学家在这里创造了不朽的业绩，写下了千古传诵的篇章。历史上的夫子庙曾四毁五建，最后一次毁于1937年日军侵略的炮火。自1984年复建以来，夫子庙已接待游客一亿多人，平时日人流量在10万人次以上，节假日在30万人次以上。";
        mSceneDAO.add(scene1);

        Scene scene2 = new Scene();
        scene2.drawableName = "jingdian2";
        scene2.name = "明孝陵";
        scene2.describe =
                "明孝陵位于江苏省南京市玄武区紫金山南麓独龙阜玩珠峰下，东毗中山陵，南临梅花山，位于钟山风景名胜区内，是明太祖朱元璋与其皇后的合葬陵墓。因皇后马氏谥号”孝慈高皇后“，又因奉行孝治天下，故名”孝陵“。其占地面积达170余万平方米，是中国规模最大的帝王陵寝之一。明孝陵始建于明洪武十四年(1381年)，至明永乐三年(1405年)建成，先后调用军工10万，历时达25年。承唐宋帝陵“依山为陵”旧制，又创方坟为圜丘新制。将人文与自然和谐统一，达到天人合一的完美高度，成为中国传统建筑艺术文化与环境美学相结合的优秀典范。明孝陵作为中国明皇陵之首，代表了明初建筑和石刻艺术的最高成就，直接影响明清两代五百余年20多座帝王陵寝的形制，依历史进程分布于北京、湖北、辽宁、河北等地的明清皇家陵寝，均按南京明孝陵的规制和模式营建，在中国帝陵发展史上有着特殊的地位，故而有”明清皇家第一陵“的美誉。1961年3月，明孝陵列为首批全国重点文物保护单位;2003年7月，根据世界文化遗产遴选标准，明孝陵及其明功臣墓被列为世界文化遗产;2006年12月，又被列为首批国家重点风景名胜区和首批国家AAAAA级旅游景区。";
        mSceneDAO.add(scene2);

        Scene scene3 = new Scene();
        scene3.drawableName = "jingdian3";
        scene3.name = "玄武湖";
        scene3.describe =
                "玄武湖公园位于江苏省南京市玄武区，东枕紫金山，西靠明城墙，是中国最大的皇家园林湖泊，也是中国仅存的江南皇家园林和江南地区最大的城内公园，被誉为”金陵明珠“，现为国家重点公园、国家AAAA级旅游景区。玄武湖古名桑泊、后湖，已有两千三百年的人文历史，其人文历史最早可追溯至先秦时期，六朝时期辟为皇家园林，明朝时为黄册库，均系皇家禁地，直至清末举办南洋劝业会时，开辟丰润门(今玄武门)，为玄武湖公园之滥觞。玄武湖方圆近五里，分作五洲(环洲、樱洲、菱洲、梁洲、翠洲)，洲洲堤桥相通，浑然一体，处处有山有水。宋人欧阳修也曾写道“金陵莫美于后湖;钱塘莫美于西湖”，玄武湖为风景园林，亦为文化胜地，历代文人骚客，政要名流都曾在此留下身影，皆为后人传为美谈。";
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
        intent.putExtra("befor", "景点");
        startActivity(intent);
    }
}
