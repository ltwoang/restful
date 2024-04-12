package com.restful.Service;

import com.restful.Model.Student;
import com.restful.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (student != null) {
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if (student != null) {
            Student student1 = studentRepository.getById(id);
            if (student1 != null) {
                student1.setFullName(student1.getFullName());
                student1.setBirthday(student1.getBirthday());
                student1.setGender(student1.getGender());
                student1.setHometown(student1.getHometown());
                student1.setMajor(student1.getMajor());
                student1.setClassName(student1.getClassName());
                student1.setAverageMark(student1.getAverageMark());
                return studentRepository.save(student1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteStudent(Long id) {
        if(id > 1) {
            Student student = studentRepository.getById(id);
            if (student != null) {
                studentRepository.delete(student);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty.");
        }
        return studentRepository.findByFullNameContainingIgnoreCase(keyword);
    }


    @Override
    public List<Student> getStudentsWithBirthdayToday() {
        return studentRepository.findByBirthday(LocalDate.now());
    }

    @Override
    public List<Student> searchStudents(String fullName, String birthdayFrom, String birthdayTo, String gender, String hometown, String className, String major, Double averageMarkFrom, Double averageMarkTo) {
        return List.of();
    }

    @Override
    public List<Student> getStudentsWithBirthday(int monthValue, int dayOfMonth) {
        return List.of();
    }

}
