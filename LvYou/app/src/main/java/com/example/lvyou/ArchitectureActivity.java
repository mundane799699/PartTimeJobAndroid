package com.example.lvyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.example.lvyou.adapter.ArchitectureAdapter;
import com.example.lvyou.bean.Architecture;
import com.example.lvyou.dao.ArchitectureDAO;

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

        setTitle("建筑");
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
        arc1.name = "紫峰";
        arc1.describe =
                "南京紫峰大厦。 世界第七、中国第四、大陆第二高楼紫峰大厦18日晚在南京举行落成点亮仪式。这是继上海环球金融中心后，中国大陆地区的第二高度。至此，两岸三地已有六座建筑跻身世界十大高楼行列。 紫峰大厦由上海绿地集团投资开发，由美国著名设计公司SOM设计事务所按照国际一流标准设计。位于南京中心城区被誉为“心脏地段”的鼓楼广场，总建筑面积30多万平方米，高度450米，是集超五星级洲际酒店、甲级办公楼、高档商场等业态为一体的超高层大型城市综合体。绿地集团董事长、总裁张玉良表示，紫峰大厦的落成投用是推动城市建设水平、提升产业结构以及沪苏两地友好合作交流的重要举措，也是当前国内企业贯彻党中央关于加快发展现代服务业要求、打造城市高端现代服务业集聚区取得的阶段性成果。";
        mArchitectureDAO.add(arc1);

        Architecture arc2 = new Architecture();
        arc2.drawableName = "jianzhu2";
        arc2.name = "南京眼";
        arc2.describe =
                "南京眼位于江苏省南京市建邺区河西新城青奥轴线中轴，是长江上首座观光步行桥，跨越南京长江夹江，起点位于南京青奥文化体育公园内，终点在江心洲青年森林公园内。羽翼般斜拉的钢索振翅向上就像竖琴的琴弦，行人穿行其间犹如琴弦上跳跃的音符，是南京的新地标也是南京的新景点。2014年3月24日，南京2014青奥会吉祥物抽抽以”架起运动、青春、友谊之桥“为主题，用中、英、法三国语言面向海内外发出“征集令”，为步行桥全球征名，吸引了海内外网友的广泛关注和积极参与，共征集桥名近5万个。最后，”南京眼“获得的网友支持率最高，达28.85%，从中脱颖而出，正式成为南京青奥文化体育公园步行桥的新桥名。";
        mArchitectureDAO.add(arc2);

        Architecture arc3 = new Architecture();
        arc3.drawableName = "jianzhu3";
        arc3.name = "明城墙";
        arc3.describe =
                "南京明城墙，整体包括明朝时期修筑的宫城、皇城、京城和外郭四重城墙，现多指保存完好的京城(内城)城墙。南京明城墙始建于1366年(元至正廿六年)，全部完工于1393年(明洪武廿六年)，动用全国五省二十八府，一百五十二州县共28万民工，约3.5亿块城砖，历时达27年，终完成明王朝都城四重城垣的格局。南京明城墙营造时，一改以往都城墙取方形或矩形的旧制，根据南京山脉、水系的走向筑城，得山川之利，空江湖之势，形成由内向外“南斗北斗”的环套格局。其中京城城墙蜿蜒盘桓35.3公里，不仅是中国第一大城垣，还是世界第一大城垣，并成功入选世界纪录协会世界第一大城墙，而京城之外的外郭城墙更是超过60公里。南京明城墙高坚甲于海内，据岗垄之脊，依山傍水而建，是中国礼教制度与自然相结合的典范，也是古代都城建设的杰出代表。南京明城墙作为中国古代军事防御设施、城垣建造技术集大成之作，无论历史价值、观赏价值、考古价值以及建筑设计、规模、功能等诸方面，国内外城墙都无法与之比拟，可谓是继中国长城之后的又一宏构。1956年10月，南京明城墙分多段列入江苏省文物保护单位;1988年1月，南京明城墙全段被确认为全国重点文物保护单位。2012年11月，京城城墙作为”中国明清城墙“项目牵头的城市被列入中国世界文化遗产预备名单。";
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
        intent.putExtra("befor","建筑");
        startActivity(intent);
    }
}
