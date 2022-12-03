package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<String,Student> studentMap;
    Map<String,Teacher> teacherMap;
    Map<String,List<String>> teacherStudentMap;

    public StudentRepository() {
        studentMap=new HashMap<>();
        teacherMap=new HashMap<>();
        teacherStudentMap=new HashMap<>();
    }

    public void addStudent(Student student) {
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)) {
            List<String> studentsList;
            if(teacherStudentMap.containsKey(teacher)) {
                studentsList = teacherStudentMap.get(teacher);
                studentsList.add(student);
                teacherStudentMap.put(teacher,studentsList);
            }else{
                studentsList = new ArrayList<>();
                studentsList.add(student);
                teacherStudentMap.put(teacher,studentsList);
            }
        }
    }

    public Student getStudentByName(String name) {
        return studentMap.getOrDefault(name,null);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.getOrDefault(name,null);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return teacherStudentMap.getOrDefault(teacher,null);
    }

    public List<String> getAllStudents() {
        List<String> studentsNameList=new ArrayList<>();
        for(String name:studentMap.keySet()) studentsNameList.add(name);
        return studentsNameList;
    }

    public void deleteTeacherByName(String teacher) {
        if(teacherStudentMap.containsKey((teacher))){
            List<String> studentsList=teacherStudentMap.get(teacher);
            for(String name:studentsList){
                if(studentMap.containsKey(name)) studentMap.remove(name);
            }
            teacherStudentMap.remove(teacher);
        }
        teacherMap.remove(teacher);
    }

    public void deleteAllTeachers() {
        for(String name:teacherStudentMap.keySet()){
            List<String> studentsList=teacherStudentMap.get(name);
            for(String studentName:studentsList){
                if(studentMap.containsKey(studentName)) studentMap.remove(studentName);
            }
            teacherMap.remove(name);
            teacherStudentMap.remove(name);
        }
    }
}
