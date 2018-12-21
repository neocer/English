package cn.neocer.english;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main2Activity extends AppCompatActivity {
    private int now = 0;
    private int True = 0;
    private static int all =20;
    private String YUANWEN;
    private TextView title;
    private EditText yuanwen;
    private EditText daan;
    private Question nowquestion;
    private Question questionall[];
    private int num[];
    private boolean isbuhui=false;
    boolean isload = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        yuanwen = findViewById(R.id.editText2);
        daan = findViewById(R.id.editText3);
        title=findViewById(R.id.textView2);

    }
    public void buttonclick(View view) {
        if(daan.getText().toString().equals(""))
            ;else
        istrue();

    }

    public void buttonclick2(View view) {
        isbuhui=true;
       String str="";
        for(String tmp:this.nowquestion.da)
        {
            if(!str.equals(""))
                str=str+"或";
            str=str+"【"+tmp+"】";
        }
        AlertDialog dialog = new AlertDialog.Builder(Main2Activity.this)
                .setTitle("提示")//设置对话框的标题
                .setMessage("问题:【"+ nowquestion.wt+"】\n答案:"+str)//设置对话框的内容
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
    private void getnew()
    {

        if(this.now>=this.all) {
            AlertDialog dialog = new AlertDialog.Builder(Main2Activity.this)
                    .setTitle("回答完毕")//设置对话框的标题
                    .setMessage("你做对了"+True+"道题，一共"+all+"道题。")//设置对话框的内容
                    //设置对话框的按
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();

                            finish();
                            dialog.dismiss();
                        }
                    }).create();
            dialog.setCanceledOnTouchOutside(false);

            dialog.show();

            return;
        }
        this.now++;
        nowquestion=questionall[num[now-1]];
        yuanwen.setText(nowquestion.wt);
        daan.setText("");
        title.setText("当前第"+now+"/"+all+"题 已答对了"+True+"道");

    }
    private void istrue()
    {
        boolean panduan = false;
        for(String tmp:nowquestion.da)
        {
            if(tmp.equals(daan.getText().toString()))
            {
                panduan=true;
                break;
            }
        }
        String zqda="";
        for(String tmp:nowquestion.da)
        {
            if(!zqda.equals(""))
                zqda=zqda+"或";
            zqda=zqda+"【"+tmp+"】";
        }

        if(panduan){

            AlertDialog dialog = new AlertDialog.Builder(Main2Activity.this)
                    .setTitle("提示")//设置对话框的标题
                    .setMessage("回答正确\n\n问题:【"+ nowquestion.wt+"】\n答案:"+zqda)//设置对话框的内容
                    //设置对话框的按
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                            if(isbuhui==true)
                            {
                                isbuhui =false;
                            }
                            else
                                True++;

                            getnew();
                            dialog.dismiss();
                        }
                    }).create();
            dialog.setCanceledOnTouchOutside(false);

            dialog.show();
        }
        else
        {

            AlertDialog dialog = new AlertDialog.Builder(Main2Activity.this)
                    .setTitle("提示")//设置对话框的标题
                    .setMessage("回答错误！\n你的答案是【" + daan.getText()+ "】\n\n\n问题:【"+ nowquestion.wt+"】\n答案:"+zqda)//设置对话框的内容
                    //设置对话框的按
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                            getnew();
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        }

    }

    public void Message(String title,String text,String button)
    {
        AlertDialog dialog = new AlertDialog.Builder(Main2Activity.this)
                .setTitle(title)//设置对话框的标题
                .setMessage(text)//设置对话框的内容
                //设置对话框的按
                .setPositiveButton(button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    protected void onStart() {
        super.onStart();
        if(isload == true) return;
        isload = true;
        YUANWEN =  getFromAssets("wt.txt");
        YUANWEN = Publicclass.source;
      //  InputStream is=Main2Activity.this.getResources().openRawResource(R.drawable.wenti);
        String tmp[] = Stringcut.GetMidString2(YUANWEN,"<Q>","</Q>");
        if(tmp.length>=all);
        else all=tmp.length;
        questionall = new Question[tmp.length];
        for(int i =0;i<tmp.length;i++)
        {
            questionall[i] = new Question();
            String da = Stringcut.GetMidString(tmp[i],"<D>","</D>");
            questionall[i].wt = Stringcut.GetMidString(tmp[i],"<W>","</W>");
            questionall[i].da =  da.split("\\|");
        }
        num = randomCommon(1,questionall.length,all);
        getnew();
    }
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader( getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
