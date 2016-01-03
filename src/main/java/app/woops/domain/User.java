package app.woops.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.springframework.util.StringUtils.trimWhitespace;

@Entity
@Table(name = "users") // just 'user' is not allowed in postgres
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String phone;

    @Column
    private String name;

    public User(String phone) {
        this.phone = trimWhitespace(phone);
    }

    private User() {}

    public String getPhone() {
        return phone;
    }

    public String getName() {
        if(name != null) { return name; }
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }
}
