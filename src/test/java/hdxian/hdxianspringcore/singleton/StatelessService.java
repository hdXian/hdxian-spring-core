package hdxian.hdxianspringcore.singleton;

public class StatelessService {

    // 상태가 유지되는 필드.
    // private int price;

    // 무상태(stateless)로 설계하는 방법 예시 -> 변수 등을 지역변수로 처리한다. (테스트코드는 StateFulServiceTest에)
    public int order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        // this.price = price;
        return price;
    }

//    public int getPrice() {
//        return this.price;
//    }
}
