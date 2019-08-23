package io.lishman.bootdemo.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", columnDefinition = "serial")
    private Long id;

    @Column(name = "usr_first_name")
    @NotBlank(message = "first name must not be blank")
    private String firstName;

    @Column(name = "usr_last_name")
    private String lastName;

    @Column(name = "usr_user_name")
    @NotBlank(message = "{user.name.not.blank}")
    private String userName;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_phone_number")
    private String phoneNumber;

    @Column(name = "usr_age")
    @Range(min = 1, max = 120, message = "age must be between 1 and 120")
    private Integer age;

    @Column(name = "usr_website")
    private String website;

    public User() {
    }

    private User(final Long id,
                 final String firstName,
                 final String lastName,
                 final String userName,
                 final String email,
                 final String phoneNumber,
                 final Integer age,
                 final String website) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.website = website;
    }

    public static User newInstance(final Long id,
                                   final String firstName,
                                   final String lastName,
                                   final String userName,
                                   final String email,
                                   final String phoneNumber,
                                   final Integer age,
                                   final String website) {
        return new User(id, firstName, lastName, userName, email, phoneNumber, age, website);
    }

    public User cloneWithNewId(final Long id) {
        return new User(id, firstName, lastName, userName, email, phoneNumber, age, website);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public String getWebsite() {
        return website;
    }

    public String getFullName() {
        return String.join(" ", getFirstName(), getLastName());
    }

    public boolean isAdult() {
        return getAge() >= 18;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(age, that.age) &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, userName, email, phoneNumber, age, website);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", website='" + website + '\'' +
                '}';
    }
}
