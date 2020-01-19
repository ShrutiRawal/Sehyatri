
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

//Firebase settings
#define FIREBASE_HOST "codeutsava-15484.firebaseio.com"
#define FIREBASE_AUTH "mpDeWxLTfJ3CElZ1jX9HJh5bm7bqPeHokuD3ILmF"

//Wi-Fi settings
#define WIFI_SSID "codeutsava"
#define WIFI_PASSWORD "bsp12345"

//Define trigger and echo digital pins
const int trigPin = D0;// Connect to D2
const int echoPin = D1;// Connect to D1
float pingTime;  //time for ping to travel from sensor to target and return
float targetDistance; //Distance to Target in inches
float speedOfSound=767.6; //Speed of sound in miles per hour when temp is 77 degrees.
float targetVolume;

// The amount of time the ultrassonic wave will be travelling for
long duration = 0;
// Define the distance variable
//double distance = 0;

void setup()
{

    // Connect to Wi-Fi
    Serial.print("Wi-Fi...");
    WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
    Serial.print("Connecting...");
    while (WiFi.status() != WL_CONNECTED)
    {
        Serial.print(".");
        delay(500);
    }
    Serial.println();
    Serial.print("Connected to: ");
    Serial.println(WiFi.localIP());

    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

    // Ultrasonic sensor, set echo as Input and trigger as Output
    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);

    Serial.begin(115200);
}

void getDistance()
{
    // Clear trigPin
    digitalWrite(trigPin, LOW);
    delayMicroseconds(2000);

    // trigPin HIGH por 10ms
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(15);
    digitalWrite(trigPin, LOW);
    delayMicroseconds(10);

   pingTime = pulseIn(echoPin, HIGH);  //pingTime is presented in microceconds
  pingTime=pingTime/1000000; //convert pingTime to seconds by dividing by 1000000 (microseconds in a second)
  pingTime=pingTime/3600; //convert pingtime to hourse by dividing by 3600 (seconds in an hour)
  targetDistance= speedOfSound * pingTime;  //This will be in miles, since speed of sound was miles per hour
  targetDistance=targetDistance/2; //Remember ping travels to target and back from target, so you must divide by 2 for actual target distance.
  targetDistance= targetDistance*63360;//Convert miles to inches by multipling by 63360 (inches per mile)
  targetVolume = (28-targetDistance*2.54)*(3.14)*(10.5*10.5);
    // Sends the distance value to Firebase
    Firebase.setFloat("volume", targetVolume/1000);

}
void loop()
{

    getDistance();
    // Prints the distance value to the serial monitor
    Serial.print("Volume: ");
    Serial.println(targetVolume/1000);
  
    delay(1000);
}

