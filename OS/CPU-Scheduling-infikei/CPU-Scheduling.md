# CPU 스케줄링 (CPU Scheduling)

## 1. CPU Burst VS I/O Burst

- **CPU Burst** : **일반적인 CPU 연산을 수행하는 시간**으로, 프로세스의 상태 중 **running 상태**에 해당된다.

- **I/O Burst** : I/O 요청에 의해 **I/O 작업이 끝날 때까지 waiting queue에서 기다리는 시간**으로, 프로세스의 상태 중 **waiting 상태**에 해당된다.

## 2. CPU-bound Process VS I/O-bound Process

- **CPU-bound Process** : I/O burst에 비해 **CPU burst의 비율이 높은 프로세스**를 말한다.

    - 예를 들어, 수치해석, 빅데이터 연산, 딥러닝, 데이터마이닝 등의 작업이 CPU-bound Process에 해당한다.

- **I/O-bound Process** : CPU burst에 비해 **I/O burst의 비율이 높은 프로세스**를 말한다.

    - 일상에서 사용하는 대부분의 프로그램들은 사용자와 상호작용하며 작업을 수행하므로 I/O-bound Process에 해당한다.

- CPU-bound Process는 일반적으로 연산이 오래 걸리고 사용자와 상호작용하지 않는다는 특징을 가진다. 반면 I/O-bound Process는 사용자와 상호작용하며 짧게 짧게 동작한다는 특징을 가진다.

- 따라서 사용자 입장에서는 CPU-bound Process보다 I/O-bound Process를 더 중요하게 여기게 된다.

- 즉, time-sharing으로 자원을 공유하여 사용하는 환경에서 스케줄링의 우선순위를 따져봤을 때, CPU-bound Process의 우선순위보다는 **응답성이 중요한 I/O bound Process의 우선순위가 일반적으로 더 높다.**

## 3. Dispatch, Dispatch Latency

### (1) Dispatch

- 스케줄러에 의해 선정된 프로세스의 PCB에 저장되어 있는 register 값들을 CPU의 register로 load하는 과정을 의미한다.

- 즉, CPU에서 내려가기 이전의 상태를 불러와서 복구하는 과정이다.

- Dispatch 과정을 수행하는 주체를 dispatcher라고 한다.

- Dispatch 과정은 context switching의 overhead 중 하나가 된다.

### (2) Dispatch Latency

- 이전 프로세스가 수행을 멈춘 시점으로부터 다음으로 올라온 프로세스의 수행이 시작되는 시점까지의 시간을 의미한다.

- 즉, context switching이 일어날 때 운영체제가 개입하는 시간이다.

- 이전 프로세스의 상태를 PCB에 저장하고 다음에 올라올 프로세스의 PCB에 저장되어 있는 상태를 CPU의 register로 복구하는 과정을 포함한다.

### (3) Scheduling VS Dispatch

- 스케줄링은 스케줄러가 ready queue의 프로세스들 중에서 하나의 프로세스를 선택하는 과정을 의미한다. 이렇게 스케줄러에 의해 선정된 프로세스를 불러와서 PCB에 저장되어 있던 이전 상태를 복구하는 과정이 dispatch 과정이다.

- 두 개념은 엄밀하게는 다른 의미이지만, 넓은 의미에서 이 과정을 모두 포함하여 스케줄링이라고 하기도 한다.

## 4. Preemptive Scheduling VS Non-preemptive Scheduling

### (1) 선점형 스케줄링 (Preemptive Scheduling)

- 현재 CPU에서 수행되고 있는 프로세스보다 높은 우선순위를 가지는 프로세스가 ready queue에 들어온 상황에서, 현재 수행 중인 프로세스를 바로 ready 상태로 전환하여 CPU에서 내리고 우선순위가 높은 프로세스를 수행하는 방식을 말한다.

### (2) 비선점형 스케줄링 (Non-preemptive Scheduling)

- 현재 CPU에서 수행되고 있는 프로세스보다 높은 우선순위를 가지는 프로세스가 ready queue에 들어온 상황에서, 현재 수행 중인 프로세스를 바로 CPU에서 내리지 않고 timer interrupt나 I/O 요청 등이 일어나지 않을 때까지 계속 작업을 수행할 수 있도록 하는 방식을 말한다.

- Windows 3.1이나 스티브 잡스 복귀 이전의 Mac OS에서는 비선점형 스케줄링 방식을 사용했다.

- 그러나 **현재 존재하는 대부분의 운영체제에서는 선점형 스케줄링 방식을 채택**하고 있다.

- 현실 세계에서는 비선점형 방식이 더 자연스럽지만, 컴퓨터의 동작에서는 그렇지 않다.

- time sharing과 multi-processing으로 동작하는 현재의 환경에서는 **대부분의 프로세스들이 I/O-bound process**이다. 따라서 **응답 시간이 매우 중요해졌기 때문에 선점형 스케줄링 방식을 채택**하게 되었다.

## 5. 스케줄링의 5가지 기준

1. CPU 사용률 (CPU utilization)

    - CPU를 놀게 하지 않고 최대한으로 사용하도록 하는 것이 중요하다.

2. 처리량 (Throughput)

    - 단위 시간당 처리량을 의미한다.

    - 단위 시간동안 동작을 완료하는 프로세스들의 수를 의미하며 높을수록 좋다.

3. Turnaround Time

    - 특정 프로세스가 시작되어서 끝날 때까지의 시간을 의미한다.

    - 즉, 프로세스가 메모리에 load되었을 때부터 완전히 종료될 때까지의 시간을 의미한다.

4. Waiting Time

    - 프로세스가 ready queue에서 기다리는 시간을 의미한다.

    - 즉, 프로세스가 ready 상태가 된 이후부터 running 상태가 되기 이전까지의 시간을 의미한다.

    - 이름은 Waiting Time이지만 waiting 상태를 말하는 것이 아니라는 점에 주의해야 한다.

5. Response Time

    - 사용자에게 보여지는 반응 시간을 의미한다.

    - 요청이 들어온 시점부터 요청에 대한 첫번째 응답이 이루어지는 시점까지의 시간이다.

- CPU 사용률과 처리량이 높아지기 위해서는 context switching이 덜 자주 일어나야 한다. 따라서 CPU-bound Process는 context switching이 덜 자주 일어나는 것이 유리할 것이다.

- 반면, Turnaround Time, Waiting Time, Response Time이 짧아지기 위해서는 context switching이 더 자주 일어나야 한다. 따라서 I/O-bound Process는 context switching이 더 자주 일어나는 것이 유리할 것이다.

- 이와 같이 CPU 사용률, 처리량이 높아지는 것과 Turnaround Time, Waiting Time, Response Time이 짧아지는 것은 서로 양립할 수 없는 관계(trade-off 관계)에 있다. 따라서 CPU 사용률, 처리량은 더 높이고 Turnaround Time, Waiting Time, Response Time은 더 짧아질 수 있도록 최적화하는 것을 목표로 삼아야 한다.

## 6. 스케줄링의 목표

- 모든 시스템에 대해서 해당되는 목표는 Fairness와 Balance이다.

    - **공정성 (Fairness)** : 각각의 프로세스가 CPU 자원을 공정하게 사용하는 것이다. 즉 **모든 프로세스가 골고루 수행될 수 있도록** 하는 것을 의미한다.

    - **균형 (Balance)** : 시스템의 전체적인 성능이 떨어지지 않도록 **시스템 자원이 충분히 활용될 수 있게** 하는 것을 의미한다.

- 시스템의 종류에 따라 스케줄링의 목표가 달라질 수 있다.

    - Batch System에서는 scheduling이나 context switching이라는 개념이 존재하지 않는다. 따라서 처리량(throughput)을 높이는 것이 제일 중요했으며, CPU 사용률(CPU utilization)을 100%에 가깝게 유지하는 것이 중요했다.

    - 우리는 일반적으로 time sharing이 되고 multi-processing을 지원하는 interactive system을 사용한다. Interactive System은 말 그대로 사용자와 상호작용하는 시스템이므로 사용자 경험이 중요할 수 밖에 없다. 따라서 Interactive System에서는 response time을 최소화하는 것이 가장 중요하며, waiting time을 줄이는 것 역시 중요한 목표가 된다.

    - Real-time System에서는 정해진 기한(deadline)을 넘기지 않도록, 즉 deadline을 지키도록 하는 것이 제일 중요하다. 이러한 deadline을 잘 지키기 위해서는 process가 수행될 시간을 예측하는 것이 중요한데, 사실 이렇게 예측하는 것은 현실적으로 불가능에 가깝다.

- 또한 스케줄링에서는 **Starvation이 일어나지 않도록 방지해야 한다.**

    - Starvation은 해석하면 기아, 굶주림이라는 뜻으로, **스케줄링 과정에서 계속 선택되지 않아서 프로세스가 아무리 기다리더라도 실행되지 않는 상황**을 의미한다.

    - 스케줄링을 잘못 설정하게 되면 starvation이 일어나게 되므로, starvation이 발생하지 않도록 적절한 스케줄링 방식을 선택해야 한다.

    - **Starvation이 발생한다는 것은 곧 fairness가 보장되지 않는다는 것**과 동일하다. 즉, starvation이 발생하지 않도록 fairness를 고려하면서 적절한 스케줄링 알고리즘을 설계해야 한다.

## 7. 스케줄링 알고리즘

### (1) FCFS Scheduling (First-Come First-Served)

- Ready queue에 먼저 들어온 프로세스를 먼저 수행하도록 하는 방식이다. 즉, ready queue에 들어온 순서가 곧 스케줄링의 순서이다.

- 현실 세계의 방식과 가장 비슷한 방식이며, 기다리다보면 언젠가는 CPU에 올라가게 되므로 starvation이 발생하지 않고 fairness를 가장 잘 보장해준다는 장점이 있다.

- 수행 시간이 긴 프로세스가 먼저 실행되는 상황에서는 average waiting time이 상당히 길어지게 된다는 문제가 생긴다. 즉, 프로세스가 수행되는 순서에 따라 scheduling 성능이 엄청나게 달라지게 되므로 balance가 아주 안 좋다는 문제가 있으며, 이는 I/O와 CPU에도 좋지 않은 영향을 미치게 된다.

- 이러한 문제를 해결하기 위해서는 수행 시간이 짧은 프로세스가 먼저 수행될 수 있도록 하면 되는데, 바로 다음에 설명할 SJF 방식이다.

### (2) SJF Scheduling (Shortest Job First)

- 수행시간이 짧은 프로세스를 먼저 수행되도록 하는 방식이다. Average waiting time의 기준에서 최적의 방식이라는 장점이 있다.

- 그러나 실제로 이러한 수행시간, 즉 burst time을 예측하는 것은 불가능에 가까운 영역이기 때문에, SJF schduling 방식은 현실적으로는 어려운 이상적인 방식이라는 첫번째 문제가 있다. (아무리 좋은 예측 모델을 쓰더라도 변수가 너무 많기 때문에 예측이 거의 불가능하다.)

- 또한 수행시간이 긴 process는 우선순위에서 계속 밀리게 될 수 있으므로 starvation이 일어날 가능성이 높다는 두번째 문제가 있다.

### (2-1) SRTF Scheduling (Shortest Remaining Time First)

- Preemptive한 SJF 방식이라고 볼 수 있으며, remaining time이 가장 적게 남은 프로세스를 먼저 수행하는 방식이다.

### (3) Priority Scheduling

- 우선순위를 기반으로 하는 스케줄링 기법이다.

- 각각의 프로세스마다 우선순위를 부여해주고 우선순위가 높은 프로세스를 가장 먼저 처리되도록 하는 방식이다. 이를 위해 각각의 프로세스마다 정수 값을 부여해준다.

- 시스템이 어떠한 목적으로 설계되었는지에 따라 우선순위의 조정이 가능하다. 즉 우선순위를 시스템에 맞게 적용할 수 있으므로 flexible하다는 특징이 있다.

- 상황에 따라 우선순위를 다르게 할 수 있으므로 유연한 대처가 가능해진다는 장점이 있다.

- 그러나 priority scheduling은 fairness를 가장 보장해주지 못하는 방식이므로 starvation이 발생할 수 있다는 문제가 있다. 우선순위가 높은 프로세스가 끊임없이 ready queue에 추가된다면 우선순위가 낮은 프로세스는 계속 밀려서 영원히 실행되지 않게 되기 때문이다.

- 이러한 문제를 해결하기 위해 aging이라는 개념이 추가되었는데, waiting time이 길어질수록 해당 프로세스의 우선순위를 조금씩 올려주는 것이다. 이러한 aging을 통해 starvation 문제를 방지할 수 있게 된다.

- 우선순위를 계속 계산해야 하고 aging 연산도 계속 수행해야 하므로 복잡도가 엄청 증가하게 된다는 문제가 있다.

- starvation이 발생할 수 있다는 문제가 있는데, 사실 fairness를 보장할 수 없는 특성 때문에 starvation이 발생하게 되는 것이다.

### (4) Round Robin (RR) Scheduling

- 각각의 프로세스들이 시간을 정해놓고 돌아가면서 수행될 수 있도록 하는 방식이다.

- 이 정해져있는 시간을 time quantum이라고 하며, time interrupt에 설정된 시간이 바로 time quantum이다.

- 즉 time quantum은 process가 CPU를 점유해서 사용할 수 있는 최대 시간을 의미한다. (여기서 최대 시간이라고 말하는 이유는 process가 I/O 요청이나 다른 interrupt 등으로 인해 최대 시간이 되기 전에 CPU에서 내려올 수도 있기 때문이다.)

- Time quantum 개념을 도입하면서 RR 스케줄링은 scheduling의 복잡도를 낮추면서도 starvation을 방지할 수 있게 되었고, 이로 인해 RR 스케줄링은 가장 핵심적이면서 기본적인 알고리즘이 되었다.

- Time quantum을 너무 길게 잡게 되면 FCFS 방식과 같아지게 되고, 반대로 time quantum을 너무 짧게 잡으면 context switching이 너무 자주 일어나게 되므로 scheduling overhead가 엄청 커지게 된다.

- 따라서 RR 스케줄링에서는 적당한 time quantum을 설정하는 것이 매우 중요하다. 보통은 CPU burst time의 평균 정도로 time quantum을 정하게 되며, 보통 10ms~100ms 정도로 설정한다고 한다.

- SJF 방식과 비교했을 때 average turnaround time이 길어지는 반면, 응답성은 좋아져서 response time이 짧아진다는 장점이 있다. 우리가 사용하는 운영체제에서도 이러한 RR 방식이 기반이 된다.

- Time quantum을 잘못 설정하게 되면 문제가 생기게 된다. 따라서 적절한 time quantum을 어떻게 설정한 것인지에 관한 문제가 있을 수 밖에 없다. 이와 관련하여 엄지의 법칙(rule of thumb)이라는 것이 있는데, 전체 CPU burst time의 80% 정도를 time quantum으로 두는 것이 가장 좋다는 법칙이다.

### (5) Multilevel Queue Scheduling

- 프로세스의 성격에 따라 구분하여 각각의 queue를 두고 스케줄링 알고리즘을 다르게 적용하는 스케줄링 방식이다.

- 사용자와 interactive한 특징을 지니고 있는 foreground process들은 RR 방식을 적용하고, CPU burst time이 긴 편인 background process들은 FCFS 방식을 적용하는 것이다.

- 여기서 foreground process들만 실행되거나 background process들만 실행되어 starvation이 발생하는 것도 방지해야 하므로, foreground와 background의 시간도 적절히 분할해주는 것이 필요하다. 보통 전체 시간의 80%는 foreground가, 20%는 background가 동작할 수 있도록 한다.

- 그런데 이렇게 프로세스를 구분하는 기준을 kernel 개발자가 정해야 하므로, 사람이 분류를 해야하다보니 실수가 있을 수 있다는 문제가 있다. 이를 개선하기 위해서 다음의 multilevel feedback queue scheduling이 나오게 되었다.

### (6) Multilevel Feedback Queue Scheduling

- 정해진 time quantum 시간을 다 채우지 못하고 CPU에서 내려오는 프로세스들의 경우 I/O-bound process일 가능성이 높고, 반대로 정해진 time quantum 시간을 다 채우고 나서 CPU에서 내려오는 프로세스들은 CPU-bound process일 가능성이 높다는 점에 착안한 스케줄링 방식이다.

- 정해진 time quantum을 다 채운 프로세스들은 time quantum이 더 긴 queue에 넣음으로써 time quantum을 각각 다르게 설정할 수 있도록 하는 방식이다.

- 이 방식은 실제로도 사용하고 있는 스케줄링 방식이며, CPU에 올라갔다가 내려오기까지의 시간을 기준으로 프로세스들을 분류하는 것이므로 우선순위가 동적으로 바뀐다는 특징이 있다.

- Response time이 짧아지고 starvation이 발생하지 않는다는 장점이 있다.
