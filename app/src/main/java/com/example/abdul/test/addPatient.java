package com.example.abdul.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Vidi on 5/5/2016.
 */
public class addPatient  extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Filter_Object> mArrFilter;
    private ScrollView mScrollViewFilter;
    private Filter_Adapter mFilter_Adapter ;
    private FlowLayout mFlowLayoutFilter ;
    private String[] getSelected;
    Dialog dialog;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);
    }
    public void ShowForm2(View v)
    {
        setContentView(R.layout.activity_addpatient2);
    }
    public void ShowForm3(View v)
    {
        setContentView(R.layout.activity_addpatient3);
    }

    public void ShowHealthCon(View v)
    {
        setContentView(R.layout.activity_healthcon);
        displayHealthList();

    }

    public void AddHealthCon(View v)
    {
        setContentView(R.layout.activity_addpatient3);
        TextView txt1=(TextView) findViewById(R.id.healthcon);
        String text="";
        if(getSelected!=null) {
            for (int i = 0; i < getSelected.length; i++) {
                if (i == getSelected.length - 1)
                    text = text + getSelected[i];
                else
                    text = text + getSelected[i] + ",\n";
            }
        }
        txt1.setText(text);
    }


    private void displayHealthList() {

        mArrFilter = new ArrayList<Filter_Object>();

        String[] strArr = new String[5];
        strArr[0]="Heart Disease";
        strArr[1]="Diabetes";
        strArr[2]="Hepatitis";
        strArr[3]="Respiration";
        strArr[4]="Obesity";



        int lengthOfstrArr = strArr.length;

        for (int i = 0; i < lengthOfstrArr; i++) {
            Filter_Object filter_object = new Filter_Object();
            filter_object.mName = strArr[i];
            filter_object.mIsSelected = false;
            mArrFilter.add(filter_object);
        }


        Collections.sort(mArrFilter, new Comparator<Filter_Object>() {
            @Override
            public int compare(Filter_Object lhs, Filter_Object rhs) {
                char lhsFirstLetter = TextUtils.isEmpty(lhs.mName) ? ' ' : lhs.mName.charAt(0);
                char rhsFirstLetter = TextUtils.isEmpty(rhs.mName) ? ' ' : rhs.mName.charAt(0);
                int firstLetterComparison = Character.toUpperCase(lhsFirstLetter) - Character.toUpperCase(rhsFirstLetter);
                if (firstLetterComparison == 0)
                    return lhs.mName.compareTo(rhs.mName);
                return firstLetterComparison;
            }
        });



        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        mListView = (ListView) findViewById(R.id.listViewFilter);
        mScrollViewFilter = (ScrollView)findViewById(R.id.scrollViewFilter);
        mFlowLayoutFilter = (FlowLayout)findViewById(R.id.flowLayout);

        mFilter_Adapter = new Filter_Adapter(mArrFilter);
        mListView.setAdapter(mFilter_Adapter);
    }

    public void addFilterTag() {
        final ArrayList<Filter_Object> arrFilterSelected = new ArrayList<>();

        mFlowLayoutFilter.removeAllViews();

        int length = mArrFilter.size();
        boolean isSelected = false;
        for (int i = 0; i < length; i++) {
            Filter_Object fil = mArrFilter.get(i);
            if (fil.mIsSelected) {
                isSelected = true;
                arrFilterSelected.add(fil);
            }
        }
        if (isSelected) {
            mScrollViewFilter.setVisibility(View.VISIBLE);
        } else {
            mScrollViewFilter.setVisibility(View.GONE);
        }
        int size = arrFilterSelected.size();
        getSelected=new String[size];
        LayoutInflater layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < size; i++) {
            View view = layoutInflater.inflate(R.layout.filter_tag_edit, null);

            TextView tv = (TextView) view.findViewById(R.id.tvTag);
            LinearLayout linClose = (LinearLayout) view.findViewById(R.id.linClose);
            final Filter_Object filter_object = arrFilterSelected.get(i);
            linClose.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showToast(filter_object.name);


                    int innerSize = mArrFilter.size();
                    for (int j = 0; j < innerSize; j++) {
                        Filter_Object mFilter_Object = mArrFilter.get(j);
                        if (mFilter_Object.mName.equalsIgnoreCase(filter_object.mName)) {
                            mFilter_Object.mIsSelected = false;

                        }
                    }
                    addFilterTag();
                    mFilter_Adapter.updateListView(mArrFilter);
                }
            });


            tv.setText(filter_object.mName);
            getSelected[i]=filter_object.mName;
            int color = getResources().getColor(R.color.themecolor);

            View newView = view;
            newView.setBackgroundColor(color);

            FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            params.topMargin = 5;
            params.leftMargin = 10;
            params.bottomMargin = 5;

            newView.setLayoutParams(params);

            mFlowLayoutFilter.addView(newView);
        }
    }

    public class Filter_Adapter extends BaseAdapter {
        ArrayList<Filter_Object> arrMenu;

        public Filter_Adapter(ArrayList<Filter_Object> arrOptions) {
            this.arrMenu = arrOptions;
        }

        public void updateListView(ArrayList<Filter_Object> mArray) {
            this.arrMenu = mArray;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return this.arrMenu.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.filter_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mTtvName = (TextView) convertView.findViewById(R.id.tvName);
                viewHolder.mTvSelected = (TextView) convertView.findViewById(R.id.tvSelected);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final Filter_Object mService_Object = arrMenu.get(position);
            viewHolder.mTtvName.setText(mService_Object.mName);

            if (mService_Object.mIsSelected) {
                viewHolder.mTvSelected.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mTvSelected.setVisibility(View.INVISIBLE);
            }
            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    mService_Object.mIsSelected = !mService_Object.mIsSelected;
                    mScrollViewFilter.setVisibility(View.VISIBLE);

                    addFilterTag();
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }

        public class ViewHolder {
            TextView mTtvName, mTvSelected;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}