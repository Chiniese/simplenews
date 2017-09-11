package com.p2p.dsad.news.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 所有fragment的基类
 * Created by dsad on 2017/9/11.
 */

public abstract class BaseFragment extends Fragment
{
    protected Context mContext;
    protected Activity mActivity;
    protected View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(inintview(),null);
        ButterKnife.bind(this,mView);
        return mView;
    }

    protected abstract int inintview();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        mActivity=getActivity();
        inintdata();
    }

    protected abstract void inintdata();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
