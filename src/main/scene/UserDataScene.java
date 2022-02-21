package main.scene;

public class UserDataScene extends Scene {


    //
    // toDo :: userData DB와 fetch 이후 정보 openGl 및 래스터/이미지화
    // toDo :: userData DB 구현(System IO 관련 모듈 참고)
    //
    // UserDataScene, OptionScene, MapScene
    // 3개 java 코드 모두 적용.
    //
    //

    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;


    public UserDataScene() {
        System.out.println("UserData Select/Info Window");

    }

    public void init() {

    }

    @Override
    public void update(float dt) {

        System.out.println("" + (1.0f / dt) + "FPS");
    }




}
