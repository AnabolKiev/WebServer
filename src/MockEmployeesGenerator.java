import java.util.Random;

class Employee {
    int id;
    String name;
    int age;
    double salary;
    boolean gender;
}

class Manager extends Employee {
}

class Developer extends Employee {
    int fixedBugs;
}

class Cleaner extends Employee {
    double rate;
    int workedDays;
}
//Developer(id,name,age,salary,gender,fixedBugs)
//        Manager(id,name,age,salary,gender)
//        Cleaner(id,name,age,salary,gender,rate,workedDays)

public class MockEmployeesGenerator {
    static Employee[] generate(int size) {
        Random random = new Random();
        Employee[] newEmployees = new Employee[size];
        for (int i = 0; i < size; i++) {
            int j = random.nextInt(3);
            switch (j) {
                case 0:
                    newEmployees[i] = new Manager();
                    break;
                case 1:
                    newEmployees[i] = new Developer();
                    break;
                case 2:
                    newEmployees[i] = new Cleaner();
                    break;
            }
        }
        return newEmployees;
    }

    public static void main(String[] args) {
        Employee[] employees = generate(10);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}