# 커맨드 패턴 (Command Pattern)

## 1. 커맨드 패턴

- 커맨드 패턴은 객체의 행위(메서드)를 클래스로 만들어서 캡슐화하는 패턴이다.
- 실행할 기능을 캡슐화함으로써 주어진 여러 기능을 실행할 수 있는 클래스를 설계하는 패턴이다. 이를 통해 클래스의 재사용성을 높일 수 있다.
- 서로 다른 사용자나 시간, 프로젝트에 따라 요청이 달라지는 경우에 유용하다. 이러한 요청을 객체로 만들면, 요청을 큐에 저장하거나 로그로 기록하고 재실행할 수 있다.
- 행동 디자인 패턴에 속한다.

### (1) 커맨드 패턴의 구조

- `Command` 인터페이스 : `execute()` 메서드를 선언하여 `Receiver` 에서 수행할 연산을 구체화하는 방법을 정의한다.
- `ConcreteCommand` 클래스 : `Command` 인터페이스를 구현하고, 이를 통해 `Receiver` 클래스의 함수를 호출한다. 이 함수 호출은 일반적으로 `execute()` 메서드에서 수행한다.
- `Receiver` 클래스 : 요청을 수행하는 데 필요한 실제 작업을 구현한 클래스이다
- `Invoker` 클래스 : 사용자의 요청을 `Command` 객체로 변환하며, 이 객체를 저장하고 실행하는 역할을 수행한다.

### (2) 커맨드 패턴의 장단점

- 장점
    - 요청 발신자와 수신자 사이의 결합도를 낮출 수 있다. 즉, 요청에 대해 발신자와 수신자 사이에 직접적인 연결이 없다.
    - 실행할 기능을 캡슐화함으로써 예제 코드에서 `Button` 클래스를 수정하지 않고 그대로 사용할 수 있다. 즉, SOLID 원칙의 OCP를 지킬 수 있다.
    - 요청을 쉽게 추가하거나 수정할 수 있다. 즉, 새로운 요청은 `Command` 인터페이스를 구현하는 새로운 클래스를 만드는 것으로 추가할 수 있다.
    - 요청을 저장하거나, 로그를 남기는 등의 작업을 쉽게 수행할 수 있다.
    - 실행 취소와 재실행 같은 기능을 제공하기가 수월하다.
- 단점
    - 각각의 개별 요청에 대해 `Command` 를 만들어야 하기 때문에, 클래스가 많아질 수 있다.
    - 고수준의 코드 복잡성이 증가할 수 있다.

## 2. 예제 코드

### (1) 커맨드 패턴을 적용하지 않은 코드

```java
public class Alarm {
    public void start() {
        System.out.println("Alarm!!");
    }
}

public class Lamp {
    public void turnOn() {
        System.out.println("Lamp is ON!");
    }

    public void turnOff() {
        System.out.println("Lamp is OFF!");
    }
}
```

```java
enum Mode { ALARM_START, LAMP_ON, LAMP_OFF };

public class Button {
    private Alarm alarm;
    private Lamp lamp;
    private Mode mode;

    public Button(Alarm alarm, Lamp lamp) {
        this.alarm = alarm;
        this.lamp = lamp;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void press() {
        switch (mode) {
            case ALARM_START:
                alarm.start();
                break;
            case LAMP_ON:
                lamp.turnOn();
                break;
            case LAMP_OFF:
                lamp.turnOff();
                break;
        }
    }
}
```

```java
public class Client {
    public static void main(String[] args) {
        Alarm alarm = new Alarm();
        Lamp lamp = new Lamp();

        Button button = new Button(alarm, lamp);
        button.setMode(Mode.LAMP_ON);
        button.press(); // Lamp is ON!

        button.setMode(Mode.LAMP_OFF);
        button.press(); // Lamp is OFF!

        button.setMode(Mode.ALARM_START);
        button.press(); // Alarm!!
    }
}
```

- 이렇게 하면 필요한 기능을 새로 추가하거나 수정할 때마다 `Button` 클래스의 코드도 수정해야 하므로, 재사용이 어려워지고 SOLID 원칙의 OCP에 위배된다.

### (2) 커맨드 패턴을 적용한 코드

- `Command` 인터페이스
    
    ```java
    // Command Interface
    public interface Command {
        void execute();
    }
    ```
    
- `ConcreteCommand` 클래스 ( `Command` 인터페이스를 구현)
    
    ```java
    // Concrete Commands
    public class AlarmStartCommand implements Command {
        private Alarm alarm;
    
        public AlarmStartCommand(Alarm alarm) {
            this.alarm = alarm;
        }
    
        @Override
        public void execute() {
            alarm.start();
        }
    }
    
    public class LampOnCommand implements Command {
        private Lamp lamp;
    
        public LampOnCommand(Lamp lamp) {
            this.lamp = lamp;
        }
    
        @Override
        public void execute() {
            lamp.turnOn();
        }
    }
    
    public class LampOffCommand implements Command {
        private Lamp lamp;
    
        public LampOffCommand(Lamp lamp) {
            this.lamp = lamp;
        }
    
        @Override
        public void execute() {
            lamp.turnOff();
        }
    }
    ```
    
- `Receiver` 클래스
    
    ```java
    // Receivers
    public class Alarm {
        public void start() {
            System.out.println("Alarm!!");
        }
    }
    
    public class Lamp {
        public void turnOn() {
            System.out.println("Lamp is ON!");
        }
    
        public void turnOff() {
            System.out.println("Lamp is OFF!");
        }
    }
    ```
    
- `Invoker` 클래스
    
    ```java
    // Invoker
    public class Button {
        private Command command;
    
        public Button(Command command) {
            this.command = command;
        }
    
        public void setCommand(Command command) {
            this.command = command;
        }
    
        public void press() {
            command.execute();
        }
    }
    ```
    
- `Client` 코드
    
    ```java
    public class Client {
        public static void main(String[] args) {
            Alarm alarm = new Alarm();
            Lamp lamp = new Lamp();
    
            Command lampOnCommand = new LampOnCommand(lamp);
            Command lampOffCommand = new LampOffCommand(lamp);
            Command alarmStartCommand = new AlarmStartCommand(alarm);
    
            Button button = new Button(lampOnCommand);
            button.press(); // Lamp is ON!
    
            button.setCommand(lampOffCommand);
            button.press(); // Lamp is OFF!
    
            button.setCommand(alarmStartCommand);
            button.press(); // Alarm!!
        }
    }
    ```
    

## 3. 내용 출처 및 참고

- https://gmlwjd9405.github.io/2018/07/07/command-pattern.html
- [https://gsbang.tistory.com/entry/Design-Pattern-Command-Pattern커맨드-패턴](https://gsbang.tistory.com/entry/Design-Pattern-Command-Pattern%EC%BB%A4%EB%A7%A8%EB%93%9C-%ED%8C%A8%ED%84%B4)
