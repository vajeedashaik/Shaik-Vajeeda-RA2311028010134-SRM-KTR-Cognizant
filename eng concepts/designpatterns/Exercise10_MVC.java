// Model
class Student {
    private String name;
    private String id;
    private String grade;

    Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public String getName()  { return name;  }
    public String getId()    { return id;    }
    public String getGrade() { return grade; }

    public void setName(String name)   { this.name = name;   }
    public void setGrade(String grade) { this.grade = grade; }
}

// View
class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("=== Student Details ===");
        System.out.println("  ID    : " + id);
        System.out.println("  Name  : " + name);
        System.out.println("  Grade : " + grade);
    }
}

// Controller
class StudentController {
    private Student model;
    private StudentView view;

    StudentController(Student model, StudentView view) {
        this.model = model;
        this.view  = view;
    }

    public void setStudentName(String name)   { model.setName(name);   }
    public void setStudentGrade(String grade) { model.setGrade(grade); }

    public String getStudentName()  { return model.getName();  }
    public String getStudentId()    { return model.getId();    }
    public String getStudentGrade() { return model.getGrade(); }

    public void updateView() {
        view.displayStudentDetails(
            model.getName(), model.getId(), model.getGrade());
    }
}

public class Exercise10_MVC {
    public static void main(String[] args) {
        Student student = new Student("S001", "Alice Johnson", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        System.out.println("--- Initial ---");
        controller.updateView();

        controller.setStudentName("Alice M. Johnson");
        controller.setStudentGrade("A+");

        System.out.println("\n--- After Update ---");
        controller.updateView();
    }
}
