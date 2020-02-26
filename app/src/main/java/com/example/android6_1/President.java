package com.example.android6_1;

public class President {
  private String name, description;
  private String startYear, endYear;

  President(String name, String startYear, String endYear, String description){
    this.name = name;
    this.startYear = startYear;
    this.endYear = endYear;
    this.description = description;
  }

  public String getName()       {return this.name;}
  public String getStartYear()  {return String.valueOf(this.startYear);}
  public String getEndYear()    {return String.valueOf(this.endYear);}
  public String getDescription(){return this.description;}

  public void setName       (String name)       {this.name = name;}
  public void setStartYear  (String startYear)  {this.startYear = startYear;}
  public void setEndYear    (String endYear)    {this.endYear = endYear;}
  public void setDescription(String description){this.description = description;}

  @Override
  public String toString(){
    return this.name;
  }
}
