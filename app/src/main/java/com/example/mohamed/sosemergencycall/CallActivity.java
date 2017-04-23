package com.example.mohamed.sosemergencycall;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CallActivity extends AppCompatActivity {


    TextView country;
    String path;
    List<Country> countryList;
    Country userCountry = new Country();
    Button police, fire, ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        country = (TextView) findViewById(R.id.country);
        country.setText(getIntent().getStringExtra("country"));
        path = "\\D:\\Mohamed\\Projets\\android\\SOSEmergencyCall\\emergency_numbers.txt";
      /*  CountriesBDD countriesBdd = new CountriesBDD(this);
        try {
            countryList = getAllCountries(path);
            for (int i = 0; i < countryList.size(); i++) {
                Country c = countryList.get(i);
                countriesBdd.insertCountry(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        userCountry = countriesBdd.getCountryWithName(getIntent().getStringExtra("country"));
*/
        List<Country> all = new ArrayList<>();
        all = getAllCountries();


        for (int i =0; i<all.size();i++){
            if (all.get(i).getName().toLowerCase().equals(getIntent().getStringExtra("country").toLowerCase()))
                userCountry = all.get(i);
        }

        police = (Button) findViewById(R.id.polic);
        fire = (Button) findViewById(R.id.fire);
        ambulance = (Button) findViewById(R.id.ambulance);

        final String ambulanceNumber = String.valueOf(userCountry.getAmbulance());
        final String policeNumber = String.valueOf(userCountry.getPolice());

        final String fireNumber = String.valueOf(userCountry.getFire());
        Log.d("police",policeNumber);
        Log.d("ambulance", ambulanceNumber);
        Log.d("fire", fireNumber);
        final String sos = "991";
        Log.d("sos", sos);


        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+policeNumber));
                if (ActivityCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });


        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + ambulanceNumber));
                if (ActivityCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + fireNumber));
                if (ActivityCompat.checkSelfPermission(MainActivity.getAppContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });




    }


    public static List<Country> getAllCountries(String path) throws IOException {
        Country country ;
        List<Country> countryList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                // int postion = line.indexOf(";");
                String[] parts = line.split(";");

                String name = parts[0];
                int police = Integer.parseInt(parts[1]);
                int ambulance = Integer.parseInt(parts[2]);
                int fire = Integer.parseInt(parts[3]);
                country = new Country(name, police, ambulance, fire);
                countryList.add(country);
                line = br.readLine();
            }

        }

        return countryList;
    }


    public static List<Country> getAllCountries (){
        Country country ;
        List<Country> countryList = new ArrayList<>();
        String str ="Algeria;18;14;14\n" +
                "Angola;113;112;115\n" +
                "Ascension Island;999;999;999\n" +
                "Benin;117;112;118\n" +
                "Burundi;117;112;118\n" +
                "Botswana;911;911;911\n" +
                "Burkina Faso;17;112;18\n" +
                "Cameroon;112;112;112\n" +
                "Cape Verde;132;130;131\n" +
                "Central African Republic;117;1220;118\n" +
                "Chad;17;2251;18\n" +
                "Comoros;17;772;18\n" +
                "Republic of Congo;117;0;118\n" +
                "Democratic Republic of Congo;112;0;118\n" +
                "Djibouti;17;19;18\n" +
                "Egypt;122;123;180\n" +
                "Equatorial Guinea;114;112;115\n" +
                "Eritrea;113;114;116\n" +
                "Ethiopia;911;911;911\n" +
                "Gabon;1730;1300;18\n" +
                "Gambia;117;116;188\n" +
                "Ghana;999;999;999\n" +
                "Guinea;122;442;1717\n" +
                "Guinea-Bissau;112;112;112\n" +
                "Ivory Coast;111;185;180\n" +
                "Liberia;911;911;911\n" +
                "Kenya;112;112;112\n" +
                "Libya;1515;1515;1515\n" +
                "Lesotho;123;121;122\n" +
                "Madagascar;117;124;118\n" +
                "Malawi;997;998;999\n" +
                "Mali;17;15;18\n" +
                "Mauritius;112;114;115\n" +
                "Mauritania;117;101;118\n" +
                "Mayotte;112;112;112\n" +
                "Morocco;19;15;15\n" +
                "Mozambique;119;117;198\n" +
                "Namibia;112;112;112\n" +
                "Niger;17;15;18\n" +
                "Reunion;112;112;112\n" +
                "Rwanda;112;912;112\n" +
                "Saint Helena;999;911;999\n" +
                "Sao Tome and Principe;112;112;112\n" +
                "Senegal;17;15;18\n" +
                "Seychelles;112;112;112\n" +
                "Sierra Leone;019;999;999\n" +
                "Somalia;888;999;555\n" +
                "South Africa;10111;10177;10177\n" +
                "Sudan;999;999;999\n" +
                "South Sudan;999;999;999\n" +
                "Swaziland;999;977;933\n" +
                "Tanzania;112;115;114\n" +
                "Togo;117;8200;118\n" +
                "Tristan da Cunha;999;911;999\n" +
                "Tunisia;197;190;198\n" +
                "Tunisie;197;190;198\n" +
                "Uganda;112;911;112\n" +
                "Western Sahara;150;150;150\n" +
                "Zambia;999;999;999\n" +
                "Zimbabwe;999;999;999\n" +
                "Antarctica;911;911;911\n" +
                "Abkhazia;102;103;101\n" +
                "Afghanistan;119;112;119\n" +
                "Akrotiri and Dhekelia;112;112;112\n" +
                "Bahrain;999;999;999\n" +
                "Bangladesh;999;199;199\n" +
                "Bhutan;113;112;110\n" +
                "British Indian Ocean Territory;112;112;112\n" +
                "Brunei;993;991;995\n" +
                "Cambodia;117;119;118\n" +
                "China;110;120;119\n" +
                "Christmas Island;000;000;000\n" +
                "East Timor;112;112;112\n" +
                "Hong Kong;999;999;999\n" +
                "India;100;102;101\n" +
                "Indonesia;110;118;113\n" +
                "Iran;110;115;125\n" +
                "Traq;112;112;112\n" +
                "Japan;110;119;119\n" +
                "Jordan;911;911;911\n" +
                "Kazakhstan;112;112;112\n" +
                "Kyrgyzstan;102;103;101\n" +
                "North Korea;119;119;119\n" +
                "South Korea;112;119;119\n" +
                "Kurdistan;112;112;112\n" +
                "Kuwait;112;112;112\n" +
                "Laos;191;195;190\n" +
                "Lebanon;112;140;175\n" +
                "Macau;99;999;999\n" +
                "Maldives;119;102;118\n" +
                "Malaysia;999;999;999\n" +
                "Mongolia;105;105;105\n" +
                "Nepal;100;102;101\n" +
                "Oman;999;999;999\n" +
                "Pakistan;15;115;16\n" +
                "Philippines;911;911;911\n" +
                "Qatar;999;999;999\n" +
                "Saudi Arabia;112;112;112\n" +
                "Singapore;999;995;995\n" +
                "Sri Lanka;119;110;110\n" +
                "Syria;112;110;113\n" +
                "Taiwan;110;119;119\n" +
                "Tajikstan;112;112;112\n" +
                "Thailand;191;1669;199\n" +
                "Tukmenistan;112;112;112\n" +
                "United Arab Emirates;112;112;112\n" +
                "Uzbekistan;112;112;112\n" +
                "Vietnam;113;115;114\n" +
                "Yemen;194;191;191\n" +
                "Palestine;100;101;102\n" +
                "Albania;112;112;112\n" +
                "Andorra;112;112;112\n" +
                "Armenia;112;112;112\n" +
                "Austria;112;112;112\n" +
                "Azerbaijan;112;112;112\n" +
                "Belarus;102;103;101\n" +
                "Belgium;112;112;112\n" +
                "Bosnia and Herzegovina;112;112;112\n" +
                "Bulgaria;112;112;112\n" +
                "Croatia;112;112;112\n" +
                "Cyprus;112;112;112\n" +
                "Czech Republic;112;112;112\n" +
                "Denmark;112;112;112\n" +
                "Estonia;112;112;112\n" +
                "Faroe Islands;112;112;112\n" +
                "Finland;112;112;112\n" +
                "France;112;112;112\n" +
                "Georgia;112;112;112\n" +
                "Germany;112;112;112\n" +
                "Gibraltar;112;112;112\n" +
                "Greece;112;112;112\n" +
                "Greenland;112;112;112\n" +
                "Guernsey;112;112;112\n" +
                "Hungary;112;112;112\n" +
                "Iceland;112;112;112\n" +
                "Ireland;112;112;112\n" +
                "Italy;112;112;112\n" +
                "Jersey;112;112;112\n" +
                "Latvia;112;112;112\n" +
                "Lithuania;112;112;112\n" +
                "Liechtenstein;112;112;112\n" +
                "Luxembourg;112;112;112\n" +
                "Former Yugoslav Republic of Macedonia;112;112;112\n" +
                "Malta;112;112;112\n" +
                "Moldova;902;903;901\n" +
                "Monaco;112;112;112\n" +
                "Montenegro;112;112;112\n" +
                "Netherlands;112;112;112\n" +
                "Cyprus;12;112;112\n" +
                "Norway;112;113;110\n" +
                "Poland;112;112;112\n" +
                "Portugal;112;112;112\n" +
                "Romania;112;112;112\n" +
                "Russia;112;112;112\n" +
                "San Marino;112;112;112\n" +
                "Serbia;192;194;193\n" +
                "Slovakia;112;112;112\n" +
                "Spain;112;112;112\n" +
                "Sweden;112;112;112\n" +
                "Switzerland;112;112;112\n" +
                "Transnistria;102;103;101\n" +
                "Turkey;112;112;112\n" +
                "Ukraine;112;112;112\n" +
                "United Kingdom;112;112;112\n" +
                "Vatikan City;112;112;112\n" +
                "Australia;000;000;000\n" +
                "Cook Islands;999;998;996\n" +
                "Fiji;000;000;000\n" +
                "French Polynesia;112;112;112\n" +
                "Guam;911;911;911\n" +
                "Kiribati;999;999;999\n" +
                "Marshall Islands;911;911;911\n" +
                "Micronesia;911;911;911\n" +
                "Nauru;110;111;112\n" +
                "New Caledonia;112;112;112\n" +
                "New Zealand;111;111;111\n" +
                "Palau;911;911;911\n" +
                "Papua New Guinea;112;111;110\n" +
                "Samoa;999;999;999\n" +
                "Solomon Islands;911;911;911\n" +
                "Tonga;911;911;911\n" +
                "Tuvalu;911;911;911\n" +
                "Vanuatu;112;112;112\n" +
                "American Samoa;911;911;911\n" +
                "Antigua and Barbuda;911;911;911\n" +
                "Anguilla;911;911;911\n" +
                "Aruba;911;911;911\n" +
                "Belize;911;911;911\n" +
                "Bermuda;911;911;911\n" +
                "British Virgin Islands;911;911;911\n" +
                "Canada;911;911;911\n" +
                "Clipperton Island;112;112;112\n" +
                "Cuba;106;104;105\n" +
                "Curacao;106;104;105\n" +
                "Dominica;999;999;999\n" +
                "Grenada;911;911;911\n" +
                "Guadeloupe;112;112;112\n" +
                "Martinique;112;112;112\n" +
                "Mexico;112;112;112\n" +
                "Mexico;066;066;066\n" +
                "Montserrat;911;911;911\n" +
                "Navassa Island;911;911;911\n" +
                "Saint Kitts and Nevis;911;911;911\n" +
                "Saint Lucia;911;911;911\n" +
                "Saint Pierre and Miquelon;112;112;112\n" +
                "Saint Vincent and the Grenadines;911;911;911\n" +
                "United States of America;911;911;911\n" +
                "United States Virgin Islands;911;911;911\n" +
                "Barbados;911;911;911\n" +
                "The Bahamas;911;911;911\n" +
                "Bonaire;911;911;911\n" +
                "Cayman Islands;911;911;911\n" +
                "Costa Rica;911;911;911\n" +
                "Dominican Republic;911;911;911\n" +
                "Guatemala;110;128;122\n" +
                "El Salvador;911;132;913\n" +
                "Haiti;114;116;115\n" +
                "Honduras;112;195;198\n" +
                "Jamaica;119;110;110\n" +
                "Nicaragua;118;128;115\n" +
                "Panama;911;911;911\n" +
                "Puerto Rico;911;911;911\n" +
                "Trinidad and Tobago;999;990;990\n" +
                "Argentina;911;911;911\n" +
                "Bolivia;911;911;911\n" +
                "Brazil;190;192;193\n" +
                "Chile;133;131;132\n" +
                "Colombia;123;123;123\n" +
                "Ecuador;911;911;911\n" +
                "Falkland Islands;112;112;112\n" +
                "French Guiana;999;999;999\n" +
                "Paraguay;911;911;911\n" +
                "Peru;911;911;911\n" +
                "South Georgia and the South Sandwich Islands;999;999;999\n" +
                "Suriname;112;112;112\n" +
                "Uruguay;911;911;911\n" +
                "Venezuela;911;911;911";
        InputStream is = new ByteArrayInputStream(str.getBytes());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                // int postion = line.indexOf(";");
                String[] parts = line.split(";");

                String name = parts[0];
                int police = Integer.parseInt(parts[1]);
                int ambulance = Integer.parseInt(parts[2]);
                int fire = Integer.parseInt(parts[3]);
                country = new Country(name, police, ambulance, fire);
                countryList.add(country);
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return countryList;
    }
}