package cn.neocer.english;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Message;
import android.os.Handler;
import android.app.ProgressDialog;

public class MainActivity extends AppCompatActivity {

    ProgressDialog Messagebox ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Messagebox = new ProgressDialog(this);
    }
    public void buttonclick(View view) {
        Messagebox.setMessage("正在加载在线题库，请稍后...");
        Messagebox.show();
        Messagebox.setCancelable(false);
        new Thread(new Runable()).start();
    }
    public void buttonclick1(View view) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("关于软件")//设置对话框的标题
                .setMessage("任何问题联系QQ:709653366\n不一定给解决！")//设置对话框的内容
                //设置对话框的按
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                        //getnew();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    class Runable implements Runnable
    {
        @Override
        public void run()
        {
            String source = MyRequest.sendGet("https://gitee.com/filmapp/extra_field_practice/raw/master/wt.txt","");
            Publicclass.source = source;
            Messagebox.dismiss();

            Message msg = Message.obtain();
            msg.what=0;
            Myhandler.sendMessage(msg);

        }
    }
    Handler Myhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if(msg.what == 0)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                MainActivity.this.startActivity(intent);

            }
        }
    };


}
