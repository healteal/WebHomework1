import models.Instructor;
import models.Section;
import models.SectionType;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class General {
    public static void main(String[] args) {
        first_homework();
        second_homework("jdbc:mysql://localhost:3306/gym", "root", "123qwe");
    }

    private static void first_homework() {
        Set<Instructor> instructors = new HashSet<>();
        instructors.add(new Instructor("Петров Пётр Петрович", 35,
                new Section("Дзюдо", 20, SectionType.first_hall),
                5, "МС по дзюдо"));
        instructors.add(new Instructor("Петрова Мария Петровна", 27,
                new Section("Каратэ", 20, SectionType.second_hall),
                5, "МС по каратэ"));
        instructors.add(new Instructor("Леонтьев Леонтий Леонтьевич", 40,
                new Section("Бальные танцы", 30, SectionType.third_hall),
                15, "МС по бальным танцам"));
        instructors.add(new Instructor("Алексеев Алексей Алексеевич", 50,
                new Section("Плаванье", 50, SectionType.swimming_pool),
                30, "Чемпион мира по кролю"));

        instructors.stream().
                filter(instructor -> instructor.getAge() > 30
                        && instructor.getSection().getSectionType() == SectionType.swimming_pool
                        || instructor.getSection().getSectionType() == SectionType.first_hall).
                forEach(System.out::println);
    }

    private static void second_homework(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM instructor WHERE (age > 30 AND (id_section = 1 OR id_section = 4));");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("fio"));
            }
            statement.clearBatch();
            statement.executeUpdate("INSERT INTO instructor(fio, age, id_section, experience, education) " +
                    "VALUES ('Шуппо Кирилл Геннадьевич', 25, 1, 5, 'КМС по Дзюдо')," +
                    " ('Иванов Иван Иванович', 30, 2, 5, 'КМС по каратэ')," +
                    " ('Степанов Степан Степанович', 40, 3, 15, 'Танго');"); // Дополнении к четвёртому домашнему заданию
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM instructor WHERE fio LIKE ?");
            preparedStatement.setString(1, "Шуппо%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " "
                        + resultSet.getString("fio") + " "
                        + resultSet.getInt("age") + " "
                        + resultSet.getString("experience") + " "
                        + resultSet.getString("education"));
            }
            statement.clearBatch();
            statement.executeUpdate("DELETE FROM instructor WHERE fio = 'Шуппо Кирилл Геннадьевич'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}






