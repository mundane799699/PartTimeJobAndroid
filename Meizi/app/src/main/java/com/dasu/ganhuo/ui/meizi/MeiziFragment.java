package com.dasu.ganhuo.ui.meizi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dasu.ganhuo.R;
import com.dasu.ganhuo.mode.logic.base.GankSp;
import com.dasu.ganhuo.mode.logic.category.GanHuoEntity;
import com.dasu.ganhuo.mode.logic.meizi.MeiziController;
import com.dasu.ganhuo.ui.base.OnItemClickListener;
import com.dasu.ganhuo.ui.view.recyclerview.LoadMoreRecyclerView;
import com.dasu.ganhuo.ui.view.recyclerview.OnPullUpRefreshListener;
import com.dasu.ganhuo.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * MeiziFragment
 *
 * @author fangyuan
 * @date 2019/6/15
 */
public class MeiziFragment extends Fragment implements OnItemClickListener<GanHuoEntity> {
    private Context mContext;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizi, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mMeiziController = new MeiziController(this);

        mMeiziController.loadBaseData();
    }
    
    private List<GanHuoEntity> mMeiziList = new ArrayList<>();
    private MeiziController mMeiziController;
    
    private LoadMoreRecyclerView mMeiziRv;
    private MeiziRecycleAdapter mRecycleAdapter;
    
    private void initView(View view) {
        mMeiziRv = (LoadMoreRecyclerView) view.findViewById(R.id.rv_meizi_content);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mMeiziRv.setLayoutManager(layoutManager);
        mRecycleAdapter = new MeiziRecycleAdapter(mMeiziList);
        mRecycleAdapter.setOnItemClickListener(this);
        mMeiziRv.setAdapter(mRecycleAdapter);
        mMeiziRv.setOnPullUpRefreshListener(onPullUpRefresh());
    }
    
    private OnPullUpRefreshListener onPullUpRefresh() {
        return new OnPullUpRefreshListener() {
            @Override
            public void onPullUpRefresh() {
                int counts = GankSp.getGankDateCounts(mContext);
                if (mMeiziList.size() >= counts) {
                    ToastUtils.show(mContext, "到底啦！没有妹子了...");
                } else {
                    mMeiziController.startPullUpRefresh();
                }
            }
        };
    }
    
    public void updateMeizi(List<GanHuoEntity> data) {
        mMeiziList.clear();
        mMeiziList.addAll(data);
        mRecycleAdapter.notifyDataSetChanged();
    }
    
    public void refreshMeizi(List<GanHuoEntity> data) {
        int oldSize = mMeiziList.size();
        mMeiziList.addAll(data);
        mRecycleAdapter.notifyItemRangeInserted(oldSize, data.size());
    }
    
    @Override
    public void onItemClick(View view, GanHuoEntity data, int position) {
        ArrayList<String> images = new ArrayList<>();
        for (GanHuoEntity entity : mMeiziList) {
            images.add(entity.getUrl());
        }
        ImageActivity.startActivity(this, position, images);
    }
}
