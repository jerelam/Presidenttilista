package com.example.android6_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalModel {
  private List<President> presidents;
  private static final GlobalModel archive = new GlobalModel();

  public static GlobalModel getInstance(){
    return archive;
  }

  private GlobalModel(){
    presidents = new ArrayList<>();

    presidents.add(new President("Ståhlberg, Kaarlo Juho",       "1919","1925","Eka presidentti"));
    presidents.add(new President("Relander, Lauri Kristian",     "1925","1931","Toka presidentti"));
    presidents.add(new President("Svinhufvud, Pehr Evind",       "1931","1937","Kolmas presidentti"));
    presidents.add(new President("Kallio, Kyösti",               "1937","1940","Neljas presidentti"));
    presidents.add(new President("Ryti, Risto Heikki",           "1940","1944","Viides presidentti"));
    presidents.add(new President("Mannerheim, Carl Gustav Emil", "1944","1946","Kuudes presidentti"));
    presidents.add(new President("Paasikivi, Juho Kusti",        "1946","1956","Äkäinen ukko"));
    presidents.add(new President("Kekkonen, Urho Kaleva",        "1956","1982","Pelimies"));
    presidents.add(new President("Koivisto, Mauno Henrik",       "1982","1994","Manu"));
    presidents.add(new President("Ahtisaari, Martti Oiva Kalevi","1994","2000","Mahtisaari"));
    presidents.add(new President("Halonen, Tarja Kaarina",       "2000","2012","Eka naispreseidentti"));
    presidents.add(new President("Niinistö, Sauli Väinämö",      "2012","2024","Owner of Lennu, the First Dog"));
  }

  public List<President>getPresidents(){
    return presidents;
  }

  public void setPresidents(List<President> presidents){
    this.presidents = presidents;
  }

  public President getPresident(int index){
    return presidents.get(index);
  }

  public void removePresident(int index){
    presidents.remove(index);
  }

  public int movePresidentUp(int index){
    if(index+1 < presidents.size()){
      Collections.swap(presidents, index, index+1);
      index++;
    }
    return index;
  }

  public int movePresidentDown(int index){
    if(index > 0){
      Collections.swap(presidents, index, index-1);
      index--;
    }
    return index;
  }
}
