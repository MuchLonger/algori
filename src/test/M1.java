import Util.Node;
import org.junit.Test;

import java.util.Optional;

/**
 * @description:
 * @Time: 2019/11/21 11:36
 */
class Car {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class UserWithOp{
    public Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
class UserWithNoOp{
    public Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
public class M1 {
    @Test
    public void m1(){
        UserWithOp user=new UserWithOp();
        Optional.ofNullable(user)
                .flatMap(u->u.getCar())
                .ifPresent(u->u.getName());
    }
    @Test
    public void m2(){
        UserWithNoOp user=new UserWithNoOp();
        Optional.ofNullable(user)
                .map(u->u.getCar())
                .ifPresent(u->u.setName(user.car.name));
    }

    public int cqy(Node node) {
        node.next = new Node(1);
        return 0;
    }

    @Test
    public void test() {
        Node n=new Node(0);
        cqy(n);
        System.out.println(n.value+","+n.next);
    }
}
