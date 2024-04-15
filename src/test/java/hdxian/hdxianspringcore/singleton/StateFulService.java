package hdxian.hdxianspringcore.singleton;

public class StateFulService {

    // 상태가 유지되는 필드.
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        // 상태가 유지되는 필드를 여러군데에서 수정할 수 있는 상황.
        // 이 클래스의 인스턴스가 싱글톤으로 관리되면, 하나의 변수를 여러군데서 수정하므로 문제가 발생할 수 있음.
        this.price = price;

    }

    public int getPrice() {
        return this.price;
    }

}
