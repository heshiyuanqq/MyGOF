package gof.canNotChange;



/**
 * a. 去除所有setter方法以及可以修改自身属性的方法；

b. 将所有属性设置为private的，并用final标记，确保其不可修改；

c. 确保没有子类可以继承该类；

d. 有一个可以创建完整对象的构造函数。
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class Demo {
       private String name;
       private String address;
       private int age;
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getAge() {
		return age;
	}
       
    public static void main(String[] args) {
		Demo demo = new Demo();
		demo.name="11111";
		System.out.println(demo.getName());
	}
       
       
}
