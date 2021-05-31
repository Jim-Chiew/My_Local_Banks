package sg.edu.rp.c346.id20004713.mylocalbanks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvDBS;
    TextView tvUOB;
    TextView tvOCBC;

    String wordClicked = "";  //Check to see which word is chosen

    // used to determine if button is toggled
    boolean dbsFav = true;
    boolean ocbcFav = true;
    boolean uobFav = true;

    int DefaultColor;  //saved the default color of TV

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDBS = findViewById(R.id.tvDBS);
        tvUOB = findViewById(R.id.tvUOB);
        tvOCBC = findViewById(R.id.tvOCBC);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvUOB);
        registerForContextMenu(tvOCBC);

        DefaultColor = tvDBS.getCurrentTextColor();  //get default color value
    }

    //_______________________________ Choose action (Website / Contact bank) _______________________
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0,"Website");
        menu.add(0,1,1,"Contact the Bank");
        menu.add(0, 2, 2, "Add To Favorite");

        if (v == tvDBS){  //used to identify which text is being clicked
            wordClicked = "DBS";
        } else if (v == tvOCBC){
            wordClicked = "OCBC";
        } else if (v == tvUOB){
            wordClicked = "UOB";
        }


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (wordClicked.equalsIgnoreCase("DBS")){  //If chosen word is DBS
            //String websiteDBS = "https://www.dbs.com.sg";
            //long numberDBS = 18001111111L;
            //action(item.getItemId(), websiteDBS, numberDBS);

            if (item.getItemId() == 2){  //IF add to favorite is chosen
                if(dbsFav){
                    tvDBS.setTextColor(Color.parseColor("#FF0000"));
                    dbsFav = false;
                } else {
                    tvDBS.setTextColor(DefaultColor);  //My phone is in dark mode thus i set to white
                    dbsFav = true;
                }
            } else {
                action(item.getItemId(), getString(R.string.dbsWebSite), getString(R.string.dbsNumber));
            }

        } else if (wordClicked.equalsIgnoreCase("OCBC")){
            //String websiteOCBC = "https://www.ocbc.com";
            //long numberOCBC = 18003633333L;
            //action(item.getItemId(), websiteOCBC, numberOCBC);
            if (item.getItemId() == 2){
                if(ocbcFav){
                    tvOCBC.setTextColor(Color.parseColor("#FF0000"));
                    ocbcFav = false;
                } else {
                    tvOCBC.setTextColor(DefaultColor);  //My phone is in dark mode thus i set to white
                    ocbcFav = true;
                }
            } else {
                action(item.getItemId(), getString(R.string.ocbcWebSite), getString(R.string.ocbcNumber));
            }

        } else if (wordClicked.equalsIgnoreCase("UOB")){
            //String websiteUOB = "https://www.uob.com.sg";
            //long numberUOB = 18002222121L;
            //action(item.getItemId(), websiteUOB, numberUOB);
            if (item.getItemId() == 2){
                if(uobFav){
                    tvUOB.setTextColor(Color.parseColor("#FF0000"));
                    uobFav = false;
                } else {
                    tvUOB.setTextColor(DefaultColor);  //My phone is in dark mode thus i set to white
                    uobFav = true;
                }
            } else {
                action(item.getItemId(), getString(R.string.uobWebSite), getString(R.string.uobNumber));
            }
        }
        return super.onContextItemSelected(item);
    }

    //_______________________________ Execute action _______________________________________________
    private void action (int id, String web, String number){
        if (id == 0){
            // Webiste
            Intent intent = new Intent(Intent. ACTION_VIEW, Uri.parse(web));
            startActivity(intent);
        } else if (id == 1){
            // Context
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + number));
            startActivity(intentCall);
        }
    }

    //_______________________________ Change Language ______________________________________________
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //Change Language
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.EnglishSelection){
            tvOCBC.setText(R.string.ocbc);
            tvDBS.setText(R.string.dbs);
            tvUOB.setText(R.string.uob);

        } else if (id == R.id.chineseSelection){
            tvUOB.setText(R.string.uob_chinese);
            tvDBS.setText(R.string.dbs_chinese);
            tvOCBC.setText(R.string.ocbc_chinese);
        }

        return super.onOptionsItemSelected(item);
    }
}