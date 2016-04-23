package com.example.song.ichat;

/**
 * Created by Ethan on 2016/4/21.
 */
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MsgAdapter extends BaseAdapter
{
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public MsgAdapter(Context context, List<ChatMessage> datas)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * 接受到消息为1，发送消息为0
     */
    @Override
    public int getItemViewType(int position)
    {
        ChatMessage msg = mDatas.get(position);
        return msg.getType() == ChatMessage.Type.INPUT ? 1 : 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChatMessage chatMessage = mDatas.get(position);

        ViewHolder viewHolder = null;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            if (chatMessage.getType() == ChatMessage.Type.INPUT)
            {
                convertView = mInflater.inflate(R.layout.left_msg,
                        parent, false);
                viewHolder.createDate = (TextView) convertView
                        .findViewById(R.id.chat_time_lt);
                viewHolder.content = (TextView) convertView
                        .findViewById(R.id.msg_from_Isong);
                convertView.setTag(viewHolder);
            } else
            {
                convertView = mInflater.inflate(R.layout.right_msg,
                        null);

                viewHolder.createDate = (TextView) convertView
                        .findViewById(R.id.chat_time_rt);
                viewHolder.content = (TextView) convertView
                        .findViewById(R.id.msg_from_visitor);
                convertView.setTag(viewHolder);
            }

        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.content.setText(chatMessage.getMsg());
        viewHolder.createDate.setText(chatMessage.getDateStr());

        return convertView;
    }

    private class ViewHolder
    {
        public TextView createDate;
        public TextView name;
        public TextView content;
    }

}
