package com.example.StringBootProject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class StudentController {

    MongoClient mongo = new MongoClient("localhost", 27017);
    MongoDatabase database = mongo.getDatabase("EmployeeDetails");
    MongoCollection collection = database.getCollection("StudentData");

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String getStudent() {
        MongoCursor cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = (Document) cursor.next();
            return doc.toJson();
        }
        return null;
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public String addNewUsers(@RequestBody Model student) {
       try {
           MongoCursor cursor= (MongoCursor) collection.insertOne(Document.parse(student.toString()));
           return "Student Created";

       }catch (Exception e)
       {
           System.out.println("Some Exception are occurred");
       }
       return null;
    }

    @PutMapping("/update/student")
    public void updateStudent() {
        System.out.println("From put request");
    }

    @DeleteMapping("/student/{studentid}")
    public String deleteStudent(@PathVariable("studentid") String studenName) {
        try {
            MongoCursor count = (MongoCursor) collection.deleteOne(Filters.eq("studentName", studenName));
        }catch (Exception e)
        {
            System.out.println("Some Exception occurred");
        }
        return "Record Deleted Successfully";
    }
}
