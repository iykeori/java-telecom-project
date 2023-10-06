package com.iyke.app.demos.Person;

import java.util.UUID;

//Java bean class for a Person
public class Person {
  
  private UUID personId;
  private String eye;
  private String nose;
  private String mouth;

  //default constructor
  public Person() {
    this(null, null, null);
  }

  public Person(String eye, String nose, String mouth) {
    this.personId = UUID.randomUUID();
    this.eye = eye;
    this.nose = nose;
    this.mouth = mouth;
  }

  /**
   * @return the eye
   */
  public String getEye() {
    return eye;
  }

  /**
   * @param eye the eye to set
   */
  public void setEye(String eye) {
    this.eye = eye;
  }

  /**
   * @return the nose
   */
  public String getNose() {
    return nose;
  }

  /**
   * @param nose the nose to set
   */
  public void setNose(String nose) {
    this.nose = nose;
  }

  /**
   * @return the mouth
   */
  public String getMouth() {
    return mouth;
  }

  /**
   * @param mouth the mouth to set
   */
  public void setMouth(String mouth) {
    this.mouth = mouth;
  }

  /**
   * @return the personId
   */
  public UUID getPersonId() {
    return personId;
  }

  /**
   * @param personId the personId to set
   */
  public void setPersonId(UUID personId) {
    this.personId = personId;
  }
}
