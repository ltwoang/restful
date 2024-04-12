package com.restful.Service;

import com.restful.Model.Student;
import java.util.List;

public interface IStudentService {

    public Student addStudent(Student student);
    public Student updateStudent(Long id, Student student);
    public boolean deleteStudent(Long id);
    public List<Student> getAllStudents();
    public List<Student> searchStudents(String keyword);
    public List<Student> getStudentsWithBirthdayToday();

    List<Student> searchStudents(String fullName, String birthdayFrom, String birthdayTo, String gender, String hometown, String className, String major, Double averageMarkFrom, Double averageMarkTo);
    List<Student> getStudentsWithBirthday(int monthValue, int dayOfMonth);

}

