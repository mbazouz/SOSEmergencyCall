package com.example.mohamed.sosemergencycall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed on 05/12/2016.
 */

public class Test {

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

    public static void main (String[] args) throws IOException {
        List<Country> all = new ArrayList<>();
        Country userCountry = new Country();
        try {
            all = getAllCountries("emergency_numbers.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i =0; i<all.size();i++){
            if (all.get(i).getName().toLowerCase().equals("Tunisie".toLowerCase()))
                userCountry = all.get(i);
        }
        System.out.println(userCountry.toString());



    }
}
