package hdxian.hdxianspringcore.order;

public interface OrderService {
    // 회원 id, 상품 이름, 가격을 전달받아 주문(Order)을 리턴함.
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
