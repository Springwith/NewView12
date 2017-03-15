package admi.newview;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import admi.newview.view.DrawView;
import admi.newview.view.SectorItem;

public class MainActivity extends Activity implements View.OnClickListener{
    private DrawView drawView;
    private Button start, pause, stop;

    private List<SectorItem> list;
    private ArrayList<Integer> colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (DrawView) findViewById(R.id.dv);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        drawView.setViewClickListenrt(new DrawView.ViewClickListener() {
            @Override
            public void clickListener(SectorItem item) {
                Toast.makeText(MainActivity.this, "You Click :" + item.getName() + " Area !", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initData() {
        colors = new ArrayList<Integer>();
        list = new ArrayList<SectorItem>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.WHITE);
        drawView.setColors(colors);
        SectorItem item = new SectorItem();
        item.setStartAngle(-90);
        item.setEndAngle(0);
        item.setName("Red");
        list.add(item);

        item = new SectorItem();
        item.setStartAngle(0);
        item.setEndAngle(80);
        item.setName("Green");
        list.add(item);

        item = new SectorItem();
        item.setStartAngle(80);
        item.setEndAngle(150);
        item.setName("BLUE");
        list.add(item);

        item = new SectorItem();
        item.setStartAngle(150);
        item.setEndAngle(220);
        item.setName("YELLOW");
        list.add(item);
        item = new SectorItem();
        item.setStartAngle(220);
        item.setEndAngle(270);
        item.setName("White");
        list.add(item);

        drawView.setData(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (drawView.isRefresh()) {
            drawView.stop();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                initData();
                drawView.start();
                break;
            case R.id.pause:
                drawView.pause();
                break;
            case R.id.stop:
                drawView.stop();
                break;
        }
    }
}
