package BankOne.beans;

import java.util.Date;

public class Employeer {
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String position;
    private  static Employeer instance;
    public Employeer() {
    }

    public String getName() {
        return name;
    }

    public Employeer(String name, String surname, Date dateOfBirth, String position) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public static Employeer getInstance(){
        if (instance == null)
        {
            //synchronized block to remove overhead
            synchronized (Employeer.class)
            {
                if(instance==null)
                {
                    // if instance is null, initialize
                    instance = new Employeer();
                }

            }
        }
        return instance;
    }
}
