package com.restful.Controller;

import com.restful.Model.Student;
import com.restful.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @PostMapping("/add")
    public ResponseEntity<Object> addStudent(@RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin không hợp lệ: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Student addedStudent = iStudentService.addStudent(student);
        return ResponseEntity.ok().body(addedStudent);
    }


//    @PutMapping("/update")
//    public Student updateStudent(@RequestParam("id") long id, @RequestBody Student student) {
//        return iStudentService.updateStudent(id, student);
//    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStudent(@RequestParam("id") long id, @RequestBody Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thông tin không hợp lệ: " + bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        Student updatedStudent = iStudentService.updateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sinh viên với ID: " + id);
        }

        return ResponseEntity.ok().body(updatedStudent);
    }

//    @DeleteMapping("/delete/{id}")
//    public boolean deleteStudent(@PathVariable("id") long id) {
//        return iStudentService.deleteStudent(id);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") long id) {
        boolean deleted = iStudentService.deleteStudent(id);
        if (deleted) {
            return ResponseEntity.ok().body("Xóa sinh viên thành công");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    @GetMapping("/list")
    public List<Student> getAllStudent() {
        return iStudentService.getAllStudents();
    }

//    @GetMapping("/search")
//    public List<Student> searchStudents(@RequestParam(required = false) String keyword) {
//        return iStudentService.searchStudents(keyword);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String birthdayFrom,
            @RequestParam(required = false) String birthdayTo,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String hometown,
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String major,
            @RequestParam(required = false) Double averageMarkFrom,
            @RequestParam(required = false) Double averageMarkTo
    ) {
            List<Student> students = iStudentService.searchStudents(
                    fullName,
                    birthdayFrom,
                    birthdayTo,
                    gender,
                    hometown,
                    className,
                    major,
                    averageMarkFrom,
                    averageMarkTo
            );
            return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/birthday")
    public List<Student> getStudentsWithBirthdayToday() {
        LocalDate today = LocalDate.now();
        return iStudentService.getStudentsWithBirthday(today.getMonthValue(), today.getDayOfMonth());
    }
}
