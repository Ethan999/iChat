package com.example.song.ichat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView mChatView;
    private EditText mMsg;
    private List<ChatMessage> mDatas = new ArrayList();
    private MsgAdapter mAdapter;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            ChatMessage from = (ChatMessage)msg.obj;
            MainActivity.this.mDatas.add(from);
            MainActivity.this.mAdapter.notifyDataSetChanged();
            MainActivity.this.mChatView.setSelection(MainActivity.this.mDatas.size() - 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);

        initView();

        this.mAdapter = new MsgAdapter(this, this.mDatas);
        this.mChatView.setAdapter(this.mAdapter);
    }

    private void initView()
    {
        this.mChatView = ((ListView)findViewById(R.id.id_chat_listView));
        this.mMsg = ((EditText)findViewById(R.id.id_chat_msg));
        this.mDatas.add(new ChatMessage(ChatMessage.Type.INPUT, "Hi，很高兴为您服务"));
    }

    public void sendMessage(View view)
    {
        final String msg = this.mMsg.getText().toString();
        if (TextUtils.isEmpty(msg))
        {
            Toast.makeText(this, "您还没有填写信息呢...",Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage to = new ChatMessage(ChatMessage.Type.OUTPUT, msg);
        to.setDate(new Date());
        this.mDatas.add(to);

        this.mAdapter.notifyDataSetChanged();
        this.mChatView.setSelection(this.mDatas.size() - 1);

        this.mMsg.setText("");

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);


        if (imm.isActive())
        {
            imm.toggleSoftInput(1,
                    2);
        }

        new Thread(msg)
        {
            public void run()
            {
                ChatMessage from = null;
                try
                {
                    from = HttpUtils.sendMsg(msg);
                }
                catch (Exception e) {
                    from = new ChatMessage(ChatMessage.Type.INPUT, "服务器挂了呢...");
                }
                Message message = Message.obtain();
                message.obj = from;
                MainActivity.this.mHandler.sendMessage(message);
            }
        }.start();


    }
}