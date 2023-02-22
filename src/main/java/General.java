import models.Instructor;
import models.Section;
import models.SectionType;

import java.util.HashSet;
import java.util.Set;

public class General {
    public static void main(String[] args) {
        Set<Instructor> instructors = new HashSet<Instructor>();
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
}
