package app.android.example.com.nutri_potum50;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.bluetooth.*;
import android.widget.Toast;

import app.android.example.com.nutri_potum50.R;
import app.android.example.com.nutri_potum50.SummaryActivity;

/**
 * Main Screen for the Nutri-Potum drink app.
 *
 * @author Sujeeth Jinesh
 */
public class MainActivity extends AppCompatActivity {

    //Initializing sliders

    private static SeekBar carbohydrateSlider;
    private int carbsValue;
    private static TextView carbohydrateSliderText;

    private static SeekBar potassiumSlider;
    private int potassiumValue;
    private static TextView potassiumSliderText;

    private static SeekBar proteinSlider;
    private int proteinValue;
    private static TextView proteinSliderText;

    private static SeekBar fiberSlider;
    private int fiberValue;
    private static TextView fiberSliderText;

    private static SeekBar calciumSlider;
    private int calciumValue;
    private static TextView calciumSliderText;

    private static SeekBar drinkAmountSlider;
    private int drinkAmountValue = 8;
    private static TextView drinkAmountSliderText;

    //pre-initializing double and int values for space conservation

    private double price = 0;

    private int calories = 0;
    private int transFat = 0;
    private int satFat = 0;
    private int cholestrol = 0;
    private int sodium = 0;
    private int iron = 0;
    private int iodine = 0;
    private int selenium = 0;
    private int manganese = 0;
    private int vitaminA = 0;
    private int vitaminC = 0;
    private int vitaminK = 0;
    private int riboflavin = 0;
    private int niacin = 0;
    private int vitaminB6 = 0;
    private int folicAcid = 0;
    private int vitaminB12 = 0;
    private int sugar = 0;
    private int fat = 0;

    private int count = 0;

    //required permission for bluetooth

    private final static int REQUEST_ENABLE_BT = 1;

    //On start up, display and initialize these sliders.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carbohydrateSliderStart();
        potassiumSliderStart();
        proteinSliderStart();
        drinkAmountSliderStart();
        calciumSliderStart();
        fiberSliderStart();
    }

    //To move to the next page, after submit button is clicked
    public void submitOrder(View view) {

        resetAllIngredients();

        carbsValue = carbohydrateSlider.getProgress();
        potassiumValue = potassiumSlider.getProgress()/2; //divide potassium by two for health
        proteinValue = proteinSlider.getProgress();
        calciumValue = calciumSlider.getProgress();
        fiberValue = fiberSlider.getProgress();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.ChocolateFlavor);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        CheckBox multivitaminCheckBox = (CheckBox) findViewById(R.id.MultivitaminFlavor);
        boolean hasMultivitamin = multivitaminCheckBox.isChecked();

        Intent summaryPage = new Intent(this, SummaryActivity.class);

        adjustValues(hasChocolate, hasMultivitamin);

        String priceMessage = createOrderSummary(hasChocolate, hasMultivitamin);

        summaryPage.putExtra("priceMessage", priceMessage);

        summaryPage = putAllIngredientsToSend(summaryPage);

        startActivity(summaryPage);
    }

    //If necessary reset all ingredients to 0
    public void resetAllIngredients()
    {
        calories = 0;
        transFat = 0;
        satFat = 0;
        cholestrol = 0;
        sodium = 0;
        iron = 0;
        iodine = 0;
        selenium = 0;
        manganese = 0;
        vitaminA = 0;
        vitaminC = 0;
        vitaminK = 0;
        riboflavin = 0;
        niacin = 0;
        vitaminB6 = 0;
        folicAcid = 0;
        vitaminB12 = 0;
        sugar = 0;
        fat = 0;
        price = 0;
    }

    //According to the nutrition facts, adjusts the values based on sliders to ensure proper dispense
    public void adjustValues(boolean hasChocolate, boolean hasMultivitamin) {

        int placeHolderProtein = proteinValue;
        int placeHolderCarbs = carbsValue;
        int placeHolderFiber = fiberValue;
        int placeHolderPotassium = potassiumValue;
        int placeHolderCalcium = calciumValue;

        if (hasMultivitamin) {
            proteinValue = proteinValue + 4;

            fiberValue = fiberValue + 8;

            carbsValue = carbsValue + 1;

            potassiumValue = potassiumValue + 4;

            calciumValue = calciumValue + 3;

            calories = calories + 30;

            fat = fat + 2;

            iron = iron + 11;

            iodine = iodine + 6;

            selenium = selenium + 6;

            manganese = manganese + 12;

            vitaminA = manganese + 99;

            vitaminC = vitaminC + 47;

            vitaminK = vitaminK + 49;

            riboflavin = riboflavin + 22;

            niacin = niacin + 3;

            vitaminB6 = vitaminB6 + 3;

            folicAcid = folicAcid + 13;

            vitaminB12 = vitaminB12 + 23;

            price += 0.28;
        }

        if (hasChocolate) {
            fiberValue = fiberValue + 8;

            carbsValue = carbsValue + 1;

            calories = calories + 10;

            fat = fat + 1;

            iron = iron + 2;

            price += 0.07;
        }

        if (proteinValue > 0) {
            carbsValue = placeHolderCarbs + (placeHolderProtein / 48) * 1;

            calciumValue = placeHolderCalcium + (placeHolderProtein / 48) * 8;

            calories = (int) (calories + (double) (placeHolderProtein / 48) * 120);

            fat = (int) (fat + (double) (placeHolderProtein / 48) * 2);

            price += 0.02*placeHolderProtein;
        }

        if (carbsValue > 0) {
            calories = (int) (calories + (double) (placeHolderCarbs / 21) * 250);

            sodium = sodium + 2;

            sugar = (int) (sugar + (double) (placeHolderCarbs / 21) * 2);

            price += 0.02*placeHolderCarbs;
        }

        if (fiberValue > 0) {
            carbsValue = carbsValue + (placeHolderFiber / 12) * 1;

            calories = (int) (calories + (double) (placeHolderCarbs / 12) * 15);

            price += 0.01*placeHolderFiber;
        }

        if (potassiumValue > 0) {
            calories = (int) (calories + (double) (placeHolderPotassium / 10) * 324);

            price += (0.0015)*potassiumValue;
        }
        if (calciumValue > 0) {
            calories = (int) (calories + (double) (placeHolderCalcium / 60) * 227);

            price += 0.0004*calciumValue;
        }
    }

    //information to send to SummaryActivity and display on the screen.
    public Intent putAllIngredientsToSend(Intent toSend) {
        toSend.putExtra("calories", calories);
        toSend.putExtra("transFat", transFat);
        toSend.putExtra("fat", fat);
        toSend.putExtra("satFat", satFat);
        toSend.putExtra("sodium", sodium);
        toSend.putExtra("iron", iron);
        toSend.putExtra("iodine", iodine);
        toSend.putExtra("selenium", selenium);
        toSend.putExtra("manganese", manganese);
        toSend.putExtra("vitaminA", vitaminA);
        toSend.putExtra("vitaminC", vitaminC);
        toSend.putExtra("vitaminK", vitaminK);
        toSend.putExtra("riboflavin", riboflavin);
        toSend.putExtra("niacin", niacin);
        toSend.putExtra("vitaminB6", vitaminB6);
        toSend.putExtra("folicAcid", folicAcid);
        toSend.putExtra("vitaminB12", vitaminB12);
        toSend.putExtra("sugar", sugar);
        toSend.putExtra("protein", proteinValue);
        toSend.putExtra("carbohydrates", carbsValue);
        toSend.putExtra("fiber", fiberValue);
        toSend.putExtra("potassium", potassiumValue);
        toSend.putExtra("calcium", calciumValue);
        toSend.putExtra("drinkSize", drinkAmountValue);
        toSend.putExtra("price", price);

        return toSend;
    }

    //Creates a summary of order to also send to SummaryActivity
    private String createOrderSummary(boolean addChocolateFlavor, boolean addVanillaFlavor) {
        String priceMessage = "ORDER SUMMARY (based on daily value)";

        priceMessage += "\nDrink Amount: " + drinkAmountValue + "oz";

        priceMessage += "\nAdd Chocolate? " + addChocolateFlavor;
        priceMessage += "\nAdd Multivitamin? " + addVanillaFlavor;

        priceMessage += "\nProtein: " + proteinValue + "%";
        priceMessage += "\nCarbohydrates: " + carbsValue + "%";
        priceMessage += "\nPotassium: " + potassiumValue + "%";

        priceMessage += "\nFiber: " + fiberValue + "%";
        priceMessage += "\nCalcium: " + calciumValue + "%";

        return priceMessage;
    }

    //Initializes bluetooth to connect to the arduino for dispensing of powders.
    public void bluetoothMethod(View view) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(MainActivity.this, "Your phone does not support bluetooth.", Toast.LENGTH_SHORT).show();
        }

        if (!mBluetoothAdapter.isEnabled()) {

            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    //The following initializes the sliders to begin on startup.
    public void drinkAmountSliderStart() {
        drinkAmountSlider = (SeekBar) findViewById(R.id.drinkAmountSlider);
        drinkAmountSliderText = (TextView) findViewById(R.id.drinkAmountText);
        drinkAmountSliderText.setText("Drink Amount: " + 8 + " oz.");

        drinkAmountSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        drinkAmountValue = (int) ((int) (progress * 6.0 / 100) * 4.0 + 8.0);
                        drinkAmountSliderText.setText("Drink Amount: " + drinkAmountValue + " oz.");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void carbohydrateSliderStart() {
        carbohydrateSlider = (SeekBar) findViewById(R.id.carbohydrateSlider);
        carbohydrateSliderText = (TextView) findViewById(R.id.carbohydrateText);
        carbohydrateSliderText.setText("Carbohydrates: " + carbohydrateSlider.getProgress() + "%");

        carbohydrateSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        carbsValue = progress;
                        carbohydrateSliderText.setText("Carbohydrates: " + carbsValue + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void potassiumSliderStart() {
        potassiumSlider = (SeekBar) findViewById(R.id.potassiumSlider);
        potassiumSliderText = (TextView) findViewById(R.id.potassiumText);
        potassiumSliderText.setText("\nPotassium: " + potassiumSlider.getProgress() + "%");

        potassiumSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        potassiumValue = progress / 2;
                        potassiumSliderText.setText("\nPotassium: " + potassiumValue + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void proteinSliderStart() {
        proteinSlider = (SeekBar) findViewById(R.id.proteinSlider);
        proteinSliderText = (TextView) findViewById(R.id.proteinText);
        proteinSliderText.setText("Protein: " + proteinSlider.getProgress() + "%");

        proteinSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        proteinValue = progress;
                        proteinSliderText.setText("Protein: " + proteinValue + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void fiberSliderStart() {
        fiberSlider = (SeekBar) findViewById(R.id.fiberSlider);
        fiberSliderText = (TextView) findViewById(R.id.fiberText);
        fiberSliderText.setText("\nFiber: " + fiberSlider.getProgress() + "%");

        fiberSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        fiberValue = progress;
                        fiberSliderText.setText("\nFiber: " + fiberValue + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void calciumSliderStart() {
        calciumSlider = (SeekBar) findViewById(R.id.calciumSlider);
        calciumSliderText = (TextView) findViewById(R.id.calciumText);
        calciumSliderText.setText("\nCalcium: " + calciumSlider.getProgress() + "%");

        calciumSlider.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        calciumValue = progress;
                        calciumSliderText.setText("\nCalcium: " + calciumValue + "%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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