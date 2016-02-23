char androidNutrientInput = 'b'; //some code to get input from the arduino - consider hooking up to raspberry pi and use ethernet

int androidNutrientGrams = 5; //some code to get input value from the arduino

void setup()
{
  // initialize serial communication at 9600 bps:
  Serial.begin(9600);
  // initialize the pins for nutrients:
  for (int thisPin = 0; thisPin < 9; thisPin++)
  {
    pinMode(thisPin, OUTPUT);
  }
}

void loop()
{
  //checks the cases to see which nutrient to pour
  switch (androidNutrientInput)
  {
      case 'a':
        Nutrient1(androidNutrientGrams);
        break;
      case 'b':
        Nutrient2(androidNutrientGrams);
        break;
      case 'c':
        Nutrient3(androidNutrientGrams);
        break;
      case 'd':
        Nutrient4(androidNutrientGrams);
        break;
      case 'e':
        Nutrient5(androidNutrientGrams);
        break;
      case 'f':
        Nutrient6(androidNutrientGrams);
        break;
      case 'g':
        Nutrient7(androidNutrientGrams);
        break;
      case 'h':
        Nutrient8(androidNutrientGrams);
        break;
      case 'i':
        Nutrient9(androidNutrientGrams);
        break;
  }
  androidNutrientInput = '0'; //terminates the switch statement
}

void Nutrient1(int grams)
{
  digitalWrite(1, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(1, LOW); //turns off motor
}

void Nutrient2(int grams)
{
  digitalWrite(2, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  //digitalWrite(2, LOW); //turns off motor
}

void Nutrient3(int grams)
{
  digitalWrite(3, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(3, LOW); //turns off motor
}

void Nutrient4(int grams)
{
  digitalWrite(4, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(4, LOW); //turns off motor
}

void Nutrient5(int grams)
{
  digitalWrite(5, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(5, LOW); //turns off motor
}

void Nutrient6(int grams)
{
  digitalWrite(6, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(6, LOW); //turns off motor
}

void Nutrient7(int grams)
{
  digitalWrite(7, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(7, LOW); //turns off motor
}

void Nutrient8(int grams)
{
  digitalWrite(8, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(8, LOW); //turns off motor
}

void Nutrient9(int grams)
{
  digitalWrite(9, HIGH); //turns on the motor
  delay(grams*1000);     //delay so the motor can turn for that specified amount of time (find experimentally)
  digitalWrite(9, LOW); //turns off motor
}