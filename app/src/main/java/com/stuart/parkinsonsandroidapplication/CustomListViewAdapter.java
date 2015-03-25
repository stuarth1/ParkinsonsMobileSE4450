package com.stuart.parkinsonsandroidapplication;

/**
 * Created by Stuart Hunter on 3/24/2015.
 */
import java.util.List;

import com.stuart.parkinsonsandroidapplication.PreviousData.ListViewItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter
{

    LayoutInflater inflater;
    List<ListViewItem> items;

    public CustomListViewAdapter(Activity context, List<ListViewItem> items) {
        super();

        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ListViewItem item = items.get(position);
        View vi=convertView;

        if(convertView==null)
            vi = inflater.inflate(R.layout.item_row, null);

        ImageView imgThumbNail = (ImageView) vi.findViewById(R.id.imgThumbnail);
        TextView txtTitle = (TextView) vi.findViewById(R.id.txtTitle);
        TextView txtSubTitle = (TextView) vi.findViewById(R.id.txtSubTitle);

        imgThumbNail.setImageResource(item.ThumbnailResource);
        txtTitle.setText(item.title);
        txtSubTitle.setText(item.subtitle);


        return vi;
    }
}