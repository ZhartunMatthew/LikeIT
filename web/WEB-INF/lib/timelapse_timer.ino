// фото таймер для съемки таймлапсов 
// http://youtu.be/rKcvwzKYLkA

#include <LiquidCrystal.h>

LiquidCrystal lcd(8, 9, 4, 5, 6, 7);

byte shotPin = 15; // пин на затвор камеры

byte key(){
 int val = analogRead(0);
  if (val < 50) return 5;
  else if (val < 150) return 3;
  else if (val < 350) return 4;
  else if (val < 500) return 2;
  else if (val < 800) return 1;
  else return 0;  
}
// --- конец функции кнопок

void startMenu(){
  byte menuPos = 1;
  char menuTxt[3][16] = {" Down to Set", "TimeLapse", "B timer"};
   do{                  
       lcd.clear();  // выводим инфу на экран
       lcd.setCursor(0, 0);
       lcd.print(menuPos);
       lcd.print(".");
       lcd.print(menuTxt[menuPos]); 
       lcd.setCursor(14, 0);
       lcd.print("<>");   
       lcd.setCursor(0, 1);
       lcd.print(menuTxt[0]);
    
       if (key() == 2) menuPos--; // если нажата назад
       else if (key() == 5) menuPos++;  // если нажата вперед  
       else if (key() == 4) { // если нажата вниз
             if (menuPos == 1) menuTimeLap();
             else if (menuPos == 2) menuB();
       }
       else if (key() == 1) analogWrite(10, 255);
       if (menuPos > 2) menuPos = 1; // проверка дабы не вылезти за меню
       if (menuPos < 1) menuPos = 2;
     delay(250);
   }while(1);
}
// --- 

void menuTimeLap() {
  float delayTime = 2600;
  float shot = 160;  
  do {
      lcd.clear();
      lcd.setCursor(0, 0); 
      lcd.print("Tm:");
      lcd.print(delayTime/1000);
      lcd.print("s|");
      lcd.print(((delayTime*shot)/1000)/60);
      lcd.print("min");
      lcd.setCursor(0, 1); 
      lcd.print("shot:");
      lcd.print(int(shot));
      lcd.print("|");
      lcd.print(shot/25);
      lcd.print("sec");      
      
    if (key() == 3) {// up 
      if (delayTime < 3500) delayTime += 100;
      else if (delayTime < 5000) delayTime += 250;
      else if (delayTime < 10000) delayTime += 500;
      else delayTime += 1000;
    } 
    else if (key() == 4) { // down  
      if (delayTime < 3500) delayTime -= 100;
      else if (delayTime < 5000) delayTime -= 250;
      else if(delayTime < 10000) delayTime -= 500;
      else delayTime -= 1000;
    } 
    else if (key() == 5) shot += 10;  // right 
    else if (key() == 2) shot -= 10; // left     
    else if (key() == 1) timeLap(delayTime, shot);
    
    if (delayTime < 1500) delayTime = 30000;
    if (delayTime > 30000) delayTime = 1500;
    if (shot < 120) shot = 1500;
    if (shot > 1500) shot = 120;
    delay(200);  
  } while(1);  
}
// 

void menuB(){ 
int BulbTimerMin = 3; // время по умолчанию
do{
    lcd.clear();
    lcd.print("BulbTimer");  
    lcd.print(" set");
    lcd.setCursor(2, 1);
    lcd.print("min:");
    lcd.print(BulbTimerMin);
      
     byte k = key();
       if (k == 3 || k == 2) BulbTimerMin--;// up         
       else if (k == 4 || k == 5) BulbTimerMin++;  // down  
       else if (k == 1)  Bulp(BulbTimerMin); // set   
       
      if (BulbTimerMin > 59) BulbTimerMin = 1; 
      if (BulbTimerMin < 1) BulbTimerMin = 59;
       
      lcd.setCursor(6, 1);
      lcd.print(BulbTimerMin);         

       delay(250);
    } while (1);
}
////////////////

void Bulp(int min1){
  digitalWrite(shotPin, HIGH); // включаем 
  
  unsigned long int previousMillis = 0;  
  int sek = 0;
  do  { 
   if (millis() - previousMillis > 1000) {   
     previousMillis = millis();   
     sek--;       
   if(sek == -1) {
     sek=59; 
     min1--; 
   }       

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("* Bulb Timer *");  
    lcd.setCursor(2, 1); 
    lcd.print(min1);
    lcd.setCursor(4, 1);
    lcd.print(":");
    lcd.print(sek);   
   }
   
  } while (sek != 0 || min1 != 0); 

 digitalWrite(shotPin, LOW); // выключаем
}
/////////////

void timeLap(unsigned int delayTime, int shot){
   unsigned long prMillis = 0;  
   float shotAll = shot; 
    
   do  {    
     if (millis() - prMillis > delayTime)  {  
       prMillis = millis();   
       shot--;
   
         lcd.clear();
         lcd.setCursor(0, 0);
         lcd.print("***TimeLapse***");
         lcd.setCursor(0, 1);  
         lcd.print(int(shotAll));
         lcd.print(" "); 
         lcd.print(shot);
         lcd.print(" ");   
         lcd.print(int(shot/shotAll*100)); // показываем проценты
         lcd.print("%"); 
   
   digitalWrite(shotPin, HIGH); // включаем 
   digitalWrite(13, HIGH);  
   delay(200);
   digitalWrite(shotPin, LOW); // выключаем 
   digitalWrite(13, LOW);  
  }       
 } while (shot != 0);
}

void setup() {
    lcd.begin(16, 2);  
    lcd.clear();
    pinMode(shotPin, OUTPUT);
    pinMode(13, OUTPUT);    
//    analogWrite(10, 50);
}

void loop() {
//timeLap(2000, 230);
  startMenu();
 
}
