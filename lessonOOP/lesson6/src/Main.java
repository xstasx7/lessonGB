import java.util.ArrayList;
import java.util.List;

// Класс модели учителя
class Teacher {
    private final int id;
    private final String name;
    private final String subject;

    public Teacher(int id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }
}

// Интерфейс сервиса учителей
interface TeacherDataService {
    void addTeacher(Teacher teacher);
    List<Teacher> getTeachers();
}

// Реализация сервиса учителей
class TeacherService implements TeacherDataService {
    private final List<Teacher> teachers;

    public TeacherService() {
        this.teachers = new ArrayList<>();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public List<Teacher> getTeachers() {
        return teachers;
    }
}

// Интерфейс представления учителей
interface TeacherView {
    void displayTeacher(Teacher teacher);
}

// Реализация представления учителя
class ConsoleTeacherView implements TeacherView {
    @Override
    public void displayTeacher(Teacher teacher) {
        System.out.println("ID: " + teacher.getId() + ", Name: " + teacher.getName() + ", Subject: " + teacher.getSubject());
    }
}

// Класс контроллера учителей
class TeacherController {
    private final TeacherDataService teacherDataService;
    private final TeacherView teacherView;

    public TeacherController(TeacherDataService teacherDataService, TeacherView teacherView) {
        this.teacherDataService = teacherDataService;
        this.teacherView = teacherView;
    }

    public void addTeacher(int id, String name, String subject) {
        Teacher teacher = new Teacher(id, name, subject);
        teacherDataService.addTeacher(teacher);
    }

    public void displayTeachers() {
        List<Teacher> teachers = teacherDataService.getTeachers();
        for (Teacher teacher : teachers) {
            teacherView.displayTeacher(teacher);
        }
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        // Создание сервиса и контроллера учителей
        TeacherDataService teacherDataService = new TeacherService();
        TeacherView teacherView = new ConsoleTeacherView();
        TeacherController teacherController = new TeacherController(teacherDataService, teacherView);

        // Добавление учителей
        teacherController.addTeacher(1, "Иван Иванов", "Математика");
        teacherController.addTeacher(2, "Анна Ановна", "Английский");
        teacherController.addTeacher(3, "Вася Пупкин", "Физра");

        // Отображение списка учителей
        teacherController.displayTeachers();
    }
}
