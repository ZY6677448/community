package life.sbgirl.community.community.dto;

/**
 * @Author : sbgirl
 * @Date: 2020/11/16 0:58
 **/
public class GithunUser {
    private String name;
    private long  id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
