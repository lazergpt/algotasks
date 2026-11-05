package by.algotsk;
// 1. Сгруппировать сотрудников по отделу и посчитать среднюю зарплату в каждом.
// 2. а как найти отдел с максимальной средней зарплатой?

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupEmployeesByOtdelsAndCountMidSalary {
    static void main() {
        record Employee(String name, String dept, double salary) {
        }

        List<Employee> staff = List.of(
                new Employee("Анна", "backend", 280_000),
                new Employee("Игорь", "backend", 240_000),
                new Employee("Лена", "frontend", 230_000),
                new Employee("Пётр", "frontend", 210_000),
                new Employee("Олег", "devops", 300_000)
        );

        System.out.println(staff
                .stream()
                .collect(Collectors.groupingBy(Employee::dept, Collectors.averagingDouble(Employee::salary)))  // 1. Конец первой задачи
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())); // 2. Конец второй задачи
    }
}
