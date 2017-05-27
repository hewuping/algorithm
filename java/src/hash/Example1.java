package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Zero
 *         Created on 2017/5/10.
 */
public class Example1 {

    public static void main(String[] args) {

        User user1 = new User();
        User user2= new User();
        user1.equals(user2);

        Map<User, String> map = new HashMap<>();
        map.put(user1, "");
    }

    private static class User{
        String username;
        int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(username, user.username);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, age);
        }
    }
}
