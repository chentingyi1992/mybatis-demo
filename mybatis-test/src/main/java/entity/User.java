package entity;

/**
 * @author ：Mr.chen
 * @date ：Created in 2022/4/2 15:17
 * @description：
 * @modified By：
 * @version: $
 */
public class User {

    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
