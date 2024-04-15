package hdxian.hdxianspringcore.singleton;

public class SingletonService {

    // 관례상 싱글톤 인스턴스 이름은 instance.
    // static으로 선언 -> 클래스 레벨에서 로드됨.
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
        // 생성자를 private로 선언해서 외부에서 SingletonService 객체를 생성하지 못하도록 한다.
    }

    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("singletonService의 로직 실행");
    }

}
