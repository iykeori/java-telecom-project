package com.iyke.app.demos.Person;

public class CreateAPerson {

  public void shapeNose(Person p) {
    // and start shaping some nose!
    System.out.println(p.getPersonId() + "'s nose has been shaped!");
  }
  
  public static void main(String[] args) {
    CreateAPerson c = new CreateAPerson();
    Person james = new Person();
    c.shapeNose(james);
  }
}
