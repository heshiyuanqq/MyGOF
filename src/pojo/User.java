package pojo;

import java.util.Arrays;

public class User {
	    private String id;
		private String username;
		private String password;
		private String age;
		private String address;
		private String[] hobbys;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String[] getHobbys() {
			return hobbys;
		}
		public void setHobbys(String[] hobbys) {
			this.hobbys = hobbys;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password="
					+ password + ", age=" + age + ", addres=" + address
					+ ", hobbys=" + Arrays.toString(hobbys) + "]";
		}
		
		
		
		
		
		
		
}
