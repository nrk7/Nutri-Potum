package app.android.example.com.nutri_potum50;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Summary Screen for the Nutri-Potum drink app.
 *
 * @author Sujeeth Jinesh
 */
public class SummaryActivity extends AppCompatActivity {

    //fetch previous values from the MainActivity class
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summary_page);

        Intent activityThatCalled = getIntent();

        String priceMessage = activityThatCalled.getExtras().getString("priceMessage");
        TextView callingActivityMessage = (TextView) findViewById(R.id.finalOrder);
        callingActivityMessage.setText(priceMessage);

        double price = activityThatCalled.getExtras().getDouble("price");
        callingActivityMessage.append("\nPrice: $" + price);

        int calories = activityThatCalled.getExtras().getInt("calories");
        callingActivityMessage.append("\nCalories: " + calories);

        int transFat = activityThatCalled.getExtras().getInt("transFat");
        callingActivityMessage.append("\nTrans fat: " + transFat + "%");

        int fat = activityThatCalled.getExtras().getInt("fat");
        callingActivityMessage.append("\nFat: " + fat + "%");

        int satFat = activityThatCalled.getExtras().getInt("satFat");
        callingActivityMessage.append("\nSat. fat: " + satFat + "%");

        int sodium = activityThatCalled.getExtras().getInt("sodium");
        callingActivityMessage.append("\nSodium: " + sodium + "%");

        int iron = activityThatCalled.getExtras().getInt("iron");
        callingActivityMessage.append("\nIron: " + iron + "%");

        int iodine = activityThatCalled.getExtras().getInt("iodine");
        callingActivityMessage.append("\nIodine: " + iodine + "%");

        int selenium = activityThatCalled.getExtras().getInt("selenium");
        callingActivityMessage.append("\nSelenium: " + selenium + "%");

        int manganese = activityThatCalled.getExtras().getInt("manganese");
        callingActivityMessage.append("\nManganese: " + manganese + "%");

        int vitaminA = activityThatCalled.getExtras().getInt("vitaminA");
        callingActivityMessage.append("\nVitamin A: " + vitaminA + "%");

        int vitaminC = activityThatCalled.getExtras().getInt("vitaminC");
        callingActivityMessage.append("\nVitamin C: " + vitaminC + "%");

        int vitaminK = activityThatCalled.getExtras().getInt("vitaminK");
        callingActivityMessage.append("\nVitamin K: " + vitaminK + "%");

        int riboflavin = activityThatCalled.getExtras().getInt("riboflavin");
        callingActivityMessage.append("\nRiboflavin: " + riboflavin + "%");

        int niacin = activityThatCalled.getExtras().getInt("niacin");
        callingActivityMessage.append("\nNiacin: " + niacin + "%");

        int vitaminB6 = activityThatCalled.getExtras().getInt("vitaminB6");
        callingActivityMessage.append("\nVitamin B6: " + vitaminB6 + "%");

        int folicAcid = activityThatCalled.getExtras().getInt("folicAcid");
        callingActivityMessage.append("\nFolic acid: " + folicAcid + "%");

        int vitaminB12 = activityThatCalled.getExtras().getInt("vitaminB12");
        callingActivityMessage.append("\nVitamin B12: " + vitaminB12 + "%");

        int sugar = activityThatCalled.getExtras().getInt("sugar");
        callingActivityMessage.append("\nSugar: " + sugar + " g");

        int calcium = activityThatCalled.getExtras().getInt("calcium");
        callingActivityMessage.append("\nCalcium: " + calcium + " g");
    }

    //In order to prevent extra consumption of memory when returning, simply finish current activity
    public void backButtonMethod(View view)
    {
        finish();
    }


    //email summary method
    public void emailButtonMethod(View view)
    {

        Intent activityThatCalled = getIntent();


        TextView message = (TextView) findViewById(R.id.finalOrder);

        String priceMessage = activityThatCalled.getExtras().getString("priceMessage");

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        Calendar date = Calendar.getInstance();
        int day = date.get(Calendar.DATE);
        int month = date.get(Calendar.MONTH);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Nutri-Potum Summary " + (month + 1) + "/" + day);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, 0);
        }
    }
}
