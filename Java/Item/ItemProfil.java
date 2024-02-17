package Java.Item;

import java.util.Date;

public class ItemProfil {

    int userId;
    String username;
    String email;
    Date birthDate;
    String image;

    public ItemProfil(int userId, String username, String email, Date birthDate, String image) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.image = image;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getImage() {
        return image;
    }
}

