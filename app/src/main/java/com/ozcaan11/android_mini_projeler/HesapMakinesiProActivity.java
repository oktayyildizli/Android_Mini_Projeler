package com.ozcaan11.android_mini_projeler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HesapMakinesiProActivity extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<>();
    String deger = "";
    String yeni_deger = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hesap_makinesi_pro);
    }


    public void btn_clicked(View view) {
        TextView txt_ekran = (TextView) findViewById(R.id.txt_ekran);
        Button button = (Button) view;

        deger = button.getText().toString();

        if (!deger.contains("+") && !deger.contains("-") && !deger.contains("x") && !deger.contains("/")) {
            yeni_deger += deger; //##

            /* Bu bölümü kullanmamızın sebebi örneğin biz 12 sayısını girdik diyelim
            *  Her bir sayıya tıkladığımızda onu yeni bir array elemanı olarak kaydediyor
            *  Yani [1, 12] olarak kaydediyor
            *  Bunun önüne geçmek için eğer ilk sayıdan sonra bir sayı girilirse
            *  en son girilen sayının indexini silip öncekine ekliyoruz
            *  array.size = 0 deger=yok yeni_deger=yok
            *  girilen sayı(deger) 1 --> array = [1] ve yeni_deger=1 || array.size = 1
            *  girilen sayı(deger) 9 --> array = [1, 9] ve yeni_deger=19 || array.size() = 2 şimdi size-1 yap array.size = 1 || array = [19]
            *  Her yeni sayı girildiğinde hep bu şekilde gerçekleşecek */

            if (arrayList.size() > 0) {
                arrayList.remove(arrayList.size() - 1);
            }
            arrayList.add(yeni_deger);

        } else {
            /* Burda + - x / den herhangi birisi eklendiğinde iki kere ekliyoruz çünkü
            *  bunlardan sonra bir sayı gelecek ve bir üstteki if e girecek eğer tek sembol yani bi tane + eklemiş olasaydık
            *  array.size zaten 0 dan büyük o yüzden girdiğimiz son değer yani artıyı silecek yerine yeni sayı girecek
            *  o yüzden iki kere girdik
            *  array = [12] olsun
            *  deger = + --> array = [12, +, +]
            *  eğer bundan sonra yeni sayı girilirse son artı silinecek yerine değerler gelecek */
            arrayList.add(deger);
            arrayList.add(deger);
            yeni_deger = "";
        }
        txt_ekran.setText(txt_ekran.getText().toString() + deger);
    }


    public void btn_esittir_clicked(View view) {
        TextView txt_sonuc = (TextView) findViewById(R.id.txt_sonuc);

        int sonuc = 0;
        int c = arrayList.size();

        while (c != 1) {
            if (c > 3) { // array = [44,+,10,/,2]   / işareti arrayin 3. elemanı(get(3) bunun için)... NOT:array elemanları 0 dan başlayarak indexleniyor
                /* Burda 3 ten başlamamızın sebebi ilk işlemden sonraki işlemi bulup ona göre öncelik prefixi ayarlamak
                 * Ama burda girilen ilk işlem neyse yapılmıyor bir çarpma yada bölme varsa o yapılıyor  */
                if (arrayList.get(3).contains("x") || arrayList.get(3).contains("/")) {
                    if (arrayList.get(3).contains("x")) {
                        sonuc = Integer.parseInt(arrayList.get(2)) * Integer.parseInt(arrayList.get(4));
                    }
                    if (arrayList.get(3).contains("/")) {
                        // array = [44,+,10,/,2] --> 10/2 ü ilk yapacak = 5
                        if (!arrayList.get(4).contains("0")) {
                            sonuc = Integer.parseInt(arrayList.get(2)) / Integer.parseInt(arrayList.get(4));
                        } else {
                            Toast.makeText(HesapMakinesiProActivity.this, "0 ile bölme yapılamaz !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    arrayList.remove(2); // array = [44,+,10,/,2] index = 2 yi kaldır array = [44,+,/,2]
                    arrayList.remove(2); // array = [44,+,/,] index = 2 yi kaldır array = [44,+,2]
                    arrayList.remove(2); // array = [44,+,2] index = 2 yi kaldır array = [44,+]
                    arrayList.add(2, Integer.toString(sonuc)); // index = 2 ye sonucu ata array = [44,+,5]
                    c = arrayList.size();
                } else { // index = 3 olan yerde x veya / yoksa ilk işlemi yap o zaman .. get(1) 1.index i yani +,-,x,/ kısımları
                    if (arrayList.get(1).contains("+")) {
                        sonuc = Integer.parseInt(arrayList.get(0)) + Integer.parseInt(arrayList.get(2));
                    }
                    if (arrayList.get(1).contains("-")) { // array = [34,-,3,+,4] ise önce 34-3 yap
                        sonuc = Integer.parseInt(arrayList.get(0)) - Integer.parseInt(arrayList.get(2));
                    }
                    if (arrayList.get(1).contains("x")) { // array = [12,x,3,-,7] ise önce 12x3 yap
                        sonuc = Integer.parseInt(arrayList.get(0)) * Integer.parseInt(arrayList.get(2));
                    }
                    if (arrayList.get(1).contains("/")) {
                        if (!arrayList.get(2).contains("0")) {
                            sonuc = Integer.parseInt(arrayList.get(0)) / Integer.parseInt(arrayList.get(2));
                        } else {
                            Toast.makeText(HesapMakinesiProActivity.this, "0 ile bölme yapılamaz !", Toast.LENGTH_SHORT).show();
                        }
                    }
                    arrayList.remove(0); // array = [34,-,3,+,4] index = 0 ı kaldır array = [-,3,+,4]
                    arrayList.remove(0); // array = [-,3,+,4] index = 0 ı kaldır array = [3,+,4]
                    arrayList.remove(0); // array = [3,+,4] index = 0 ı kaldır array = [+,4]
                    arrayList.add(0, Integer.toString(sonuc)); // index = 0 a sonucu ata array = [31,+,4] sonra tekrar başa dön
                    c = arrayList.size();
                }
            } else {
            /* Eğer array.size 3 ten büyük değilse yani array = [15,+,5] ise
               Normal işlemleri yap :)
            * */
                if (arrayList.get(1).contains("+")) {
                    sonuc = Integer.parseInt(arrayList.get(0)) + Integer.parseInt(arrayList.get(2));
                }
                if (arrayList.get(1).contains("-")) {
                    sonuc = Integer.parseInt(arrayList.get(0)) - Integer.parseInt(arrayList.get(2));
                }
                if (arrayList.get(1).contains("x")) {
                    sonuc = Integer.parseInt(arrayList.get(0)) * Integer.parseInt(arrayList.get(2));
                }
                if (arrayList.get(1).contains("/")) {
                    if (!arrayList.get(2).contains("0")) {
                        sonuc = Integer.parseInt(arrayList.get(0)) / Integer.parseInt(arrayList.get(2));
                    } else {
                        Toast.makeText(HesapMakinesiProActivity.this, "0 ile bölme yapılamaz !", Toast.LENGTH_SHORT).show();
                    }
                }
                arrayList.remove(0); // array = [15,+,5]  index = 0 ı kaldır array = [+,5]
                arrayList.remove(0); // array = [+,5] index = 0 ı kaldır array = [5]
                arrayList.remove(0); // array = [5] index = 0 ı kaldır array = []
                arrayList.add(0, Integer.toString(sonuc)); // index = 0 a sonucu ata array = [20]
                c = arrayList.size();
            }
        }
        txt_sonuc.setText(Integer.toString(sonuc));
    }

    public void btn_temizle_clicked(View view) {
        TextView txt_ekran = (TextView) findViewById(R.id.txt_ekran);
        TextView txt_sonuc = (TextView) findViewById(R.id.txt_sonuc);

        deger = "";
        yeni_deger = "";
        txt_ekran.setText("");
        txt_sonuc.setText("0");
        arrayList.clear();
    }

}
